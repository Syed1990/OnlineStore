package automationFramework;

import java.io.File;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import automationFramework.ConfigFileReader;
import automationFramework.ConfigManager;
import automationFramework.Login;

import org.testng.TestNG;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import automationFramework.TestNG_CreateXML_RunTime;


public class FirstTestCase {

	static WebDriver driver;
	
	@Test
	public void main() throws InterruptedException {
		//driver = Login.getInstance().GetDriver();
		//Login.getInstance().SignIn(driver);
		
	}
	
}