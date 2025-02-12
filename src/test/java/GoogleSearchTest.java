import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import com.github.javafaker.Faker;


import static org.junit.jupiter.api.Assertions.assertTrue;

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


        Faker testData = new Faker();
        String fullname = testData.name().fullName();

        WebElement searchBox = driver.findElement(By.name("q")); // Locate search box
        searchBox.sendKeys(fullname); // Search query
        searchBox.submit(); // Submit search

        // Pause execution for manual CAPTCHA solving
        System.out.println("Solve CAPTCHA manually in 60seconds to continue.");
        try {
            Thread.sleep(50000); // Pause for 50 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        assertTrue(driver.getTitle().contains(fullname)); // Verify title
        System.out.println(driver.getTitle().split(" - ")[0] + " " + "searched successfully");
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit(); // Close the browser
        }
    }
}
