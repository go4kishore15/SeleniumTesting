package com.example.demo.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BasePage {
	
	private static Logger logger = LogManager.getLogger(BasePage.class);
	
//   WebDriver driver;
	@FindBy(linkText = "Pay")
	WebElement payLink;
	@FindBy(linkText = "Accounts")
	WebElement accountsLink;
	@FindBy(linkText = "logout")
	WebElement logoutLink;

	public BasePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
//		this.driver = driver;
	}

	public void goToPayPage() {
		
		payLink.click();
	}

	public void goToAccountsPage() {
		accountsLink.click();
	}

	public void logout() {
		logoutLink.click();
	}
}
