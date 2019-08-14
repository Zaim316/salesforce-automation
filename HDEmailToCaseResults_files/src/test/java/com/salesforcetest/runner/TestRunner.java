package com.salesforcetest.runner;

import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
	
@RunWith(Cucumber.class)
@CucumberOptions(//features = "src/test/java/features/uatg",
	features = "src/test/java/features",
	//glue = { "com.salesforcetest.mapper.uatg" },
	glue = { "com.salesforcetest.mapper" },
	//glue = { "com.salesforcetest.mapper.eaaqc" },
	//plugin = {"com.cucumber.listener.ExtentCucumberFormatter:test-report/report.html" },
	//tags = {"@EmailHD,@DirectApp,@EmailIpo,@E2EwithAddiRev,@E2EwithoutAddiRev,@E2EwithAddiRevHD,@DirectApprovalHD,@E2EwithoutAddRev,@HDCoach2ApprovalScenario"},
	//tags = {"@DirectApp,@EmailIpo,@E2EwithAddiRev,@E2EwithoutAddiRev,@E2EwithAddiRevHD,@DirectApprovalHD,@E2EwithoutAddRev"},
	tags = {"@EmailHD,@E2EwithAddiRevHD,@HDCoach2ApprovalScenario,@DirectApprovalHD,@E2EwithoutAddRev"},
	//tags = {"@DirectApp,@EmailIpo,@E2EwithAddiRev,@E2EwithoutAddiRev"},
	//tags = {"@PCQSE2ECCISO,@PCQSE2ECCCISOTESTU,@PCQSE2EHDISOVSC,@PCQSE2EIPOCSR,@PCQSE2EOCCATT,@PCQSE2EOIDPANALYST,@PCQSE2EOOCINTUSR,@PCQSE2ETIER1CC,@PCQSE2EUCCDCCRU"},
	strict = true,
    monochrome = false)
public class TestRunner { 
	// , @RegForIpo
}







