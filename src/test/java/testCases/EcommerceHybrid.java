package testCases;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
//import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;


//import com.aventstack.extentreports.ExtentTest;



import java.io.IOException;
//import java.time.Duration;
import java.util.HashMap;
import java.util.List;
//import java.util.Set;

//import org.openqa.selenium.By;
//import org.openqa.selenium.Keys;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;
import testBase.BaseClass;
import pageObjects.CartPage;
import pageObjects.FormPage;
//import pageObjects.FormPage;
import pageObjects.ProductCatalogue;
//import org.testng.Assert;


//import io.appium.java_client.AppiumBy;
//import io.appium.java_client.android.Activity;
//import io.appium.java_client.android.nativekey.AndroidKey;
//import io.appium.java_client.android.nativekey.KeyEvent;

public class EcommerceHybrid extends BaseClass{
	
	@BeforeClass(alwaysRun = true)
	public void preSetupClass() {
	    formPage = new FormPage(driver);   // ✅ driver is ready here
	                // ✅ safe to use
	}


	
	@Test(dataProvider="getData", groups= {"Smoke"})
	public void FillForm(HashMap<String,String> input) throws InterruptedException
	{	
		formPage.setActivity();
		formPage.setNameField(input.get("name"));
		formPage.setGender(input.get("gender"));
		formPage.setCountrySelection(input.get("country"));
		ProductCatalogue productCatalogue = formPage.submitForm();
		productCatalogue.addItemToCartByIndex(0);
		productCatalogue.addItemToCartByIndex(0);
		CartPage cartPage = productCatalogue.goToCartPage();
		
		//Thread.sleep(2000);
//	WebDriverWait wait =new WebDriverWait(driver,Duration.ofSeconds(5));
//wait.until(ExpectedConditions.attributeContains(driver.findElement(By.id("com.androidsample.generalstore:id/toolbar_title")),"text" , "Cart"));
		double totalSum = cartPage.getProductsSum();
		double displayFormattedSum = cartPage.getTotalAmountDisplayed();
		AssertJUnit.assertEquals(totalSum, displayFormattedSum);
		cartPage.acceptTermsConditions();
		cartPage.submitOrder();
	
	//		
		
	}
	
	
	
	@DataProvider
	public Object[][] getData() throws IOException
	{
		List<HashMap<String, String>>	data =getJsonData(System.getProperty("user.dir")+"\\src\\test\\java\\testData\\eCommerce.json");
	//	return new Object[][] { {"rahul shetty","female","Argentina"},{"shetty MR","male","Argentina"}  };
		
		
		return new Object[][] { {data.get(0)},{data.get(1)}  };
	}
	
	
	//key-name ,value - rahul shetty
	// {   {hash},   {hash}    }   
	
	
}
