package com.salesforcetest.mapper.recr;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.salesforcetest.main.uatg.HD_ISO_VSC_Customer_Search_Main;
import com.salesforcetest.main.uatg.HD_ISO_VSC_Refresh_Existing_User_Record;
import com.salesforcetest.pages.salesforce.SalesforceLogin;
import com.salesforcetest.shared.Constants;
import com.salesforcetest.shared.ExtentReporter;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class HD_ISO_VSC_Existing_User_Refresh {
	private static WebDriver driver;

	private HD_ISO_VSC_Refresh_Existing_User_Record main = null;
	
	private static ExtentReports extent = ExtentReporter.getInstance().getExtentReports();

	private ExtentTest testReporter;

	@Given("^Registered User is logged in with \"(.*)\"$")
	public void init(String user) {
		System.setProperty("webdriver.chrome.driver", "driver//chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize(); // maximizes
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		main = new HD_ISO_VSC_Refresh_Existing_User_Record(driver);
		
		testReporter = extent.startTest("HD_ISO_VSC_Existing_User_Record_Refresh");
		main.setTestReporter(testReporter);
		
		new SalesforceLogin(driver).login(Constants.salesforce_url_uatg, Constants.salesforce_username_uatg, Constants.salesforce_password_uatg);
	}
	
	@Then("Verifying the current logged in user profile$")
	public void verify_Logged_In_User_Profile() {
		main.verifyLoggedInUserProfile();
	}
	@When("^Search for required Internal User \"(.*)\"$")
	public void search_For_Internal_User(String user) {
		//System.out.println("User: "+user);
		main.searchUser(user);
	}
	@Then("^Logging in as Internal user and verifying \"(.*)\"$")
	public void logging_In_As_Internal_User_And_Verify_Profile(String user) {
		main.logInAsInternalUser(user);
	}
	@When("^Search for customer \"(.*)\"$")
	public void search_customer(String customer) {
		//System.out.println("Customer: "+customer);
		main.customerSearch(customer);
	}
	@Then("^Refresh the customer data and wait for Refresh successful message$")
	public void update_customer() {
		main.customerDataRefresh();
	}
	@Then("^Global Search with Customer ID \"(.*)\"$")
	public void Global_Search_With_Customer_ID(String applicationId) {
		main.searfchForApplicationRecord(applicationId);
	}
	@Then("^Open application record from search result \"(.*)\"$")
	public void Open_Application_Record(String applicationId) {
		main.openRecordFromGlobalSearchResult(applicationId);
	}
	@Then("^Click on the new service item button$")
	public void Open_Application_Record() {
		main.clickOnCreateNewItem();
	}
	@And("^Provide Organization name for new service item \"(.*)\"$")
	public void Enter_Org_Name(String orgName) {
		main.giveOrgName(orgName);
	}
	@And("^Provide Email ID for new service item \"(.*)\"$")
	public void Enter_Email_ID(String email) {
		main.giveEmailId(email);
	}
	@And("^Provide Sender type for new service item \"(.*)\"$")
	public void Enter_Sender_Type(String senderType) {
		main.giveSenderType(senderType);
	}
	@And("^Provide Subject and Description for new service item$")
	public void Enter_Sub_Des() {
		main.giveSubjectAndDes();
	}
	@And("^Provide Category and Kind for new service item$")
	public void Enter_Cat_Kind() {
		main.giveCategoryAndKind();
	}
	@And("^Provide Service Item Origin for new service item \"(.*)\"$")
	public void Enter_SI_Origin(String serviceItem) {
		main.giveSIOrigin(serviceItem);
	}
	@And("^Provide Initial Queue for new service item \"(.*)\"$")
	public void Enter_Initial_Queue(String initialQueue) {
		main.giveSInitialQueue(initialQueue);
	}
	@And("^Provide Receive Date for new service item \"(.*)\"$")
	public void Enter_Received_Date(String receivedDate) {
		main.giveReceivedDate(receivedDate);
	}
	@Then("^Saving new service item$")
	public void Save_SI() {
		main.saveNewServiceItem();
	}
	@Then("^Verify New Service Item number and Details$")
	public void Verify_New_SI() {
		main.validateNewlyCreatedSI();
	}
	@Then("^Create a new response for new Service Item$")
	public void Create_New_Response() {
		main.createNewResponse();
	}
	@Then("^Verify newly created service response status$")
	public void Verify_New_Service_Response() {
		main.verifySR();
	}
	@Then("^Logout from current profile$")
	public void log_Out() {
		main.logOut();
	}
	@When("^Search for required Supervisor User \"(.*)\"$")
	public void search_For_Supervisor_User(String user) {
		//System.out.println("User: "+user);
		main.searchWithSupervisorId(user);
	}
	@Then("^Logging in as Supervisor user and verifying \"(.*)\"$")
	public void logging_In_As_Supervisor_User_And_Verify_Profile(String user) {
		main.loginWithSupervisorId(user);
	}
	@Then("^Select required service item with Supervisor user to approve/reject$")
	public void Select_Required_Service_item() {
		main.selectRequiredSI();
	}
	@Then("^Approve the selected service request$")
	public void Approve_Selected_Service_Rqst() {
		main.approveTheServiceRqst();
	}
	@Then("^Validate approved request response$")
	public void Validate_Approved_Rqst() {
		main.validateApproveRqstResponse();
	}
	
	@Then("^Stop Report Generation for current scenario$")
	public void getResult() {
		//extent.endTest("HD_ISO_VSC_Existing_User_Record_Refresh");
		extent.endTest(testReporter);
		extent.flush();
		extent.close();
	}
	@Then("^Close the browser for current scenario$")
	public void flushReporter() {
		driver.quit();
	}
	/*public static void main(String[] args) {
		// TODO Auto-generated method stub

	}*/

}
