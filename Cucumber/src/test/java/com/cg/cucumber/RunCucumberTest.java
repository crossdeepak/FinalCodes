package com.cg.cucumber;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		format={"pretty","html:target/cucumber"},
		features="classpath:test2.feature",
		glue="classpath:com.cg"
)
public class RunCucumberTest {

}