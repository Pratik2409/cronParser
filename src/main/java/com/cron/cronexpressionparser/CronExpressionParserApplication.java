package com.cron.cronexpressionparser;

import com.cron.cronexpressionparser.service.ParserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CronExpressionParserApplication implements CommandLineRunner {

    @Autowired
    ParserService parserService;


    public static void main(String[] args) {

        SpringApplication app = new SpringApplication(CronExpressionParserApplication.class);
        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);

    }

    @Override
    public void run(String... args) throws Exception {
        if (args.length != 1) {
            System.out.println("Please provide valid cron input!!");
        }

        if (args.length > 0) {
            System.out.println(parserService.parse(args[0]));
        }
    }
}
