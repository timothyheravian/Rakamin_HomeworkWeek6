import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Login {
    @Test
    public void success_login(){
        WebDriver driver;
        String baseUrl = "https://www.saucedemo.com/";

        WebDriverManager.chromedriver().setup();
        //ChromeOptions opt = new ChromeOptions();

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(baseUrl);
        String title = driver.getTitle();
        System.out.println(title);

        //username field
        WebElement element1 = driver.findElement(By.id("user-name"));
        element1.isDisplayed();
        element1.click();
        element1.sendKeys("standard_user");
        element1.getText();

        //password field
        WebElement element2 = driver.findElement(By.id("password"));
        element2.isDisplayed();
        element2.click();
        element2.sendKeys("secret_sauce");
        element2.getText();

        //login button
        WebElement element3 = driver.findElement(By.id("login-button"));
        element3.isDisplayed();
        element3.click();

        //driver.findElement(By.xpath("")).isDisplayed();
        //assertion dashboard
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
    @Test
    public void failed_login(){
        WebDriver driver;
        String baseUrl = "https://www.saucedemo.com/";

        WebDriverManager.chromedriver().setup();
        //ChromeOptions opt = new ChromeOptions();

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(baseUrl);
        String title = driver.getTitle();
        System.out.println(title);

        //username field
        WebElement element1 = driver.findElement(By.id("user-name"));
        element1.isDisplayed();
        element1.click();
        element1.sendKeys("standard_userr");
        element1.getText();

        //password field
        WebElement element2 = driver.findElement(By.id("password"));
        element2.isDisplayed();
        element2.click();
        element2.sendKeys("secret_sauce");
        element2.getText();

        //login button
        WebElement element3 = driver.findElement(By.id("login-button"));
        element3.isDisplayed();
        element3.click();

        //driver.findElement(By.xpath("")).isDisplayed();
        //assertion failed login
        String errorMessage = driver.findElement(By.xpath("//h3[contains(text(), 'Epic sadface: Username and password do not match any user in this service')]")).getText();
        Assert.assertEquals(errorMessage, "Epic sadface: Username and password do not match any user in this service");

        driver.close();
    }

}