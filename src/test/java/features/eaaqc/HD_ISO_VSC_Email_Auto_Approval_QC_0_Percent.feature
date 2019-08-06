Feature: Verifying auto submission process and creating a service item with sending an email 
  Verifying End to End flow for email auto approval by sending an email
  @Reg
  Scenario: Log in and verify the default profile
  	Given Email auto approval process Registered User is logged in with "zabid_cis@acumensolutions.com.uatg"
    Then Email auto approval process Verifying the current logged in user profile
  @Reg  
  Scenario: Fetch the email id from email services
  	Then Email auto approval process Fetching email EmailRelayRoutingHandler from by navigating to email services area

  @Reg
  Scenario: Log into email and send an email with URl fetched from salesforce website
  	Then Email auto approval process Logging into email "https://acumensolutions-com.clearlogin.com/login" with user id "zabid"
  @Reg 
  Scenario: Log in with ONE HD SUPERVISOR and Set QC percentage to 5%
    When Email auto approval process Search for required Internal User "One HD Supervisor"
    Then Email auto approval process Logging in as Internal user and verifying "One HD Supervisor"
    Then Email auto approval process set QC Percentage to "5"
  @Reg 
  Scenario: Fetch the service item number which is created from email and store it and log out
  	Then Email auto approval process Fetch the Service item number which is created through Email
  @Reg 
  Scenario: Log in with HD ISO VSC and mark all opened items as Duplicate and Assign new Item and open the service item in edit mode
  	Then Email auto approval process Log Into as HD VSC user and mark openend items as duplicate and Assign a new item with user "HD ISO VSC"
  
  @Reg	
  Scenario: Editing service item by providing below input data and saving the item
   And To edit service item Provide all new mandatory data
  | Receipt No 	  |		Contact		| Org Name    | Email 	| Sender Type   | Subject 		| Description      | Form No | Form Type                                      	 | Category    | Kind                    | Comments   | Item Origin | Queue     			   | DateTime         | Response Comments     |
  | WAC1690258857 |	EDVARD EDOUARD  | Test Org14  | Auto 	| ASC 			 | Auto 		| Auto 			   | I192 	 | I-192 Advance Permission to Enter as Nonimmigrant | AWA Case    | AWA Concerns Identified | Creating SI| Email       | VAWA_HotlineFollowupI360 | 10/4/2018 9:44 AM| New Response Approval |
  
  @Reg
  Scenario: Validate the saved item
   Then Email auto approval process Validating edited service details saved reflecting in next page
   Then Stop Report Generation for current scenario Email auto approval process
   Then Close the browser Email auto approval process
   