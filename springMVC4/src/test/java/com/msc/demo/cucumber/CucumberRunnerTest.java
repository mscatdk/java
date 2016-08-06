package com.msc.demo.cucumber;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
	features = "src/test/cucumber",
	plugin = {"pretty", "html:target/cucumber-html-report"},
	tags = {}
)
public class CucumberRunnerTest {

}
