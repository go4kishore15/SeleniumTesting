package com.example.demo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PayPage extends BasePage {
	WebDriver driver;

	@FindBy(name="fromAccNum")
	WebElement fromAccNumberField ;
	@FindBy(name="toAccNum")
	WebElement toAccNumberField  ;
	@FindBy(name="amount")
	WebElement amountField  ;
	@FindBy(tagName="button")
	WebElement transferBtn ;
	
	public PayPage(WebDriver driver)
	{
		super(driver);
		PageFactory.initElements(driver, this);
	}

	 public void fileTxnForm(String fromAccNum, String toAccNum, double amount)
	 {
		 fromAccNumberField.sendKeys(fromAccNum);
		 toAccNumberField.sendKeys(toAccNum);
		 amountField.sendKeys(String.valueOf(amount));
	 }
	
	public void doTransfer()
	{
		transferBtn.submit();
	}
}
