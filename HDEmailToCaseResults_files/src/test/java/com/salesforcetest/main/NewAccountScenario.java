package com.salesforcetest.main;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.salesforcetest.pages.salesforce.AccountScenario;

public class NewAccountScenario
{
	private WebDriver driver;
	
	private ExtentTest testReporter;
	
	public NewAccountScenario(WebDriver driver) {
		this.driver = driver;
	}
	
	public void create_internal_account_and_contact() throws InterruptedException {
		AccountScenario accountScenario = new AccountScenario(driver);
		
		try {
			accountScenario.create_internal_account();
			
			accountScenario.create_internal_contact();
			
			testReporter.log(LogStatus.PASS, "create_internal_account_and_contact");
		} catch(Exception e) {
			testReporter.log(LogStatus.FAIL, "create_internal_account_and_contact");
			Assert.assertTrue(false);
			e.printStackTrace();
		}

	}

	public void create_external_account_and_contact() throws InterruptedException {
		AccountScenario accountScenario = new AccountScenario(driver);
		
		try {
			accountScenario.create_external_account();
			
			accountScenario.create_external_contact();
			
			testReporter.log(LogStatus.PASS, "create_external_account_and_contact");
		} catch(Exception e) {
			testReporter.log(LogStatus.FAIL, "create_external_account_and_contact");
			Assert.assertTrue(false);
			e.printStackTrace();
		}
		
		
	}

	public void setTestReporter(ExtentTest testReporter) {
		this.testReporter = testReporter;
	}
}
