package com.Amazon.pages;

//import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.Amazon.common.GenericMethods;

public class LoginPage {

	
	private WebDriver driver;
	public GenericMethods gm;
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	    gm = new GenericMethods(driver);
		
	}
	@FindBy(xpath="//span[@id='nav-link-accountList-nav-line-1']") 
	WebElement Home_page;
	@FindBy(xpath="//input[@id ='ap_email']") 
	WebElement txt_email;
	@FindBy(xpath="//input[@type='password']") 
	WebElement txt_password ;
	@FindBy(xpath="//span[@id ='continue']") 
	WebElement buttonContinue;
	@FindBy(xpath="//*[@id='signInSubmit']") 
	WebElement signButton;
	
	
	//By Homepage = By.xpath(" //*[@id='nav-link-accountList']/span");
	//By txt_email= By.xpath("//*[@id ='ap_email']");
	//By  = By.xpath("//input[@type='password']");
	//By buttonContinue= By.xpath("//*[@id ='continue']");
	//By signButton= By.xpath("//*[@id='signInSubmit']");
	
	public void loginTest(String mbn, String pwd) {
		
		gm.clickElement(Home_page);
		setMail(mbn);
		continueButton();
		setpwd(pwd);
		signInButton();
		
		}
	public void setMail(String mbn) {
	
	gm.enterElement(txt_email, mbn);
	}
	public void continueButton() {
		
		gm.clickElement(buttonContinue);
	}
	public void signInButton() {
		
		gm.clickElement(signButton);
	}
	public void setpwd(String pwd) {
		
		gm.enterElement(txt_password, pwd);
	}
}
