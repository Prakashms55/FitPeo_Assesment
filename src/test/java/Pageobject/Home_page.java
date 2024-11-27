package Pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class Home_page {
	public WebDriver driver;
		
	// Constructor to initialize the WebDriver instance and web elements using PageFactory
	public Home_page(WebDriver rdriver) {
		this.driver=rdriver;
		PageFactory.initElements(rdriver,this);
	}
	
	// WebElement representing the 'Revenue Calculator' element on the home page
	@FindBy(xpath = "//div[contains(text(),'Revenue Calculator')]")
	WebElement Revenue_calculator;
	
	// Method to verify if the home page title matches the expected title
	public void verify_navigate_homepage(String expected_title) {
		String acctual_title= driver.getTitle();// Get the current page title
		Assert.assertEquals(acctual_title,expected_title );// Assert if the actual title matches the expected title }
	}
	
	// Method to get and return the title of the home page
	public String print_homePage_title() {
	String Home_page_title=	driver.getTitle();// Get the current page title
	return Home_page_title;// Return the page title
	}
	
	// Method to click on the 'Revenue Calculator' element and navigate to the calculator page
	public calculator_page click_revenueCalculator() {
		Revenue_calculator.click();// Click on the 'Revenue Calculator' element
		return new calculator_page(driver);// Return a new instance of calculator_page
	}
	
}
