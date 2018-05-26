package automationFramework;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.text.ParseException;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;

public class TestCase_with_TestNG {

	public WebDriver driver;
	String exePath = "C:\\Users\\Syed Mateen\\chromedriver.exe";
	
	@Test
	public void main() throws InterruptedException {
		// TODO Auto-generated method stub
		TestCase_with_TestNG frst = new TestCase_with_TestNG();
		
		/*String exePath = "C:\\Users\\Syed Mateen\\IEDriverServer.exe";
		System.setProperty("webdriver.ie.driver", exePath);
		WebDriver driver = new InternetExplorerDriver();*/
		
		//WebDriverWait wait = new WebDriverWait(driver, 15);
		 
		//driver.get("http://demo.automationtesting.in/Static.html");
		//driver.get("http://jqueryui.com/sortable/");
		
		//driver.manage().window().maximize();
		
		//driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@class='demo-frame']")));
		
		//Actions action = new Actions(driver);
		
		//WebElement dragEle = driver.findElement(By.xpath("//div[@class='ui-resizable-handle ui-resizable-se ui-icon ui-icon-gripsmall-diagonal-se']"));
		//WebElement dragEle = driver.findElement(By.xpath("//li[text()='Item 1']"));
		//WebElement dropArea = driver.findElement(By.xpath("//li[text()='Item 4']"));
		
		//int y = dropArea.getLocation().y;
		//wait.until(ExpectedConditions.visibilityOf(dragEle));
		
		//System.out.println("y location - "+y);
		//action.dragAndDrop(dragEle, dropArea).build().perform();
		//action.dragAndDropBy(dragEle, 0, y).build().perform();
		//action.clickAndHold(dragEle).moveByOffset(400, 0).build().perform();

		String[] arrMonth = {"Syed","Mateen","Nadeem","Wasim","Mom","Dad"};
		arrMonth = this.sortArray(arrMonth, "Asc");
		for(String s: arrMonth) {
			System.out.println(s.toString());
		}
		
		//JavascriptExecutor js = (JavascriptExecutor) driver;
		//js.executeScript("alert('Exe is complete');", "");	
				
	}	
	
	
	 
	  public void beforeMethod() {
	 
	      //Put a Implicit wait, this means that any search for elements on the page could take the time the implicit wait is set for before throwing exception
		driver = new ChromeDriver();
		System.setProperty("webdriver.chrome.driver", exePath);
	      //driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	 
	      //Launch the Online Store Website
	     // driver.get("http://jqueryui.com/sortable/");
			
	     // driver.manage().window().maximize();
	 
	  }
	 
	  @AfterMethod
	 
	  public void afterMethod() {
	 
		  // Close the driver
	 
	      //driver.quit();
	 
	  }
	  
	  public String[] sortArray(String[] arrMonth, String sortType) {
		  String temp;
		  for(int i=0; i<arrMonth.length-1; i++) {
			  for(int j=i; j<arrMonth.length-1; j++) {
				  if (arrMonth[i].compareTo(arrMonth[j+1]) > 0) {
					  temp = arrMonth[i];
					  arrMonth[i] = arrMonth[j+1];
					  arrMonth[j+1] = temp;
				  }
			  }
		  }
		  
		  return arrMonth;
	  }
}