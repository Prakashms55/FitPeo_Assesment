package BaseTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class base_test {
	// WebDriver instance used by the class
	public WebDriver driver;
	
	// Method to initialize the WebDriver based on the specified browser
	public  WebDriver intializebrowser(String browser) {
		switch (browser) {
		case "chrome":
			WebDriverManager.chromedriver().setup();// Setup ChromeDriver
			driver=new ChromeDriver();// Initialize ChromeDriver break;
			break;
		case "edge":
			WebDriverManager.edgedriver().setup();// Setup EdgeDriver
			driver=new EdgeDriver();// Initialize EdgeDriver break;
			break;
		case "firefox":
			WebDriverManager.firefoxdriver().setup();// Setup FirfoxDriver
			driver=new FirefoxDriver();// Initialize firefoxDriver break;
			break;
		default:
			break;
		}
		return driver;// Return the initialized WebDriver }
	}
	
	// Method to capture a screenshot
	public static  String capturescreen(WebDriver driver) throws Exception  {
		
		Date date=new Date();
		String FileName = date.toString().replace(":", "").replace(" ", "");// Format date as file name
		
		File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);	// Take screenshot	
		File des=new File("./screenshot/"+FileName+".png");// Define destination file path
		String destination=des.getAbsolutePath();// Get absolute path of the destination file
		FileUtils.copyFile(src, des);// Copy screenshot to destination
		return destination;// Return the destination path }
}
	// Method to retrieve properties from a file
		public static  String getproperties(String keyname) throws IOException {
			File file=new File("C:\\Users\\praka\\eclipse-workspace\\FitPeo_Assesment\\src\\test\\resources\\testNG_properties\\fitPeo.properties");
			FileInputStream stream=new FileInputStream(file);// Open file input stream
			Properties pro=new Properties();// Create Properties object
			pro.load(stream);// Load properties from the file
			return (String) pro.get(keyname);// Return the value for the specified key
			
		
}
}
