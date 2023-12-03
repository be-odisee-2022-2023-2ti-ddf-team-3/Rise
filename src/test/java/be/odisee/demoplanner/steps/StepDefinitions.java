package be.odisee.demoplanner.steps;

import java.time.Duration;
import java.util.List;
import java.util.Map;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;

import org.junit.Assert;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class StepDefinitions {

    WebDriver driver;

    @Given("^the marketeer is on the page where they can schedule a demo$")
    public void marketeer_is_on_the_page_to_schedule_a_demo() throws Throwable {
        driver = new ChromeDriver();
        driver.navigate().to("http://localhost:8080/nieuweDemo.html");
    }

    @When("^the marketeer enters \"([^\"]*)\" in the date field$")
    public void marketeer_enters_in_the_date_field(String date) throws Throwable {
        driver.findElement(By.id("date")).sendKeys(date);
    }

    @When("^the marketeer enters \"([^\"]*)\" in the schoolnaam field$")
    public void marketeer_enters_in_the_schoolnaam_field(String schoolName) throws Throwable {
        driver.findElement(By.id("schoolnaam")).sendKeys(schoolName);
    }

    @When("^the marketeer enters \"([^\"]*)\" in the emailadres field$")
    public void marketeer_enters_in_the_emailadres_field(String email) throws Throwable {
        driver.findElement(By.id("emailadres")).sendKeys(email);
    }

    @When("^the marketeer presses on the Submit button$")
    public void marketeer_presses_on_the_Submit_button() throws Throwable {
        driver.findElement(By.name("submit")).click();
    }

    class LabelData {
        String label;
        String data;
    }

    @Then("^the marketeer should see the following on the screen$")
    public void marketeer_should_see_the_following_on_the_screen(DataTable demoDetails) throws Throwable {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.textToBePresentInElementLocated(By.tagName("body"), "Details van demo"));

        // Wat zit er in de body van de HTML-pagina?
        String bodyText = driver.findElement(By.tagName("body")).getText();

        // Verkrijg de dataTable als een lijst van Map-objecten
        List<Map<String, String>> dataTable = demoDetails.asMaps();

        // Overloop elke rij ... check of de gewenste data aan wezig is
        for (Map<String, String> rowFromTable : dataTable) {
            // Construeer een OnderwerpStatusEntry-object met de gegevens van één rij
            LabelData ld = new LabelData();
            for (Map.Entry<String, String> entry : rowFromTable.entrySet()) {
                // pik het "label"-attribuut met haar waarde op
                if (entry.getKey().equals("label")) ld.label = entry.getValue();
                // pik het "data"-attribuut met haar waarde op
                if (entry.getKey().equals("data")) ld.data = entry.getValue();
            }
            // Check of "label: data" voorkomt in de bodyText
            String text2bFound = ld.label + " " + ld.data;
            Assert.assertTrue("Did not find this text: " + text2bFound + "\n", bodyText.contains(text2bFound));
        }
    }

    @When("^the marketeer clicks the Lijst van alle demo Link$")
    public void marketeer_clicks_the_List_of_All_Demo_Link() throws Throwable {
        driver.findElement(By.linkText("Lijst van alle demo")).click();
    }

    @Then("^the marketeer should see a list containing \"([^\"]*)\"$")
    public void marketeer_should_see_a_list_containing(String text2bFound) throws Throwable {
        // wacht tot de juiste pagina geladen is
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions
                .textToBePresentInElementLocated(By.tagName("body"), "Lijst van demo's"));
        String bodyText = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Did not find this text: "+text2bFound+"\n",bodyText.contains(text2bFound));
    }

    @Then("^the marketeer closes the browser$")
    public void marketeer_closes_the_browser() throws Throwable {
        driver.quit();
    }

    @When("^the marketeer tries to schedule a demo in the weekend$")
    public void marketeer_tries_to_schedule_demo_in_weekend() throws Throwable {
    }

    @Then("^the application should not allow the scheduling$")
    public void application_should_not_allow_scheduling() throws Throwable {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.tagName("body"), "Cannot schedule demos on weekends"));
    }
}
