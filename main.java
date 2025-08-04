import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileWriter;
import java.time.Duration;
import java.util.List;

public class LinkedInDesignerSearch {

    private static final String CHROME_DRIVER_PATH = "drivers/chromedriver.exe";
    private static final String LINKEDIN_LOGIN_URL = "https://www.linkedin.com/login";
    private static final String LINKEDIN_SEARCH_URL = "https://www.linkedin.com/search/results/people/?keywords=Designer";

    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_PATH);

        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        String email = System.getenv("LINKEDIN_EMAIL");
        String password = System.getenv("LINKEDIN_PASSWORD");

        if (email == null || password == null) {
            System.err.println("Error: Please set LINKEDIN_EMAIL and LINKEDIN_PASSWORD environment variables.");
            driver.quit();
            return;
        }

        try {
            loginToLinkedIn(driver, wait, email, password);
            List<WebElement> designerNames = searchForDesigners(driver, wait);
            saveResultsToCSV(designerNames);
            takeScreenshot(driver);
            System.out.println("Designer search completed successfully. Results saved to designer_search_results.csv");
        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }

    private static void loginToLinkedIn(WebDriver driver, WebDriverWait wait, String email, String password) {
        driver.get(LINKEDIN_LOGIN_URL);

        WebElement emailInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username")));
        WebElement passwordInput = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.xpath("//button[@type='submit']"));

        emailInput.sendKeys(email);
        passwordInput.sendKeys(password);
        loginButton.click();

        wait.until(ExpectedConditions.urlContains("/feed"));
    }

    private static List<WebElement> searchForDesigners(WebDriver driver, WebDriverWait wait) {
        driver.get(LINKEDIN_SEARCH_URL);

        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                By.cssSelector("span.entity-result__title-text > a > span[aria-hidden='true']"))
        );

        return driver.findElements(
                By.cssSelector("span.entity-result__title-text > a > span[aria-hidden='true']"));
    }

    private static void saveResultsToCSV(List<WebElement> names) throws Exception {
        try (FileWriter writer = new FileWriter("designer_search_results.csv")) {
            writer.write("Name\n");
            for (WebElement person : names) {
                String name = person.getText().trim();
                System.out.println("Name: " + name);
                writer.write(name + "\n");
            }
        }
    }

    private static void takeScreenshot(WebDriver driver) throws Exception {
        File screenshot = ((org.openqa.selenium.TakesScreenshot) driver)
                .getScreenshotAs(org.openqa.selenium.OutputType.FILE);
        File output = new File("designer_search_screenshot.png");
        if (screenshot.renameTo(output)) {
            System.out.println("Screenshot saved to: " + output.getAbsolutePath());
        } else {
            throw new Exception("Failed to save screenshot.");
        }
    }
}
