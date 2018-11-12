package com.salesforcetest.runner;

import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(//features = "src/test/java/features/uatg",
		features = "src/test/java/features/nsrrf",
	//glue = { "com.salesforcetest.mapper.uatg" },
	glue = { "com.salesforcetest.mapper.srire2e" },
	plugin = {"com.cucumber.listener.ExtentCucumberFormatter:test-report/report.html" },
	tags = {"@Reg"},
	strict = true,
    monochrome = false)
public class TestRunner {
	
}
