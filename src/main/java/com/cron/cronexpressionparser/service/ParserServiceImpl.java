package com.cron.cronexpressionparser.service;


import org.quartz.CronExpression;
import org.springframework.stereotype.Service;

import java.text.ParseException;

@Service
public class ParserServiceImpl implements ParserService {

    @Override
    public String parse(String command) {

        //System.out.println(command);

        String[] commandArray = command.split(" ");
        StringBuilder unixCommand = new StringBuilder();
        StringBuilder cronExpression =  new StringBuilder();
        StringBuilder cronDayOfWeekExpression = new StringBuilder("0 * * ? * ");

        if (commandArray.length < 6 || commandArray.length > 7) {
            System.out.println("Please provide valid cron input!!");
            System.exit(0);
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


        try {
            CronExpression cronExpressions = new CronExpression(cronExpression.toString());
            String result = cronExpressions.getExpressionSummary();

            CronExpression cronExpression2 = new CronExpression(cronDayOfWeekExpression.toString());
            String result2 = cronExpression2.getExpressionSummary();

            String[] resultDayOfWeekExpressionSummaryArray = result2.split("\\r?\\n");

            String[] resultExpressionSummaryArray = result.split("\\r?\\n");

            for (String data : resultExpressionSummaryArray) {

                if (data.contains("minutes")) {

                    if (data.contains("*")) {
                        StringBuilder minutes = new StringBuilder();

                        for (int i = 1; i < 61; i++) {
                            minutes.append(i + " ");
                        }

                        System.out.println(data.replace(",", " ").replace("minutes:", "minute        ").replace("*", minutes.toString()));
                    } else {
                        System.out.println(data.replace(",", " ").replace("minutes:", "minute        "));
                    }


                }

                if (data.contains("hours")) {
                    System.out.println(data.replace(",", " ").replace("hours:", "hour          ")
                            .replace("*", "1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24"));
                }

                if (data.contains("daysOfMonth")) {
                    System.out.println(data.replace(",", " ").replace("daysOfMonth:", "day of month  "));
                }

                if (data.contains("months")) {

                    if (data.contains("*")) {
                        System.out.println(data.replace(",", " ").replace("months:", "date of month ").replace("*",
                                "1 2 3 4 5 6 7 8 9 10 11 12"));
                    } else {
                        System.out.println(data.replace(",", " ").replace("months:", "date of month "));
                    }

                }

            }

            for (String data : resultDayOfWeekExpressionSummaryArray) {

                if (data.contains("daysOfWeek")) {
                    System.out.println(data.replace(",", " ").replace("daysOfWeek:", "day of week   ")
                            .replace("*", "1 2 3 4 5 6 7"));
                }

            }

            System.out.println("command        " + unixCommand);

        } catch (ParseException e) {

            System.out.println(e.getMessage());
        }


        return null;
    }
}
