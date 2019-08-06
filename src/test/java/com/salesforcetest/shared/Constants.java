package com.salesforcetest.shared;

import java.io.File;

public class Constants {
	public static final String reporter_email = "zabid@acumensolutions.com";

	public static final String reporter_username = "zabid";

	public static final String reporter_password = "Mohammad1988";

	public static final String reporter_account_url = "https://www.gmail.com";
	
	public static final String test_file_folder =  System.getProperty("user.dir") + File.separatorChar + "files" + File.separatorChar;

//	public static final String attachment_url = "/Users/ZaimAbid/Documents/Privacy Test Files/SORN.pdf";
	
//	public static final String attachment_url_reply = "/Users/ZaimAbid/Documents/Privacy Test Files/privacy-pia-uscis-09-a-cis-april-2017.pdf";
	
	public static final String sorn_attachment_url = test_file_folder +"SORN.pdf";
	
	public static final String privacy_pia_attachment_url = test_file_folder +"privacy-pia-uscis-09-a-cis-april-2017.pdf";
	
	public static final String external_contact_attachment_url = test_file_folder +"External Contact File.pdf";

	public static final String privacy_test_attachment_url = test_file_folder +"Privacy Test File 8971 Test.docx";
	
	// Incident properties

	public static final String incident_report_email = "pvy.incidents@gmail.com";

	public static final String incident_msg = "This is an incident";

	public static final String incident_subject = "New Incident Test " + System.currentTimeMillis();
	
	public static final String closure_email_subject = "Closure of Incident";
	
	public static final String sir_number = "123456";
	
	public static final String incident_number = "123456";
	
	public static final String service_now_number = "123456789";
	
	// Complience properties

	public static final String compliance_report_email = "pvy.compliance@gmail.com";

	public static final String compliance_msg = "This is an compliance report";

	public static final String compliance_subject = "New Compliance Test " + System.currentTimeMillis();
	
	public static final String dhs_email = "acumenuscistest+dhs@gmail.com";
	
	public static final String dhs_error_msg = "You are not approved to send this email.";
	
	//PI Service Item
	
	public static final String service_item_subject_suffix = " Test " + System.currentTimeMillis();
	
	// End

	//Salesforce UATG credentials
	
	public static final String salesforce_url_uatg = "https://uscis--uatg.my.salesforce.com/";
	
	public static final String salesforce_username_uatg = "zabid_cis@acumensolutions.com.uatg";

	public static final String salesforce_password_uatg = "acumentest7";
	
	public static final String email_password = "Mohammad1988";

	//Salesforce credentials
	
	public static final String salesforce_url = "https://uscis-2--uat.cs32.my.salesforce.com/";

	public static final String salesforce_username = "pstaff@test.com.uat";

	public static final String salesforce_password = "acumentest4";
	
	public static final String salesforce_pstaff2_username = "pstaf2@test.com.uat";
	
	public static final String incident_owner = "Privacy Staff2";
	
	public static final String compliance_owner = "Privacy Staff";
			
	public static final String salesforce_pstaff2_password = "acumentest4";

	public static final String email_subject= "Just Missed You";
	
	public static final String LOGGER_FILE = "logs/report-"+ System.currentTimeMillis()+".html";
	
	//Supervisor Salesforce Account credentials
	
	public static final String supervisor_salesforce_username = "Pbran@test.com.uat";

	public static final String supervisor_salesforce_password = "acumentest4";
	
	// Privacy Staff NOT Training credentials
	
	public static final String privacy_not_training_username = "pvy@example.com";

	public static final String privacy_not_training_password = "acumentest4";
	
	public static final String sample_occ_email = "acumenuscistest+occ@gmail.com";
	
	//Case Team Member credentials
	
	public static final String case_team_member_sf_username = "Cstaf@test.com.uat";

	public static final String case_team_member_sf_password = "acumentest4";
	
	public static boolean isWindows() {
		String os = System.getProperty("os.name");
		if (os.contains("Windows") || os.contains("WINDOWS") || os.contains("windows")) {
			return true;
		}
		return false;
	}
	
	public static long getRamdomId() {
		return System.currentTimeMillis();
	}
}
