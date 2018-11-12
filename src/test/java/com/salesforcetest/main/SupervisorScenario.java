package com.salesforcetest.main;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.salesforcetest.pages.salesforce.SupervisorAction;

public class SupervisorScenario {

	private WebDriver driver;

	private ExtentTest testReporter;

	private SupervisorAction action = null;

	public SupervisorScenario(WebDriver driver) {
		this.driver = driver;
	}

	public void setTestReporter(ExtentTest testReporter) {
		this.testReporter = testReporter;
	}

	public void approve_service_item() {
		action = new SupervisorAction(driver);
		try {
			action.approve();
			testReporter.log(LogStatus.PASS, "approve_service_item");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "approve_service_item");
			Assert.assertEquals(e.getMessage(),"PASS");
			e.printStackTrace();
		}

	}

	public void reject_service_item() {
		action = new SupervisorAction(driver);
		try {
			action.reject();
			testReporter.log(LogStatus.PASS, "reject_service_item");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "reject_service_item");
			Assert.assertEquals(e.getMessage(),"PASS");
			e.printStackTrace();
		}
	}

	public void reassign_service_item(String newUser) {
		action = new SupervisorAction(driver);
		try {
			action.reassign(newUser);
			testReporter.log(LogStatus.PASS, "reassign_service_item");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "reassign_service_item");
			Assert.assertEquals(e.getMessage(),"PASS");
			e.printStackTrace();
		}
	}

	public void set_up_training() {
		action = new SupervisorAction(driver);
		try {
			//In Training checked
			action.setup_training();
			
			//In Training unchecked
			action.setup_training();
			
			testReporter.log(LogStatus.PASS, "set_up_training");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "set_up_training");
			Assert.assertEquals(e.getMessage(),"PASS");
			e.printStackTrace();
		}
	}

	public void creating_chatter_group() {
		action = new SupervisorAction(driver);
		try {
			// TODO implement scenario
			testReporter.log(LogStatus.PASS, "creating_chatter_group");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "creating_chatter_group");
			Assert.assertEquals(e.getMessage(),"PASS");
			e.printStackTrace();
		}
	}
}
