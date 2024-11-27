package Pageobject;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class calculator_page {
	
	public WebDriver driver;
	// Constructor to initialize the WebDriver instance and web elements using PageFactory
	public calculator_page(WebDriver rdriver) {
		this.driver=rdriver;
		PageFactory.initElements(rdriver,this );
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	}
	
	// WebElement representing the element to scroll to
	@FindBy(xpath = "//div[@class='MuiBox-root css-19zjbfs']")
	WebElement scroll_to_slider;
	// WebElement representing the slidebox
	@FindBy(xpath = "//span[@class='MuiSlider-root MuiSlider-colorPrimary MuiSlider-sizeMedium css-16i48op']")
	WebElement slidebox;
	// WebElement representing the slider thumb
	@FindBy(xpath = "//span[@class='MuiSlider-thumb MuiSlider-thumbSizeMedium MuiSlider-thumbColorPrimary MuiSlider-thumb MuiSlider-thumbSizeMedium MuiSlider-thumbColorPrimary css-1sfugkh']")
	WebElement slider;
	// WebElement representing the input field for entering numbers
	@FindBy(xpath = "//input[contains(@class,'MuiInputBase-input MuiOutlinedInput-input')]")
	WebElement enter_number;
	// WebElements representing different checkboxes for CPT codes
	@FindBy(xpath = "(//input[@type='checkbox'])[1]")
	WebElement CPT_99091;
	
	@FindBy(xpath = "(//input[@type='checkbox'])[2]")
	WebElement CPT_99453;
	
	@FindBy(xpath = "(//input[@type='checkbox'])[3]")
	WebElement CPT_99454;
	
	@FindBy(xpath = "(//input[@type='checkbox'])[8]")
	WebElement CPT_99474;
	
	// WebElement representing the total recurring reimbursement element
	@FindBy(xpath = "//p[@class='MuiTypography-root MuiTypography-body1 inter css-1bl0tdj'][normalize-space()='$110700']")
	WebElement Total_Recurring_Reimbursement_for_all_Patients_Per_Month;
	
	// Method to scroll down to the slider element
	public void scroll_Down() {
		JavascriptExecutor exe=(JavascriptExecutor)driver;
		exe.executeScript("arguments[0].scrollIntoView(true);", scroll_to_slider);
	}
	
	
	public String drag_and_drop() {
		 // Print location and size 
		
		System.out.println(slidebox.getLocation()); 
		System.out.println(slidebox.getSize().width); 
		// Calculate offset to move to a specific value 
		int sliderWidth = slidebox.getSize().width;
		 int minValue = 0;
		 int maxValue = 2000;
		 // Assuming the slider ranges from 0 to 2000 
		int targetValue = 627; 
		int xOffset = (int) ((targetValue / (double) maxValue) * sliderWidth); 
		Actions act = new Actions(driver);
		act.moveToElement(slider).click().dragAndDropBy(slider, xOffset, 0).perform();
		String slide_no= enter_number.getAttribute("value");
		return slide_no;

	}
	public String Enter_the_value(String no,String value) {
		Actions act=new Actions(driver);
		// Double-click to select the text in the input field
	    act.doubleClick(enter_number);
	 // Send the specified value to the input field and perform the action
	    act.sendKeys(enter_number, no).perform();
	 // Get the attribute value specified by the 'value' parameter from the input field
		String attr=enter_number.getAttribute(value);
		System.out.println(attr);
		return attr;// Return the attribute value
	}
	
	public String select_CPT_codes() {
		// Click on each CPT code checkbox CPT_99091.click();
		CPT_99091.click();
		CPT_99453.click();
		CPT_99454.click();
		CPT_99474.click();
		// Retrieve the text representing the total recurring reimbursement for all patients per month
		String retrive_amount=Total_Recurring_Reimbursement_for_all_Patients_Per_Month.getText();
		return retrive_amount;// Return the retrieved amount
	}

}
