package com.salesforcetest.test;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.salesforcetest.main.ComplianceManagementScenario;
import com.salesforcetest.pages.gmail.MailSender;
import com.salesforcetest.pages.salesforce.SalesforceLogin;
import com.salesforcetest.shared.Compliance;
import com.salesforcetest.shared.Constants;
import com.salesforcetest.shared.ExtentReporter;
import com.salesforcetest.shared.Reporter;
import com.salesforcetest.shared.Utils;

public class ComplianceManagementScenarioTest {
	private WebDriver driver = null;

	private ComplianceManagementScenario cms = null;

	private static ExtentReports extent = ExtentReporter.getInstance().getExtentReports();

	private ExtentTest testReporter;
	
	@BeforeClass
	public void init() {
		System.setProperty("webdriver.chrome.driver", "driver//chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize(); // maximizes
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		cms = new ComplianceManagementScenario(driver);
		//for testing
		new MailSender(driver).login(Constants.reporter_account_url, Constants.reporter_email, Constants.reporter_username, Constants.reporter_password);
		
		Utils.open_another_tab(driver);
		
		new SalesforceLogin(driver).login(Constants.salesforce_username, Constants.salesforce_password);
	}

	@BeforeMethod
	public void inject_reporter(Method method) {
		testReporter = extent.startTest(method.getName());
		cms.setTestReporter(testReporter);
	}

	@Test(testName = "Scenario_3_1", description = "Scenario 3.1 - Create a Service Item via Email", priority = 1)
	public void ComplianceManagementScenario_Test_S31() {
		Compliance compliance = new Compliance(Constants.compliance_msg, Constants.compliance_subject,
				Constants.sorn_attachment_url);

		Reporter reporter = new Reporter(Constants.reporter_email, Constants.reporter_username,
				Constants.reporter_password, Constants.reporter_account_url, compliance,
				Constants.compliance_report_email);

		// since login into salesforce is taken care in Accounts creation case. Just
		// switching the tabs will work
		Utils.switch_to_tab(driver, 0);

		cms.send_report_email_from_gmail(reporter);

		// since login into salesforce is taken care in Accounts creation case. Just
		// switching the tabs will work
		Utils.switch_to_tab(driver, 1);

		cms.select_and_assert_compliance_queue();
	}

	@Test(testName = "Scenario_3_2", description = "Scenario 3.2 - Take Ownership of the Service Item", priority = 2, dependsOnMethods = {
			"ComplianceManagementScenario_Test_S31" })
	public void ComplianceManagementScenario_Test_S32() {
		Utils.switch_to_tab(driver, 1);
		
		driver.get(Constants.salesforce_url);

		cms.select_compliance_and_change_ownership();
	}

	@Test(testName = "Scenario_3_4", description = "Scenario 3.4 - Verify Status Change", priority = 4, dependsOnMethods = {
			"ComplianceManagementScenario_Test_S32" })
	public void ComplianceManagementScenario_Test_S34() {
		Utils.switch_to_tab(driver, 1);
		
		driver.get(Constants.salesforce_url);

		cms.verify_status_change();
	}

	@Test(testName = "Scenario_3_5", description = "Scenario 3.5 - sending email from service item", priority = 5)
	public void ComplianceManagementScenario_Test_S35() {
		Utils.switch_to_tab(driver, 1);
		
		driver.get(Constants.salesforce_url);

		cms.select_service_item_and_send_email(Constants.reporter_email, Constants.privacy_pia_attachment_url,
				Constants.compliance_msg);
	}

	@Test(testName = "Scenario_3_6", description = "Scenario 3.6 - Replying email with attachment", priority = 6, dependsOnMethods = {
			"ComplianceManagementScenario_Test_S35" })
	public void ComplianceManagementScenario_Test_S36() {
		// back to sales force
		Utils.switch_to_tab(driver, 1);
				
		cms.select_service_item_and_send_email(Constants.reporter_email, Constants.privacy_pia_attachment_url,"");
	}

	@Test(testName = "Scenario_3_7", description = "Scenario 3.7 - Send email to DHS", priority = 7)
	public void ComplianceManagementScenario_Test_S37() {
		Utils.switch_to_tab(driver, 1);
				
		driver.get(Constants.salesforce_url);

		cms.send_email_to_dhs(Constants.dhs_email);
	}

	@Test(testName = "Scenario_3_8", description = "Scenario 3.8 - Submit for approval", priority=8)
	public void ComplianceManagementScenario_Test_S38() {
		cms.submit_service_item_for_approval();
	}

	@Test(testName = "Scenario_3_9", description = "Scenario 3.9 - Submit send another service item for approval", priority = 9)
	public void ComplianceManagementScenario_Test_S39() {
		Compliance compliance = new Compliance(Constants.compliance_msg, Constants.compliance_subject,
				Constants.sorn_attachment_url);

		Reporter reporter = new Reporter(Constants.reporter_email, Constants.reporter_username,
				Constants.reporter_password, Constants.reporter_account_url, compliance,
				Constants.compliance_report_email);

		Utils.switch_to_tab(driver, 0);

		cms.send_report_email_from_gmail(reporter);

		Utils.switch_to_tab(driver, 1);

		cms.select_and_assert_compliance_queue();
		
		cms.select_compliance_and_change_ownership();
		
		cms.update_service_item_type();
		
		cms.submit_service_item_for_approval();
	}

	@AfterMethod
	public void getResult(ITestResult result) {
		extent.endTest(testReporter);
	}

	@AfterClass(alwaysRun = true)
	public void flushReporter() {
		extent.flush();
		driver.quit();
	}
}
