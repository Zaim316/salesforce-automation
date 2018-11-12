package com.salesforcetest.pages.salesforce;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.salesforcetest.shared.Constants;
import com.salesforcetest.shared.Utils;

public class SalesforceLogin {

	private String baseUrl = Constants.salesforce_url;

	private WebDriver driver;

	@FindBy(id = "username")
	WebElement userNameTextBox;

	@FindBy(id = "password")
	WebElement passwordTextBox;
	
	@FindBy(id = "Login")
	WebElement loginButton;
	
	public SalesforceLogin(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.driver = driver;
	}

	public void login(String baseUrl, String userName, String pass) {
		this.baseUrl = baseUrl;
		login(userName, pass);
	}
	
	public void login(String userName, String pass) {
		try {
		driver.get(baseUrl);
		
		userNameTextBox.sendKeys(userName);
		
		passwordTextBox.sendKeys(pass);
		
		loginButton.click();
		
		Utils.sleep(3);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public void logout() {
		Utils.sleep(2);
		
		driver.findElement(By.xpath("//*[@id=\"oneHeader\"]/div[2]/span/ul/li[9]/button")).click();
		
		Utils.sleep(2);
		
		driver.findElement(By.linkText("Log Out")).click();
		
		Utils.sleep(3);
	}
}
