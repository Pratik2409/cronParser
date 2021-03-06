package com.cron.cronexpressionparser.service;


import org.quartz.CronExpression;
import org.springframework.stereotype.Service;

import java.text.ParseException;

@Service
public class ParserServiceImpl implements ParserService {

    @Override
    public String parse(String command) {

        String[] commandArray = command.split(" ");
        StringBuilder unixCommand = new StringBuilder();
        StringBuilder cronExpression =  new StringBuilder();
        StringBuilder cronDayOfWeekExpression = new StringBuilder("0 * * ? * ");

        StringBuilder response = new StringBuilder();

        if (commandArray.length < 6 || commandArray.length > 7) {
            response.append("Please provide valid cron input!!");
            return response.toString();
        }

        /*
         * Input without second parameter and last one is command
         *
         * second minute hour day_of_month month day_of_week
         *
         */


        unixCommand.append((commandArray.length == 6) ? commandArray[5] : commandArray[6]);
        cronExpression.append((commandArray.length == 6) ? "0 " : "");

        for (int i = 0; i < commandArray.length - 1; i++) {

            if (i == commandArray.length - 2) {
                cronDayOfWeekExpression.append(commandArray[i]) ;
                cronExpression.append("?");
            } else {
                cronExpression.append(commandArray[i]);
            }

            if (i != commandArray.length - 1) {
                cronExpression.append(" ");
            }

        }

        /*
            day-of-month and day-of-week is not supported by quartz library together.
            Created two separate cron expression for day-of-month and day-of-week.

        */

        try {
            CronExpression cronExpressions = new CronExpression(cronExpression.toString());
            String cronExpressionResult = cronExpressions.getExpressionSummary();

            CronExpression cronExpression2 = new CronExpression(cronDayOfWeekExpression.toString());
            String cronDayOfWeekExpressionResult = cronExpression2.getExpressionSummary();

            String[] resultDayOfWeekExpressionSummaryArray = cronDayOfWeekExpressionResult.split("\\r?\\n");

            String[] resultExpressionSummaryArray = cronExpressionResult.split("\\r?\\n");

            for (String data : resultExpressionSummaryArray) {

                if (data.contains("minutes")) {

                    if (data.contains("*")) {
                        StringBuilder minutes = new StringBuilder();

                        for (int i = 1; i < 61; i++) {
                            minutes.append(i).append(" ");
                        }

                        response.append("\n").append(data.replace(",", " ").replace("minutes:", "minute        ").replace("*", minutes.toString()));
                    } else {
                        response.append("\n").append(data.replace(",", " ").replace("minutes:", "minute        "));
                    }


                }

                if (data.contains("hours")) {
                    response.append("\n").append(data.replace(",", " ").replace("hours:", "hour          ")
                            .replace("*", "1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24"));
                }

                if (data.contains("daysOfMonth")) {
                    response.append("\n").append(data.replace(",", " ").replace("daysOfMonth:", "day of month  "));
                }

                if (data.contains("months")) {

                    if (data.contains("*")) {
                        response.append("\n").append(data.replace(",", " ").replace("months:", "date of month ").replace("*",
                                "1 2 3 4 5 6 7 8 9 10 11 12"));


                    } else {
                        response.append("\n").append(data.replace(",", " ").replace("months:", "date of month "));
                    }

                }

            }

            for (String data : resultDayOfWeekExpressionSummaryArray) {

                if (data.contains("daysOfWeek")) {
                    response.append("\n").append(data.replace(",", " ").replace("daysOfWeek:", "day of week   ")
                            .replace("*", "1 2 3 4 5 6 7"));

                }

            }
            response.append("\ncommand        " + unixCommand);
            System.out.println();

        } catch (ParseException e) {
            System.out.println(e.getMessage());
        }
        return response.toString();
    }
}
