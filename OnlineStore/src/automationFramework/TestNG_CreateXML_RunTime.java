package automationFramework;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.ITestNGListener;
import org.testng.TestNG;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlInclude;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import automationFramework.ConfigFileReader;
import automationFramework.ConfigManager;
import automationFramework.ExcelReadData;
import automationFramework.Listener;

public class TestNG_CreateXML_RunTime {

	  public static WebDriver driver;
	  static TestNG testNG;
	  public static String strFilePath = "C:\\Temp\\" + System.currentTimeMillis();
	  public static String strScreenshotsPath = strFilePath + "\\Screenshots";
	  
	  @BeforeMethod
	  public WebDriver getdriver(){
	    if (driver == null){
	      driver = Login.getInstance().GetDriver();
	      return driver;
	    }else{
	      return driver;
	    }
	  }
	
	  @BeforeMethod
	  public void createFolder(){
		  File file = new File(strFilePath);
		  File filePath = new File(strScreenshotsPath);
	    if (!file.isDirectory()){
	      file.mkdir();
	    }
	    if (!filePath.isDirectory()) {
	    	filePath.mkdir();
	    }
	  }

	public static void main(String[] args) throws InterruptedException {
		
		XmlSuite xmlSuite = new XmlSuite();
		xmlSuite.setName("Regression");
		//xmlSuite.setVerbose(1);
		
		XmlTest xmlTest = new XmlTest(xmlSuite);
		xmlTest.setName("Testing");
		
		List<XmlClass> classes = new ArrayList<XmlClass>();
		List<String> classNames = new ArrayList<String>();
		List<Class<? extends ITestNGListener>> listeners = new ArrayList<Class<? extends ITestNGListener>>();
		
		classNames = ExcelReadData.getInstance().GetComponents();
		
		for(int i=0;i<classNames.size();i++) {
			XmlClass cls1 = new XmlClass(classNames.get(i));
			XmlInclude xmlInclude = new XmlInclude("SignIn");
			XmlInclude xmlInclude2 = new XmlInclude("Display");
			List<XmlInclude> includes = new ArrayList<XmlInclude>();
			includes.add(xmlInclude);
			includes.add(xmlInclude2);
			cls1.setIncludedMethods(includes);
			classes.add(cls1);
		}
		
		xmlTest.setXmlClasses(classes);
		 
		listeners.add(Listener.class);
		List<XmlSuite> suite = new ArrayList<XmlSuite>();
		suite.add(xmlSuite);
		
		
		testNG = new TestNG();
		testNG.setXmlSuites(suite);
		testNG.setListenerClasses(listeners);
		testNG.run();
	}
	

}
