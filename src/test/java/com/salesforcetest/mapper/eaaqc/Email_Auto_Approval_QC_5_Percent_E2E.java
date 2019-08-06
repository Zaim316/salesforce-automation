package com.salesforcetest.mapper.eaaqc;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.Calendar;
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
import com.salesforcetest.shared.Constants;
import com.salesforcetest.shared.ExtentReporter;
import com.salesforcetest.shared.Utils;

import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Email_Auto_Approval_QC_5_Percent_E2E {
	public static WebDriver driver;
	private WebElement element;
	public static String newSINo, emailLink, subjectLine;
	private By ele;
private HD_ISO_VSC_Service_Request_Rejection_E2E main = null;
	
	static ExtentReports extent = ExtentReporter.getInstance().getExtentReports();

	static ExtentTest testReporter;
	@Given("^Email auto approval process Registered User is logged in with \"(.*)\"$")
	public void init(String user) {
		main = new HD_ISO_VSC_Service_Request_Rejection_E2E(driver);
		
		testReporter = extent.startTest("End to End Approval and Rejection Flow");
		try {
			launch();
			testReporter.log(LogStatus.PASS, "User logs in successfully");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "User logs in successfully");
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
	@Then("Email auto approval process Verifying the current logged in user profile$")
	public void verify_Logged_In_User_Profile() {
		try {
			verifyProfile();
			testReporter.log(LogStatus.PASS, "Verifying the current logged in user profile:");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Verifying the current logged in user profile:");
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
	@Then("Email auto approval process Fetching email EmailRelayRoutingHandler from by navigating to email services area$")
	public void Fetch_Email_From_Email_Services() {
		try {
			fetchEmailLink();
			testReporter.log(LogStatus.PASS, "Fetching email from by navigating to email services EmailRelayRoutingHandler area");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Fetching email from by navigating to email services EmailRelayRoutingHandler area");
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
	@Then("Email auto approval process Logging into email \"(.*)\" with user id \"(.*)\"$")
	public void Log_Into_Email(String url, String username) {
		try {
			logIntoGmail(url, username, Constants.email_password);
			launch();
			testReporter.log(LogStatus.PASS, "Logging into email to send email to create a Service Item");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Logging into email to send email to create a Service Item");
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
	@When("^Email auto approval process Search for required Internal User \"(.*)\"$")
	public void search_For_Internal_User(String user) {
		try {
			searchHDISOVSCitems(user);
			testReporter.log(LogStatus.PASS, "Search with required User");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Search with required User");
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
	@Then("^Email auto approval process Logging in as Internal user and verifying \"(.*)\"$")
	public void logging_In_As_Internal_User_And_Verify_Profile(String user) {
		try {
			logInAsInternalUser(user);
			testReporter.log(LogStatus.PASS, "Verify log in as Internal User");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Verify log in as Internal User");
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
	@Then("Email auto approval process set QC Percentage to \"(.*)\"$")
	public void Set_QC_Percentage(String val) {
		try {
			setPercentage(val);
			testReporter.log(LogStatus.PASS, "Set percentage to "+val+" successful.");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Set percentage to "+val+" successful.");
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
	@Then("Email auto approval process Fetch the Service item number which is created through Email$")
	public void Fetch_The_SI_Number() {
		try {
			hotLineDropDown();
			currentUserLogOut();
			testReporter.log(LogStatus.PASS, "Fetch the Service Item number whose subject line is "+subjectLine+" and SI number "+newSINo);
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Fetch the Service Item number whose subject line is "+subjectLine+" and SI number "+newSINo);
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
	@Then("Email auto approval process Log Into as HD VSC user and mark openend items as duplicate and Assign a new item with user \"(.*)\"$")
	public void Fetch_The_SI_Number(String user) {
		try {
			searchHDISOVSCitems("HD ISO VSC");
			logInAsInternalUser("HD ISO VSC");
			duplicateSIandOpenSI();
			editSIandSave();
			testReporter.log(LogStatus.PASS, "Marking all open items as duplicate and assign new SI and Open the item in Edit mode with "+user+" user.");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Marking all open items as duplicate and assign new SI and Open the item in Edit mode with "+user+" user.");
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
	@And("^To edit service item Provide all new mandatory data$")
	public void create_new_service_item(DataTable dt) {
		//need to code as per feature file.
		List<Map<String, String>> list = dt.asMaps(String.class, String.class);
		String receiptNumber = list.get(0).get("Receipt No");//new
		String contactNm = list.get(0).get("Contact");//new
		String fOrgName = list.get(0).get("Org Name");
		String email = list.get(0).get("Email");
		String senderType = list.get(0).get("Sender Type");
		String subject = list.get(0).get("Subject");
		String description = list.get(0).get("Description");
		String formNo = list.get(0).get("Form No");//new
		String formType = list.get(0).get("Form Type");
		String category = list.get(0).get("Category");
		String kind = list.get(0).get("Kind");
		String comm = list.get(0).get("Comments");
		String io = list.get(0).get("Item Origin");
		String queue = list.get(0).get("Queue");
		String dateTime = list.get(0).get("DateTime");
		String responseComments = list.get(0).get("Response Comments");//new
		try {
			//RefreshExistingUserRecordAction refObj = new RefreshExistingUserRecordAction();
			setReceiptNumberAndContactName(receiptNumber, contactNm);
			testReporter.log(LogStatus.PASS, "Set receipt number and contact name respectively : "+receiptNumber+" and "+contactNm);
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Set receipt number and contact name respectively : "+receiptNumber+" and "+contactNm);
			Assert.assertTrue(false);
			e.printStackTrace();
		}
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
		/*try {
			//RefreshExistingUserRecordAction refObj = new RefreshExistingUserRecordAction();
			setEmail(email);
			testReporter.log(LogStatus.PASS, "Give Email Id: "+email);
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Give Email Id: "+email);
			Assert.assertTrue(false);
			e.printStackTrace();
		}*/
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
		/*try {
			//RefreshExistingUserRecordAction refObj = new RefreshExistingUserRecordAction();
			setParamSubjectAndDesAndFormType(subject, description);
			testReporter.log(LogStatus.PASS, "Provide Subject,  Description and Form Type respectively: "+subject+" ,"+description);
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Provide Subject,  Description and Form Type respectively: "+subject+" ,"+description);
			Assert.assertTrue(false);
			e.printStackTrace();
		}*/
		try {
			//RefreshExistingUserRecordAction refObj = new RefreshExistingUserRecordAction();
			setformNumberAndFormType(formNo, formType);
			testReporter.log(LogStatus.PASS, "Select FormNumber and Form Type respectively: "+formNo+" ,"+formType);
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Select FormNumber and Form Type respectively: "+formNo+" ,"+formType);
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
		/*try {
			//RefreshExistingUserRecordAction refObj = new RefreshExistingUserRecordAction();
			setReceivedDate(dateTime);
			testReporter.log(LogStatus.PASS, "Give Received Date (any past date) : "+dateTime);
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Give Received Date (any past date) : "+dateTime);
			Assert.assertTrue(false);
			e.printStackTrace();
		}*/
		try {
			//RefreshExistingUserRecordAction refObj = new RefreshExistingUserRecordAction();
			setResponseComments(responseComments);
			testReporter.log(LogStatus.PASS, "Provide response comments : "+responseComments);
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Provide response comments : "+responseComments);
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
	@Then("Email auto approval process Validating edited service details saved reflecting in next page$")
	public void Validate_Edit_Service_Item_Details() {
		try {
			validateEditedItemDetailsSaved();
			testReporter.log(LogStatus.PASS, "Validating edited service details saved reflecting in next page");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Validating edited service details saved reflecting in next page");
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
	@Then("^Stop Report Generation for current scenario Email auto approval process$")
	public void getResult() {
		//extent.endTest("HD_ISO_VSC_Existing_User_Record_Refresh");
		extent.endTest(testReporter);
		extent.flush();
		extent.close();
	}
	@Then("^Close the browser Email auto approval process$")
	public void flushReporter() {
		driver.close();
		driver.quit();
	}
	
	
	
	//Main functions
	public void launch() {
		System.setProperty("webdriver.chrome.driver", "driver//chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize(); // maximizes
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
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
	public void deleteExistingSI () {
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
		element = driver.findElement(By.xpath("//select[contains(@id,'listSelect')]/option[text()='HD Hotline Follow-up I-360']/preceding::option[1]"));
		scrollingFunction();
		driver.findElement(By.xpath("//select[contains(@id,'listSelect')]/option[text()='HD Hotline Follow-up I-360']")).click();
		//driver.findElement(By.xpath("//select[contains(@id,'listSelect')]/option[text()='HD All Open Service Items']")).click();
		Utils.sleep(2);
		driver.findElement(By.xpath("//div[@title='Subject']")).click();
		Utils.sleep(1);
		try {
			driver.findElement(By.xpath(".//input[@id='allBox']")).click();
			driver.findElement(By.xpath("//a[text()='Delete']")).click();
			Utils.sleep(2);
			wait.until(ExpectedConditions.alertIsPresent());
			driver.switchTo().alert().accept();
			Utils.sleep(2);
			/*Robot robot = new Robot();
			Utils.sleep(2);
			robot.keyPress(KeyEvent.VK_ENTER);
			Utils.sleep(1);
			robot.keyRelease(KeyEvent.VK_ENTER);
			Utils.sleep(2);*/
		} catch (Exception e) {
			
		}
		driver.switchTo().defaultContent();
	}
	public void fetchEmailLink () {
		driver.findElement(By.id("setupLink")).click();
		ele = By.xpath(".//input[@id='setupSearch']");
		fluentWaitForElementVisibility();
		driver.findElement(ele).sendKeys("email services");
		Utils.sleep(2);
		driver.findElement(By.xpath(".//a[text()='Email Services']")).click();
		element = driver.findElement(By.xpath("//a[contains(text(),'Email Service Name')]"));
		scrollingFunction();
		element = driver.findElement(By.xpath("//table[@class='list']/tbody/tr[2]/td[2]/a[text()='EmailRelayRoutingHandler']"));
		highlightElement();
		element.click();
		ele = By.xpath("//*[text()='Email Service: EmailRelayRoutingHandler']");
		fluentWaitForElementVisibility();
		element = driver.findElement(By.xpath(".//*[text()='Email Addresses']"));
		scrollingFunction();
		element = driver.findElement(By.xpath("//table[@class='list']/tbody/tr[1]/td[3]/a"));
		highlightElement();
		emailLink = element.getText();
		System.out.println("Fetch email link :"+emailLink);
	}
	public String randomDateTime() {
	      Calendar cal = Calendar.getInstance();
	      int year = cal.get(Calendar.YEAR);
	      int month = cal.get(Calendar.MONTH);
	      int day = cal.get(Calendar.DAY_OF_MONTH);
	      int hour = cal.get(Calendar.HOUR_OF_DAY);
	      int minute = cal.get(Calendar.MINUTE);
	      int second = cal.get(Calendar.SECOND);
	      String randomDateTime = String.valueOf(year) + String.valueOf(month) + String.valueOf(day)
	      + String.valueOf(hour) + String.valueOf(minute) + String.valueOf(second);
	      //System.out.printf("Now random "+randomDateTime);
	      subjectLine = "UATG Automation Test SI - " + randomDateTime;
	      return randomDateTime;
	}
	public void logIntoGmail (String url, String username, String passowrd) throws AWTException {
		Robot robot = new Robot();
		driver.get(url);
		driver.findElement(By.id("sign_in_username")).sendKeys(username);
		driver.findElement(By.id("sign_in_password")).sendKeys(passowrd);
		driver.findElement(By.xpath("//input[@value='Log In']")).click();
		try {
			WebDriverWait wait = new WebDriverWait (driver, 5);
			ele = By.xpath("//div[text()='zabid@acumensolutions.com']");
			wait.until(ExpectedConditions.visibilityOfElementLocated(ele));
			driver.findElement(By.xpath("//span[text()='Continue']")).click();
		} catch (Exception e) {
			
		}
		ele = By.xpath("//div[text()='Compose']");
		fluentWaitForElementVisibility();
		driver.findElement(ele).click();
		ele = By.xpath("//textarea[@name='to']");
		fluentWaitForElementVisibility();
		driver.findElement(ele).click();
		driver.findElement(ele).sendKeys(emailLink);
		robot.keyPress(KeyEvent.VK_TAB);
		Utils.sleep(1);
		robot.keyRelease(KeyEvent.VK_TAB);
		Utils.sleep(1);
		randomDateTime();
		//driver.findElement(By.xpath("//input[@name='subjectbox']")).click();
		driver.findElement(By.xpath("//input[@name='subjectbox']")).sendKeys(subjectLine);
		robot.keyPress(KeyEvent.VK_TAB);
		Utils.sleep(1);
		robot.keyRelease(KeyEvent.VK_TAB);
		Utils.sleep(1);
		driver.findElement(By.xpath("//div[contains(@class,'Am Al editable')]")).click();
		driver.findElement(By.xpath("//div[contains(@class,'Am Al editable')]")).sendKeys(subjectLine);
		driver.findElement(By.xpath("//div[text()='Send']")).click();
		Utils.sleep(4);
		ele = By.xpath("//span[text()='Message sent.']");
		fluentWaitForElementVisibility();
		driver.close();
		driver.quit();
	}
	public void hotLineDropDown () {
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
				driver.findElement(By.xpath("//select[contains(@id,'listSelect')]")).click();
				Utils.sleep(2);
				element = driver.findElement(By.xpath("//select[contains(@id,'listSelect')]/option[text()='HD Hotline Follow-up I-360']/preceding::option[1]"));
				scrollingFunction();
				driver.findElement(By.xpath("//select[contains(@id,'listSelect')]/option[text()='HD Hotline Follow-up I-360']")).click();
				//driver.findElement(By.xpath("//select[contains(@id,'listSelect')]/option[text()='HD All Open Service Items']")).click();
				Utils.sleep(2);
				driver.findElement(By.xpath("//div[@title='Subject']")).click();
				Utils.sleep(1);
				element = driver.findElement(By.xpath(".//span[text()='"+subjectLine+"']"));
				highlightElement();
				element = driver.findElement(By.xpath(".//span[text()='"+subjectLine+"']/parent::a/parent::div/parent::td/preceding-sibling::td[2]/div/a"));
				highlightElement();
				newSINo = driver.findElement(By.xpath(".//span[text()='"+subjectLine+"']/parent::a/parent::div/parent::td/preceding-sibling::td[2]/div/a")).getText(); //// need to chang
				Utils.sleep(4);
				driver.switchTo().defaultContent();
	}
	public void duplicateSIandOpenSI () throws AWTException {
		Utils.sleep(1);
		driver.findElement(By.xpath("//div[@id='navigatortab']/div[2]/div[@class='x-tab-tabmenu-right']")).click();
		Utils.sleep(1);
		driver.findElement(By.xpath("//a/span[text()='Close all primary tabs']")).click();
		Utils.sleep(4);
		try {
			driver.findElement(By.xpath("//button[contains(text(),'Don')]")).click();
			Utils.sleep(1);
		} catch (Exception e) {
			
		}
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
		driver.findElement(By.xpath("//select[contains(@id,'listSelect')]")).click();
		Utils.sleep(2);
		element = driver.findElement(By.xpath("//select[contains(@id,'listSelect')]/option[text()='My Open Service Items']/preceding::option[1]"));
		scrollingFunction();
		driver.findElement(By.xpath("//select[contains(@id,'listSelect')]/option[text()='My Open Service Items']")).click();
		//driver.findElement(By.xpath("//select[contains(@id,'listSelect')]/option[text()='HD All Open Service Items']")).click();
		Utils.sleep(2);
		driver.findElement(By.xpath("//div[@title='Subject']")).click();
		Utils.sleep(1);
		for (int itr = 0; itr < 4; itr++ ) {
		Robot robot = new Robot();
		try {
			driver.findElement(By.xpath(".//input[@id='allBox']")).click();
			driver.findElement(By.xpath(".//input[@value='Mark as Duplicate']")).click();
			Utils.sleep(2);
			wait.until(ExpectedConditions.alertIsPresent());
			driver.switchTo().alert().accept();
			Utils.sleep(2);
			wait.until(ExpectedConditions.alertIsPresent());
			Utils.sleep(2);
			robot.keyPress(KeyEvent.VK_ENTER);
			Utils.sleep(1);
			robot.keyRelease(KeyEvent.VK_ENTER);
			Utils.sleep(2);
		} catch (Exception e) {
			
		}
		driver.findElement(By.xpath(".//input[@title='Refresh']")).click();
		Utils.sleep(3);
			try {
				driver.findElement(By.xpath("//input[@value='Assign a Service Item']")).click();
				wait.until(ExpectedConditions.alertIsPresent());
				Utils.sleep(1);
				robot.keyPress(KeyEvent.VK_ENTER);
				Utils.sleep(1);
				robot.keyRelease(KeyEvent.VK_ENTER);
				Utils.sleep(3);
				element = driver.findElement(By.xpath("//table[@class='x-grid3-row-table']/tbody/descendant::a[text()='"+newSINo+"']"));
				highlightElement();
				driver.findElement(By.xpath("//table[@class='x-grid3-row-table']/tbody/descendant::a[text()='"+newSINo+"']")).click();
				break;
			} catch (Exception e) {
				
			}
		}
		//driver.findElement(By.xpath("//table[@class='x-grid3-row-table']/tbody/tr[1]/td[4]/div/a")).click();
		Utils.sleep(2);
		driver.switchTo().defaultContent();
	}
	public void editSIandSave () {
		fetchCorrectIframe(By.xpath("//a[@title='Details']/span/span[1]"));
		driver.findElement(By.xpath("//a[@title='Details']/span/span[1]")).click();
		Utils.sleep(1);
		driver.findElement(By.xpath(".//*[@id='topButtonRow']/input[3]")).click();
		Utils.sleep(2);
		driver.switchTo().defaultContent();
	}
	public void setReceiptNumberAndContactName (String receiptNo, String contactNm) throws AWTException {
		WebDriverWait wait = new WebDriverWait (driver, 8);
		fetchCorrectIframe(By.xpath("//div[@class='pbSubsection'][1]/table[@class='detailList']/tbody/tr[3]/td[2]/input"));
		driver.findElement(By.xpath("//div[@class='pbSubsection'][1]/table[@class='detailList']/tbody/tr[1]/td[2]/span/input")).sendKeys(receiptNo);
		driver.findElement(By.xpath("//div[@class='pbSubsection'][1]/table[@class='detailList']/tbody/tr[1]/td[4]/div[@class='requiredInput']/span/a/img")).click();
		Utils.sleep(2);
		System.out.println("Window Count :: "+driver.getWindowHandles().size());
		String winHandleBefore = driver.getWindowHandle();
		for(String winHandle : driver.getWindowHandles()){
			System.out.println(winHandle);
			if (!winHandleBefore.equals(winHandle)) {
		    driver.switchTo().window(winHandle);
			}
		}
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver.findElement(By.xpath("//frame[@id='resultsFrame']"))));
		driver.findElement(By.xpath(".//*[@id='Contact_body']/table/tbody/tr[2]/th/a[text()='"+contactNm+"']")).click();
		Utils.sleep(2);
		//driver.switchTo().defaultContent();
		driver.switchTo().window(winHandleBefore);
		driver.switchTo().defaultContent();
	}
	public void currentUserLogOut() {
		driver.findElement(By.xpath(".//*[@id='userNavLabel']")).click();
		Utils.sleep(1);
		driver.findElement(By.xpath("//a[text()='Logout']")).click();
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
	public void setParamSubjectAndDesAndFormType(String subject, String description) {
		//WebDriverWait wait = new WebDriverWait (driver, 15);
		//wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver.findElement(By.xpath("//div[@id='navigatortab']/div[3]/descendant::div[contains(@class,'sd_primary_container x-border-layout-ct')][3]/descendant::iframe[1]"))));
		element = driver.findElement(By.xpath("//*[contains(text(),'How Can We Help You')]"));
		scrollingFunction();
		driver.findElement(By.xpath("//div[@class='pbSubsection'][2]/table[@class='detailList']/tbody/tr[1]/td[2]/div/input")).sendKeys(subject);
		driver.findElement(By.xpath("//div[@class='pbSubsection'][2]/table[@class='detailList']/tbody/tr[2]/td[2]/textarea")).sendKeys(description);
		//selectRandomDropdownListValue(driver.findElement(By.xpath("//div[@class='pbSubsection'][2]/table[@class='detailList']/tbody/tr[2]/td[4]/div/span/span/select")));
		//selectDropdownListValue(formType, driver.findElement(By.xpath("//div[@class='pbSubsection'][2]/table[@class='detailList']/tbody/tr[2]/td[4]/div/span/span/select")));
		//driver.switchTo().defaultContent();
		Utils.sleep(2);
	}
	public void setformNumberAndFormType(String formNumber, String formType) {
		//WebDriverWait wait = new WebDriverWait (driver, 15);
		//wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver.findElement(By.xpath("//div[@id='navigatortab']/div[3]/descendant::div[contains(@class,'sd_primary_container x-border-layout-ct')][3]/descendant::iframe[1]"))));
		element = driver.findElement(By.xpath("//*[contains(text(),'How Can We Help You')]"));
		scrollingFunction();
		selectDropdownListValue(formNumber, driver.findElement(By.xpath("//div[@class='pbSubsection'][2]/table[@class='detailList']/tbody/tr[1]/td[4]/div/span/select")));
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
	public void setResponseComments(String text) throws AWTException { //String receivedDate
		//String receivedDate = "10/4/2018 9:43 AM";
		//WebDriverWait wait = new WebDriverWait (driver, 15);
		//wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver.findElement(By.xpath("//div[@id='navigatortab']/div[3]/descendant::div[contains(@class,'sd_primary_container x-border-layout-ct')][3]/descendant::iframe[1]"))));
		driver.findElement(By.xpath("//div[@class='pbSubsection'][4]/table[@class='detailList']/tbody/tr[5]/td[4]/textarea")).sendKeys(text);
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
	public void validateEditedItemDetailsSaved() {
		fetchCorrectIframe(By.xpath("//a[@title='Details']/span/span[1]"));
		driver.findElement(By.xpath("//a[@title='Details']/span/span[1]")).click();
		Utils.sleep(1);
		element = driver.findElement(By.xpath("//div[@class='pbSubsection'][2]/table[@class='detailList']/tbody/tr[2]/td[2]"));
		highlightElement();
		element = driver.findElement(By.xpath("//div[@class='pbSubsection'][2]/table[@class='detailList']/tbody/tr[1]/td[4]"));
		highlightElement();
		driver.switchTo().defaultContent();
	}
}
