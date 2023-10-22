package homework.cucumber.stepDefinition;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en.And;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.time.Duration;

import java.util.concurrent.TimeUnit;

public class Logout {
    WebDriver driver;
    String baseUrl = "https://www.saucedemo.com/";

    @Given("user is already login")
    public void user_is_already_login(){
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

    @When("user click burger button and click logout")
    public void user_click_burger_button_and_click_logout() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));

        //user click burger_button
        driver.findElement(By.id("react-burger-menu-btn")).click();

        //user click logout
        driver.findElement(By.id("logout_sidebar_link")).click();
    }

    @Then("user can successfully logout from the websites")
    public void user_can_successfully_logout_from_the_websites() {
        //username field assertion
        WebElement username_field = driver.findElement(By.xpath("//input[contains(@id, 'user-name')]"));
        username_field.isDisplayed();

        //password field assertion
        WebElement password_field = driver.findElement(By.xpath("//input[contains(@id, 'password')]"));
        password_field.isDisplayed();

        //login button assertion
        WebElement login_button = driver.findElement(By.xpath("//input[contains(@id, 'login-button')]"));
        login_button.isDisplayed();

        driver.close();
    }

}