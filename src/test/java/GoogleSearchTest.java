import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GoogleSearchTest {
    private WebDriver driver;

    @BeforeEach
    void setUp() {
        WebDriverManager.chromedriver().setup(); // Auto-manages ChromeDriver
        driver = new ChromeDriver(); // Launch Chrome browser
    }

    @Test
    void googleSearchTest() {
        driver.get("https://www.google.com"); // Open Google

        WebElement searchBox = driver.findElement(By.name("q")); // Locate search box
        searchBox.sendKeys("Selenium WebDriver"); // Type search query
        searchBox.submit(); // Submit search

        // Pause execution for manual CAPTCHA solving
        System.out.println("Solve CAPTCHA manually, press Enter to continue...");
        try {
            Thread.sleep(60000); // Pause for 30 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // Wait for user input

        assertEquals("Selenium WebDriver - Google Search", driver.getTitle()); // Verify title
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit(); // Close the browser
        }
    }
}
