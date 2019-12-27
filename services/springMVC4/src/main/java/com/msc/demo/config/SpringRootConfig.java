package com.msc.demo.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({ "com.msc.demo.service", "com.msc.demo.service.impl", "com.msc.demo.das" })
public class SpringRootConfig {
}
