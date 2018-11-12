package com.salesforcetest.pages.salesforce;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.salesforcetest.shared.Constants;
import com.salesforcetest.shared.Utils;

public class ServiceItemActionHelper {

	protected void select_service_item_list_option(WebDriver driver, String serviceItemLinkText,
			WebElement serviceItemsTab, WebElement serviceItemsPageOptions) {
		Utils.sleep(4);

		serviceItemsTab.click();

		Utils.sleep(2);

		serviceItemsPageOptions.click();

		Utils.sleep(2);

		WebElement dropdown = driver.findElement(By.className("forceVirtualAutocompleteMenuList"));

		dropdown.findElement(By.linkText(serviceItemLinkText)).click();
	}

	protected void change_service_item_owner(WebDriver driver, String type, String ownerSearchTxt) {
		Utils.sleep(2);
		driver.navigate().refresh();
		Utils.sleep(4);

		Actions builder = new Actions(driver);
		WebElement ownerEl = driver.findElement(By.cssSelector("button[title='Change Owner']"));
		builder.moveToElement(ownerEl).click().build().perform();

		Utils.sleep(2);

		WebElement parent = driver.findElement(By.className("modal-container"));

		if (type != "People") {
			// Change People to Queues
			parent.findElement(By.className("entityMenuTrigger")).click();

			Utils.sleep(1);

			// types - 1. People 2. Queues
			driver.findElement(By.linkText(type)).click();

			Utils.sleep(2);
		}

		WebElement combo = parent.findElement(By.className("autocompleteWrapper"));

		// Fill owner name
		combo.findElement(By.tagName("input")).sendKeys(ownerSearchTxt);

		Utils.sleep(2);

		parent.findElement(By.cssSelector("div[title='" + ownerSearchTxt + "']")).click();

		parent.findElement(By.className("nonEditableOptions")).click();

		Utils.sleep(2);

		parent.findElement(By.cssSelector("button[title='Change Owner']")).click();

		Utils.sleep(2);
	}

	protected void assert_error_msg(WebDriver driver, String expectedMsg) throws Exception {
		List<WebElement> errorList = driver.findElements(By.className("errorsList"));
		boolean found = false;
		for (WebElement el : errorList) {
			String html = el.getAttribute("innerHTML");
			System.out.println("Actual Error: " + html);
			System.out.println("Expected Error: " + expectedMsg);
			if (html.contains(expectedMsg)) {
				found = true;
				break;
			}
		}

		if (!found) {
			throw new Exception("Error message not verified");
		}
	}

	protected WebElement getElementByLableText(WebDriver driver, String lableTxt) {
		WebElement el = driver.findElement(By.xpath("//label[.//span[text()='" + lableTxt + "']]"));
		return driver.findElement(By.id(el.getAttribute("for")));
	}

	protected void change_service_item_status(WebDriver driver, String fromStatus, String toStatus,
			WebElement saveButton) throws Exception {
		Utils.sleep(3);

		Utils.scrollWindow(driver, 100);
		Actions action = new Actions(driver);
		// Select status

		WebElement el = driver.findElement(By.cssSelector("button[title='Edit Status']"));
		action.moveToElement(el).click().build().perform();

		Utils.sleep(1);

		driver.findElement(By.linkText(fromStatus)).click();

		Utils.sleep(2);

		// Select Archived
		WebElement status = driver.findElement(By.linkText(toStatus));
		action.moveToElement(status).click().build().perform();
		
		Utils.sleep(2);
		
		// Save
		saveButton.click();

		Utils.sleep(4);
	}

	protected void is_document_by_doc_name_found(WebDriver driver, String docName, int threshold, int counter)
			throws Exception {
		Utils.sleep(2);

		driver.findElement(By.linkText("Related")).click();

		List<WebElement> files = driver.findElements(By.className("forceContentVirtualRelatedListStencil"));
		boolean fileFound = false;

		for (int ii = 0; ii < files.size(); ii++) {
			String fileName = files.get(ii).findElement(By.tagName("a")).getAttribute("title");
			if (docName.contains(fileName)) {
				fileFound = true;
				Assert.assertTrue(fileFound);
				break;
			}
		}
		
		if(!fileFound) {
			counter++;
			if (counter < threshold) {
				Utils.sleep(5);
				driver.navigate().refresh();
				is_document_by_doc_name_found(driver, docName, threshold, counter);
			} else {
				Assert.assertTrue(false);
				throw new Exception("Timeout: Attachment not found.");
			}
		} else {
			Utils.sleep(2);
		}
		
	}

	protected void click_email_and_send(WebDriver driver, WebElement composeBox, String toEmail,
			boolean withAttachments) throws Exception {

		// Clicking email tab
		driver.findElement(By.linkText("Email")).click();

		Utils.sleep(2);

		Utils.scrollWindow(driver,100);
		
		// clicking cross in to email
		List<WebElement> sections = composeBox.findElement(By.cssSelector("div"))
				.findElements(By.className("forcePageBlockSectionRow"));

		// removing to address
		sections.get(1).findElement(By.className("deleteAction")).click();

		Utils.sleep(1);

		sections.get(1).findElement(By.className("uiInputTextForAutocomplete")).sendKeys(toEmail);

		Utils.sleep(1);

		Utils.scrollWindow(driver,450);

		if (withAttachments) {
			// use templates
			// old
//			composeBox.findElement(By.cssSelector("div")).findElement(By.xpath("div[3]/div[1]"))
//					.findElement(By.className("uiPopupTrigger")).click();

			// use templates
			List<WebElement> iconList = driver.findElements(By.className("cuf-attachmentsItem"));

			// Click adding template
			iconList.get(2).click();

			Utils.sleep(1);

			driver.findElement(By.linkText("Insert a template...")).click();

			Utils.sleep(3);

			WebElement modal = driver.findElement(By.className("modal-body"));

			Select templateDrop = new Select(modal.findElement(By.tagName("select")));

			templateDrop.selectByVisibleText("Sample Lightning Templates");

			Utils.sleep(3);

			modal.findElement(By.cssSelector("a[title='SAMPLE: Called, But No Answer']")).click();

			WebElement subjectInput = getElementByLableText(driver, "Subject");
			subjectInput.clear();
			subjectInput.sendKeys(Constants.email_subject);

			Utils.scrollWindow(driver, 200);
		}

		// click send button
		// NEW
		Utils.sleep(3);
		
		driver.findElement(By.xpath("//span[text()='Send']")).click();
		
		Utils.sleep(3);
	}

	protected void update_service_item_type(WebDriver driver, String type, WebElement saveButton) throws Exception {
		// click update type button
		Actions action = new Actions(driver);
		Utils.scrollWindow(driver, 50);
		WebElement el = driver.findElement(By.cssSelector("button[title='Edit Type']"));
		action.moveToElement(el).click().build().perform();
		Utils.sleep(2);
		try {
			driver.findElement(By.linkText("--None--")).click();

			Utils.sleep(2);

			driver.findElement(By.linkText(type)).click();

			Utils.sleep(2);

			// Save
			saveButton.click();

			Utils.sleep(5);

			driver.navigate().refresh();

			Utils.sleep(2);
		} catch (Exception e) {

		}
	}

	protected String getSuccessMessage(WebElement successPopup) {
		return successPopup.getText();
	}

	// Assuming service item is opened
	protected String getServiceItemNo(WebDriver driver) {
		
		driver.navigate().refresh();
		
		Utils.sleep(2);
		
		List<WebElement> list = driver.findElements(By.className("forceHighlightsDesktopListRecordItem"));
		
		WebElement labelSpan = list.get(2).findElement(By.cssSelector("span[title='Service Item Number']"));
		
		// NOTE: broken into separate statements for clarity. Could be done as one statement.
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		WebElement parentElement = (WebElement)executor.executeScript("return arguments[0].parentNode;", labelSpan);
		
		Utils.sleep(2);
		
		return parentElement.findElement(By.className("uiOutputText")).getText();
	}
	
	protected void putAttachment(WebDriver driver, String attachmentPath) throws Exception {
		driver.findElement(By.linkText("Related")).click();
		
		Utils.sleep(2);
		
		WebElement section = driver.findElement(By.tagName("lightning-primitive-file-droppable-zone"));
		
		section.click();
		
		Utils.sleep(2);
		
		StringSelection s = new StringSelection(attachmentPath);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(s, null);
		Robot robot = new Robot();
		
		// For windows
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyRelease(KeyEvent.VK_V);

		Utils.sleep(2);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		
		Utils.sleep(5);
		
		driver.findElement(By.className("modal-footer")).findElement(By.tagName("button")).click();
		
		Utils.sleep(2);
	}

}
