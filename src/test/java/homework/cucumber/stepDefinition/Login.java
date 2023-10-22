package homework.cucumber.stepDefinition;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Login {
    WebDriver driver;
    String baseUrl = "https://www.saucedemo.com/";
    //WebDriverManager.chromedriver().setup();

    @Given("user is in login page")
    public void user_is_in_login_page(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(baseUrl);

        //username field assertion
        WebElement username_field = driver.findElement(By.xpath("//input[contains(@placeholder, 'Username')]"));
        username_field.isDisplayed();

        //password field assertion
        WebElement password_field = driver.findElement(By.xpath("//input[contains(@placeholder, 'Password')]"));
        password_field.isDisplayed();

        //login button assertion
        WebElement login_button = driver.findElement(By.xpath("//input[contains(@value, 'Login')]"));
        login_button.isDisplayed();

    }

    @When("user input valid credentials and click login button")
    public void user_input_valid_credentials_and_click_login_button() {
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
    }

    @Then("user can successfully login and redirect to dashboard page")
    public void user_can_successfully_login_and_redirect_to_dashboard_page() {
        String judul = driver.findElement(By.xpath("//div[contains(text(), 'Swag Labs')]")).getText();
        Assert.assertEquals(judul, "Swag Labs");

        String produk1 = driver.findElement(By.xpath("//div[contains(text(), 'Sauce Labs Backpack')]")).getText();
        Assert.assertEquals(produk1, "Sauce Labs Backpack");

        String produk2 = driver.findElement(By.xpath("//div[contains(text(), 'Sauce Labs Bike Light')]")).getText();
        Assert.assertEquals(produk2, "Sauce Labs Bike Light");

        String produk3 = driver.findElement(By.xpath("//div[contains(text(), 'Sauce Labs Bolt T-Shirt')]")).getText();
        Assert.assertEquals(produk3, "Sauce Labs Bolt T-Shirt");

        driver.close();
    }

    @When("user input invalid username and valid password and click login button")
    public void user_input_invalid_username_and_valid_password_and_click_login_button() {
        //username field
        WebElement username_field = driver.findElement(By.id("user-name"));
        username_field.isDisplayed();
        username_field.click();
        username_field.sendKeys("standard_user_admin");
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
    }

    @Then("user can not successfully login and still in login page")
    public void user_can_not_successfully_login_and_still_in_login_page() {
        String errorMessage = driver.findElement(By.xpath("//h3[contains(text(), 'Epic sadface: Username and password do not match any user in this service')]")).getText();
        Assert.assertEquals(errorMessage, "Epic sadface: Username and password do not match any user in this service");

        driver.close();
    }
}