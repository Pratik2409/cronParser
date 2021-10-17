package com.cron.cronexpressionparser.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ParserServiceImplTest {

    @Autowired
    private ParserService parserService;


    @Test
    public void parseWrongArgumentTest() throws Exception {
        String cronExpression = "*/15 0 1,15 * 1-5 6 7 /usr/bin/find";
        Assertions.assertEquals("Please provide valid cron input!!", parserService.parse(cronExpression));
    }

    @Test
    public void parsePositiveTest() throws Exception {

        String result = "\nminute         0 15 30 45" +
                "\nhour           7" +
                "\nday of month   *" +
                "\ndate of month  8" +
                "\nday of week    5" +
                "\ncommand        /usr";

        String cronExpression = "*/15 7 * 8 5 /usr";
        Assertions.assertEquals(result, parserService.parse(cronExpression));


    }

    @Test
    public void parseNegativeTest() throws Exception {
        String result = "\nminute         0 15 30 45" +
                "\nhour           7" +
                "\nday of month   *" +
                "\ndate of month  8" +
                "\nday of week    5" +
                "\ncommand        /usr";

        String cronExpression = "*/15 7 ? 8 5 /usr";
        Assertions.assertNotEquals(result, parserService.parse(cronExpression));
    }

}
