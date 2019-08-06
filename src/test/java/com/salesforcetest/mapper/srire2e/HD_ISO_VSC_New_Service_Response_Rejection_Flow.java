package com.salesforcetest.mapper.srire2e;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.salesforcetest.main.uatg.HD_ISO_VSC_Service_Request_Rejection_E2E;
import com.salesforcetest.pages.salesforce.SalesforceLogin;
import com.salesforcetest.pages.salesforce.uatg.RefreshExistingUserRecordAction;
import com.salesforcetest.shared.Constants;
import com.salesforcetest.shared.ExtentReporter;
import com.salesforcetest.shared.Utils;

import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class HD_ISO_VSC_New_Service_Response_Rejection_Flow {
	static WebDriver driver;
	private WebElement element;
	public static String newSINo;
	private By ele;

	private HD_ISO_VSC_Service_Request_Rejection_E2E main = null;
	
	static ExtentReports extent = ExtentReporter.getInstance().getExtentReports();

	static ExtentTest testReporter;
	@Given("^For rejection and approval of response scenario Registered User is logged in with \"(.*)\"$")
	public void init(String user) {
		/*System.setProperty("webdriver.chrome.driver", "driver//chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize(); // maximizes
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);*/
		main = new HD_ISO_VSC_Service_Request_Rejection_E2E(driver);//driver
		
		testReporter = extent.startTest("End to End Approval and Rejection Flow");
		//main.setTestReporter(testReporter);
		//main.logIn();
		try {
			//RefreshExistingUserRecordAction refObj = new RefreshExistingUserRecordAction();
			launch();
			testReporter.log(LogStatus.PASS, "User logs in successfully");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "User logs in successfully");
			Assert.assertTrue(false);
			e.printStackTrace();
		}
		//new SalesforceLogin(driver).login(Constants.salesforce_url_uatg, Constants.salesforce_username_uatg, Constants.salesforce_password_uatg);
	}
	@Then("For rejection and approval of response scenario set QC Percentage to \"(.*)\"$")
	public void Set_QC_Percentage(String val) {
		//main.verifyLoggedInUserProfile();
		try {
			searchHDISOVSCitems("One HD Supervisor");
			logInAsInternalUser("One HD Supervisor");
			setPercentage(val);
			currentUserLogOut();
			testReporter.log(LogStatus.PASS, "Set percentage to "+val+" successful.");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Set percentage to "+val+" successful.");
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
	@Then("For rejection and approval of response scenario Verifying the current logged in user profile$")
	public void verify_Logged_In_User_Profile() {
		//main.verifyLoggedInUserProfile();
		try {
			//RefreshExistingUserRecordAction refObj = new RefreshExistingUserRecordAction();
			verifyProfile();
			testReporter.log(LogStatus.PASS, "Verifying the current logged in user profile:");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Verifying the current logged in user profile:");
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
	@When("^For rejection and approval of response scenario Search for required Internal User \"(.*)\"$")
	public void search_For_Internal_User(String user) {
		//System.out.println("User: "+user);
		//main.searchUser(user);
		try {
			//RefreshExistingUserRecordAction refObj = new RefreshExistingUserRecordAction();
			searchHDISOVSCitems(user);
			testReporter.log(LogStatus.PASS, "Search with required User");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Search with required User");
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
	@Then("^For rejection and approval of response scenario Logging in as Internal user and verifying \"(.*)\"$")
	public void logging_In_As_Internal_User_And_Verify_Profile(String user) {
		//main.logInAsInternalUser(user);
		try {
			//RefreshExistingUserRecordAction refObj = new RefreshExistingUserRecordAction();
			logInAsInternalUser(user);
			testReporter.log(LogStatus.PASS, "Verify log in as Internal User");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Verify log in as Internal User");
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
	@When("^For rejection and approval of response scenario Search for customer \"(.*)\"$")
	public void search_customer(String customer) throws AWTException {
		//System.out.println("Customer: "+customer);
		//main.customerSearch(customer);
		try {
			//RefreshExistingUserRecordAction refObj = new RefreshExistingUserRecordAction();
			customerSearch(customer);
			testReporter.log(LogStatus.PASS, "Search for required customer");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Search for required customer");
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	@Then("^For rejection and approval of response scenario Refresh the customer data and wait for Refresh successful message$")
	public void update_customer() throws AWTException {
		//main.customerDataRefresh();
		try {
			//RefreshExistingUserRecordAction refObj = new RefreshExistingUserRecordAction();
			customerRefresh();
			testReporter.log(LogStatus.PASS, "Refreshing the input customer details and wait till refreshing mechanism is completed");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Refreshing the input customer details and wait till refreshing mechanism is completed");
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
	@Then("^For rejection and approval of response scenario Open the application record details from Refresh User page \"(.*)\"$")
	public void open_application_details(String appNumber) {
		//main.openApplicationDetails(appNumber);
		try {
			//RefreshExistingUserRecordAction refObj = new RefreshExistingUserRecordAction();
			clickOnRecordItemFromOwnerPage(appNumber);
			testReporter.log(LogStatus.PASS, "Opening the Application details from Customer refresh page.");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Opening the Application details from Customer refresh page.");
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
	@Then("^For rejection and approval of response scenario Click on create new service item \"(.*)\"$")
	public void click_on_create_new_service_item(String appNumber) {
		//main.clickOnCreateNewItem(appNumber);
		try {
			//RefreshExistingUserRecordAction refObj = new RefreshExistingUserRecordAction();
			createNewServiceItemParam(appNumber);
			testReporter.log(LogStatus.PASS, "Click on Create new Service item button");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Click on Create new Service item button");
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
	@And("^To create new service item Provide all new mandatory data$")
	public void create_new_service_item(DataTable dt) {
		//need to code as per feature file.
		List<Map<String, String>> list = dt.asMaps(String.class, String.class);
		String fOrgName = list.get(0).get("Org Name");
		String email = list.get(0).get("Email");
		String senderType = list.get(0).get("Sender Type");
		String subject = list.get(0).get("Subject");
		String description = list.get(0).get("Description");
		String formType = list.get(0).get("Form Type");
		String category = list.get(0).get("Category");
		String kind = list.get(0).get("Kind");
		String comm = list.get(0).get("Comments");
		String io = list.get(0).get("Item Origin");
		String queue = list.get(0).get("Queue");
		String dateTime = list.get(0).get("DateTime");
	    /*System.out.println(fOrgName);
	    System.out.println(email);
	    System.out.println(senderType);*/
		//main.giveOrgName(fOrgName);
		try {
			//RefreshExistingUserRecordAction refObj = new RefreshExistingUserRecordAction();
			setOrgName(fOrgName);
			testReporter.log(LogStatus.PASS, "Give Organization Name : "+fOrgName);
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Give Organization Name : "+fOrgName);
			Assert.assertTrue(false);
			e.printStackTrace();
		}
		//main.giveEmailId(email);
		try {
			//RefreshExistingUserRecordAction refObj = new RefreshExistingUserRecordAction();
			setEmail(email);
			testReporter.log(LogStatus.PASS, "Give Email Id: "+email);
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Give Email Id: "+email);
			Assert.assertTrue(false);
			e.printStackTrace();
		}
		//main.giveSenderType(senderType);
		try {
			//RefreshExistingUserRecordAction refObj = new RefreshExistingUserRecordAction();
			selectSenderType(senderType);
			testReporter.log(LogStatus.PASS, "Give Sender Type Name : "+senderType);
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Give Sender Type Name : "+senderType);
			Assert.assertTrue(false);
			e.printStackTrace();
		}
		//main.setSubDesAndFormType(subject, description, formType);
		try {
			//RefreshExistingUserRecordAction refObj = new RefreshExistingUserRecordAction();
			setParamSubjectAndDesAndFormType(subject, description, formType);
			testReporter.log(LogStatus.PASS, "Provide Subject,  Description and Form Type respectively: "+subject+" ,"+description+" and "+formType);
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Provide Subject,  Description and Form Type respectively: "+subject+" ,"+description+" and "+formType);
			Assert.assertTrue(false);
			e.printStackTrace();
		}
		//main.setCategoryAndKind(category, kind, comm);
		try {
			//RefreshExistingUserRecordAction refObj = new RefreshExistingUserRecordAction();
			setParamCategoryAndKind(category, kind, comm);
			testReporter.log(LogStatus.PASS, "Give Category, Kind Name and Comments respectively :"+category+" ,"+kind+" ,"+comm);
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Give Category, Kind Name and Comments respectively :"+category+" ,"+kind+" ,"+comm);
			Assert.assertTrue(false);
			e.printStackTrace();
		}
		//main.giveSIOrigin(io);
		try {
			//RefreshExistingUserRecordAction refObj = new RefreshExistingUserRecordAction();
			selectSIOrigin(io);
			testReporter.log(LogStatus.PASS, "Give Service Item Origin :"+io);
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Give Service Item Origin :"+io);
			Assert.assertTrue(false);
			e.printStackTrace();
		}
		//main.giveSInitialQueue(queue);
		try {
			//RefreshExistingUserRecordAction refObj = new RefreshExistingUserRecordAction();
			selectInitialQueue(queue);
			testReporter.log(LogStatus.PASS, "Give Initial Queue : "+queue);
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Give Initial Queue : "+queue);
			Assert.assertTrue(false);
			e.printStackTrace();
		}
		//main.giveReceivedDate(dateTime);
		try {
			//RefreshExistingUserRecordAction refObj = new RefreshExistingUserRecordAction();
			setReceivedDate(dateTime);
			testReporter.log(LogStatus.PASS, "Give Received Date (any past date) : "+dateTime);
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Give Received Date (any past date) : "+dateTime);
			Assert.assertTrue(false);
			e.printStackTrace();
		}
		//main.saveNewServiceItem();
		try {
			//RefreshExistingUserRecordAction refObj = new RefreshExistingUserRecordAction();
			clickOnSaveSI();
			testReporter.log(LogStatus.PASS, "Saving this new service item data");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Saving this new service item data");
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
	@Then("^For rejection and approval of response scenario Verify New Service Item number and Details$")
	public void Verify_New_SI() {
		//main.validateNewlyCreatedSI();
		String newlyCreatedSI = null;
		try {
			//RefreshExistingUserRecordAction refObj = new RefreshExistingUserRecordAction();
			newlyCreatedSI = (String) fetchServiceItemNo();
			testReporter.log(LogStatus.PASS, "Validate new service item and new Service Item number :"+newlyCreatedSI);
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Validate new service item and new Service Item number :"+newlyCreatedSI);
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
	@Then("^For rejection and approval of response scenario Create a new response for new Service Item$")
	public void Create_New_Response() {
		//main.createNewResponse();
		try {
			//RefreshExistingUserRecordAction refObj = new RefreshExistingUserRecordAction();
			createNewResponse();
			testReporter.log(LogStatus.PASS, "Creating new sevice response of new service item.");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Creating new sevice response of new service item.");
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
	@Then("^For rejection and approval of response scenario Verify newly created service response status$")
	public void Verify_New_Service_Response() {
		//main.verifySR();
		try {
			//RefreshExistingUserRecordAction refObj = new RefreshExistingUserRecordAction();
			verifyNewResponseStatus();
			testReporter.log(LogStatus.PASS, "Verify new sevice response status of new service item.");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Verify new sevice response status of new service item.");
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
	@Then("^For rejection and approval of response scenario Logout from current profile$")
	public void log_Out() {
		//main.logOut();
		try {
			//RefreshExistingUserRecordAction refObj = new RefreshExistingUserRecordAction();
			currentUserLogOut();
			testReporter.log(LogStatus.PASS, "Logging out");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Logging out");
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
	@When("^For rejection and approval of response scenario Search for required Supervisor User \"(.*)\"$")
	public void search_For_Supervisor_User(String user) {
		//System.out.println("User: "+user);
		//main.searchWithSupervisorId(user);
		try {
			//RefreshExistingUserRecordAction refObj = new RefreshExistingUserRecordAction();
			searchHDISOVSCitems(user);
			testReporter.log(LogStatus.PASS, "Search with One HD Supervisor");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Search with One HD Supervisor");
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
	@Then("^For rejection and approval of response scenario Logging in as Supervisor user and verifying \"(.*)\"$")
	public void logging_In_As_Supervisor_User_And_Verify_Profile(String user) {
		//main.loginWithSupervisorId(user);
		try {
			//RefreshExistingUserRecordAction refObj = new RefreshExistingUserRecordAction();
			logInAsInternalUser(user);
			testReporter.log(LogStatus.PASS, "Login with One HD Supervisor");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Login with One HD Supervisor");
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
	@Then("^For rejection and approval of response scenario Select required service item with Supervisor user to reject$")
	public void Select_Required_Service_item() {
		//main.selectRequiredSI();
		try {
			//RefreshExistingUserRecordAction refObj = new RefreshExistingUserRecordAction();
			selectRequiredDropdownlist();
			testReporter.log(LogStatus.PASS, "Open the new service item");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Open the new service item");
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
	@Then("^For rejection and approval of response scenario Reject the selected service request$")
	public void Reject_Selected_Service_Rqst() {
		//main.rejectServiceRq();
		try {
			//RefreshExistingUserRecordAction refObj = new RefreshExistingUserRecordAction();
			rejectServiceRqst();
			testReporter.log(LogStatus.PASS, "Reject the service request with proper comments");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Reject the service request with proper comments");
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
	@Then("^For rejection and approval of response scenario Verify the rejected service request status$")
	public void Verification_of_Rejected_Service() {
		//main.rejectedResponseVerification();
		try {
			//RefreshExistingUserRecordAction refObj = new RefreshExistingUserRecordAction();
			validateRejectedResponse();
			testReporter.log(LogStatus.PASS, "Verify rejection status verification");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Verify rejection status verification");
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
	@Then("^For rejection and approval of response scenario Logout from supervisor profile$")
	public void log_Out2() {
		//main.logOut();
		try {
			//RefreshExistingUserRecordAction refObj = new RefreshExistingUserRecordAction();
			currentUserLogOut();
			testReporter.log(LogStatus.PASS, "Logging out");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Logging out");
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
	@When("^For rejection and approval of response scenario Search for required Internal User which created the response \"(.*)\"$")
	public void search_For_Internal_User2(String user) {
		//System.out.println("User: "+user);
		//main.searchUser(user);
		try {
			//RefreshExistingUserRecordAction refObj = new RefreshExistingUserRecordAction();
			searchHDISOVSCitems(user);
			testReporter.log(LogStatus.PASS, "Search with required User");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Search with required User");
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
	@Then("^For rejection and approval of response scenario Logging in as Internal user and verifying User which created the response \"(.*)\"$")
	public void logging_In_As_Internal_User_And_Verify_Profile2(String user) {
		//main.logInAsInternalUser(user);
		try {
			//RefreshExistingUserRecordAction refObj = new RefreshExistingUserRecordAction();
			logInAsInternalUser(user);
			testReporter.log(LogStatus.PASS, "Verify log in as Internal User");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Verify log in as Internal User");
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
	@Then("^For rejection and approval of response scenario Edit pre-created response and resubmit$")
	public void Edit_Resubmit_the_response() {
		//main.editSIForResubmission();
		try {
			//RefreshExistingUserRecordAction refObj = new RefreshExistingUserRecordAction();
			searchWithStoredItem();
			editRejectedSI();
			testReporter.log(LogStatus.PASS, "Log in as HD ISC VSO user and edit the service item and resubmit.");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Log in as HD ISC VSO user and edit the service item and resubmit.");
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
	@Then("^For rejection and approval of response scenario Logout from response editor's profile$")
	public void log_Out3() {
		//main.logOut();
		try {
			//RefreshExistingUserRecordAction refObj = new RefreshExistingUserRecordAction();
			currentUserLogOut();
			testReporter.log(LogStatus.PASS, "Logging out");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Logging out");
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
	@When("^For rejection and approval of response scenario Search for required Internal User Supervisor user \"(.*)\"$")
	public void search_For_Internal_User3(String user) {
		//System.out.println("User: "+user);
		//main.searchUser(user);
		try {
			//RefreshExistingUserRecordAction refObj = new RefreshExistingUserRecordAction();
			searchHDISOVSCitems(user);
			testReporter.log(LogStatus.PASS, "Search with required User");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Search with required User");
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
	@Then("^For rejection and approval of response scenario Logging in as Internal user and verifying Supervisor user \"(.*)\"$")
	public void logging_In_As_Internal_User_And_Verify_Profile3(String user) {
		//main.logInAsInternalUser(user);
		try {
			//RefreshExistingUserRecordAction refObj = new RefreshExistingUserRecordAction();
			logInAsInternalUser(user);
			testReporter.log(LogStatus.PASS, "Verify log in as Internal User");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Verify log in as Internal User");
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
	@Then("^For rejection and approval of response scenario Select required service item with Supervisor user to approve$")
	public void Select_Required_Service_item2() {
		//main.selectRequiredSI();
		try {
			//RefreshExistingUserRecordAction refObj = new RefreshExistingUserRecordAction();
			selectRequiredDropdownlist();
			testReporter.log(LogStatus.PASS, "Open the new service item");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Open the new service item");
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
	@Then("^For rejection and approval of response scenario Approve the selected service request$")
	public void Approve_Selected_Service_Rqst() {
		//main.approveTheServiceRqst();
		try {
			//RefreshExistingUserRecordAction refObj = new RefreshExistingUserRecordAction();
			approveServiceRqst();
			testReporter.log(LogStatus.PASS, "Approve the service request/response");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Approve the service request/response");
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
	@Then("^For rejection and approval of response scenario Validate approved request response$")
	public void Validate_Approved_Rqst() {
		//main.validateApproveRqstResponse();
		try {
			//RefreshExistingUserRecordAction refObj = new RefreshExistingUserRecordAction();
			validateApprovedResponse();
			testReporter.log(LogStatus.PASS, "Validate the approved request response");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Validate the approved request response");
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
	@Then("^Stop Report Generation for current scenario For rejection and approval of response scenario$")
	public void getResult() {
		//extent.endTest("HD_ISO_VSC_Existing_User_Record_Refresh");
		extent.endTest(testReporter);
		extent.flush();
		extent.close();
	}
	@Then("^Close the browser For rejection and approval of response scenario$")
	public void flushReporter() {
		driver.close();
		driver.quit();
	}
	public void launch() {
		System.setProperty("webdriver.chrome.driver", "driver//chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize(); // maximizes
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		//main = new HD_ISO_VSC_Service_Request_Rejection_E2E();
		
		//testReporter = extent.startTest("HD_ISO_VSC_Existing_User_Record_Refresh");
		//main.setTestReporter(testReporter);
		
		new SalesforceLogin(driver).login(Constants.salesforce_url_uatg, Constants.salesforce_username_uatg, Constants.salesforce_password_uatg);
	}
	public void highlightElement() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", element);
        Utils.sleep(4);
	}
	public void selectDropdownListValue(String listName, WebElement dropDownWebElement) {
		Select dropdown = new Select(dropDownWebElement);
		dropdown.selectByValue(listName);
	}
	public void fetchCorrectIframe(By dropDownWebElement1) {
		int iFrameCount = driver.findElements(By.xpath("//div[@id='navigatortab']/descendant::iframe")).size();
		System.out.println("Frame count :"+iFrameCount);
		for(int i = 1; i<=iFrameCount+1; i++) {
			try {
				driver.switchTo().frame(driver.findElement(By.xpath("//div[@id='navigatortab']/descendant::iframe["+i+"]")));
				//Utils.sleep(1);
				driver.findElement(dropDownWebElement1).getText();
				break;
			} catch (Exception e) {
				driver.switchTo().defaultContent();
			}
		}
	}
	public void selectRandomDropdownListValue(WebElement dropDownWebElement) {
		Select dropdown = new Select(dropDownWebElement);
		Random randNumber = new Random();
		int rendGeneratedNo = randNumber.nextInt(dropdown.getOptions().size());
		if (rendGeneratedNo == 0) {
			rendGeneratedNo = rendGeneratedNo+1;
		} else if (rendGeneratedNo == dropdown.getOptions().size()) {
			rendGeneratedNo = rendGeneratedNo-1;
		}
		dropdown.selectByIndex(rendGeneratedNo);
	}
	@SuppressWarnings("rawtypes")
	public Wait fluentWaitFunctionality(WebDriver driver)
	{
		@SuppressWarnings({ "unchecked", "deprecation" })
		Wait fwait = new FluentWait(driver)
		 
	    .withTimeout(20, TimeUnit.SECONDS)
	 
	    .pollingEvery(1, TimeUnit.SECONDS)
	 
	    .ignoring(NoSuchElementException.class);
		return fwait;
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void fluentWaitForElementVisibility()
	{	
		Wait fwait = fluentWaitFunctionality(driver);
		//long startTime = System.currentTimeMillis();
		try
		{
		fwait.until(ExpectedConditions.visibilityOfElementLocated(ele));
		}
		catch(Exception e)
		{
			//System.out.println("In select fuctionality fluent wait timeout"+ele);
		}
		//long endTime = System.currentTimeMillis();
		//System.out.println("Time elapsed in fluent wait clickable "+(endTime-startTime)/1000);
	}
	public void verifyProfile() {
		driver.findElement(By.id("userNavLabel")).click();
		Utils.sleep(2);
		driver.findElement(By.xpath(".//a[text()='My Profile']")).click();
		Utils.sleep(2);
		ele = By.xpath(".//*[@id='BackToServiceDesk_Tab']/a");
		fluentWaitForElementVisibility();
		element = driver.findElement(By.id("tailBreadcrumbNode"));
		highlightElement();
		driver.findElement(ele).click();
		Utils.sleep(2);
	}
	public void searchHDISOVSCitems(String user) throws AWTException { // String user
		//String user = "HD ISO VSC";
		try {
		//clear all tab code
		driver.findElement(By.xpath("//div[@id='navigatortab']/div[2]/div[@class='x-tab-tabmenu-right']")).click();
		Utils.sleep(2);
		driver.findElement(By.xpath("//a/span[text()='Close all primary tabs']")).click();
		Utils.sleep(4);
		} catch (Exception e) {
			
		}
		//************
		driver.findElement(By.id("phSearchInput")).sendKeys(user);
		Robot robot = new Robot();
		Utils.sleep(2);
		robot.keyPress(KeyEvent.VK_DOWN);
		Utils.sleep(1);
		robot.keyRelease(KeyEvent.VK_DOWN);
		Utils.sleep(2);
		robot.keyPress(KeyEvent.VK_ENTER);
		Utils.sleep(1);
		robot.keyRelease(KeyEvent.VK_ENTER);
		Utils.sleep(1);
	}
	public void scrollingFunction() {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView();", element);
	}
	/*public void clickHDISOVSCsearchresultitems() {
		try {
		driver.switchTo().frame(driver.findElement(By.xpath("//div[@id='navigatortab']/descendant::iframe[3]")));
		} catch (Exception e) {
			driver.switchTo().frame(driver.findElement(By.xpath("//div[@id='navigatortab']/div[3]/descendant::iframe[2]")));
		}
		element = driver.findElement(By.xpath(".//*[@id='User_body']/table/tbody/descendant::a['"+Constants.salesforce_itemname_uatg+"']"));
		scrollingFunction();
		element.click();
		driver.switchTo().defaultContent();
	}*/
	public void logInAsInternalUser(String user) {//String user
		//String user = "HD ISO VSC";
		WebDriverWait wait = new WebDriverWait (driver, 10);
		ele = By.id("moderatorMutton");
		try {
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver.findElement(By.xpath("//div[@id='navigatortab']/descendant::iframe[2]"))));
			fluentWaitForElementVisibility();
			element = driver.findElement(By.id("moderatorMutton"));
			element.click();
		} catch (Exception e) {
			driver.switchTo().defaultContent();
			fetchCorrectIframe(ele);
			fluentWaitForElementVisibility();
			element = driver.findElement(By.id("moderatorMutton"));
			element.click();
		}
		Utils.sleep(1);
		driver.findElement(By.xpath(".//a[@id='USER_DETAIL']/span")).click();
		Utils.sleep(1);
		driver.switchTo().defaultContent();
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver.findElement(By.xpath("//div[@id='navigatortab']/descendant::iframe[2]"))));
		driver.findElement(By.xpath("//*[text()='Service Cloud User']")).click();
		Utils.sleep(1);
		driver.findElement(By.xpath(".//*[@id='topButtonRow']/input[contains(@value,'Login')]")).click();
		driver.switchTo().defaultContent();
		ele = By.xpath("//a[text()='"+user+"']");
		fluentWaitForElementVisibility();
		Utils.sleep(2);
		driver.findElement(By.xpath(".//a[text()='Back to USCIS CRM']")).click();
	}
	public void customerSearch(String customer) throws AWTException {//String customer
		//String customer = "A200453283";
		Utils.sleep(2);
		WebDriverWait wait = new WebDriverWait (driver, 14);
		ele = By.xpath("//div[@id='navigatortab']/descendant::table/tbody/tr[2]/td[2]/*");
		fluentWaitForElementVisibility();
		//clear all tab code
		driver.findElement(By.xpath("//div[@id='navigatortab']/div[2]/div[@class='x-tab-tabmenu-right']")).click();
		Utils.sleep(2);
		driver.findElement(By.xpath("//a/span[text()='Close all primary tabs']")).click();
		Utils.sleep(4);
		//************
		fluentWaitForElementVisibility();
		driver.findElement(By.xpath("//div[@id='navigatortab']/descendant::table/tbody/tr[2]/td[2]/*[@class='x-btn-split']/button")).click();
		Utils.sleep(2);
		Actions actObj = new Actions(driver);
		actObj.moveToElement(driver.findElement(By.xpath("//*[@class='x-btn-split']"))).moveByOffset(124, 0).click().build().perform();
		Utils.sleep(2);
		driver.findElement(By.xpath("//div[@id='navigator-sbmenu']/descendant::span[text()='Customer Search']")).click();
		Utils.sleep(4);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver.findElement(By.xpath("//div[@id='navigatortab']/div[3]/descendant::iframe[1]"))));
		ele = By.xpath("//*[text()='Customer Search']");
		fluentWaitForElementVisibility();
		driver.findElement(By.xpath(".//table[@class='detailList']/tbody/tr[1]/td[1]/input")).sendKeys(customer);
		Utils.sleep(2);
		driver.findElement(By.xpath(".//*[@id='asyncSearchButton']")).click();
		Utils.sleep(4);
		ele = By.xpath(".//*[@id='asynchronousResults']/descendant::*[contains(text(),'Customers Matching Primary/Secondary')]");
		fluentWaitForElementVisibility();
		driver.switchTo().defaultContent();
	}
	public void customerRefresh() throws AWTException {
		WebDriverWait wait = new WebDriverWait (driver, 40);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver.findElement(By.xpath("//div[@id='navigatortab']/div[3]/descendant::iframe[1]"))));
		driver.findElement(By.xpath("//table[@id='asynchAccountResultTable']/tbody/tr[1]/td[1]/input[@value='Refresh']")).click();
		Utils.sleep(2);
		ele = By.xpath(".//img[@alt='CONFIRM']");
		System.out.println("I am here3");
		try {
			wait.until(ExpectedConditions.presenceOfElementLocated(ele));
		} catch (Exception e) {
			
		}
		driver.switchTo().defaultContent();
		Utils.sleep(2);
		driver.findElement(By.xpath("//*[@class='x-btn-split']")).click();
		Utils.sleep(1);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver.findElement(By.xpath("//div[@id='navigatortab']/div[3]/descendant::iframe[1]"))));
		element = driver.findElement(ele);
		highlightElement();
		driver.switchTo().defaultContent();
		//Actions actObj = new Actions(driver);
		//actObj.moveToElement(driver.findElement(By.xpath("//div[@id='navigatortab']/div[2]/div[3]"))).moveByOffset(350, 0).click().build().perform();
		//driver.findElement(By.xpath("//div[@id='navigatortab']/div[2]/div[3]")).click();
		//Utils.sleep(1);
		//driver.findElement(By.xpath("//span[contains(text(),'Close all primary tabs')]")).click();
		Utils.sleep(4);
	}
	public void clickOnRecordItemFromOwnerPage (String applicationRecord2){
		//String applicationRecord2 = "WAC1690258857";
		driver.findElement(By.xpath("//span[contains(@class,'x-tab-strip-text')]/span[@class='tabText' and text()='EDVARD EDOUARD']")).click();
		Utils.sleep(2);
		fetchCorrectIframe(By.xpath("//h3[contains(text(),'Applications')]"));
		element = driver.findElement(By.xpath("//h3[contains(text(),'Applications')]"));
		scrollingFunction();
		Utils.sleep(2);
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@title='ApplicationPartiesApplicationDetailTable']")));
		element = driver.findElement(By.xpath("//a[text()='"+applicationRecord2+"']"));
		scrollingFunction();
		Utils.sleep(2);
		element.click();
		driver.switchTo().defaultContent();
		driver.switchTo().defaultContent();
	}
	public void searchForApplicationRecordWithAppNo(String applicationRecord) throws AWTException { //String applicationRecord
		//String applicationRecord = "A200453283";
		driver.findElement(By.id("phSearchInput")).sendKeys(applicationRecord);
		Robot robot = new Robot();
		Utils.sleep(2);
		robot.keyPress(KeyEvent.VK_DOWN);
		Utils.sleep(1);
		robot.keyRelease(KeyEvent.VK_DOWN);
		Utils.sleep(2);
		robot.keyPress(KeyEvent.VK_ENTER);
		Utils.sleep(1);
		robot.keyRelease(KeyEvent.VK_ENTER);
		Utils.sleep(4);
	}
	public void openApplicationRecordFromGlobalSearchResultPanel(String applicationRecord2) { //String applicationRecord2
		//String applicationRecord2 = "WAC1690258857";
		Utils.sleep(2);
		WebDriverWait wait = new WebDriverWait (driver, 15);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver.findElement(By.xpath("//div[@id='navigatortab']/descendant::iframe[contains(@src,'UnifiedSearchResults')]"))));
		element = driver.findElement(By.xpath("//a[text()='"+applicationRecord2+"']"));
		highlightElement();
		element.click();
		driver.switchTo().defaultContent();
		Utils.sleep(4);
		//wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver.findElement(By.xpath("//div[@id='navigatortab']/div[3]/descendant::div[contains(@class,'sd_secondary_container x-border-layout-ct')]/descendant::iframe[not(contains(@src,'UnifiedSearchResults')) and not(starts-with(@src,'https://uscis--uatg.my.salesforce.com'))]"))));
		//wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver.findElement(By.xpath("//iframe[contains(@style,'width: 725px;')]"))));
		fetchCorrectIframe(By.xpath("//*[@class='pageDescription' and contains(text(),'"+applicationRecord2+"')]"));
		element = driver.findElement(By.xpath("//*[@class='pageDescription' and contains(text(),'"+applicationRecord2+"')]"));
		highlightElement();
		//driver.switchTo().defaultContent();
	}
	public void createNewServiceItem() {
		//WebDriverWait wait = new WebDriverWait (driver, 15);
		//wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver.findElement(By.xpath("//div[@id='navigatortab']/div[3]/descendant::div[contains(@class,'sd_secondary_container x-border-layout-ct')]/descendant::iframe[not(contains(@src,'UnifiedSearchResults')) and not(starts-with(@src,'https://uscis--uatg.my.salesforce.com'))]"))));
		/*Utils.sleep(2);
		fetchCorrectIframe(By.xpath("//*[@class='pageDescription' and contains(text(),'"+applicationRecord2+"')]"));
		element = driver.findElement(By.xpath("//*[@class='pageDescription' and contains(text(),'"+applicationRecord2+"')]"));
		highlightElement();*/
		driver.findElement(By.xpath(".//input[@value='Create Service Item']")).click();
		driver.switchTo().defaultContent();
		Utils.sleep(4);
	}
	public void createNewServiceItemParam(String applicationRecord2) {
		//WebDriverWait wait = new WebDriverWait (driver, 15);
		//wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver.findElement(By.xpath("//div[@id='navigatortab']/div[3]/descendant::div[contains(@class,'sd_secondary_container x-border-layout-ct')]/descendant::iframe[not(contains(@src,'UnifiedSearchResults')) and not(starts-with(@src,'https://uscis--uatg.my.salesforce.com'))]"))));
		Utils.sleep(2);
		fetchCorrectIframe(By.xpath("//*[@class='pageDescription' and contains(text(),'"+applicationRecord2+"')]"));
		element = driver.findElement(By.xpath("//*[@class='pageDescription' and contains(text(),'"+applicationRecord2+"')]"));
		highlightElement();
		driver.findElement(By.xpath(".//input[@value='Create Service Item']")).click();
		driver.switchTo().defaultContent();
		Utils.sleep(4);
	}
	public void setOrgName(String orgName) { //String orgName
		//String orgName = "Test Org";
		WebDriverWait wait = new WebDriverWait (driver, 15);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver.findElement(By.xpath("//div[@id='navigatortab']/div[3]/descendant::iframe[1]"))));
		driver.switchTo().defaultContent();
		fetchCorrectIframe(By.xpath("//div[@class='pbSubsection'][1]/table[@class='detailList']/tbody/tr[3]/td[2]/input"));
		driver.findElement(By.xpath("//div[@class='pbSubsection'][1]/table[@class='detailList']/tbody/tr[3]/td[2]/input")).sendKeys(orgName);
		//driver.switchTo().defaultContent();
	}
	public void setEmail(String email) { //String email
		//String email = "zabid@acumensolutions.com";
		//WebDriverWait wait = new WebDriverWait (driver, 15);
		//wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver.findElement(By.xpath("//div[@id='navigatortab']/div[3]/descendant::div[contains(@class,'sd_primary_container x-border-layout-ct')][3]/descendant::iframe[1]"))));
		driver.findElement(By.xpath("//div[@class='pbSubsection'][1]/table[@class='detailList']/tbody/tr[2]/td[4]/input")).sendKeys(email);
		//driver.switchTo().defaultContent();
	}
	public void selectSenderType(String senderType) { //String senderType
		//String senderType = "ASC";
		//WebDriverWait wait = new WebDriverWait (driver, 15);
		//wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver.findElement(By.xpath("//div[@id='navigatortab']/div[3]/descendant::div[contains(@class,'sd_primary_container x-border-layout-ct')][3]/descendant::iframe[1]"))));
		selectDropdownListValue(senderType, driver.findElement(By.xpath("//div[@class='pbSubsection'][1]/table[@class='detailList']/tbody/tr[2]/td[2]/span/select")));
		//driver.switchTo().defaultContent();
		Utils.sleep(2);
	}
	public void setRandomSubjectAndDesAndFormType() {
		//WebDriverWait wait = new WebDriverWait (driver, 15);
		//wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver.findElement(By.xpath("//div[@id='navigatortab']/div[3]/descendant::div[contains(@class,'sd_primary_container x-border-layout-ct')][3]/descendant::iframe[1]"))));
		element = driver.findElement(By.xpath("//*[contains(text(),'How Can We Help You')]"));
		scrollingFunction();
		Random randNumber = new Random();
		driver.findElement(By.xpath("//div[@class='pbSubsection'][2]/table[@class='detailList']/tbody/tr[1]/td[2]/div/input")).sendKeys("Test Subject_"+randNumber.nextInt(10000));
		driver.findElement(By.xpath("//div[@class='pbSubsection'][2]/table[@class='detailList']/tbody/tr[2]/td[2]/textarea")).sendKeys("Test Description_"+randNumber.nextInt(10000));
		selectRandomDropdownListValue(driver.findElement(By.xpath("//div[@class='pbSubsection'][2]/table[@class='detailList']/tbody/tr[2]/td[4]/div/span/span/select")));
		//driver.switchTo().defaultContent();
		Utils.sleep(2);
	}
	public void setParamSubjectAndDesAndFormType(String subject, String description, String formType) {
		//WebDriverWait wait = new WebDriverWait (driver, 15);
		//wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver.findElement(By.xpath("//div[@id='navigatortab']/div[3]/descendant::div[contains(@class,'sd_primary_container x-border-layout-ct')][3]/descendant::iframe[1]"))));
		element = driver.findElement(By.xpath("//*[contains(text(),'How Can We Help You')]"));
		scrollingFunction();
		driver.findElement(By.xpath("//div[@class='pbSubsection'][2]/table[@class='detailList']/tbody/tr[1]/td[2]/div/input")).sendKeys(subject);
		driver.findElement(By.xpath("//div[@class='pbSubsection'][2]/table[@class='detailList']/tbody/tr[2]/td[2]/textarea")).sendKeys(description);
		//selectRandomDropdownListValue(driver.findElement(By.xpath("//div[@class='pbSubsection'][2]/table[@class='detailList']/tbody/tr[2]/td[4]/div/span/span/select")));
		selectDropdownListValue(formType, driver.findElement(By.xpath("//div[@class='pbSubsection'][2]/table[@class='detailList']/tbody/tr[2]/td[4]/div/span/span/select")));
		//driver.switchTo().defaultContent();
		Utils.sleep(2);
	}
	public void setRandomCategoryAndKind() {
		//WebDriverWait wait = new WebDriverWait (driver, 15);
		//wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver.findElement(By.xpath("//div[@id='navigatortab']/div[3]/descendant::div[contains(@class,'sd_primary_container x-border-layout-ct')][3]/descendant::iframe[1]"))));
		element = driver.findElement(By.xpath("//*[contains(text(),'Category / Kind')]"));
		scrollingFunction();
		Random randNumber = new Random();
		selectRandomDropdownListValue(driver.findElement(By.xpath("//div[@class='pbSubsection'][3]/table[@class='detailList']/tbody/tr[1]/td[2]/span/select")));
		selectRandomDropdownListValue(driver.findElement(By.xpath("//div[@class='pbSubsection'][3]/table[@class='detailList']/tbody/tr[2]/td[2]/span/span/select")));
		driver.findElement(By.xpath("//div[@class='pbSubsection'][3]/table[@class='detailList']/tbody/tr[1]/td[4]/textarea")).sendKeys("Auto Generated Comments_"+randNumber.nextInt(10000));
		//driver.switchTo().defaultContent();
		Utils.sleep(2);
	}
	public void setParamCategoryAndKind(String category, String kind, String comments) {
		//WebDriverWait wait = new WebDriverWait (driver, 15);
		//wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver.findElement(By.xpath("//div[@id='navigatortab']/div[3]/descendant::div[contains(@class,'sd_primary_container x-border-layout-ct')][3]/descendant::iframe[1]"))));
		element = driver.findElement(By.xpath("//*[contains(text(),'Category / Kind')]"));
		scrollingFunction();
		selectDropdownListValue(category,driver.findElement(By.xpath("//div[@class='pbSubsection'][3]/table[@class='detailList']/tbody/tr[1]/td[2]/span/select")));
		selectDropdownListValue(kind,driver.findElement(By.xpath("//div[@class='pbSubsection'][3]/table[@class='detailList']/tbody/tr[2]/td[2]/span/span/select")));
		driver.findElement(By.xpath("//div[@class='pbSubsection'][3]/table[@class='detailList']/tbody/tr[1]/td[4]/textarea")).sendKeys(comments);
		//driver.switchTo().defaultContent();
		Utils.sleep(2);
	}
	public void selectSIOrigin(String serviceItem) { //String serviceItem
		//String serviceItem = "Email";
		//WebDriverWait wait = new WebDriverWait (driver, 15);
		//wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver.findElement(By.xpath("//div[@id='navigatortab']/div[3]/descendant::div[contains(@class,'sd_primary_container x-border-layout-ct')][3]/descendant::iframe[1]"))));
		selectDropdownListValue(serviceItem, driver.findElement(By.xpath("//div[@class='pbSubsection'][4]/table[@class='detailList']/tbody/tr[3]/td[2]/div/span/select")));
		//driver.switchTo().defaultContent();
	}
	public void selectInitialQueue(String initialQueue) { //String initialQueue
		//String initialQueue = "VAWA_UPFD";
		//WebDriverWait wait = new WebDriverWait (driver, 15);
		//wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver.findElement(By.xpath("//div[@id='navigatortab']/div[3]/descendant::div[contains(@class,'sd_primary_container x-border-layout-ct')][3]/descendant::iframe[1]"))));
		selectDropdownListValue(initialQueue, driver.findElement(By.xpath("//div[@class='pbSubsection'][4]/table[@class='detailList']/tbody/tr[5]/td[2]/span/select")));
		//driver.switchTo().defaultContent();
	}
	public void setReceivedDate(String receivedDate) throws AWTException { //String receivedDate
		//String receivedDate = "10/4/2018 9:43 AM";
		//WebDriverWait wait = new WebDriverWait (driver, 15);
		//wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver.findElement(By.xpath("//div[@id='navigatortab']/div[3]/descendant::div[contains(@class,'sd_primary_container x-border-layout-ct')][3]/descendant::iframe[1]"))));
		driver.findElement(By.xpath("//div[@class='pbSubsection'][4]/table[@class='detailList']/tbody/tr[4]/td[2]/div/span/input")).sendKeys(receivedDate);
		Robot robot = new Robot();
		Utils.sleep(1);
		robot.keyPress(KeyEvent.VK_TAB);
		Utils.sleep(1);
		robot.keyRelease(KeyEvent.VK_TAB);
		Utils.sleep(2);
		//driver.switchTo().defaultContent();
	}
	public void clickOnSaveSI() {
		//WebDriverWait wait = new WebDriverWait (driver, 15);
		//wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver.findElement(By.xpath("//div[@id='navigatortab']/div[3]/descendant::div[contains(@class,'sd_primary_container x-border-layout-ct')][3]/descendant::iframe[1]"))));
		element = driver.findElement(By.xpath(".//*[@id='bottomButtonRow']"));
		scrollingFunction();
		driver.findElement(By.xpath(".//*[@id='bottomButtonRow']/input[contains(@value,'Save')][1]")).click();
		driver.switchTo().defaultContent();
		Utils.sleep(8);
	}
	public String fetchServiceItemNo() {
		WebDriverWait wait = new WebDriverWait (driver, 30);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver.findElement(By.xpath("//div[@id='navigatortab']/div[3]/descendant::iframe[contains(@src,'RecordType')]"))));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='New Response']")));
		element = driver.findElement(By.xpath(".//div[@class='feedUpdateContainer']/div[1]/descendant::div[@class='feedcommenttext']/following-sibling::div"));
		highlightElement();
		newSINo = driver.findElement(By.xpath("//span[text()='Service Item Number:']/following-sibling::span")).getText();
		System.out.println("Newly Generated Service Item Number is :"+newSINo);
		driver.switchTo().defaultContent();
		return newSINo;
	}
	public void createNewResponse() throws AWTException {
		WebDriverWait wait = new WebDriverWait (driver, 30);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver.findElement(By.xpath("//div[@id='navigatortab']/div[3]/descendant::iframe[contains(@src,'RecordType')]"))));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='New Response']")));
		driver.findElement(By.xpath("//span[text()='New Response']")).click();
		Utils.sleep(2);
		//driver.switchTo().defaultContent();
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver.findElement(By.xpath(".//iframe[@title='ResponseBuilder']"))));
		element = driver.findElement(By.xpath(".//span[text()='Select Folder']"));
		scrollingFunction();
		element = driver.findElement(By.xpath(".//option[contains(text(),'01. Humanitarian Division Greetings')]"));
		scrollingFunction();
		element.click();
		Utils.sleep(1);
		driver.findElement(By.xpath(".//option[contains(text(),'HD Good Day')]")).click();
		driver.findElement(By.xpath("//button[@title='Select to move the template to the selected box. Information about the template will appear below.']")).click();
		//Select 2nd one
		element = driver.findElement(By.xpath(".//option[contains(text(),'02. HD Document Requests')]"));
		//scrollingFunction();
		/*element.click();
		element.click();*/
		//((JavascriptExecutor)driver).executeScript("arguments[0].click();", element);
		Actions actObj = new Actions(driver);
		actObj.moveToElement(driver.findElement(By.xpath(".//option[contains(text(),'02. HD Document Requests')]"))).doubleClick().build().perform();
		/*element = driver.findElement(By.xpath(".//option[contains(text(),'02. HD Document Requests')]/parent::select"));
		selectDropdownListValue("02. HD Document Requests", element);*/
		Utils.sleep(1);
		driver.findElement(By.xpath(".//option[contains(text(),'I-929 Sent to NVC')]")).click();
		Utils.sleep(1);
		driver.findElement(By.xpath("//button[@title='Select to move the template to the selected box. Information about the template will appear below.']")).click();
		//Select 3rd one
		element = driver.findElement(By.xpath(".//option[contains(text(),'02. SAVE Program')]"));
		scrollingFunction();
		Utils.sleep(2);
		/*element.click();
		element.click();*/
		//selectDropdownListValue("03. Humanitarian Division Closings", element);
		//((JavascriptExecutor)driver).executeScript("arguments[0].click();", element);
		element = driver.findElement(By.xpath(".//option[contains(text(),'03. Humanitarian Division Closings')]"));
		scrollingFunction();
		actObj.moveToElement(element).doubleClick().build().perform();
		Utils.sleep(1);
		element = driver.findElement(By.xpath(".//option[contains(text(),'HD Closing')]"));
		scrollingFunction();
		Utils.sleep(1);
		element.click();
		driver.findElement(By.xpath("//button[@title='Select to move the template to the selected box. Information about the template will appear below.']")).click();
		Utils.sleep(2);
		element = driver.findElement(By.xpath("//button[contains(text(),'Generate Preview')]"));
		scrollingFunction();
		Utils.sleep(3);
		element.click();
		Utils.sleep(4);
		driver.findElement(By.xpath("//button[contains(text(),'Submit')]")).click();
		Utils.sleep(4);
		Robot robot = new Robot();
		Utils.sleep(2);
		robot.keyPress(KeyEvent.VK_ENTER);
		Utils.sleep(1);
		robot.keyRelease(KeyEvent.VK_ENTER);
		Utils.sleep(2);
		ele = By.xpath("//div[contains(text(),'Your response has been successfully submitted and is now locked.')]");
		fluentWaitForElementVisibility();
		Utils.sleep(4);
		driver.switchTo().defaultContent();
		driver.switchTo().defaultContent();
	}
	public void verifyNewResponseStatus() {
		driver.navigate().refresh();
		Utils.sleep(4);
		WebDriverWait wait = new WebDriverWait (driver, 30);
		try {
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver.findElement(By.xpath("//div[@id='navigatortab']/div[3]/descendant::iframe[starts-with(@src,'https://uscis--uatg.my.salesforce.com')]"))));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='New Response']")));
		driver.findElement(By.xpath("//a[@title='Details']/span[1]")).click();
		} catch (Exception e) {
			driver.switchTo().defaultContent();
			fetchCorrectIframe(By.xpath("//span[text()='New Response']"));
			driver.findElement(By.xpath("//a[@title='Details']/span[1]")).click();
		}
		element = driver.findElement(By.xpath("//img[@title='Response']/following::*[text()='Responses']"));
		scrollingFunction();
		
		element = driver.findElement(By.xpath("//table[@class='list']/tbody/tr[2]/td[contains(text(),'Under Review')]"));
		highlightElement();
		Utils.sleep(2);
		driver.switchTo().defaultContent();
	}
	public void currentUserLogOut() {
		driver.findElement(By.xpath(".//*[@id='userNavLabel']")).click();
		Utils.sleep(1);
		driver.findElement(By.xpath("//a[text()='Logout']")).click();
		Utils.sleep(4);
	}
	//Call supervisor function to log in with supervisor
	public void selectRequiredDropdownlist() {
		//clear all tab code
				driver.findElement(By.xpath("//div[@id='navigatortab']/div[2]/div[@class='x-tab-tabmenu-right']")).click();
				Utils.sleep(2);
				driver.findElement(By.xpath("//a/span[text()='Close all primary tabs']")).click();
				Utils.sleep(4);
				//************
		//newSINo = "02734526";
		WebDriverWait wait = new WebDriverWait (driver, 14);
		ele = By.xpath("//div[@id='navigatortab']/descendant::table/tbody/tr[2]/td[2]/*");
		fluentWaitForElementVisibility();
		driver.findElement(By.xpath("//div[@id='navigatortab']/descendant::table/tbody/tr[2]/td[2]/*[@class='x-btn-split']/button")).click();
		Utils.sleep(2);
		Actions actObj = new Actions(driver);
		actObj.moveToElement(driver.findElement(By.xpath("//*[@class='x-btn-split']"))).moveByOffset(124, 0).click().build().perform();
		Utils.sleep(2);
		driver.findElement(By.xpath("//div[@id='navigator-sbmenu']/descendant::span[text()='Service Items']")).click();
		Utils.sleep(4);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver.findElement(By.xpath("//div[@id='navigatortab']/div[3]/descendant::iframe[not(starts-with(@src,'https://uscis--uatg.my.salesforce.com'))]"))));
		//selectDropdownListValue("My Open Service Items", driver.findElement(By.xpath("//img[@title='Service Item']/following::select[@title='View:']")));
		driver.findElement(By.xpath("//select[contains(@id,'listSelect')]")).click();
		Utils.sleep(2);
		driver.findElement(By.xpath("//select[contains(@id,'listSelect')]/option[text()='My Open Service Items']")).click();
		Utils.sleep(4);
		ele = By.xpath("//a[text()='"+newSINo+"']");
		fluentWaitForElementVisibility();
		element = driver.findElement(ele);
		scrollingFunction();
		driver.findElement(By.xpath("//a[text()='"+newSINo+"']")).click(); //// need to change
		System.out.println("Clicked on new service item :"+newSINo);
		Utils.sleep(4);
		driver.switchTo().defaultContent();
	}
	public void approveServiceRqst() {
		WebDriverWait wait = new WebDriverWait (driver, 8);
		fetchCorrectIframe(By.xpath("//a[@title='Details']/span/span[1]"));
		driver.findElement(By.xpath("//a[@title='Details']/span/span[1]")).click();
		Utils.sleep(1);
		//driver.switchTo().defaultContent();
		//wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver.findElement(By.xpath("//div[@id='navigatortab']/div[3]/descendant::iframe[2]"))));
		element = driver.findElement(By.xpath("//img[@title='Response']/following::*[text()='Responses']"));
		scrollingFunction();
		Utils.sleep(2);
		element = driver.findElement(By.xpath("//tr[2]/td[1]/a[@class='actionLink'][text()='Edit']"));
		highlightElement();
		driver.findElement(By.xpath("//tr[2]/td[1]/a[@class='actionLink'][text()='Edit']")).click();
		driver.switchTo().defaultContent();
		Utils.sleep(2);
		try {
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver.findElement(By.xpath("//div[@id='navigatortab']/div[3]/descendant::iframe[3]"))));
			element = driver.findElement(By.xpath("//span[text()='Approve/Reject Response']"));
			scrollingFunction();
			highlightElement();
			Utils.sleep(2);
			//selectDropdownListValue("My Open Service Items", driver.findElement(By.xpath("//img[@title='Service Item']/following::select[@title='View:']")));
			driver.findElement(By.xpath("//input[@id='input-10']")).click();
		} catch (Exception e) {
			driver.switchTo().defaultContent();
			fetchCorrectIframe(By.xpath("//span[text()='Approve/Reject Response']"));
			element = driver.findElement(By.xpath("//span[text()='Approve/Reject Response']"));
			scrollingFunction();
			highlightElement();
			Utils.sleep(2);
			//selectDropdownListValue("My Open Service Items", driver.findElement(By.xpath("//img[@title='Service Item']/following::select[@title='View:']")));
			driver.findElement(By.xpath("//input[@id='input-10']")).click();
		}
		Utils.sleep(1);
		driver.findElement(By.xpath("//*[@data-value='Approve']")).click();
		Utils.sleep(1);
		driver.findElement(By.xpath("//textarea[@id='input-3']")).sendKeys("Auto Approved from System.");
		Utils.sleep(1);
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		Utils.sleep(1);
		ele = By.xpath("//*[contains(text(),'Response decision successfully saved.')]");
		fluentWaitForElementVisibility();
		element = driver.findElement(ele);
		highlightElement();
		driver.switchTo().defaultContent();
		Utils.sleep(4);
	}
	public void validateApprovedResponse() {
		//newSINo = "02734526";
		driver.navigate().refresh();
		Utils.sleep(4);
		ele = By.xpath(".//span[@class='tabText' and text()='"+newSINo+"']");
		fluentWaitForElementVisibility();
		driver.findElement(ele).click();
		Utils.sleep(2);
		fetchCorrectIframe(By.xpath("//a[@title='Details']/span/span[1]"));
		driver.findElement(By.xpath("//a[@title='Details']/span/span[1]")).click();
		Utils.sleep(1);
		element = driver.findElement(By.xpath("//img[@title='Response']/following::*[text()='Responses']"));
		scrollingFunction();
		Utils.sleep(2);
		element = driver.findElement(By.xpath("//tr[2]/td[text()='Sent']"));
		highlightElement();
		element = driver.findElement(By.xpath("//*[text()='Emails']/parent::td[@class='pbTitle']"));
		scrollingFunction();
		element = driver.findElement(By.xpath("//th[text()='Sent']"));
		highlightElement();
		driver.switchTo().defaultContent();
	}
	public void rejectServiceRqst() {
		WebDriverWait wait = new WebDriverWait (driver, 8);
		fetchCorrectIframe(By.xpath("//a[@title='Details']/span/span[1]"));
		driver.findElement(By.xpath("//a[@title='Details']/span/span[1]")).click();
		Utils.sleep(1);
		//driver.switchTo().defaultContent();
		//wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver.findElement(By.xpath("//div[@id='navigatortab']/div[3]/descendant::iframe[2]"))));
		driver.findElement(By.xpath(".//*[@id='topButtonRow']/input[3]")).click();
		driver.switchTo().defaultContent();
		Utils.sleep(2);
		fetchCorrectIframe(By.xpath("//div[@class='pbSubsection'][1]/table[@class='detailList']/tbody/tr[3]/td[4]/input[@type='checkbox']"));
		driver.findElement(By.xpath("//div[@class='pbSubsection'][1]/table[@class='detailList']/tbody/tr[3]/td[4]/input[@type='checkbox']")).click();
		clickOnSaveSI();
		driver.switchTo().defaultContent();
		fetchCorrectIframe(By.xpath("//a[@title='Details']/span/span[1]"));
		ele = By.xpath("//a[@title='Details']/span/span[1]");
		fluentWaitForElementVisibility();
		driver.findElement(By.xpath("//a[@title='Details']/span/span[1]")).click();
		Utils.sleep(1);
		element = driver.findElement(By.xpath("//img[@title='Checked']"));
		highlightElement();
		Utils.sleep(2);
		element = driver.findElement(By.xpath("//img[@title='Response']/following::*[text()='Responses']"));
		scrollingFunction();
		Utils.sleep(2);
		element = driver.findElement(By.xpath("//tr[2]/td[1]/a[@class='actionLink'][text()='Edit']"));
		highlightElement();
		driver.findElement(By.xpath("//tr[2]/td[1]/a[@class='actionLink'][text()='Edit']")).click();
		driver.switchTo().defaultContent();
		Utils.sleep(2);
		try {
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver.findElement(By.xpath("//div[@id='navigatortab']/div[3]/descendant::iframe[3]"))));
			element = driver.findElement(By.xpath("//span[text()='Approve/Reject Response']"));
			scrollingFunction();
			highlightElement();
			Utils.sleep(2);
			//selectDropdownListValue("My Open Service Items", driver.findElement(By.xpath("//img[@title='Service Item']/following::select[@title='View:']")));
			driver.findElement(By.xpath("//input[@id='input-10']")).click();
		} catch (Exception e) {
			driver.switchTo().defaultContent();
			fetchCorrectIframe(By.xpath("//span[text()='Approve/Reject Response']"));
			element = driver.findElement(By.xpath("//span[text()='Approve/Reject Response']"));
			scrollingFunction();
			highlightElement();
			Utils.sleep(2);
			//selectDropdownListValue("My Open Service Items", driver.findElement(By.xpath("//img[@title='Service Item']/following::select[@title='View:']")));
			driver.findElement(By.xpath("//input[@id='input-10']")).click();
		}
		Utils.sleep(1);
		driver.findElement(By.xpath("//*[@data-value='Reject']")).click();
		Utils.sleep(1);
		driver.findElement(By.xpath("//input[@id='input-11']")).click();
		Utils.sleep(1);
		driver.findElement(By.xpath("//*[@data-value='Plain Language']")).click();
		Utils.sleep(1);
		driver.findElement(By.xpath("//textarea[@id='input-3']")).sendKeys("Auto Reject from System.");
		Utils.sleep(1);
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		Utils.sleep(1);
		ele = By.xpath("//*[contains(text(),'Response decision successfully saved.')]");
		fluentWaitForElementVisibility();
		element = driver.findElement(ele);
		highlightElement();
		driver.switchTo().defaultContent();
		Utils.sleep(4);
	}
	public void validateRejectedResponse() {
		//newSINo = "02734526";
		driver.navigate().refresh();
		Utils.sleep(4);
		ele = By.xpath(".//span[@class='tabText' and text()='"+newSINo+"']");
		fluentWaitForElementVisibility();
		driver.findElement(ele).click();
		Utils.sleep(2);
		fetchCorrectIframe(By.xpath("//a[@title='Details']/span/span[1]"));
		driver.findElement(By.xpath("//a[@title='Details']/span/span[1]")).click();
		Utils.sleep(1);
		element = driver.findElement(By.xpath("//img[@title='Response']/following::*[text()='Responses']"));
		scrollingFunction();
		Utils.sleep(2);
		element = driver.findElement(By.xpath("//tr[2]/td[text()='Edits Required']"));
		highlightElement();
		/*element = driver.findElement(By.xpath("//*[text()='Emails']/parent::td[@class='pbTitle']"));
		scrollingFunction();
		element = driver.findElement(By.xpath("//th[text()='Sent']"));
		highlightElement();*/
		driver.switchTo().defaultContent();
	}
	//Log out
	//Log in with HD ISC VSO
	public void searchWithStoredItem() throws AWTException { //String applicationRecord
		//String newSINo = "A200453283";
		//clear all tab code
		Utils.sleep(2);
		driver.findElement(By.xpath("//div[@id='navigatortab']/div[2]/div[@class='x-tab-tabmenu-right']")).click();
		Utils.sleep(2);
		driver.findElement(By.xpath("//a/span[text()='Close all primary tabs']")).click();
		Utils.sleep(4);
		//************
		//newSINo = "02734526";
		driver.findElement(By.id("phSearchInput")).sendKeys(newSINo);
		Robot robot = new Robot();
		Utils.sleep(2);
		robot.keyPress(KeyEvent.VK_DOWN);
		Utils.sleep(1);
		robot.keyRelease(KeyEvent.VK_DOWN);
		Utils.sleep(2);
		robot.keyPress(KeyEvent.VK_ENTER);
		Utils.sleep(1);
		robot.keyRelease(KeyEvent.VK_ENTER);
		Utils.sleep(4);
	}
	public void editRejectedSI() throws AWTException {
		//newSINo = "02734526";
		try {
			WebDriverWait wait = new WebDriverWait (driver, 8);
			fetchCorrectIframe(By.xpath("//table[@class='list']/descendant::a[text()='"+newSINo+"']"));
			driver.findElement(By.xpath("//table[@class='list']/descendant::a[text()='"+newSINo+"']")).click();
			driver.switchTo().defaultContent();
			Utils.sleep(1);
		} catch (Exception e) {
			//e.printStackTrace();
		}
		fetchCorrectIframe(By.xpath("//a[@title='Details']/span/span[1]"));
		driver.findElement(By.xpath("//a[@title='Details']/span/span[1]")).click();
		Utils.sleep(1);
		element = driver.findElement(By.xpath("//img[@title='Response']/following::*[text()='Responses']"));
		scrollingFunction();
		Utils.sleep(2);
		element = driver.findElement(By.xpath("//td[text()='Edits Required']/parent::tr/td[1]/a[text()='Edit']"));
		highlightElement();
		driver.findElement(By.xpath("//td[text()='Edits Required']/parent::tr/td[1]/a[text()='Edit']")).click();
		driver.switchTo().defaultContent();
		fetchCorrectIframe(By.xpath("//div[@aria-label='Compose text']/p[1]"));
		//driver.findElement(By.xpath("//div[@aria-label='Compose text']/p[1]")).click();
		Actions actObj = new Actions(driver);
		actObj.moveToElement(driver.findElement(By.xpath("//div[@aria-label='Compose text']/p[1]"))).click().build().perform();
		Utils.sleep(2);
		//driver.findElement(By.xpath("//div[@aria-label='Compose text']/p[1]")).sendKeys("Good day, /br /br Changes for edit /br");
		((JavascriptExecutor)driver).executeScript("arguments[0].textContent = arguments[1];", driver.findElement(By.xpath("//div[@aria-label='Compose text']/p[1]")), "Good day, Changes for edit.");
		Utils.sleep(2);
		element = driver.findElement(By.xpath("//button[contains(text(),'Submit')]"));
		scrollingFunction();
		Utils.sleep(2);
		driver.findElement(By.xpath("//button[contains(text(),'Submit')]")).click();
		Utils.sleep(4);
		Robot robot = new Robot();
		Utils.sleep(2);
		robot.keyPress(KeyEvent.VK_ENTER);
		Utils.sleep(1);
		robot.keyRelease(KeyEvent.VK_ENTER);
		Utils.sleep(2);
		ele = By.xpath("//div[contains(text(),'Your response has been successfully submitted and is now locked.')]");
		fluentWaitForElementVisibility();
		Utils.sleep(4);
		driver.switchTo().defaultContent();
	}
	public void setPercentage(String percentageValue) throws AWTException { //String applicationRecord
		//clear all tab code
		driver.findElement(By.xpath("//div[@id='navigatortab']/div[2]/div[@class='x-tab-tabmenu-right']")).click();
		Utils.sleep(2);
		driver.findElement(By.xpath("//a/span[text()='Close all primary tabs']")).click();
		Utils.sleep(4);
		//************
		//newSINo = "02734526";
		WebDriverWait wait = new WebDriverWait (driver, 14);
		ele = By.xpath("//div[@id='navigatortab']/descendant::table/tbody/tr[2]/td[2]/*");
		fluentWaitForElementVisibility();
		driver.findElement(By.xpath("//div[@id='navigatortab']/descendant::table/tbody/tr[2]/td[2]/*[@class='x-btn-split']/button")).click();
		Utils.sleep(2);
		Actions actObj = new Actions(driver);
		actObj.moveToElement(driver.findElement(By.xpath("//*[@class='x-btn-split']"))).moveByOffset(124, 0).click().build().perform();
		Utils.sleep(2);
		driver.findElement(By.xpath("//div[@id='navigator-sbmenu']/descendant::span[text()='Quality Control Percentage']")).click();
		Utils.sleep(4);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver.findElement(By.xpath("//div[@id='navigatortab']/div[3]/descendant::iframe[not(starts-with(@src,'https://uscis--uatg.my.salesforce.com'))]"))));
		ele = By.xpath(".//input[@title='QC Percentage for HD ISO VSC']");
		fluentWaitForElementVisibility();
		Utils.sleep(2);
		driver.findElement(By.xpath(".//table[@title='ISO Users']/tbody/tr[2]/td[1]/input[@type='checkbox']")).click();
		Utils.sleep(1);
		driver.findElement(ele).clear();
		driver.findElement(ele).sendKeys(percentageValue);
		Utils.sleep(1);
		driver.findElement(By.xpath(".//div[contains(@class,'pbBottomButtons')]/descendant::input[@value='Set QC Percentage']")).click();
		Utils.sleep(2);
		ele = By.xpath(".//*[contains(text(),'Successfully updated QC Percentage for ISOs')]");
		fluentWaitForElementVisibility();
		element = driver.findElement(By.xpath(".//*[contains(text(),'Successfully updated QC Percentage for ISOs')]"));
		highlightElement();
		driver.switchTo().defaultContent();
	}
}
