package com.Amazon.common;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GenericMethods {

	
	public WebDriver driver;
	public WebDriverWait wait;
	
	public GenericMethods(WebDriver driver) {
		this.driver=driver;
		 wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}
	
	public void enterElement(WebElement elm, String valu) {
		
		try {
			wait.until(ExpectedConditions.visibilityOf(elm));
			elm.isEnabled();
			elm.clear();
			elm.sendKeys(valu);
			/*
			 * if (elm.getAttribute("value").equals(valu)){ System.out.println("PASSED"); }
			 * else { System.out.println("Failed"); }
			 */
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		
		
	}
public void clickElement(WebElement elm) {
		
		   
			try {
				wait.until(ExpectedConditions.elementToBeClickable(elm));
				elm.click();
				
					System.out.println("PASSED");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
			// TODO Auto-generated catch block
			
	}
}
