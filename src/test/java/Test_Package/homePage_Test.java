package Test_Package;

import java.io.File;

import java.io.IOException;

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

import BaseTest.base_test;
import Pageobject.Home_page;

//Test class for Home Page functionality extending base_test
public class homePage_Test extends base_test {
	public  ExtentSparkReporter sparkreporter; // Reporter for generating HTML reports
	public com.aventstack.extentreports.ExtentReports extent;// ExtentReports instance for report generation
	public com.aventstack.extentreports.ExtentTest logger;// Logger for capturing test logs
	 Home_page homepage;// Instance of Home_page class
	
		@BeforeTest	
		public void before() {
			// Set up the ExtentSparkReporter with the report file path
			sparkreporter = new ExtentSparkReporter  (System.getProperty("user.dir") + File.separator+"reports"+File.separator+"homepageReport.html");
			extent=new com.aventstack.extentreports.ExtentReports();// Initialize ExtentReports
			extent.attachReporter(sparkreporter);// Attach the reporter to ExtentReports
			// Configure the report settings
			sparkreporter.config().setTheme (Theme.STANDARD);
			extent.setSystemInfo ("HostName", "prakash");
			extent.setSystemInfo ("UserName", "Muthu");
			sparkreporter.config().setDocumentTitle ("Automation Report");
			sparkreporter.config().setReportName ("Automation Tests Results by prakashms");
		}
	 
		@AfterTest
		public void after_test() {
			extent.flush();// Flush the report to ensure all information is written
		}
	 
		
		@BeforeMethod
		public void before_test() throws IOException {
			// Retrieve browser and URL from properties file
			String browser=BaseTest.base_test.getproperties("browser");
			String url=BaseTest.base_test.getproperties("url");
			// Initialize the browser and navigate to the URL
			intializebrowser(browser);
			driver.manage().window().maximize();
			driver.get(url);
			homepage=new Home_page(driver);// Initialize Home_page instance
		}
		
		@AfterMethod
		public void teardown1(ITestResult result) throws Exception {
			// Handle test result logging based on the test status
			if(ITestResult.FAILURE==result.getStatus()) {
				logger.log(Status. FAIL, MarkupHelper.createLabel (result.getName()+" Test Case Failed", ExtentColor.RED)); 
				logger.log(Status.FAIL, MarkupHelper.createLabel (result.getThrowable()+" Test Case Failed", ExtentColor.RED));
				logger.log(Status.FAIL,"test case is failed-"+result.getName());
				logger.addScreenCaptureFromPath(capturescreen(driver));// Capture and add screenshot for failed test
			}
			else if(result.getStatus()== ITestResult.SKIP){
				logger.log(Status.SKIP, MarkupHelper.createLabel (result.getName() +"- Test Case Skipped ", ExtentColor.ORANGE)) ;
			}
			else if(result.getStatus() ==ITestResult.SUCCESS) {
				logger.log(Status.PASS, MarkupHelper.createLabel(result.getName() + "- Test Case PASS ", ExtentColor.GREEN)); 
			}
	
			driver.quit();// Quit the browser
		}
		
		
		@Test
		public void verify_homePage() {
			
			logger=extent.createTest("HomePage", "Verify that user is navigated to the home page Of FitPeo And Click Revenue Calculator");
			System.out.println(homepage.print_homePage_title());
			homepage.verify_navigate_homepage("Remote Patient Monitoring (RPM) - fitpeo.com");
			homepage.click_revenueCalculator();// Click the 'Revenue Calculator' link
			logger.log(Status.INFO, "user is navigated to home page and click calculator link");
			logger.log(Status.PASS, "test case is passed");
		}
		
	}
