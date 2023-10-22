package homework.cucumber.stepDefinition;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.time.Duration;

import java.util.concurrent.TimeUnit;

public class Add_to_cart {
    WebDriver driver;
    String baseUrl = "https://www.saucedemo.com/";

    @Given("user is in dashboard")
    public void user_is_in_dashboard(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(baseUrl);

        //username field
        WebElement username_field = driver.findElement(By.id("user-name"));
        username_field.isDisplayed();
        username_field.click();
        username_field.sendKeys("standard_user");
        username_field.getText();

        //password field
        WebElement password_field = driver.findElement(By.id("password"));
        password_field.isDisplayed();
        password_field.click();
        password_field.sendKeys("secret_sauce");
        password_field.getText();

        //login button
        WebElement login_button = driver.findElement(By.id("login-button"));
        login_button.isDisplayed();
        login_button.click();

        String judul = driver.findElement(By.xpath("//div[contains(text(), 'Swag Labs')]")).getText();
        Assert.assertEquals(judul, "Swag Labs");

        String produk1 = driver.findElement(By.xpath("//div[contains(text(), 'Sauce Labs Backpack')]")).getText();
        Assert.assertEquals(produk1, "Sauce Labs Backpack");

        String produk2 = driver.findElement(By.xpath("//div[contains(text(), 'Sauce Labs Bike Light')]")).getText();
        Assert.assertEquals(produk2, "Sauce Labs Bike Light");

        String produk3 = driver.findElement(By.xpath("//div[contains(text(), 'Sauce Labs Bolt T-Shirt')]")).getText();
        Assert.assertEquals(produk3, "Sauce Labs Bolt T-Shirt");

    }

    @When("user is choosing desired product and click add to cart button")
    public void user_is_choosing_desired_product_and_click_add_to_cart_button() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));

        //user is choosing product 1
        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();

        //user is choosing product 2
        driver.findElement(By.id("add-to-cart-sauce-labs-bike-light")).click();

    }

    @Then("user will successfully add product to cart")
    public void user_will_successfully_add_product_to_cart() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));

        //user click cart button
        driver.findElement(By.id("shopping_cart_container")).click();

        String product_1 = driver.findElement(By.xpath("//div[contains(text(), 'Sauce Labs Backpack')]")).getText();
        Assert.assertEquals(product_1, "Sauce Labs Backpack");

        String product_2 = driver.findElement(By.xpath("//div[contains(text(), 'Sauce Labs Bike Light')]")).getText();
        Assert.assertEquals(product_2, "Sauce Labs Bike Light");

        driver.close();
    }
}