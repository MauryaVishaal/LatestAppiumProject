package testCases;

import org.testng.annotations.Test;

//import com.google.common.collect.ImmutableMap;

import org.testng.AssertJUnit;

import org.openqa.selenium.By;
//import org.openqa.selenium.JavascriptExecutor;
import testBase.BaseClass;
//import org.testng.Assert;
//import org.testng.annotations.BeforeMethod;


import io.appium.java_client.AppiumBy;
//import io.appium.java_client.android.Activity;

public class Ecommerce_TC_03_cloud extends BaseClass{

	

	
	@Test

	public void FillForm_ErrorValidation() throws InterruptedException
	{
		
	//	driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Rahul Shetty");
		driver.hideKeyboard();
		driver.findElement(By.xpath("//android.widget.RadioButton[@text='Female']")).click();
		driver.findElement(By.id("android:id/text1")).click();
		driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Argentina\"));"));
		driver.findElement(By.xpath("//android.widget.TextView[@text='Argentina']")).click();
		driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();
		String toastMessage = driver.findElement(By.xpath("(//android.widget.Toast)[1]")).getAttribute("name");
		AssertJUnit.assertEquals(toastMessage,"Please enter your name");
		
		
			
	}
	

	
}
