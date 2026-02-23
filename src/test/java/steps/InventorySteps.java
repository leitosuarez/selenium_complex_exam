package steps;

import driver.DriverSingleton;
import io.cucumber.java.en.Then;
import static org.assertj.core.api.Assertions.assertThat;

import org.openqa.selenium.WebDriver;
import page.InventoryPage;

public class InventorySteps {
    private final WebDriver driver = DriverSingleton.getDriver();
    private final InventoryPage inventoryPage = new InventoryPage(driver);

    @Then("I am redirected to the inventory page")
    public void iAmRedirectedToTheInventoryPage() {
        String title = driver.getTitle();
        assertThat(title).isEqualTo("Swag Labs");
    }

    @Then("I see the inventory page title {string}")
    public void iSeeTheInventoryPageTitle(String arg0) {
        assertThat(inventoryPage.getTitle()).isEqualTo(arg0);
    }
}
