package com.salesforcetest.pages.salesforce;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.salesforcetest.shared.Constants;
import com.salesforcetest.shared.Utils;

public class AccountScenario {

	private WebDriver driver;

	@FindBy(linkText = "Accounts")
	WebElement accountTab;
	
	@FindBy(linkText = "New")
	WebElement newButton;
	
	public AccountScenario(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.driver = driver;
	}
	
	public void create_internal_account() {
		open_account_tab_and_click_new();
		
		//Clicking internal contact
		select_radio_and_next(1);
		
		fill_account_form_and_save("Test Internal Account "+Constants.getRamdomId());
	}
	
	public void create_internal_contact() {
		driver.findElements(By.className("forceRelatedListSingleContainer")).get(0).findElement(By.cssSelector("a[title='New']")).click();
		
		Utils.sleep(3);
		
		select_radio_and_next(1);
		
		Utils.sleep(3);
		
		fill_contact_form_and_save();
		
	}
	
	public void create_external_account() {
		open_account_tab_and_click_new();
		
		//select nothing . just press next
		select_radio_and_next(0);
		
		fill_account_form_and_save("Test External Account "+Constants.getRamdomId());
	}
	
	public void create_external_contact() {
		driver.findElements(By.className("forceRelatedListSingleContainer")).get(0).findElement(By.cssSelector("a[title='New']")).click();

		Utils.sleep(3);
		
		select_radio_and_next(0);
		
		Utils.sleep(3);
		
		fill_contact_form_and_save();
	}
	
	/**
	 * common method 
	 */
	private void open_account_tab_and_click_new() {
		Utils.sleep(3);
		
		//click Account tab button
		accountTab.click();
		
		Utils.sleep(3);
		
		//click 'New' buton under Account
		newButton.click();
		
		Utils.sleep(3);
	}
	
	/**
	 * Account form is similar to both account types. So, making it common
	 */
	private void fill_account_form_and_save(String actionName) {
		
		//Fill Account Name
		WebElement accNameLabel = driver.findElement(By.xpath("//label[.//span[text()='Account Name']]"));
		
		driver.findElement(By.id(accNameLabel.getAttribute("for"))).sendKeys(actionName);
		
		//Press save
		List<WebElement> footerButtons = driver.findElement(By.className("modal-footer")).findElements(By.tagName("button"));
		footerButtons.get(2).click();
		
		Utils.sleep(3);
	}
	
	/**
	 * Contact form is similar to both account types. So, making it common
	 */
	private void fill_contact_form_and_save() {
		
		//Fill first name
		WebElement firstNameLabel = driver.findElement(By.xpath("//label[.//span[text()='First Name']]"));
		
		driver.findElement(By.id(firstNameLabel.getAttribute("for"))).sendKeys("Zaim");
		
		//Fill Last Name
		WebElement lastNameLabel = driver.findElement(By.xpath("//label[.//span[text()='Last Name']]"));
		
		driver.findElement(By.id(lastNameLabel.getAttribute("for"))).sendKeys("Abid");
		
		//Fill Email
		WebElement emailLabel = driver.findElement(By.xpath("//label[.//span[text()='Email']]"));
		
		driver.findElement(By.id(emailLabel.getAttribute("for"))).sendKeys("zabid"+Constants.getRamdomId()+"@acumensolutions.com");
		
		Utils.sleep(2);
		
		//Press save
		List<WebElement> footerButtons = driver.findElement(By.className("modal-footer")).findElements(By.tagName("button"));
		footerButtons.get(2).click();
		
		Utils.sleep(3);
	}
	
	/**
	 * common method
	 * @param num
	 */
	private void select_radio_and_next(int num) {
		//Clicking internal contact
		List<WebElement> radioList = driver.findElements(By.cssSelector("input[type='radio']"));
		
		radioList.get(num).findElement(By.xpath("following-sibling::span")).click();
		
		Utils.sleep(3);
		
		WebElement footer = driver.findElement(By.className("forceChangeRecordTypeFooter"));
		
		footer.findElements(By.tagName("button")).get(1).click();
		
		Utils.sleep(2);
	}
	
}
