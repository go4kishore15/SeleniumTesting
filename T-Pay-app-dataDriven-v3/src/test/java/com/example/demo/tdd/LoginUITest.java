package com.example.demo.tdd;

import static org.testng.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.example.demo.pages.AccountsPage;
import com.example.demo.pages.HomePage;
import com.example.demo.pages.LoginPage;
import com.example.demo.pages.PayPage;
import com.mysql.jdbc.Driver;

public class LoginUITest {
	WebDriver driver;
	LoginPage loginPage;
	HomePage homePage;
	PayPage payPage;
	AccountsPage accountsPage;

	final static Logger logger = LogManager.getLogger(LoginUITest.class.getName());

	@BeforeSuite
	public void initDriver() {
//		System.setProperty("webdriver.chrome.driver", "F:\\Drivers\\chromedriver.exe");		
	}

	@Parameters({ "browser" })
	@BeforeTest
	public void launchBrowser(String browser) {
		if (browser.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", "F:\\Drivers\\chromedriver.exe");
			driver = new ChromeDriver();
		}
		if (browser.equals("firefox"))
		{
			System.setProperty("webdriver.gecko.driver", "F:\\Drivers\\geckodriver.exe");
			driver = new FirefoxDriver();
		}
		if (browser.equals("headless"))
		{
//			System.setProperty("phantomjs.binary.path", "F:\\Drivers\\phantomjs-2.1.1-windows\\bin\\phantomjs.exe");
//			driver = new PhantomJSDriver();
			driver = new HtmlUnitDriver();
			
		}

		driver.get("http://localhost:8080/login.html");
		logger.debug("Opening the browser with TPayApp");
		loginPage = new LoginPage(driver);
		homePage = new HomePage(driver);
		payPage = new PayPage(driver);
		accountsPage = new AccountsPage(driver);
	}

	@DataProvider(name = "user_credentials")
	public Object[][] getData() throws IOException {

		return new Object[][] { { "user1", "user1pass" }, { "user2", "user2pass" } };

//		return getDataFromCSVFile();

//		return getDataFromExcel();

//		return getDataFromSqlDB();
	}

	@Test(dataProvider = "user_credentials")
	public void ValidLogin(String username, String password) {
		logger.trace("Processing Valid login with username and password");
		loginPage.SetUserName(username);
		loginPage.SetPassword(password);
		loginPage.LoginBtnClick();

		String actual = homePage.getUserName();
		assertEquals(actual, username);
	}

	@AfterMethod
	public void logoutMethod() {
		logger.warn("Logging out of the WebPage");
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
		logger.error("Driver is going to get closed");
		driver.close();
	}

	public Object[][] getDataFromExcel() throws IOException {
		Object[][] testData = null;
		// Opening the Excel File
		FileInputStream file = new FileInputStream("UserCredentials.xlsx");
		// OPening the workbook
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		// Open the Sheet and pass the sheet name "Login"
		XSSFSheet loginSheet = workbook.getSheet("Login");
		// to get the number of row in the excel
		int numberOfData = loginSheet.getPhysicalNumberOfRows();
		System.out.println(numberOfData);
		//
		testData = new Object[numberOfData][2];

		for (int i = 0; i < numberOfData; i++) {
			XSSFRow row = loginSheet.getRow(i);
			// Goto the row
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

	// TestData from SQL
	public Object[][] getDataFromSqlDB() throws SQLException {
		Object[][] testData = null;
		// Step1 : register the driver
		DriverManager.registerDriver(new Driver());
		// Step 2: create the connection
		String url = "jdbc:mysql://localhost:3306/tpaydb";
		String user = "root";
		String password = "Bhar@1981";
		Connection connection = DriverManager.getConnection(url, user, password);
		// Step 3: Query the DB and get the values and update Object[][]
		String sql = "select * from tpaydb.userdetails";
		// execute the query create connection
		Statement sqlStatement = connection.createStatement();
		ResultSet rs = sqlStatement.executeQuery(sql);

		List<String[]> lines = new ArrayList<String[]>();

		while (rs.next()) {
			String[] line = new String[2];
			line[0] = rs.getString(1);
			line[1] = rs.getString(2);
			lines.add(line);
		}

		String[][] sdata = new String[lines.size()][0];
		testData = lines.toArray(sdata);

		// Step 4: Close the connection
		connection.close();

		return testData;
	}

	public Object[][] getDataFromCSVFile() throws IOException {

		Object[][] testData = null;
		// from CSV file
		// Open the file for reading the data
		File file = new File("UserCredentials.csv");
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		String line = null;
		List<String[]> lines = new ArrayList<String[]>();
		// Parsing the data and adding to the list
		while ((line = br.readLine()) != null) {
			System.out.println(line);
			lines.add(line.split(","));
			System.out.println(lines.get(0)[0]);
			System.out.println(lines.get(0)[1]);
		}
		// two dimensions string array first one specifies how many times the test to
		// run
		String[][] data = new String[lines.size()][0];
		// converting the string data to object array
		testData = lines.toArray(data);

		return testData;
	}

}
