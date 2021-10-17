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
    public void parseTest() throws Exception {

        String cronExpression = "*/15 0 1,15 * 1-5 /usr/bin/find";
        Assertions.assertNull(parserService.parse(cronExpression));

    }

}
