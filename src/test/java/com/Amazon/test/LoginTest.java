package com.Amazon.test;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

//import org.testng.annotations.Test;

import com.Amazon.pages.LoginPage;

public class LoginTest extends BaseTest {
	
	@BeforeClass
	public void launchApp(){
		data = readTestData("Login");
		launchBrowser();
		 
	}
	@AfterClass
	public void closeApp() {
		tearDown();
	}
	@Test
	public void validLogin() {
		
		LoginPage lp = new LoginPage(driver);
		lp.loginTest(data.get(0).get("Userid"), data.get(0).get("Password"));
		System.out.println("validlogin");
	}

}
