package automationFramework;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.xml.XmlInclude;

import automationFramework.ConfigFileReader;
import automationFramework.ConfigManager;


public class ExcelReadData {
	
	private static ExcelReadData ExcelReaderManager = new ExcelReadData();
	String excelPath = ConfigManager.getInstance().getConfigReader().getExcelInputSheet();
	//private static ConfigFileReader configFileReader;
	
	private ExcelReadData() {
	}
 
	 public static ExcelReadData getInstance( ) {
	      return ExcelReaderManager;
	 }
 
	 /*public ConfigFileReader getConfigReader() {
		 return (configFileReader == null) ? new ConfigFileReader() : configFileReader;
	 }*/
	 
	public HashMap<String,HashMap<String,String>> returnData() throws NullPointerException {
		
		HashMap<String, HashMap<String, String>> dictData;
		HashMap<String,String> dictStep;
		dictData = new HashMap<String, HashMap<String, String>>();
		dictStep = new HashMap<String,String>();
		try {
			FileInputStream inputStream = new FileInputStream(excelPath);
			XSSFWorkbook workBook = new XSSFWorkbook(inputStream);
			//int Sheets = workBook.getNumberOfSheets();
			
			XSSFSheet sheet = workBook.getSheet("Steps");
			
			for(int i=1;i<=sheet.getLastRowNum();i++) {
				String Key = sheet.getRow(i).getCell(1).getStringCellValue();
				String sheetName = sheet.getRow(0).getCell(1).getStringCellValue();
				dictStep = ExcelReaderManager.loadData(workBook.getSheet(sheetName), Key, sheetName);
				dictData.put(sheetName,dictStep);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return dictData;
	}
	
	public HashMap<String,String> loadData(XSSFSheet tempSheet,String Key,String sheetName) throws NullPointerException{

		HashMap<String, String> dict;
		int Count = tempSheet.getLastRowNum();
		dict = new HashMap<String, String>();
		try {
			for(int i=1;i<=Count;i++) {
				if(tempSheet.getRow(i).getCell(0).getStringCellValue().equalsIgnoreCase(Key)) {
					int Col = tempSheet.getRow(0).getLastCellNum();
					for(int j=0;j<Col;j++) {
						dict.put(tempSheet.getRow(0).getCell(j).getStringCellValue(), tempSheet.getRow(i).getCell(j).getStringCellValue());
					}
				}
				
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return dict;
	}
	
	public List<String> GetComponents(){
		List<String> components = new ArrayList<String>();
		try {
			FileInputStream inputStream = new FileInputStream(excelPath);
			XSSFWorkbook workBook = new XSSFWorkbook(inputStream);
			
			XSSFSheet sheet = workBook.getSheet("New Steps");
			for(int i=1;i<=sheet.getLastRowNum();i++) {
				String Key = sheet.getRow(i).getCell(1).getStringCellValue();
				components.add(Key);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return components;
	}
	
	public List<XmlInclude> GetMethods(){
		List<String> Methods = new ArrayList<String>();
		List<XmlInclude> includeMethods = new ArrayList<XmlInclude>();
		try {
			FileInputStream inputStream = new FileInputStream(excelPath);
			XSSFWorkbook workBook = new XSSFWorkbook(inputStream);
			
			XSSFSheet sheet = workBook.getSheet("New Steps");
			for(int i=1;i<=sheet.getLastRowNum();i++) {
				String Key = sheet.getRow(i).getCell(2).getStringCellValue();
				String[] arr = Key.split(";");
				for(String a : arr) {
					Methods.add(a);
					XmlInclude inc = new XmlInclude(a) ;
					includeMethods.add(inc);
				}			
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return includeMethods;
	}
}
