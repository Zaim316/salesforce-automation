package com.salesforcetest.shared;

import java.io.File;

public class Constants {
	//test
	public static final String reporter_email = "zabid@acumensolutions.com";

	public static final String reporter_username = "zabid";

	public static final String reporter_password = "Acumentest1";

	public static final String reporter_account_url = "https://www.gmail.com";
	
	public static final String test_file_folder =  System.getProperty("user.dir") + File.separatorChar + "files" + File.separatorChar;

//	public static final String attachment_url = "/Users/ZaimAbid/Documents/Privacy Test Files/SORN.pdf";
	
//	public static final String attachment_url_reply = "/Users/ZaimAbid/Documents/Privacy Test Files/privacy-pia-uscis-09-a-cis-april-2017.pdf";
	
	public static final String sorn_attachment_url = test_file_folder +"SORN.pdf";
	
	public static final String privacy_pia_attachment_url = test_file_folder +"privacy-pia-uscis-09-a-cis-april-2017.pdf";
	
	public static final String external_contact_attachment_url = test_file_folder +"External Contact File.pdf";

	public static final String privacy_test_attachment_url = test_file_folder +"Privacy Test File 8971 Test.docx";
	
	// Incident properties
	
	//UAT
	public static String incident_report_email = "pvy.incidents@gmail.com";
	
	//UATCMAS
	//public static final String incident_report_email = "incident@y-1490wovgooaqpl17zwi6nhe8kr4sfsf0q6y54rsn43qx8igc25.r-1kyueau.cs32.apex.sandbox.salesforce.com";

	public static final String incident_msg = "This is an incident";

	public static final String incident_subject = "New Incident Test " + System.currentTimeMillis();
	
	public static final String closure_email_subject = "Closure of Incident";
	
	public static final String sir_number = "123456";
	
	public static final String incident_number = "123456";
	
	public static final String service_now_number = "123456789";
	
	// Complience properties

	//UAT
	 public static String compliance_report_email = "pvy.compliance@gmail.com";

	//UATCMAS
	//public static final String compliance_report_email = "compliance@r-1ajci6b81fxo1q28d1ubkngo45mipzxp9ioldwc10vdobs60jf.r-1kyueau.cs32.apex.sandbox.salesforce.com";
	
	public static final String compliance_msg = "This is an compliance report";

	public static String compliance_subject = "New Compliance Test " + System.currentTimeMillis();
	
	public static final String dhs_email = "acumenuscistest+dhs@gmail.com";
	
	public static final String dhs_error_msg = "You are not approved to send this email.";
	
	//PI Service Item
	
	public static final String service_item_subject_suffix = " Test " + System.currentTimeMillis();
	
	// End

	//Salesforce UATG credentials
	
	//public static final String salesforce_url_uatg = "https://uscis--uatg.my.salesforce.com";
	public static final String salesforce_url_uatg = "https://uscis--uatg.my.salesforce.com/?login";
	
	//public static final String salesforce_username_uatg = "zabid_cis@acumensolutions.com.uatg";
	
	public static final String salesforce_username_uatg = "mohammad.z.abid@uscis.dhs.gov.uatg";

	public static final String salesforce_password_uatg = "acumentest3";
	
	public static final String email_password = "Acumentest1";

	//Salesforce credentials
	
	
	//Privacy Credentials
	
	
	//STARS 2 URL
	
	//UATCMAS
	//public static final String salesforce_url = "https://uscis2--uatcmas.cs32.my.salesforce.com/?ec=302&startURL=%2Fhome%2Fhome.jsp"; 
	
	//UAT
	public static final String salesforce_url = "https://uscis2--uat2.my.salesforce.com/";
	//public static final String salesforce_url = "https://uscis2--stars2qa.my.salesforce.com/";
	//QA
	//public static final String salesforce_url = "https://uscis2--stars2qa.lightning.force.com/lightning/page/home";	
	
	//Privacy Staff User
	
	//UATCMAS
	//public static final String salesforce_username = "pstaff@acumensolutions.com.uatcmas";
	//public static final String salesforce_password = "acumentest1";
	
	//UAT
	public static final String salesforce_username = "pstaff@acumensolutions.com.uat";
	public static final String salesforce_password = "acumentest1";
	
	//QA
	//public static final String salesforce_username = "pstaff@acumensolutions.com.stars2qa";
	//public static final String salesforce_password = "acumentest2";
	
	
	//Privacy Staff 2 User
	//UAT
	public static final String salesforce_pstaff2_username = "pstaff2@acumensolutions.com.uat";
	public static final String salesforce_pstaff2_password = "acumentest1";
	
	//QA
	//public static final String salesforce_pstaff2_username = "pstaff2@acumensolutions.com.stars2qa";	
	//public static final String salesforce_pstaff2_password = "acumentest2";	
	
	//Admin User
	
	//QA
	//public static final String salesforce_pstaff2_username_Admin = "zabid_cis2@acumensolutions.com.stars2qa";	
	//public static final String salesforce_pstaff2_password_Admin = "acumentest1";
	//public static final String incident_owner = "Privacy Staff 2";
	
	//UAT
	public static final String salesforce_pstaff2_username_Admin = "zabid_cis2@acumensolutions.com.uat2";	
	public static final String salesforce_pstaff2_password_Admin = "acumentest2";
	//public static final String salesforce_pstaff2_username_Admin = "zabid_cis2@acumensolutions.com.stars2qa";	
	//public static final String salesforce_pstaff2_password_Admin = "acumentest2";
	
	//Incident and Compliance Owner
	
	public static final String incident_owner = "Privacy Staff 2";
	public static final String compliance_owner = "Privacy Staff"; 
	
	//UATCMAS
	//public static final String salesforce_pstaff2_username = "pstaff2@acumensolutions.com.uatcmas";
	//public static final String salesforce_pstaff2_password = "acumentest6";
	//public static final String incident_owner = "Privacy Staff 2";

	public static String email_subject= "Just Missed You";
	public static final String LOGGER_FILE = "logs/report-"+ System.currentTimeMillis()+".html";
	
	//Privacy Branch Chief (Supervisor)
	
	//UAT
	public static final String supervisor_salesforce_username = "Pbranchchief@acumensolutions.com.uat";
	public static final String supervisor_salesforce_password = "acumentest1";
	
	//QA
	//public static final String supervisor_salesforce_username = "pbranchchief@acumensolutions.com.stars2qa";
	//public static final String supervisor_salesforce_password = "acumentest2";

	//UATCMAS
	//public static final String supervisor_salesforce_username = "Pbranchchief@acumensolutions.com.uatcmas";
	//public static final String supervisor_salesforce_password = "acumentest1";	
	
	
	// Privacy Staff NOT Training credentials
	
	//UAT
	public static final String privacy_not_training_username = "pnot@acumensolutions.com.uat";
	public static final String privacy_not_training_password = "acumentest1";
	
	//QA
	//public static final String privacy_not_training_username = "pnot@acumensolutions.com.stars2qa";
	//public static final String privacy_not_training_password = "acumentest2";
	
	//UATCMAS
	//public static final String privacy_not_training_username = "pnot@example.com";
	//public static final String privacy_not_training_password = "acumentest1";		
	
	public static final String sample_occ_email = "acumenuscistest+occ@gmail.com";
	
	//Case Team Member credentials
	
	//UAT
	//public static final String case_team_member_sf_username = "Cstaf@test.com.uat";
	//public static final String case_team_member_sf_password = "acumentest1";
	//public static final String case_team_member_sf_username = "pnot@acumensolutions.com.stars2qa";
	//public static final String case_team_member_sf_password = "acumentest2";
	
	//public static final String case_team_member_sf_username = "Cstaf@test.com.uatcmas";
	//public static final String case_team_member_sf_password = "acumentest1";
	
	
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
	public static void setEmailUrl(String url) {
		compliance_report_email = url;
		incident_report_email = url;
	}
}
