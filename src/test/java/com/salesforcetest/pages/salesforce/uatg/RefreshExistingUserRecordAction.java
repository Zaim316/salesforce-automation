package com.salesforcetest.pages.salesforce.uatg;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.Calendar;
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

import com.salesforcetest.main.uatg.HD_ISO_VSC_Service_Request_Rejection_E2E;
import com.salesforcetest.mapper.srire2e.HD_ISO_VSC_New_Service_Response_Rejection_Flow;
import com.salesforcetest.pages.salesforce.SalesforceLogin;
import com.salesforcetest.shared.Constants;
import com.salesforcetest.shared.Utils;

public class RefreshExistingUserRecordAction {
	public static WebDriver driver;
	private WebElement element;
	public static String newSINo, emailLink, subjectLine;
	private By ele;
	
	public RefreshExistingUserRecordAction(WebDriver driver) {
		this.driver = driver;
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
		((JavascriptExecutor)driver).executeScript("arguments[0].textContent = arguments[1];", driver.findElement(By.xpath("//div[@aria-label='Compose text']/p[1]")), "Good day, /br /br Changes for edit /br");
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
	//Second Scenario Start
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
		Utils.sleep(8);
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
		String dontSave = "Don't Save";
		driver.findElement(By.xpath("//button[text()='Don't Save']")).click();
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
		/*driver.findElement(By.xpath("//div[@title='Subject']")).click();
		Utils.sleep(1);
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
		driver.findElement(By.xpath("//input[@value='Assign a Service Item']")).click();
		wait.until(ExpectedConditions.alertIsPresent());
		Utils.sleep(1);
		robot.keyPress(KeyEvent.VK_ENTER);
		Utils.sleep(1);
		robot.keyRelease(KeyEvent.VK_ENTER);
		Utils.sleep(2);*/
		driver.findElement(By.xpath("//table[@class='x-grid3-row-table']/tbody/tr[1]/td[4]/div/a")).click();
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
		//driver.findElement(By.xpath("//div[@class='pbSubsection'][1]/table[@class='detailList']/tbody/tr[1]/td[4]/div[@class='requiredInput']/span/input")).sendKeys(contactNm);
		Robot robot = new Robot();
		Utils.sleep(2);
		robot.keyPress(KeyEvent.VK_ENTER);
		Utils.sleep(1);
		robot.keyRelease(KeyEvent.VK_ENTER);
		Utils.sleep(2);
		driver.switchTo().defaultContent();
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
	//Below function is in progress
	public void createEditedResponse() throws AWTException {
		fetchCorrectIframe(By.xpath("//a[@title='Details']/span/span[1]"));
		driver.findElement(By.xpath("//a[@title='Feed']/span/span[1]")).click();
		Utils.sleep(1);
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
	public static void main(String[] args) throws AWTException {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver", "driver//chromedriver.exe");
		//ChromeOptions options = new ChromeOptions();
		//options.addArguments("--user-data-dir=C:\\Users\\Zaim3\\AppData\\Local\\Google\\Chrome\\User Data\\Default\\Default");
		//options.addArguments("--start-maximized");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize(); // maximizes
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		new SalesforceLogin(driver).login(Constants.salesforce_url_uatg, Constants.salesforce_username_uatg, Constants.salesforce_password_uatg);
		RefreshExistingUserRecordAction ref = new RefreshExistingUserRecordAction(driver);
		/*ref.verifyProfile();
		//ref.deleteExistingSI();
		ref.fetchEmailLink();
		ref.logIntoGmail("https://acumensolutions-com.clearlogin.com/login", "zabid", "Mohammad1988");
		ref.launch();
		ref.searchHDISOVSCitems("One HD Supervisor");
		ref.logInAsInternalUser("One HD Supervisor");
		ref.setPercentage("5");
		ref.hotLineDropDown();
		ref.currentUserLogOut();*/
		ref.searchHDISOVSCitems("HD ISO VSC");
		ref.logInAsInternalUser("HD ISO VSC");
		ref.duplicateSIandOpenSI();
		ref.editSIandSave();
		ref.setReceiptNumberAndContactName("WAC1690258857", "EDVARD EDOUARD");
		/*ref.searchHDISOVSCitems("HD ISO VSC");
		ref.logInAsInternalUser("HD ISO VSC");
		ref.customerSearch("A200453283");
		ref.customerRefresh();
		ref.clickOnRecordItemFromOwnerPage("WAC1690258857");
		//ref.searchForApplicationRecordWithAppNo("A200453283");
		//ref.openApplicationRecordFromGlobalSearchResultPanel("WAC1690258857");
		ref.createNewServiceItemParam("WAC1690258857");*/
		ref.setOrgName("Test Organi");
		//ref.setEmail("zabid@acumensolutions.com");
		ref.selectSenderType("ASC");
		ref.setformNumberAndFormType("I192", "I-192 Advance Permission to Enter as Nonimmigrant");
		//ref.setRandomSubjectAndDesAndFormType();
		ref.setParamCategoryAndKind("AWA Case", "AWA Concerns Identified", "New Comments");
		ref.selectSIOrigin("Email");
		ref.selectInitialQueue("VAWA_HotlineFollowupI360");
		//ref.setReceivedDate("10/4/2018 9:43 AM");
		ref.setResponseComments("New Responses");
		ref.clickOnSaveSI();
		//
		/*ref.fetchServiceItemNo();
		ref.createNewResponse();
		ref.verifyNewResponseStatus();
		ref.currentUserLogOut();
		
		ref.searchHDISOVSCitems("One HD Supervisor");
		ref.logInAsInternalUser("One HD Supervisor");
		ref.selectRequiredDropdownlist();
		ref.rejectServiceRqst();
		ref.validateRejectedResponse();
		ref.currentUserLogOut();
		ref.searchHDISOVSCitems("HD ISO VSC");
		ref.logInAsInternalUser("HD ISO VSC");
		ref.searchWithStoredItem();
		ref.editRejectedSI();
		ref.currentUserLogOut();*/
		/*ref.searchHDISOVSCitems("One HD Supervisor");
		ref.logInAsInternalUser("One HD Supervisor");
		ref.setPercentage("5");
		ref.currentUserLogOut();*/
		/*ref.selectRequiredDropdownlist();
		ref.approveServiceRqst();
		ref.validateApprovedResponse();*/
		//driver.close();
		//driver.quit();
	}
}
