package com.example.demo.bdd;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.example.demo.pages.AccountsPage;
import com.example.demo.pages.HomePage;
import com.example.demo.pages.LoginPage;
import com.example.demo.pages.PayPage;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class LoginUISteps {
	WebDriver driver;
	LoginPage loginPage;
	HomePage homePage;
	PayPage payPage;
	AccountsPage accountsPage;

	@Given("^I am on Login Page$")
    public void i_am_on_login_page() throws Throwable {
    	System.setProperty("webdriver.chrome.driver", "F:\\Drivers\\chromedriver.exe");
    	driver = new ChromeDriver();
		driver.get("http://localhost:8080/login.html");
		loginPage = new LoginPage(driver);
		homePage = new HomePage(driver);
		payPage = new PayPage(driver);
		accountsPage =  new AccountsPage(driver);
    }

	@When("^i fillup (.+)$")
    public void i_fill_username_something(String strArg1) throws Throwable {
        loginPage.SetUserName(strArg1);
    }

	 @And("^i fillingup  (.+)$")
    public void i_fill_password_something(String strArg1) throws Throwable {
        loginPage.SetPassword(strArg1);
    }
    
    @And("^i press loginButton$")
    public void i_press_loginbutton() throws Throwable {
        loginPage.LoginBtnClick();
    }
    
    @Then("^It takes to homepage and see welcome with (.+)$")
    public void it_takes_to_homepage_and_see_welcome_with_something(String strArg1) throws Throwable {
        String actual = homePage.getUserName();
        assertEquals(actual,strArg1);
    }
}
