package Test_Package;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.mongodb.diagnostics.logging.Logger;

import BaseTest.base_test;
import Pageobject.Home_page;
import Pageobject.calculator_page;

public class calculatorpage_Test extends base_test{
	public  ExtentSparkReporter sparkreporter;
	public com.aventstack.extentreports.ExtentReports extent;
	public com.aventstack.extentreports.ExtentTest logger;
	 Home_page homepage;
	 calculator_page	cal_page;
	
		@BeforeTest	
		public void before() {
			sparkreporter = new ExtentSparkReporter  (System.getProperty("user.dir") + File.separator+"reports"+File.separator+"calculator_Page_Report.html");
			extent=new com.aventstack.extentreports.ExtentReports();
			extent.attachReporter(sparkreporter);
					
			sparkreporter.config().setTheme (Theme.STANDARD);
			extent.setSystemInfo ("HostName", "prakash");
			extent.setSystemInfo ("UserName", "Muthu");
			sparkreporter.config().setDocumentTitle ("Automation Report");
			sparkreporter.config().setReportName ("Automation Tests Results by prakashms");
		}
	 
		@AfterTest
		public void after_test() {
			extent.flush();
		}
	
	@BeforeMethod
	public void before_test() throws IOException {
		String browser=BaseTest.base_test.getproperties("browser");
		String url=BaseTest.base_test.getproperties("url");
		intializebrowser(browser);
		driver.manage().window().maximize();
		driver.get(url);
		homepage=new Home_page(driver);
		cal_page=homepage.click_revenueCalculator();	
	}
	
	@AfterMethod
	public void teardown1(ITestResult result) throws Exception {
		if(ITestResult.FAILURE==result.getStatus()) {
			logger.log(Status. FAIL, MarkupHelper.createLabel (result.getName()+" Test Case Failed", ExtentColor.RED)); 
			logger.log(Status.FAIL, MarkupHelper.createLabel (result.getThrowable()+" Test Case Failed", ExtentColor.RED));
			logger.log(Status.FAIL,"test case is failed-"+result.getName());
			logger.addScreenCaptureFromPath(capturescreen(driver));
		}
		else if(result.getStatus()== ITestResult.SKIP){
			logger.log(Status.SKIP, MarkupHelper.createLabel (result.getName() +"- Test Case Skipped ", ExtentColor.ORANGE)) ;
		}
		else if(result.getStatus() ==ITestResult.SUCCESS) {
			logger.log(Status.PASS, MarkupHelper.createLabel(result.getName() + "- Test Case PASS ", ExtentColor.GREEN)); 
		}

		driver.quit();
	}
	
	@Test(priority = 0)
	public void slide_downTo_sliderSection() {
		// Log the start of the test for moving the slider to 820
		logger=extent.createTest("Move the slider to 820","verify that the slider is able to move to 820");
		// Scroll down to the slider section
		cal_page.scroll_Down();
		// Drag and drop the slider, then get the actual value
		String actual=cal_page.drag_and_drop();
		logger.log(Status.INFO, "unable to move the slider to 820 ,the slider is  move to 816 and next to 822 unable to move to 820 in the manually also can't move");
		logger.log(Status.FAIL, "unable to slide to 820");
		// Verify that the actual value is 820
		Assert.assertEquals(actual, "820");
		// Log the failure information if unable to move the slider to 820
		
		
	}
	
	@Test(priority = 1)
	public void enter_values_in_text_field() {
		// Log the start of the test for entering values in the text field
		logger=extent.createTest("Enter the values in the box filed of 560","verify that the user is able to enter 560 in the box field");
		// Scroll down to the text field section
		cal_page.scroll_Down();
		// Enter the value 560 in the text field and get the actual value
		String actual=cal_page.Enter_the_value("560","value");
		// Verify that the actual value is 560
		Assert.assertEquals(actual,"560");
		// Log the pass information for entering values in the field
		logger.log(Status.PASS, "user is able to enter the values in the field");
	}
	
	@Test(priority = 2)
	public void click_CPT_codes() throws Exception {
		// Log the start of the test for clicking CPT codes
		logger=extent.createTest("Click the CPT Codes","verify that the user is able to click the multiple codes");
		// Scroll down to the CPT codes section
		cal_page.scroll_Down();
		// Enter the value 820 in the text field
		cal_page.Enter_the_value("820","value");
		// Click the CPT codes and retrieve the amount
		String actual=cal_page.select_CPT_codes();
		// Log the information for selecting the CPT codes
		logger.log(Status.INFO, "user is selected the CPT codes");
		System.out.println(actual);
		// Verify that the retrieved amount matches the expected value
		Assert.assertEquals(actual, "$110700");
		// Log the pass information and add a screenshot for the passed test
		logger.log(Status.PASS, "Expected result of $110700 is matching with the expected value");
		logger.addScreenCaptureFromPath(capturescreen(driver));
		
	}
	
	
	
}
