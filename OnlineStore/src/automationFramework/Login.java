package automationFramework;

import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import automationFramework.ConfigFileReader;
import automationFramework.ConfigManager;
import automationFramework.ExcelReadData;
import automationFramework.Reporting;

public class Login extends TestNG_CreateXML_RunTime {
	
	public By FirstName = By.xpath("//input[@placeholder='First Name']");
	public By LastName = By.xpath("//input[@placeholder='Last Name']");
	public By EmailID = By.xpath("//input[@type='email']");
	public By Phone = By.xpath("//input[@type='tel']");
	public By Country = By.xpath("//select[@id='countries']");
	public By SubmitBtn = By.id("submitbtn");
	
	public HashMap<String,HashMap<String,String>> dictObj;
	
	private static Login LoginManager = new Login();
	//private static ConfigFileReader configFileReader;
 
	private Login() {
	}
 
	 public static Login getInstance( ) {
	      return LoginManager;
	 }
	
	
	public WebDriver GetDriver() {
		String exePath;
		exePath = ConfigManager.getInstance().getConfigReader().getDriverPath();
		System.setProperty("webdriver.chrome.driver", exePath);
		WebDriver driver = new ChromeDriver();
		driver.get(ConfigManager.getInstance().getConfigReader().getApplicationUrl());
		this.Wait(driver).until(ExpectedConditions.visibilityOf(driver.findElement(FirstName)));
		driver.manage().window().maximize();
		return driver;
	}
	
	public WebDriverWait Wait(WebDriver driver) {
		WebDriverWait wait = new WebDriverWait(driver, 15);
		return wait;
	}
	
	@Test
	public void SignIn() throws Exception {
		dictObj = ExcelReadData.getInstance().returnData();
		
		Reporting.getInstance().EditFields(driver.findElement(FirstName), dictObj.get("Details").get("FirstName"));
		Reporting.getInstance().EditFields(driver.findElement(LastName), dictObj.get("Details").get("LastName"));
		Reporting.getInstance().EditFields(driver.findElement(EmailID), dictObj.get("Details").get("EmailAddress"));
		Reporting.getInstance().EditFields(driver.findElement(Phone), dictObj.get("Details").get("Phone"));
		
		driver.findElement(By.xpath("//input[@type='radio' and @value='"+dictObj.get("Details").get("Gender")+"']")).click();
		new Select(driver.findElement(Country)).selectByValue(dictObj.get("Details").get("Country"));
		
		driver.findElement(SubmitBtn).submit();
	}
	
	@Test
	public void Display() throws Exception {
		System.out.println("Display Method executed");
	}
}
