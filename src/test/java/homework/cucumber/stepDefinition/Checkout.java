package homework.cucumber.stepDefinition;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.time.Duration;

import java.util.concurrent.TimeUnit;

public class Checkout {

    WebDriver driver;
    String baseUrl = "https://www.saucedemo.com/";

    @Given("user is already add the product and click checkout")
    public void userIsAlreadyAddTheProductAndClickCheckout() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(baseUrl);

        //user is login
        WebElement username_field = driver.findElement(By.id("user-name"));
        username_field.isDisplayed();
        username_field.click();
        username_field.sendKeys("standard_user");
        username_field.getText();

        WebElement password_field = driver.findElement(By.id("password"));
        password_field.isDisplayed();
        password_field.click();
        password_field.sendKeys("secret_sauce");
        password_field.getText();

        WebElement login_button = driver.findElement(By.id("login-button"));
        login_button.isDisplayed();
        login_button.click();

        //user is in dashboard page
        String judul = driver.findElement(By.xpath("//div[contains(text(), 'Swag Labs')]")).getText();
        Assert.assertEquals(judul, "Swag Labs");

        String produk1 = driver.findElement(By.xpath("//div[contains(text(), 'Sauce Labs Backpack')]")).getText();
        Assert.assertEquals(produk1, "Sauce Labs Backpack");

        String produk2 = driver.findElement(By.xpath("//div[contains(text(), 'Sauce Labs Bike Light')]")).getText();
        Assert.assertEquals(produk2, "Sauce Labs Bike Light");

        //user is choosing product a product
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        driver.findElement(By.id("add-to-cart-sauce-labs-bike-light")).click();

        //user is in cart page
        driver.findElement(By.id("shopping_cart_container")).click();

        String product_1 = driver.findElement(By.xpath("//div[contains(text(), 'Sauce Labs Backpack')]")).getText();
        Assert.assertEquals(product_1, "Sauce Labs Backpack");

        String product_2 = driver.findElement(By.xpath("//div[contains(text(), 'Sauce Labs Bike Light')]")).getText();
        Assert.assertEquals(product_2, "Sauce Labs Bike Light");

        driver.findElement(By.id("checkout")).click();

    }

    @When("user is filling checkout information")
    public void userIsFillingCheckoutInformation() {
        //user input checkout information
        WebElement firstname_field = driver.findElement(By.id("first-name"));
        firstname_field.isDisplayed();
        firstname_field.click();
        firstname_field.sendKeys("Timothy");
        firstname_field.getText();

        WebElement lastname_field = driver.findElement(By.id("last-name"));
        lastname_field.isDisplayed();
        lastname_field.click();
        lastname_field.sendKeys("Heravian");
        lastname_field.getText();

        WebElement postal_field = driver.findElement(By.id("postal-code"));
        postal_field.isDisplayed();
        postal_field.click();
        postal_field.sendKeys("16320");
        postal_field.getText();

        driver.findElement(By.id("continue")).click();
    }

    @And("user checkout the product and click finish button")
    public void userCheckoutTheProductAndClickFinishButton() {
        //user in checkout overview page
        String product_1 = driver.findElement(By.xpath("//div[contains(text(), 'Sauce Labs Backpack')]")).getText();
        Assert.assertEquals(product_1, "Sauce Labs Backpack");

        String product_2 = driver.findElement(By.xpath("//div[contains(text(), 'Sauce Labs Bike Light')]")).getText();
        Assert.assertEquals(product_2, "Sauce Labs Bike Light");

        String payment_info = driver.findElement(By.xpath("//div[contains(text(), 'Payment Information')]")).getText();
        Assert.assertEquals(payment_info, "Payment Information");

        String shipping_info = driver.findElement(By.xpath("//div[contains(text(), 'Shipping Information')]")).getText();
        Assert.assertEquals(shipping_info, "Shipping Information");

        String price_total = driver.findElement(By.xpath("//div[contains(text(), 'Price Total')]")).getText();
        Assert.assertEquals(price_total, "Price Total");

        driver.findElement(By.id("finish")).click();
    }

    @Then("user will successfully pay the product")
    public void userWillSuccessfullyPayTheProduct() {
        String price_total = driver.findElement(By.xpath("//h2[contains(text(), 'Thank you for your order!')]")).getText();
        Assert.assertEquals(price_total, "Thank you for your order!");

        driver.close();
    }

}