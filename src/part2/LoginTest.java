package part2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class LoginTest {
    private WebDriver chromeDriver;
    private WebDriver firefoxDriver;

    @BeforeClass
    public void setUp() {
        ChromeOptions chromeoption = new ChromeOptions();
        chromeoption.addArguments("--remote-allow-origins=*");
        System.setProperty("webdriver.chrome.driver", "/opt/homebrew/bin/chromedriver");
        chromeDriver = new ChromeDriver(chromeoption);

        FirefoxOptions firefoxoption = new FirefoxOptions();
        firefoxoption.addArguments("--remote-allow-origins=*");
        System.setProperty("webdriver.gecko.driver", "/opt/homebrew/bin/geckodriver");
        firefoxDriver = new FirefoxDriver(firefoxoption);
    }

    @Test
    public void testLoginWithCorrectCredentials() {
        String username = "testuser";
        String password = "testpassword";

        testLogin(chromeDriver, username, password);
        testLogin(firefoxDriver, username, password);
    }

    private void testLogin(WebDriver driver, String username, String password) {
        driver.get("file:///Users/ulpanb/IdeaProjects/SeleniumProject/src/part2/html/login.html");

        WebElement usernameInput = driver.findElement(By.id("username"));
        WebElement passwordInput = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.xpath("//input[@type='submit']"));

        usernameInput.sendKeys(username);
        passwordInput.sendKeys(password);
        loginButton.click();

        // Check if the welcome page is loaded
        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals(currentUrl, "file:///Users/ulpanb/IdeaProjects/SeleniumProject/src/part2/html/welcome.html");
    }

    @Test
    public void testLoginWithIncorrectCredentials() {
        String username = "invaliduser";
        String password = "invalidpassword";

        testInvalidLogin(chromeDriver, username, password);
        testInvalidLogin(firefoxDriver, username, password);
    }

    private void testInvalidLogin(WebDriver driver, String username, String password) {
        driver.get("file:///Users/ulpanb/IdeaProjects/SeleniumProject/src/part2/html/login.html");

        WebElement usernameInput = driver.findElement(By.id("username"));
        WebElement passwordInput = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.xpath("//input[@type='submit']"));

        usernameInput.sendKeys(username);
        passwordInput.sendKeys(password);
        loginButton.click();

        // Check if the login page is still loaded
        String currentUrl = driver.getCurrentUrl();
        Assert.assertNotEquals(currentUrl, "file:///Users/ulpanb/IdeaProjects/SeleniumProject/src/part2/html/welcome.html");
    }

    @AfterClass
    public void tearDown() {
        chromeDriver.quit();
        firefoxDriver.quit();
    }
}

