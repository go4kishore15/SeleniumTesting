package com.example.demo.tdd;

import static org.testng.Assert.assertEquals;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.example.demo.pages.AccountsPage;
import com.example.demo.pages.HomePage;
import com.example.demo.pages.LoginPage;
import com.example.demo.pages.PayPage;

public class LoginUITest {
	WebDriver driver;
	LoginPage loginPage;
	HomePage homePage;
	PayPage payPage;
	AccountsPage accountsPage;

	@BeforeSuite
	public void initDriver() {
		System.setProperty("webdriver.chrome.driver", "F:\\Drivers\\chromedriver.exe");
	}

	@BeforeTest
	public void launchBrowser() {
		driver = new ChromeDriver();
		driver.get("http://localhost:8080/login.html");
		loginPage = new LoginPage(driver);
		homePage = new HomePage(driver);
		payPage = new PayPage(driver);
		accountsPage = new AccountsPage(driver);

	}

	@DataProvider(name = "user_credentials")
	public Object[][] getData() throws IOException  {
		
		Object[][] testData = null;
//		return new Object[][] { 
//			{ "user1", "user1pass" }, 
//			{ "user2", "user2pass" } 
//			};
		// read from the file
		// parsing to seperate values using the delimiter ,
		// retun Object[][]
		
		// from CSV  file 
//		// Open the file for reading the data
//		File file =new File("UserCredentials.csv");
//		FileReader fr = new FileReader(file);		
//		BufferedReader br = new BufferedReader(fr);		
//		String line = null;
//		List<String[]> lines = new ArrayList<String[]>();
//		// Parsing the data and adding to the list
//		while((line = br.readLine())!=null)
//		{
//			System.out.println(line);
//			lines.add(line.split(","));
//		}
//		// two dimensions string array first one specifies how many times the test to run
//		String[][] data = new String[lines.size()][0];
//		// converting the string data to object array
//		testData = lines.toArray(data);
		
		// Opening the  Excel File
		FileInputStream file = new FileInputStream("UserCredentials.xlsx");
		// OPening the workbook
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		// Open the Sheet and pass the sheet name "Login"
		XSSFSheet loginSheet = workbook.getSheet("Login");
		//to get the number of row in the excel 
		int numberOfData = loginSheet.getPhysicalNumberOfRows();
		System.out.println(numberOfData);
		//
		testData = new Object[numberOfData][2];
		
		for(int i=0;i<numberOfData;i++)
		{
			XSSFRow row = loginSheet.getRow(i);
			XSSFCell username = row.getCell(0);
			XSSFCell password = row.getCell(1);
			// to get the value of the cells
			testData[i][0] = username.getStringCellValue();
			testData[i][1] = password.getStringCellValue();			
			System.out.println(testData[i][0]);
			System.out.println(testData[i][1]);
		}
				
		return testData;
	}

	@Test(dataProvider = "user_credentials")
	public void ValidLogin(String username, String password) {
		loginPage.SetUserName(username);
		loginPage.SetPassword(password);
		loginPage.LoginBtnClick();
		String actual = homePage.getUserName();
		assertEquals(actual, username);
	}

	@AfterMethod
	public void logoutMethod() {
		homePage.logout();
	}

//    @Test(dependsOnMethods = {"ValidLogin"})
	@Test(enabled = false)
	public void TxnMoney() {
		homePage.goToPayPage();
		payPage.fileTxnForm("1", "2", 100.0);
		payPage.doTransfer();
		String accBalVal = accountsPage.GetAccountBalance();
		assertEquals(accBalVal, "400.0");
	}

	@AfterTest
	public void terminateBrowser() {
		driver.close();
	}

}
