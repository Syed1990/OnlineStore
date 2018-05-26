package automationFramework;

import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import javax.imageio.ImageIO;

import automationFramework.ConfigFileReader;
import automationFramework.ConfigManager;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Reporting extends TestNG_CreateXML_RunTime {
	private static Reporting ReportingManager = new Reporting();
	//private static ConfigFileReader configFileReader;
 
	private Reporting() {
	}
 
	 public static Reporting getInstance( ) {
	      return ReportingManager;
	 }
	 
	public void Report(String status, String elementName, String value, String filePath) {
		File file = new File(strFilePath + "\\Reports.htm");
		String strData, contents;
		strData = "";
		try {
			Long totalSize = file.getTotalSpace();
			if (totalSize == 0) {
				strData = "<!DOCTYPE html><html><head><style>table, th, td {    border: 1px solid black;    border-collapse: collapse;}</style></head><body><center><h2><u>Run Results</u></h2><p> </p><table style='width:100%'>  <tr>    <th>Start Time</th>    <th>End Time</th>     <th>Execution Time</th>  </tr></table><h3><u>Execution Details</u></h2><p> </p><table style='width:100%'>  <tr>    <th style='width:15%'>TC ID</th>    <th style='width:15%'>Execution Status</th>     <th style='width:25%'>Step Description</th>    <th style='width:30%'>Actual & Expected Result</th>    <th style='width:15%'>Screenshots</th>  </tr></center></table></body></html>";
			}else {
				FileReader fileReader = new FileReader(file);
				BufferedReader buffReader = new BufferedReader(fileReader);
				while ((contents = buffReader.readLine()) != null ) {
					strData = strData + contents;
				}
				buffReader.close();
				fileReader.close();
			}
			
			
			String strResult[] = strData.split("</center>");
			//System.out.println(strResult[0]);
			//System.out.println(strResult[1]);
			String temp = strResult[0] + "<tr><td style='width:15%';>"+ConfigManager.getInstance().getConfigReader().getTCName()+"</td>" + 
					"    <td style='color:blue','width:15%';><b>"+status+"</b></td>" + 
					"    <td style='width:25%';>Edit the field : <b>"+elementName+"</b></td>" + 
					"    <td style='color:blue','width:30%';>The value - <b>"+value+"</b> is edited for the field - <b>"+elementName+"</b></td>" + 
					"    <td style='color:blue','width:15%';><a href="+filePath+">Screenshot</a></td></tr></center>" + strResult[1];
			
			//System.out.println(temp);
			FileWriter fileWriter = new FileWriter(file);
			BufferedWriter buffWriter = new BufferedWriter(fileWriter);
			buffWriter.write(temp);
			buffWriter.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void EditFields(WebElement element, String value) throws Exception {
		element.sendKeys(value);
		String filePath = this.CaptureScreenshot();
		if(element.getAttribute("value").equalsIgnoreCase(value)) {
			this.Report("Pass", element.getAttribute("ng-model"), value, filePath);
		}else {
			this.Report("Fail", element.getAttribute("ng-model"), value, filePath);
		}
		
	}
	
	public String CaptureScreenshot() throws Exception {
		String screenshot_name = System.currentTimeMillis() + ".png";
        BufferedImage image = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
        File file = new File(strScreenshotsPath + "\\" + screenshot_name);
        ImageIO.write(image, "png", file);
        return file.getAbsolutePath();
	}
}
