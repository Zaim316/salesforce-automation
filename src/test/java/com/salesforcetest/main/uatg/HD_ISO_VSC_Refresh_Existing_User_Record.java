package com.salesforcetest.main.uatg;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.salesforcetest.pages.salesforce.uatg.CustomerSearchAction;
import com.salesforcetest.pages.salesforce.uatg.RefreshExistingUserRecordAction;

public class HD_ISO_VSC_Refresh_Existing_User_Record {
	private WebDriver driver;

	private ExtentTest testReporter;
	public HD_ISO_VSC_Refresh_Existing_User_Record(WebDriver driver) {
		this.driver = driver;
	}
	public void setTestReporter(ExtentTest testReporter) {
		this.testReporter = testReporter;
	}
	public void verifyLoggedInUserProfile() {

		try {
			RefreshExistingUserRecordAction refObj = new RefreshExistingUserRecordAction(driver);
			refObj.verifyProfile();
			testReporter.log(LogStatus.PASS, "Verifying the current logged in user profile:");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Verifying the current logged in user profile:");
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
	public void logInAsInternalUser(String user) {
		try {
			RefreshExistingUserRecordAction refObj = new RefreshExistingUserRecordAction(driver);
			refObj.logInAsInternalUser(user);
			testReporter.log(LogStatus.PASS, "Verify log in as Internal User");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Verify log in as Internal User");
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
	public void customerSearch(String customer) {
		try {
			RefreshExistingUserRecordAction refObj = new RefreshExistingUserRecordAction(driver);
			refObj.customerSearch(customer);
			testReporter.log(LogStatus.PASS, "Search for required customer");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Search for required customer");
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
	public void customerDataRefresh() {
		try {
			RefreshExistingUserRecordAction refObj = new RefreshExistingUserRecordAction(driver);
			refObj.customerRefresh();
			testReporter.log(LogStatus.PASS, "Refreshing the input customer details and wait till refreshing mechanism is completed");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Refreshing the input customer details and wait till refreshing mechanism is completed");
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
	public void searchUser(String user) {
		try {
			RefreshExistingUserRecordAction refObj = new RefreshExistingUserRecordAction(driver);
			refObj.searchHDISOVSCitems(user);
			testReporter.log(LogStatus.PASS, "Search with required User");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Search with required User");
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
	public void searfchForApplicationRecord(String applicationId) {
		try {
			RefreshExistingUserRecordAction refObj = new RefreshExistingUserRecordAction(driver);
			refObj.searchForApplicationRecordWithAppNo(applicationId);
			testReporter.log(LogStatus.PASS, "Search with Application id in global search panel");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Search with Application id in global search panel");
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
	public void openRecordFromGlobalSearchResult(String applicationRecord2) {
		try {
			RefreshExistingUserRecordAction refObj = new RefreshExistingUserRecordAction(driver);
			refObj.openApplicationRecordFromGlobalSearchResultPanel(applicationRecord2);
			testReporter.log(LogStatus.PASS, "Find and Open "+applicationRecord2+" record from Global Search result");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Find and Open "+applicationRecord2+" record from Global Search result");
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
	public void clickOnCreateNewItem() {
		try {
			RefreshExistingUserRecordAction refObj = new RefreshExistingUserRecordAction(driver);
			refObj.createNewServiceItem();
			testReporter.log(LogStatus.PASS, "Click on Create new Service item button");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Click on Create new Service item button");
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
	public void giveOrgName(String orgName) {
		try {
			RefreshExistingUserRecordAction refObj = new RefreshExistingUserRecordAction(driver);
			refObj.setOrgName(orgName);
			testReporter.log(LogStatus.PASS, "Give Organization Name : "+orgName);
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Give Organization Name : "+orgName);
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
	public void giveEmailId(String email) {
		try {
			RefreshExistingUserRecordAction refObj = new RefreshExistingUserRecordAction(driver);
			refObj.setEmail(email);
			testReporter.log(LogStatus.PASS, "Give Email Id: "+email);
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Give Email Id: "+email);
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
	public void giveSenderType(String senderType) {
		try {
			RefreshExistingUserRecordAction refObj = new RefreshExistingUserRecordAction(driver);
			refObj.selectSenderType(senderType);
			testReporter.log(LogStatus.PASS, "Give Sender Type Name : "+senderType);
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Give Sender Type Name : "+senderType);
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
	public void giveSubjectAndDes() {
		try {
			RefreshExistingUserRecordAction refObj = new RefreshExistingUserRecordAction(driver);
			refObj.setRandomSubjectAndDesAndFormType();
			testReporter.log(LogStatus.PASS, "Give Subject and Description Name.");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Give Subject and Description Name.");
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
	public void giveCategoryAndKind() {
		try {
			RefreshExistingUserRecordAction refObj = new RefreshExistingUserRecordAction(driver);
			refObj.setRandomCategoryAndKind();
			testReporter.log(LogStatus.PASS, "Give Category and Kind Name.");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Give Category and Kind Name.");
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
	public void giveSIOrigin(String serviceItem) {
		try {
			RefreshExistingUserRecordAction refObj = new RefreshExistingUserRecordAction(driver);
			refObj.selectSIOrigin(serviceItem);
			testReporter.log(LogStatus.PASS, "Give Service Item Origin :"+serviceItem);
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Give Service Item Origin :"+serviceItem);
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
	public void giveSInitialQueue(String initialQueue) {
		try {
			RefreshExistingUserRecordAction refObj = new RefreshExistingUserRecordAction(driver);
			refObj.selectInitialQueue(initialQueue);
			testReporter.log(LogStatus.PASS, "Give Initial Queue : "+initialQueue);
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Give Initial Queue : "+initialQueue);
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
	public void giveReceivedDate(String receivedDate) {
		try {
			RefreshExistingUserRecordAction refObj = new RefreshExistingUserRecordAction(driver);
			refObj.setReceivedDate(receivedDate);
			testReporter.log(LogStatus.PASS, "Give Received Date (any past date) : "+receivedDate);
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Give Received Date (any past date) : "+receivedDate);
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
	public void saveNewServiceItem() {
		try {
			RefreshExistingUserRecordAction refObj = new RefreshExistingUserRecordAction(driver);
			refObj.clickOnSaveSI();
			testReporter.log(LogStatus.PASS, "Saving this new service item data");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Saving this new service item data");
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
	public void validateNewlyCreatedSI() {
		String newlyCreatedSI = null;
		try {
			RefreshExistingUserRecordAction refObj = new RefreshExistingUserRecordAction(driver);
			newlyCreatedSI = (String) refObj.fetchServiceItemNo();
			testReporter.log(LogStatus.PASS, "Validate new service item and new Service Item number :"+newlyCreatedSI);
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Validate new service item and new Service Item number :"+newlyCreatedSI);
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
	public void createNewResponse() {
		try {
			RefreshExistingUserRecordAction refObj = new RefreshExistingUserRecordAction(driver);
			refObj.createNewResponse();
			testReporter.log(LogStatus.PASS, "Creating new sevice response of new service item.");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Creating new sevice response of new service item.");
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
	public void verifySR() {
		try {
			RefreshExistingUserRecordAction refObj = new RefreshExistingUserRecordAction(driver);
			refObj.verifyNewResponseStatus();
			testReporter.log(LogStatus.PASS, "Verify new sevice response status of new service item.");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Verify new sevice response status of new service item.");
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
	public void logOut() {
		try {
			RefreshExistingUserRecordAction refObj = new RefreshExistingUserRecordAction(driver);
			refObj.currentUserLogOut();
			testReporter.log(LogStatus.PASS, "Logging out");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Logging out");
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
	public void searchWithSupervisorId(String user) {
		try {
			RefreshExistingUserRecordAction refObj = new RefreshExistingUserRecordAction(driver);
			refObj.searchHDISOVSCitems(user);
			testReporter.log(LogStatus.PASS, "Search with One HD Supervisor");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Search with One HD Supervisor");
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
	public void loginWithSupervisorId(String user) {
		try {
			RefreshExistingUserRecordAction refObj = new RefreshExistingUserRecordAction(driver);
			refObj.logInAsInternalUser(user);
			testReporter.log(LogStatus.PASS, "Login with One HD Supervisor");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Login with One HD Supervisor");
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
	public void selectRequiredSI() {
		try {
			RefreshExistingUserRecordAction refObj = new RefreshExistingUserRecordAction(driver);
			refObj.selectRequiredDropdownlist();
			testReporter.log(LogStatus.PASS, "Open the new service item");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Open the new service item");
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
	public void approveTheServiceRqst() {
		try {
			RefreshExistingUserRecordAction refObj = new RefreshExistingUserRecordAction(driver);
			refObj.approveServiceRqst();
			testReporter.log(LogStatus.PASS, "Approve the service request/response");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Approve the service request/response");
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
	public void validateApproveRqstResponse() {
		try {
			RefreshExistingUserRecordAction refObj = new RefreshExistingUserRecordAction(driver);
			refObj.validateApprovedResponse();
			testReporter.log(LogStatus.PASS, "Validate the approved request response");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Validate the approved request response");
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
}
