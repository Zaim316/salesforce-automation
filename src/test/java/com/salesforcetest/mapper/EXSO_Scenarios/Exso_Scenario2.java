package com.salesforcetest.mapper.EXSO_Scenarios;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.salesforcetest.pages.salesforce.uatg.GetScreenShot;
import com.salesforcetest.shared.Constants;
import com.salesforcetest.shared.Utils;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class Exso_Scenario2 {
	private String baseUrl = Constants.salesforce_url;
	public static WebDriver driver8;
	private WebElement element;
	public static String aaoSINm,screenShotPath;
	private By ele;
	String workingDir = System.getProperty("user.dir");
	static ExtentReports extent8;
	static ExtentTest testReporter8;
	@Given("^Launching the EXSO app and logging in as admin for AAO Service Item scenario2 validation$")
	public void init_1() throws IOException {
		extent8 = new ExtentReports(workingDir+"\\test-report\\EXSO_AAOContact1_Scenario"+randomDateTime1()+".html", true);
		testReporter8 = extent8.startTest("EXSO Regression Flow Scenario 12 End to End Validation For AAO Contact 1 Service Item Validation");
		try {
			init();
			testReporter8.log(LogStatus.PASS, "User logs in successfully to Privacy portal");
		} catch (Exception e) {
			testReporter8.log(LogStatus.FAIL, "User logs in successfully to Privacy portal");
			screenShotPath = GetScreenShot.capture(driver8);
			testReporter8.log(LogStatus.INFO, "Snapshot : " +testReporter8.addScreenCapture(screenShotPath));
			getResult();
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
	@Then("^Logging in as AAO POD user and Create a New service item$")
	public void Create_New_SI_With_POD() throws IOException {
		try {
			createNewSIWithAAOusr();
			testReporter8.log(LogStatus.PASS, "Logged in as AAO Pod user and Create a new Service Item");
		} catch (Exception e) {
			testReporter8.log(LogStatus.FAIL, "Logged in as AAO Pod user and Create a new Service Item");
			screenShotPath = GetScreenShot.capture(driver8);
			testReporter8.log(LogStatus.INFO, "Snapshot : " +testReporter8.addScreenCapture(screenShotPath));
			getResult();
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
	@Then("^Validate the newly created POD user SI and fetch the SI number$")
	public void Validate_New_SI_With_POD() throws IOException {
		try {
			validateNewAAOSI();
			testReporter8.log(LogStatus.PASS, "Validate the newly created POD user SI and fetch the SI number: "+aaoSINm);
		} catch (Exception e) {
			testReporter8.log(LogStatus.FAIL, "Validate the newly created POD user SI and fetch the SI number: "+aaoSINm);
			screenShotPath = GetScreenShot.capture(driver8);
			testReporter8.log(LogStatus.INFO, "Snapshot : " +testReporter8.addScreenCapture(screenShotPath));
			getResult();
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
	@Then("^Submit the newly created SI for approval$")
	public void Submit_New_SI_With_POD() throws IOException {
		try {
			submitForApproval();
			testReporter8.log(LogStatus.PASS, "Submit the newly created SI for approval and log out as POD user.");
		} catch (Exception e) {
			testReporter8.log(LogStatus.FAIL, "Submit the newly created SI for approval and log out as POD user.");
			screenShotPath = GetScreenShot.capture(driver8);
			testReporter8.log(LogStatus.INFO, "Snapshot : " +testReporter8.addScreenCapture(screenShotPath));
			getResult();
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
	@Then("^Log in as EXSO Manager and validate the POD user created SI$")
	public void Validate_New_SI_With_EXSO_usr() throws IOException {
		try {
			logInEXSOAndValidate();
			testReporter8.log(LogStatus.PASS, "Log in as EXSO Manager and validate the POD user created SI with all the expected buttons should be available as normal SI.");
		} catch (Exception e) {
			testReporter8.log(LogStatus.FAIL, "Log in as EXSO Manager and validate the POD user created SI with all the expected buttons should be available as normal SI.");
			screenShotPath = GetScreenShot.capture(driver8);
			testReporter8.log(LogStatus.INFO, "Snapshot : " +testReporter8.addScreenCapture(screenShotPath));
			getResult();
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
	@Then("^Change the SI status to Clearance Review and Close and Archive and validate the Si type$")
	public void Change_Stat_of_SI_With_EXSO_usr() throws IOException {
		try {
			statusChangeCloseAndArchieve();
			testReporter8.log(LogStatus.PASS, "Change the SI status to Clearance Review and Close and Archive and validate the Si type");
		} catch (Exception e) {
			testReporter8.log(LogStatus.FAIL, "Change the SI status to Clearance Review and Close and Archive and validate the Si type");
			screenShotPath = GetScreenShot.capture(driver8);
			testReporter8.log(LogStatus.INFO, "Snapshot : " +testReporter8.addScreenCapture(screenShotPath));
			getResult();
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
	@Then("^EXSO Stop Report Generation for current scenario EXSO Assignment Scenario12 End to End Scenario1$")
	public void getResult() {
		extent8.endTest(testReporter8);
		extent8.flush();
		extent8.close();
	}
	@Then("^EXSO Scenario12 Close the browser Email auto approval process$")
	public void flushReporter_For_IPO() {
		driver8.close();
		driver8.quit();
	}
//**************************************************************Main Functions**********************************************************************	
	public String randomDateTime1() {
	      Calendar cal = Calendar.getInstance();
	      int year = cal.get(Calendar.YEAR);
	      int month = cal.get(Calendar.MONTH);
	      int day = cal.get(Calendar.DAY_OF_MONTH);
	      int hour = cal.get(Calendar.HOUR_OF_DAY);
	      int minute = cal.get(Calendar.MINUTE);
	      int second = cal.get(Calendar.SECOND);
	      String randomDateTime = String.valueOf(year) + String.valueOf(month) + String.valueOf(day)
	      + String.valueOf(hour) + String.valueOf(minute) + String.valueOf(second);
	      randomDateTime = "" + randomDateTime;
	      return randomDateTime;
	}
	public void init() {
		System.setProperty("webdriver.chrome.driver", "driver//chromedriver.exe");
		Map<String, Object> prefs = new HashMap<String, Object>();
		prefs.put("profile.default_content_setting_values.notifications", 2);

        // Create object of ChromeOption class
		ChromeOptions options = new ChromeOptions();

        // Set the experimental option
		options.setExperimentalOption("prefs", prefs);
		driver8 = new ChromeDriver(options);
		driver8.manage().window().maximize(); // maximizes
		driver8.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);// implicit wait
		login();
	}
	public static void open_another_tab(WebDriver driver8) {
		// Creating the JavascriptExecutor interface object by Type casting
		JavascriptExecutor js = (JavascriptExecutor) driver8;
		js.executeScript("window.open('about:blank','_blank');");

		ArrayList<String> tabs = new ArrayList<String>(driver8.getWindowHandles());
		driver8.switchTo().window(tabs.get(1));
	}
	public void login() {
		//try {
		driver8.get(baseUrl);
		Utils.sleep(2);
		driver8.findElement(By.xpath(".//*[@id='username']")).sendKeys(Constants.salesforce_pstaff2_username_Admin);
		Utils.sleep(1);
		driver8.findElement(By.xpath(".//*[@id='password']")).sendKeys(Constants.salesforce_pstaff2_password_Admin);
		Utils.sleep(1);
		driver8.findElement(By.xpath(".//*[@id='Login']")).click();
		
		Utils.sleep(3);
		/*} catch(Exception e) {
			e.printStackTrace();
		}*/
	}
	@SuppressWarnings("rawtypes")
	public Wait fluentWaitFunctionality(WebDriver driver8)
	{
		@SuppressWarnings({ "unchecked", "deprecation" })
		Wait fwait = new FluentWait(driver8)
		 
	    .withTimeout(10, TimeUnit.SECONDS)
	 
	    .pollingEvery(1, TimeUnit.SECONDS)
	 
	    .ignoring(NoSuchElementException.class);
		return fwait;
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void fluentWaitForElementVisibility()
	{	
		Wait fwait = fluentWaitFunctionality(driver8);
		try {
		fwait.until(ExpectedConditions.visibilityOfElementLocated(ele));
		}
		catch(Exception e) {
		}
	}
	public void logInAsPodUser() {
		//driver8.findElement(By.xpath("//span[text()='App Launcher']/parent::*")).click();
		driver8.navigate().to("https://uscis2--uat2.lightning.force.com/lightning/o/Contact/home");
		Utils.sleep(4);
		//driver8.findElement(By.xpath("//span[text()='Contacts']/parent::*/parent::a")).click();
		//Actions actObj = new Actions(driver8);
		//actObj.moveToElement(driver8.findElements(By.xpath("//li/a[@title='Contacts']")).get(0)).doubleClick().build().perform();
		driver8.findElement(By.xpath("//input[@title='Search Contacts and more']")).click();
		Utils.sleep(2);
		driver8.findElement(By.xpath("//input[@title='Search Contacts and more']")).sendKeys("AAO Contact 1");
		Utils.sleep(4);
		driver8.findElement(By.xpath("//div[@class='listContent']/ul/li/descendant::span[@title='AAO Contact 1']/parent::div/parent::a")).click();
		Utils.sleep(2);
		ele = By.xpath("//span[text()='AAO Contact 1']/parent::div/parent::*");
		fluentWaitForElementVisibility();
		driver8.findElement(By.xpath("//a[@title='Show 8 more actions']")).click();
		Utils.sleep(1);
		driver8.findElement(By.xpath("//a[@title='Log in to Community as User']")).click();
		ele = By.xpath("//span[text()='USCIS Corr Mgmt']/parent::*");
		fluentWaitForElementVisibility();
		Utils.sleep(1);
		driver8.findElement(By.xpath("//span[text()='USCIS Corr Mgmt']/parent::*")).click();
	}
	public void scrollingFunction() {
		JavascriptExecutor js = (JavascriptExecutor)driver8;
		js.executeScript("arguments[0].scrollIntoView();", element);
		Utils.sleep(1);
	}
	public void highlightElement() {
		JavascriptExecutor js = (JavascriptExecutor) driver8;
        js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", element);
        Utils.sleep(2);
	}
	//*************************************************************EXSO 2nd SCENARIO********************************************************************
		public void createNewSIWithAAOusr() {
			logInAsPodUser();
			ele = By.xpath("//a[@title='Service Items']/parent::*");
			fluentWaitForElementVisibility();
			Utils.sleep(1);
			driver8.findElement(By.xpath("//a[@title='Service Items']/parent::*")).click();
			Utils.sleep(2);
			driver8.findElement(By.xpath("//a[@title='New']/parent::li")).click();
			ele = By.xpath("//span[text()='Assignment Manager Memo']");
			fluentWaitForElementVisibility();
			Utils.sleep(1);
			element = driver8.findElement(ele);
			highlightElement();
			driver8.findElement(By.xpath("//span[text()='Assignment Manager Memo']/parent::*/preceding-sibling::div/span")).click();
			Utils.sleep(2);
			driver8.findElement(By.xpath("//span[text()='Next']/parent::button")).click();
			Utils.sleep(1);
			ele = By.xpath("//*[contains(text(),'New Service Item: Assignment Manager Memo')]");
			fluentWaitForElementVisibility();
			Utils.sleep(1);
			element = driver8.findElement(ele);
			highlightElement();
			driver8.findElement(By.xpath("//span[text()='Service Item Origin']/parent::*/following-sibling::div/descendant::a")).click();
			Utils.sleep(2);
			driver8.findElement(By.xpath("//a[@title='Program Office Directorate']/parent::li")).click();
			Utils.sleep(1);
			driver8.findElement(By.xpath("//button[@title='Save & New']/following-sibling::button")).click();
			Utils.sleep(1);
			ele = By.xpath("//span[contains(@class,'toastMessage')]/parent::div");
			fluentWaitForElementVisibility();
			Utils.sleep(1);
			driver8.navigate().refresh();
			Utils.sleep(2);
			ele = By.xpath("//span[@title='Service Item Number']");
			fluentWaitForElementVisibility();
			Utils.sleep(1);
		}
		public void validateNewAAOSI () throws IOException {
			aaoSINm = driver8.findElement(By.xpath("//span[@title='Service Item Number']/following-sibling::div/descendant::span")).getText();
			try {
				element = driver8.findElement(By.xpath("//span[@title='Contact Name']/parent::div/descendant::a[contains(text(),'AAO Contact 1')]"));
				testReporter8.log(LogStatus.PASS, "Contact Name is updated as AAO Contact 1");
				highlightElement();
			} catch (Exception e) {
				testReporter8.log(LogStatus.FAIL, "Contact Name is not updated as AAO Contact 1");
				screenShotPath = GetScreenShot.capture(driver8);
				testReporter8.log(LogStatus.INFO, "Snapshot : " +testReporter8.addScreenCapture(screenShotPath));
			}
			try {
				element = driver8.findElement(By.xpath("//span[text()='Program Office Directorate']/parent::*"));
				highlightElement();
				testReporter8.log(LogStatus.PASS, "Origin Type is selected as Program Office Directorate");
			} catch (Exception e) {
				testReporter8.log(LogStatus.FAIL, "Origin Type is not selected as Program Office Directorate");
				screenShotPath = GetScreenShot.capture(driver8);
				testReporter8.log(LogStatus.INFO, "Snapshot : " +testReporter8.addScreenCapture(screenShotPath));
			}
			try {
				element = driver8.findElement(By.xpath("//span[text()='Service Item Owner']/parent::div/following-sibling::div/descendant::a[contains(text(),'AAO Contact 1')]"));
				highlightElement();
				testReporter8.log(LogStatus.PASS, "Service Item Owner is updated as AAO Contact 1");
			} catch (Exception e) {
				testReporter8.log(LogStatus.FAIL, "Service Item Owner is not updated as AAO Contact 1");
				screenShotPath = GetScreenShot.capture(driver8);
				testReporter8.log(LogStatus.INFO, "Snapshot : " +testReporter8.addScreenCapture(screenShotPath));
			}
		}
		public void logOutPodUser_Parallel() {
			try {
			driver8.findElement(By.xpath("//span[text()='AAO Contact 1']/parent::a")).click();
			} catch (Exception e) {
				driver8.findElement(By.xpath("//span[text()='PVY Contact 1']/parent::a")).click();	
			}
			Utils.sleep(1);
			driver8.findElement(By.xpath("//a[text()='Logout']/parent::*")).click();
			ele = By.xpath(".//*[@id='oneHeader']/div[2]/span/div[2]/ul/li[8]/span/button");
			fluentWaitForElementVisibility();
			Utils.sleep(1);
		}
		public void submitForApproval() throws IOException {
			driver8.findElement(By.xpath("//div[@title='Submit for Approval']/parent::*/parent::*")).click();
			Utils.sleep(1);
			ele = By.xpath("//span[text()='Comments']/parent::*/following-sibling::textarea");
			fluentWaitForElementVisibility();
			Utils.sleep(1);
			driver8.findElement(ele).sendKeys("Submitting it for approval.");
			driver8.findElement(By.xpath("//span[text()='Submit']/parent::button")).click();
			Utils.sleep(6);
			testReporter8.log(LogStatus.PASS, "Service Item is successfully submitted for approval.");
			driver8.navigate().refresh();
			Utils.sleep(2);
			ele = By.xpath("//span[@title='Service Item Number']");
			fluentWaitForElementVisibility();
			Utils.sleep(1);
			try {
				element = driver8.findElement(By.xpath("//span[text()='Service Item Owner']/parent::div/following-sibling::div/descendant::span[contains(text(),'Operations Queue')]"));
				highlightElement();
				testReporter8.log(LogStatus.PASS, "Service Item Owner is updated as Operations Queue");
			} catch (Exception e) {
				testReporter8.log(LogStatus.WARNING, "Service Item Owner is not updated as Operations Queue");
				screenShotPath = GetScreenShot.capture(driver8);
				testReporter8.log(LogStatus.INFO, "Snapshot : " +testReporter8.addScreenCapture(screenShotPath));
			}
			try {
				element = driver8.findElement(By.xpath("//span[text()='Service Item Record Type']/parent::div/following-sibling::div/descendant::span[contains(text(),'Memo')]"));
				highlightElement();
				testReporter8.log(LogStatus.PASS, "Service Item Record Type is updated as Memo");
			} catch (Exception e) {
				testReporter8.log(LogStatus.FAIL, "Service Item Record Type is not updated as Memo");
				screenShotPath = GetScreenShot.capture(driver8);
				testReporter8.log(LogStatus.INFO, "Snapshot : " +testReporter8.addScreenCapture(screenShotPath));
			}
			logOutPodUser_Parallel();
		}
		public void internalUserLogin_Parallel(String internalUserNm) {
			//String internalUserNm = "Privacy Staff 2";
			WebDriverWait wait = new WebDriverWait (driver8, 8);
			Utils.sleep(2);
			try {
				driver8.findElement(By.xpath(".//*[@id='oneHeader']/div[2]/span/ul/li[6]/div")).click(); // old env
			} catch (Exception e) {
				driver8.findElement(By.xpath(".//*[@id='oneHeader']/div[2]/span/div/ul/li[6]/div")).click(); //QA env
			}
			Utils.sleep(2);
			driver8.findElement(By.xpath(".//*[@id='related_setup_app_home']/a/div[1]/div[1]/span/span[2]")).click();
			Utils.sleep(2);
			ArrayList<String> tabs = new ArrayList<String>(driver8.getWindowHandles());
			try {
			driver8.switchTo().window(tabs.get(2));
			driver8.switchTo().window(tabs.get(1));
			//System.out.println("I am here3.");
			driver8.close();
			Utils.sleep(2);
			driver8.switchTo().window(tabs.get(2));
			} catch (Exception e) {
				driver8.switchTo().window(tabs.get(0));
				driver8.close();
				Utils.sleep(2);
				driver8.switchTo().window(tabs.get(1));
			}
			try {
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='viewport']/div[2]/div[2]/descendant::input[@placeholder='Quick Find']")));
			} catch (Exception e) {
				
			}
			driver8.findElement(By.xpath("//input[@title='Search Setup']")).sendKeys(internalUserNm);
			Utils.sleep(4);
			driver8.findElement(By.xpath("//div[@class='listContent']/ul/li/descendant::span[@title='"+internalUserNm+"']/parent::div/parent::a")).click();
			Utils.sleep(4);
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver8.findElement(By.xpath("//iframe"))));
			driver8.findElement(By.xpath("//table[@class='detailList']/tbody/tr[8]/td[3]")).click();
			Utils.sleep(1);
			driver8.findElement(By.xpath(".//*[@id='topButtonRow']/input[@name='login' and contains(@value,'Login')]")).click();
			Utils.sleep(2);
			driver8.switchTo().defaultContent();
			for(int icount=0; icount<4; icount++) {
				try {
					//driver8.findElement(By.xpath(".//*[@id='oneHeader']/descendant::*[@data-id='Case']/a")).getText();
					//driver8.findElement(By.xpath(".//*[@id='oneHeader']/descendant::*[@role='listitem'][4]")).getText();
					driver8.findElement(By.xpath("//a[@title='Service Items']/parent::*")).getText();
					break;
				} catch (Exception e) {
					driver8.get(driver8.getCurrentUrl());
					driver8.navigate().refresh();
					Utils.sleep(4);
				}
			}
		}
		public void openSIfromMainSearch(String siNm) {
			driver8.findElement(By.xpath("//input[@title='Search Salesforce']")).click();
			Utils.sleep(2);
			driver8.findElement(By.xpath("//input[@title='Search Salesforce']")).sendKeys(siNm);
			Utils.sleep(4);
			driver8.findElement(By.xpath("//div[@class='listContent']/ul/li/descendant::span[@title='"+siNm+"']/parent::div/parent::a")).click();
			Utils.sleep(2);
			ele = By.xpath("//span[@title='Service Item Number']");
			fluentWaitForElementVisibility();
		}
		public void logInEXSOAndValidate() throws IOException {
			internalUserLogin_Parallel("EXSO Service Item Manager");
			openSIfromMainSearch(aaoSINm);
			try {
				element = driver8.findElement(By.xpath("//span[text()='Service Item Owner']/parent::div/following-sibling::div/descendant::span[contains(text(),'Operations Queue')]"));
				highlightElement();
				testReporter8.log(LogStatus.PASS, "Service Item Owner is updated as Operations Queue");
			} catch (Exception e) {
				testReporter8.log(LogStatus.FAIL, "Service Item Owner is not updated as Operations Queue");
				screenShotPath = GetScreenShot.capture(driver8);
				testReporter8.log(LogStatus.INFO, "Snapshot : " +testReporter8.addScreenCapture(screenShotPath));
			}
			try {
				element = driver8.findElement(By.xpath("//span[text()='Service Item Record Type']/parent::div/following-sibling::div/descendant::span[contains(text(),'Memo')]"));
				highlightElement();
				testReporter8.log(LogStatus.PASS, "Service Item Record Type is updated as Memo");
			} catch (Exception e) {
				testReporter8.log(LogStatus.FAIL, "Service Item Record Type is not updated as Memo");
				screenShotPath = GetScreenShot.capture(driver8);
				testReporter8.log(LogStatus.INFO, "Snapshot : " +testReporter8.addScreenCapture(screenShotPath));
			}
			try {
				element = driver8.findElement(By.xpath("//div[@title='Create Assignments']/parent::a/parent::li"));
				highlightElement();
				testReporter8.log(LogStatus.PASS, "Create Assignments button is present.");
			} catch (Exception e) {
				testReporter8.log(LogStatus.FAIL, "Create Assignments button is not present.");
				screenShotPath = GetScreenShot.capture(driver8);
				testReporter8.log(LogStatus.INFO, "Snapshot : " +testReporter8.addScreenCapture(screenShotPath));
			}
			try {
				element = driver8.findElement(By.xpath("//div[@title='Create FYI Records']/parent::a/parent::li"));
				highlightElement();
				testReporter8.log(LogStatus.PASS, "Create FYI Records button is present.");
			} catch (Exception e) {
				testReporter8.log(LogStatus.FAIL, "Create FYI Records button is not present.");
			}
			try {
				element = driver8.findElement(By.xpath("//div[@title='Generate RCA']/parent::a/parent::li"));
				highlightElement();
				testReporter8.log(LogStatus.PASS, "Generate RCA button is present.");
			} catch (Exception e) {
				testReporter8.log(LogStatus.FAIL, "Generate RCA button is not present.");
			}
			try {
				element = driver8.findElement(By.xpath("//div[@title='Edit File Information']/parent::a/parent::li"));
				highlightElement();
				testReporter8.log(LogStatus.PASS, "Edit File Information button is present.");
			} catch (Exception e) {
				testReporter8.log(LogStatus.FAIL, "Edit File Information button is not present.");
			}
		}
		public void statusChangeCloseAndArchieve () throws IOException {
			Utils.sleep(2);
			Actions actObj = new Actions(driver8);
			try {
			actObj.moveToElement(driver8.findElements(By.xpath("//span[contains(text(),'New')]/parent::span/parent::div/button")).get(0)).doubleClick().build().perform();
			Utils.sleep(2);
			driver8.findElements(By.xpath("//span[text()='Status']/parent::span/following-sibling::div/descendant::a[@class='select']")).get(0).click();
			Utils.sleep(2);
			element = driver8.findElement(By.xpath("//a[@title='Clearance']"));
			scrollingFunction();
			Utils.sleep(1);
			driver8.findElement(By.xpath("//a[@title='Clearance Review']")).click();
			Utils.sleep(1);
			} catch (Exception e) {
				
			}
			Utils.sleep(1);
			element = driver8.findElements(By.xpath("//span[text()='Service Item Record Type']")).get(1);
			scrollingFunction();
			Utils.sleep(2);
			driver8.findElement(By.xpath("//span[text()='EXSO Section']/parent::*/following-sibling::div/descendant::a")).click();
			Utils.sleep(2);
			driver8.findElement(By.xpath("//a[contains(text(),'Internal Section')]")).click();
			Utils.sleep(1);
			driver8.findElement(By.xpath("//button[@title='Save']")).click();
			Utils.sleep(4);
			driver8.navigate().refresh();
			Utils.sleep(1);
			ele = By.xpath("//span[text()='Internal Section']");
			fluentWaitForElementVisibility();
			Utils.sleep(2);
			ele = By.xpath("//span[@title='Status']/following-sibling::div/descendant::span[contains(text(),'Clearance Review')]");
			fluentWaitForElementVisibility();
			try {
			element = driver8.findElement(ele);
			highlightElement();
			testReporter8.log(LogStatus.PASS, "Status is updated as Clearance Review");
			} catch (Exception e) {
				testReporter8.log(LogStatus.FAIL, "Status is not updated as Clearance Review");
				screenShotPath = GetScreenShot.capture(driver8);
				testReporter8.log(LogStatus.INFO, "Snapshot : " +testReporter8.addScreenCapture(screenShotPath));
			}
			
			//Closing here
			Utils.sleep(1);
			Actions actObj1 = new Actions(driver8);
			try {
			actObj1.moveToElement(driver8.findElements(By.xpath("//span[contains(text(),'Clearance Review')]/parent::span/parent::div/button")).get(0)).doubleClick().build().perform();
			Utils.sleep(2);
			driver8.findElements(By.xpath("//span[text()='Status']/parent::span/following-sibling::div/descendant::a[@class='select']")).get(0).click();
			Utils.sleep(2);
			element = driver8.findElements(By.xpath("//a[@title='Closed']")).get(0);
			scrollingFunction();
			Utils.sleep(2);
			driver8.findElement(By.xpath("//a[@title='Closed']")).click();
			Utils.sleep(1);
			driver8.findElement(By.xpath("//button[@title='Save']")).click();
			} catch (Exception e) {
				
			}
			ele = By.xpath("//span[text()='Internal Section']");
			fluentWaitForElementVisibility();
			Utils.sleep(2);
			driver8.navigate().refresh();
			Utils.sleep(2);
			driver8.navigate().refresh();
			Utils.sleep(2);
			ele = By.xpath("//span[@title='Status']/following-sibling::div/descendant::span[contains(text(),'Closed')]");
			fluentWaitForElementVisibility();
			try {
			element = driver8.findElement(ele);
			highlightElement();
			testReporter8.log(LogStatus.PASS, "Status is updated as Closed");
			} catch (Exception e) {
				testReporter8.log(LogStatus.FAIL, "Status is not updated as Closed");
				screenShotPath = GetScreenShot.capture(driver8);
				testReporter8.log(LogStatus.INFO, "Snapshot : " +testReporter8.addScreenCapture(screenShotPath));
			}
			
			//Archive Here
			element = driver8.findElements(By.xpath("//span[text()='POD Service Item Input']/parent::button")).get(0);
			scrollingFunction();
			Utils.sleep(2);
			actObj1.moveToElement(driver8.findElements(By.xpath("//span[contains(text(),'Archive Service Item')]/parent::div/parent::div/div[2]/button")).get(0)).doubleClick().build().perform();
			Utils.sleep(2);
			driver8.findElement(By.xpath("//span[contains(text(),'Archive Service Item')]/parent::label/following-sibling::input")).click();
			Utils.sleep(1);
			try {
				element = driver8.findElement(By.xpath("//span[contains(text(),'Archive Service Item')]/parent::label/parent::*"));
				highlightElement();
				testReporter8.log(LogStatus.PASS, "Archive Service Item is updated.");
			} catch (Exception e) {
				testReporter8.log(LogStatus.FAIL, "Archive Service Item is not updated.");
				screenShotPath = GetScreenShot.capture(driver8);
				testReporter8.log(LogStatus.INFO, "Snapshot : " +testReporter8.addScreenCapture(screenShotPath));
			}
			driver8.findElement(By.xpath("//button[@title='Save']")).click();
			Utils.sleep(4);
			driver8.navigate().refresh();
			Utils.sleep(2);
			driver8.navigate().refresh();
			Utils.sleep(2);
			ele = By.xpath("//span[text()='Internal Section']");
			fluentWaitForElementVisibility();
			Utils.sleep(1);
			try {
				element = driver8.findElement(By.xpath("//span[text()='Service Item Record Type']/parent::div/following-sibling::div/descendant::span[contains(text(),'Archive')]"));
				highlightElement();
				testReporter8.log(LogStatus.PASS, "Service Item Record Type is updated as Archive.");
			} catch (Exception e) {
				testReporter8.log(LogStatus.FAIL, "Service Item Record Type is not updated as Archive.");
			}
		}
	//**************************************************************************************************************************************************
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Exso_Scenario2 ref = new Exso_Scenario2();
		ref.init();
		//aaoSINm = "00019405";
		//ref.createNewSIWithAAOusr();
		//ref.validateNewAAOSI();
		//ref.submitForApproval();
		ref.logInEXSOAndValidate();
		ref.statusChangeCloseAndArchieve();
	}

}
