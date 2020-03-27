package com.example.demo.tdd;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
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
	public void initDriver()
	{
		System.setProperty("webdriver.chrome.driver", "F:\\Drivers\\chromedriver.exe");
	}
	
	@BeforeTest
	public void launchBrowser()
	{
		driver = new ChromeDriver();
		driver.get("http://localhost:8080/login.html");
		loginPage = new LoginPage(driver);
		homePage = new HomePage(driver);
		payPage = new PayPage(driver);
		accountsPage =  new AccountsPage(driver);
		
	}

	@Test(priority  = 0)
	public void ValidLogin()
	{
		loginPage.SetUserName("user1");
		loginPage.SetPassword("user1pass");
		loginPage.LoginBtnClick();		
		String actual = homePage.getUserName();
		assertEquals(actual,"user1");
	}
	
	
	@Test(priority = 1)
	public void TxnMoney()
	{	
		homePage.goToPayPage();
		payPage.fileTxnForm("1", "2", 100.0);
		payPage.doTransfer();
		String accBalVal = accountsPage.GetAccountBalance();
		assertEquals(accBalVal,"498.0");
	}
	
	@AfterTest
	public void terminateBrowser()
	{
//		driver.close();
	}
	
}
