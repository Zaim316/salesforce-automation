package com.salesforcetest.pages.salesforce;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.testng.Assert;

import com.salesforcetest.shared.Utils;

public class SupervisorAction {

	private WebDriver driver;

	@FindBy(className = "pending-approval-card-row-button")
	List<WebElement> actionBtnList;

	@FindBy(linkText = "Approve")
	WebElement approveButton;

	@FindBy(linkText = "Reject")
	WebElement rejectButton;

	@FindBy(linkText = "Reassign")
	WebElement reassignButton;

	@FindBy(className = "modal-footer")
	WebElement modalFooter;

	@FindBy(tagName = "textarea")
	WebElement textBox;

	@FindBy(xpath = "//span[text()='Approve']")
	WebElement modalApproveBtn;
	
	@FindBy(xpath = "//span[text()='Reject']")
	WebElement modalRejectBtn;
	
	@FindBy(xpath = "//span[text()='Reassign']")
	WebElement modalReassignBtn;
	
	@FindBy(className = "toastMessage")
	WebElement successMsg;
	
	@FindBy(linkText = "Contacts")
	WebElement contactsTab;
	
	@FindBy(linkText = "Zaim Abid")
	WebElement contactLink;
	
	@FindBy(css = "button[title='Edit In Training']")
	WebElement editInTraining;
	
	@FindBy(css = "button[title='Save']")
	WebElement traningSaveButton;
	
	public SupervisorAction(WebDriver driver) {
		PageFactory.initElements(new AjaxElementLocatorFactory(driver, 15), this);
		this.driver = driver;
	}

	public void approve() {
		Utils.sleep(5);
		
		actionBtnList.get(0).click();

		Utils.sleep(2);

		approveButton.click();

		Utils.sleep(2);

		textBox.sendKeys("Approved");
		
		Utils.sleep(2);
		
		modalApproveBtn.click();
		
		Utils.sleep(3);
		
		Assert.assertEquals(successMsg.getText(), "Service Item was approved.");
	}

	public void reject() {
		Utils.sleep(5);
		
		actionBtnList.get(0).click();

		Utils.sleep(2);

		rejectButton.click();

		Utils.sleep(2);

		textBox.sendKeys("Rejected");
		
		Utils.sleep(2);
		
		modalRejectBtn.click();
		
		Utils.sleep(3);
		
		Assert.assertEquals(successMsg.getText(), "Service Item was rejected.");
	}

	public void reassign(String newUser) {
		Utils.sleep(5);
		
		actionBtnList.get(0).click();

		Utils.sleep(2);

		reassignButton.click();

		Utils.sleep(2);
		
		WebElement modal = driver.findElement(By.className("modal-body"));

		modal.findElement(By.className("nextApprover"))
				.findElement(By.tagName("input")).sendKeys(newUser);
		
		Utils.sleep(2);
		
		modal.findElement(By.cssSelector("div[title='"+newUser+"']")).click();
		
		textBox.sendKeys("Reassigned");
		
		Utils.sleep(2);
		
		modalReassignBtn.click();
		
		Utils.sleep(3);
		
		Assert.assertEquals(successMsg.getText(), "Service Item was reassigned.");
	}

	public void setup_training() {
		Utils.sleep(3);
		
		contactsTab.click();

		Utils.sleep(2);
		
		contactLink.click();
		
		Utils.sleep(2);
		
		Actions action = new Actions(driver);
		action.moveToElement(editInTraining).click().build().perform();

		Utils.sleep(2);
		
		getElementByLabelText(driver, "In Training").click();
		
		Utils.sleep(2);
		
		traningSaveButton.click();
		
		Utils.sleep(2);
	}
	
	private WebElement getElementByLabelText(WebDriver driver, String lableTxt) {
		WebElement el = driver.findElement(By.xpath("//label[.//span[text()='" + lableTxt + "']]"));
		return driver.findElement(By.id(el.getAttribute("for")));
	}

}
