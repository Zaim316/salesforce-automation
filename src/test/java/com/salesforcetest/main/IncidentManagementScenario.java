package com.salesforcetest.main;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.salesforcetest.pages.gmail.MailSender;
import com.salesforcetest.pages.salesforce.ServiceItemsAction;
import com.salesforcetest.shared.Constants;
import com.salesforcetest.shared.Incident;
import com.salesforcetest.shared.Reporter;

public class IncidentManagementScenario extends CommonServiceItemTestScenario {

	private WebDriver driver;

	private String serviceItemNumber;

	private ExtentTest testReporter;

	public IncidentManagementScenario(WebDriver driver) {
		super();
		this.driver = driver;
	}

	/**
	 * Scenario 2.1
	 * 
	 * @param cr
	 */

	public void send_report_email_from_gmail(Reporter reporter) {
		try {
			String subject = ((Incident) reporter.getType()).getSubject();
			String msg = ((Incident) reporter.getType()).getMessage();
			String attachment = ((Incident) reporter.getType()).getAttachment();

			send_report_email_from_gmail(driver, reporter.getReportingEmail(), subject, msg, attachment);

			testReporter.log(LogStatus.PASS, "send_report_email_from_gmail");
		} catch (Exception e) {
//			testReporter.log(LogStatus.FAIL, "Automated Test for sending incident email.");
			testReporter.log(LogStatus.FAIL, "send_report_email_from_gmail");
			Assert.assertEquals(e.getLocalizedMessage(), "Passed");
			e.printStackTrace();
		}
	}

	public void select_and_assert_incident_queue() {
		try {

			ServiceItemsAction serviceAction = new ServiceItemsAction(driver, testReporter);

			serviceAction.select_incident_queue_list();

			serviceItemNumber = serviceAction.assert_incident_name_get_service_item_number();

			if (serviceItemNumber == null || serviceItemNumber.isEmpty())
				throw new Exception("Service Item Number not generated");

			testReporter.log(LogStatus.PASS, "select_and_assert_incident_queue");
		} catch (Exception e) {
//			Utils.log("Error","Automated Test for asserting created incident ticket. - Failed");
			testReporter.log(LogStatus.FAIL, "select_and_assert_incident_queue");
			Assert.assertEquals(e.getLocalizedMessage(), "Passed");
			e.printStackTrace();
		}
	}

	/**
	 * Scenario 2.2
	 * 
	 * This step will make incident 'a service item'
	 */

	public void select_incident_and_change_owner() {
		try {

			if (serviceItemNumber == null || serviceItemNumber.isEmpty())
				throw new Exception("Service Item Number not generated");

			ServiceItemsAction serviceAction = new ServiceItemsAction(driver, testReporter);

			serviceAction.select_incident_queue_list();

			serviceAction.select_serviceItem_and_change_owner(serviceItemNumber,Constants.incident_owner);

			testReporter.log(LogStatus.PASS, "select_incident_and_change_owner");
		} catch (Exception e) {
//			Utils.log("Error","Automated Test for changing incident owner. - Failed");
			Assert.assertEquals(e.getLocalizedMessage(), "Passed");
			testReporter.log(LogStatus.FAIL, "select_incident_and_change_owner");
			e.printStackTrace();
		}
	}

	/**
	 * Update the incident information
	 * 
	 */
	public void verify_submitter_voilator_on_service_item() {
		try {
			if (serviceItemNumber == null || serviceItemNumber.isEmpty())
				throw new Exception("Service Item Number not generated");

			ServiceItemsAction serviceAction = new ServiceItemsAction(driver, testReporter);

			serviceAction.select_service_item(serviceItemNumber);

			serviceAction.change_incident_voilator();

			testReporter.log(LogStatus.PASS, "verify_submitter_voilator_on_service_item");
		} catch (Exception e) {
//			Utils.log("Error","Automated Test for asserting violator in service item - Failed");
			testReporter.log(LogStatus.FAIL, "verify_submitter_voilator_on_service_item");
			Assert.assertEquals(e.getLocalizedMessage(), "Passed");
			e.printStackTrace();
		}

	}

	/**
	 * send email from service item
	 * 
	 * @param reporter
	 */
	public void select_service_item_and_send_email(String toEmail, String attachment, String msg) {
		try {
			if (!"".equals(msg)) {
				select_service_item_and_send_email(serviceItemNumber, driver, testReporter, toEmail, attachment, msg);
				testReporter.log(LogStatus.PASS, "select_service_item_and_send_email");
			} else {
				select_service_item_and_verify_attachment(serviceItemNumber, driver, testReporter, attachment);
				testReporter.log(LogStatus.PASS, "select_service_item_and_verify_attachment");
			}
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "select_service_item_and_send_email_and_verify_attachment");
			Assert.assertEquals(e.getLocalizedMessage(), "Passed");
			e.printStackTrace();
		}
	}

	/**
	 * Change the service item (incident) status to closed
	 * 
	 */
	public void closing_service_item() {
		try {
			if (serviceItemNumber == null || serviceItemNumber.isEmpty())
				throw new Exception("Service Item Number not generated");

			ServiceItemsAction serviceAction = new ServiceItemsAction(driver, testReporter);

			serviceAction.select_service_item(serviceItemNumber);

			serviceAction.close_incident_service_item();

			testReporter.log(LogStatus.PASS, "closing_service_item");
		} catch (Exception e) {
//			Utils.log("Error","Automated Test for closing incident ticket. - Failed");
			testReporter.log(LogStatus.FAIL, "closing_service_item");
			Assert.assertEquals(e.getLocalizedMessage(), "Passed");
			e.printStackTrace();
		}

	}

	public void reply_closing_email_in_reporter_account() {
		try {
			MailSender sender = new MailSender(driver);

			sender.verify_closing_email(Constants.closure_email_subject);
			
			testReporter.log(LogStatus.PASS, "verify_closing_email_in_reporter_account");

			sender.open_received_email(Constants.email_subject, false);

			sender.reply_with_attachment("Replying closed email", Constants.sorn_attachment_url);
			
			testReporter.log(LogStatus.PASS, "reply_closing_email_in_reporter_account");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "reply_closing_email_in_reporter_account");
			Assert.assertEquals(e.getLocalizedMessage(), "Passed");
			e.printStackTrace();
		}

	}

	public void verify_status_and_owner() {
		try {
			if (serviceItemNumber == null || serviceItemNumber.isEmpty())
				throw new Exception("Service Item Number not generated");

			ServiceItemsAction serviceAction = new ServiceItemsAction(driver, testReporter);

			serviceAction.assert_service_item_status(serviceItemNumber,"Unassigned");
			
			serviceAction.verify_status_and_owner();
			
			testReporter.log(LogStatus.PASS, "verify_status_and_owner_after_reply_closing_email_in_reporter_account");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "verify_status_and_owner_after_reply_closing_email_in_reporter_account");
			Assert.assertEquals(e.getLocalizedMessage(), "Passed");
			e.printStackTrace();
		}
	}

	public void setTestReporter(ExtentTest testReporter) {
		this.testReporter = testReporter;
	}
}
