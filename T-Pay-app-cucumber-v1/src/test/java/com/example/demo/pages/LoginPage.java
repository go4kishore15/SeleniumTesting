package com.example.demo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage  {


//	WebDriver driver;
	@FindBy(name="username")
	WebElement userNameElement;
	@FindBy(name="password")
	WebElement passwordElement;
	@FindBy(tagName="button")
	WebElement loginBtnElement;
	
	public LoginPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
//		this.driver = driver;
	}
	
	public void SetUserName(String userName)
	{
		userNameElement.sendKeys(userName);
	}
	
	public void SetPassword(String password)
	{
		passwordElement.sendKeys(password);
	}
	
	public void LoginBtnClick()
	{
		loginBtnElement.click();
	}
	
}
