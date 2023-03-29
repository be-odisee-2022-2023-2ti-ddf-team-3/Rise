package be.odisee.demoplanner.acceptancetests.steps;

import java.time.Duration;
import java.util.List;
import java.util.Map;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;

import org.junit.Assert;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class StepDefinitions {
	
	WebDriver driver;
	
	@Given("^I am on the page where I can introduce a new person$")
	public void i_am_on_the_page_where_I_can_introduce_a_new_person() throws Throwable {

		// voor het gemak zetten we de chromedriver in de root van het project
		// indien path de backslashes in het path escapen
		// chromedriver voor Linux, chromedriver.exe voor Windows 64bit
		// zorg dat de juiste property in - en de andere uit commentaar staat
		// wat er nu bijzit is voor Chrome version 107.0.5304.X
		// System.setProperty("webdriver.chrome.driver", "chromedriver");
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");

		// Met de nieuwste versie van Selenium Chromedriver 111
		// kwam er Java.io.IOException: Invalid Status code=403 text=Forbidden
		// --remote-allow-origins=* bleek nodig
		ChromeOptions options = new ChromeOptions();
		options.addArguments("disable-infobars");   // die infobars zijn vervelend
		options.addArguments("--remote-allow-origins=*"); // dit bleek nu nodig

		driver = new ChromeDriver(options);
		driver.navigate().to("http://localhost:8080/nieuwePersoon.html");
	}

	@When("^I enter \"([^\"]*)\" in the ([^\"]*) field$")
	public void i_enter_in_the_firstname_field(String enteredText, String fieldName) throws Throwable {

		driver.findElement(By.id(fieldName)).sendKeys(enteredText);
	}

	@When("^I press on the Submit button$")
	public void i_press_on_the_Submit_button() throws Throwable {
		driver.findElement(By.name("submit")).click();
	}

	class LabelData {
		String label;
		String data;
	}
	
	@Then("^I should see the following on the screen$")
	public void i_should_see_the_following_on_the_screen(DataTable checklistDatable) throws Throwable {
		// wacht tot de juiste pagina geladen is
		new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions
												.textToBePresentInElementLocated(By.tagName("body"), "Details van persoon"));

		// Wat zit er in de body van de HTML-pagina?
		String bodyText = driver.findElement(By.tagName("body")).getText();

		// Verkrijg de dataTable als een lijst van Map-objecten
		List<Map<String, String>> dataTable = checklistDatable.asMaps();

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
			Assert.assertTrue("Did not find this text: "+text2bFound+"\n",bodyText.contains(text2bFound));
		}

	}

	@When("^I click the Lijst van alle personen Link$")
	public void i_click_the_Home_Link() throws Throwable {
		driver.findElement(By.linkText("Lijst van alle personen")).click();
	}

	@Then("^I should see a list containing \"([^\"]*)\"$")
	public void i_should_see_a_list_containing(String text2bFound) throws Throwable {
		// wacht tot de juiste pagina geladen is
		new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions
												.textToBePresentInElementLocated(By.tagName("body"), "Lijst van personen")); 
		String bodyText = driver.findElement(By.tagName("body")).getText();
		Assert.assertTrue("Did not find this text: "+text2bFound+"\n",bodyText.contains(text2bFound));
	}

	@Then("^I close the browser$")
	public void i_close_the_browser() throws Throwable {
		driver.quit();
	}
}
