package com.example.demo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountsPage extends BasePage {

	@FindBy(xpath="/html[1]/body[1]/div[1]/table[1]/tbody[1]/tr[2]/td[2]")
	WebElement accBalalanceElement;

	public AccountsPage(WebDriver driver)
	{
		super(driver);
		PageFactory.initElements(driver, this);
	}

	public String GetAccountBalance()
	{
		return accBalalanceElement.getText();
	}
	
}
