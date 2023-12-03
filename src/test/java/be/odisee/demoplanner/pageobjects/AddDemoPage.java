package be.odisee.demoplanner.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AddDemoPage {

    protected WebDriver driver;

    public AddDemoPage(WebDriver driver) {
        this.driver = driver;
    }

    public AddDemoPage navigateAddDemoPage() {
        driver.navigate().to("http://localhost:8080/nieuweDemo.html");
        return new AddDemoPage(driver);
    }

    public String getPageText(){
        return driver.findElement(By.tagName("body")).getText();
    }

    public void closeBrowser(){
        driver.quit();
    }

    public void addField(String type, String data) {
        driver.findElement(By.id(type)).sendKeys(data);
    }

    public void clickElement(String elementNaam) {
        driver.findElement(By.name(elementNaam)).click();
    }

    public void driverWait() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public String listFinder() {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions
                .textToBePresentInElementLocated(By.tagName("body"), "Lijst van demo's"));
        return driver.findElement(By.tagName("body")).getText();
    }

    public String screenChecker() {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.textToBePresentInElementLocated(By.tagName("body"), "Details van demo"));

        // Wat zit er in de body van de HTML-pagina?
        return driver.findElement(By.tagName("body")).getText();
    }

}
