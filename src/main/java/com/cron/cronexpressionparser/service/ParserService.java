package com.cron.cronexpressionparser.service;

import org.springframework.stereotype.Service;

@Service
public interface ParserService {
    String parse(String command);
}
