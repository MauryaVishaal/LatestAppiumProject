package Utilities;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.OutputType;
//import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public abstract class AppiumUtils {

    public AppiumDriverLocalService service;

    public Double getFormattedAmount(String amount) {
        return Double.parseDouble(amount.substring(1));
    }

    public List<HashMap<String, String>> getJsonData(String jsonFilePath) throws IOException {
        String jsonContent = FileUtils.readFileToString(new File(jsonFilePath), StandardCharsets.UTF_8);
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {});
    }

    public AppiumDriverLocalService startAppiumServer(String ipAddress, int port) {
        HashMap<String, String> environment = new HashMap<>();
        environment.put("PATH", "C:\\Program Files\\nodejs;" + System.getenv("PATH"));

        // Path to Appium's main.js (default for global installation on Windows)
        File appiumMainJs = new File("C:\\Users\\Fleek\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js");

        service = new AppiumServiceBuilder()
                .withAppiumJS(appiumMainJs)
                .withEnvironment(environment)
                .withIPAddress(ipAddress)
                .usingPort(port)
                .build();

        service.start();
        return service;
    }

    public void waitForElementToAppear(WebElement ele, AppiumDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.attributeContains(ele, "text", "Cart"));
    }

    public String getScreenshotPath(String testCaseName, AppiumDriver driver) throws IOException {
        File source = driver.getScreenshotAs(OutputType.FILE);
        String destinationFile = System.getProperty("user.dir") + "\\reports\\" + testCaseName + ".png";
        FileUtils.copyFile(source, new File(destinationFile));
        return destinationFile;
    }
}
