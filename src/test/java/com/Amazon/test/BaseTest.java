package com.Amazon.test;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

	public WebDriver driver;
	public Properties props;
    public 	List<Map<String, String>> data;

	public void launchBrowser() 
	{
		readproperties();
		
	   System.out.println(data.get(1).get("Userid"));
		System.out.println(data.get(1).get("Password"));
//		System.exit(0);
		if(props.getProperty("browser").equals("chrome"))
		{
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();
		}
		else if(props.getProperty("browser").equals("firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
			driver=new FirefoxDriver();
		}
		else if(props.getProperty("browser").equals("edge"))
		{
			WebDriverManager.edgedriver().setup();
			driver=new EdgeDriver();
		}
		driver.manage().window().maximize();
		driver.get(props.getProperty("AppUrl"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Integer.parseInt(props.getProperty("GlobalTimeout"))));

	}

	public void tearDown() {
		driver.quit();
	}

	public void readproperties()  {
		try {
			props= new Properties();
			FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "/src/test/resources/config/settings.properties");
			props.load(fis);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	
	}
	
	public List<Map<String, String>> readTestData(String Sheet) {
		Connection connection =null;
		Recordset recordset=null;
		List<Map<String, String>> Allmap =null;
		try {

			Fillo fillo=new Fillo();
			connection=fillo.getConnection(System.getProperty("user.dir") + "//src//TestData//Data.xlsx");
			String strQuery="Select * from "+Sheet;
			recordset=connection.executeQuery(strQuery);
			List<String> lst =recordset.getFieldNames();
			 Allmap =new ArrayList<Map<String,String>>();

			while(recordset.next()){
				//	System.out.println(recordset.getField("Details"));
				Map<String, String> map= new HashMap<String, String>();
				for(int i=0;i<=lst.size()-1;i++) {
					System.out.println(lst.get(i));
					map.put(lst.get(i), recordset.getField(lst.get(i)));
				}
				Allmap.add(map);
			}
			return Allmap;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			recordset.close();
			connection.close();
		}
		return Allmap;
	}
	 
     
       //  FileInputStream file = new FileInputStream(new File(System.getProperty("user.dir") + "//src//TestData//Data.xlsx"));

//	 try {
//		  
//         // Reading file from local directory
//         FileInputStream file = new FileInputStream(
//             new File(System.getProperty("user.dir") + "//src//TestData//Data.xlsx"));
//
//         // Create Workbook instance holding reference to
//         // .xlsx file
//         XSSFWorkbook workbook = new XSSFWorkbook(file);
//
//         // Get first/desired sheet from the workbook
//         XSSFSheet sheet = workbook.getSheetAt(0);
//
//         // Iterate through each rows one by one
//         Iterator<Row> rowIterator = sheet.iterator();
//
//         // Till there is an element condition holds true
//         while (rowIterator.hasNext()) {
//
//             Row row = rowIterator.next();
//
//             // For each row, iterate through all the
//             // columns
//             Iterator<Cell> cellIterator
//                 = row.cellIterator();
//
//             while (cellIterator.hasNext()) {
//
//                 Cell cell = cellIterator.next();
//
//                 // Checking the cell type and format
//                 // accordingly
//                 switch (cell.getCellType()) {
//
//                 // Case 1
//                 case NUMERIC:
//                     System.out.print(
//                         cell.getNumericCellValue()
//                         + "t");
//                     break;
//
//                 // Case 2
//                 case STRING:
//                     System.out.print(
//                         cell.getStringCellValue()
//                         + "t");
//                     break;
//                 }
//             }
//
//             System.out.println("");
//         }
//
//         // Closing file output streams
//         file.close();
//     }
//
//     // Catch block to handle exceptions
//     catch (Exception e) {
//
//         // Display the exception along with line number
//         // using printStackTrace() method
//         e.printStackTrace();
//     }
// }
}

