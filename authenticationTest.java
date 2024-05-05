import java.io.FileInputStream;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.time.Duration;

public class authenticationTest {
    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    String osName = System.getProperty("os.name");
    readinput read = new readinput();
    String[] credentials = read.readCredentials();
    String username = credentials[0];
    String password = credentials[1];

    public void setUp() throws InterruptedException {
        if (osName.contains("Windows")) {
        	System.setProperty("webdriver.chrome.driver", "C:\\Users\\baoth\\Downloads\\chromedriver-win64\\chromedriver.exe");
        } else {
        	System.setProperty("webdriver.chrome.driver", "C:\\Users\\baoth\\Downloads\\chromedriver-win64\\chromedriver.exe");
        }

        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.navigate().to("https://cntttest.vanlanguni.edu.vn:18081/Phancong02/Account/Login");
        WebElement acceptButton = driver.findElement(By.id("details-button"));
        acceptButton.click();
        Thread.sleep(3000);
        WebElement acceptLink = driver.findElement(By.id("proceed-link"));
        acceptLink.click();
        WebElement submitButton = driver.findElement(By.id("OpenIdConnect"));
        submitButton.click();
        Thread.sleep(3000);

        // Input email
        driver.findElement(By.id("i0116")).sendKeys(username);
        // Click next
        WebElement nextButton = driver.findElement(By.id("idSIButton9"));
        nextButton.click();
        Thread.sleep(5000);
        // Input password
        driver.findElement(By.id("i0118")).sendKeys(password);
        // Click submit
        WebElement submitButton1 = driver.findElement(By.id("idSIButton9"));
        submitButton1.click();
        Thread.sleep(8000);
        // Click enter
        WebElement enterButton = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/form[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[3]/div[1]/div[2]/div[1]/div[3]/div[2]/div[1]/div[1]/div[2]/input[1]"));
        enterButton.click();
        Thread.sleep(8000);
    }

    public void testGetCurrentUrl() {
        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals(currentUrl, "https://cntttest.vanlanguni.edu.vn:18081/Phancong02/");
    }

    public void testGetCookies() {
        // Get cookies
        String cookies = driver.manage().getCookies().toString();
        System.out.println(cookies);
        // Save cookies to file config.properties
        readinput.writeCookies(cookies);
    }

    public void testLogout() {
        driver.findElement(By.id("dropdown-user")).click();
        driver.findElement(By.xpath("//body/div[2]/nav[1]/div[1]/ul[1]/li[2]/div[1]/a[2]")).click();
        driver.quit();
    }

    public void testUserCookies() throws InterruptedException, IOException  {
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.navigate().to("https://cntttest.vanlanguni.edu.vn:18081/Phancong02/Account/Login");
        WebElement acceptButton = driver.findElement(By.id("details-button"));
        acceptButton.click();
        Thread.sleep(3000);
        WebElement acceptLink = driver.findElement(By.id("proceed-link"));
        acceptLink.click();
        // Read cookies from file
        String cookies = readinput.readCookies();
        if (cookies != null && !cookies.isEmpty()) {
            // Split cookies into key-value pairs
            String[] cookiePairs = cookies.split(";");
            // Remove []
            cookiePairs[0] = cookiePairs[0].substring(1);
            cookiePairs[cookiePairs.length - 1] = cookiePairs[cookiePairs.length - 1].substring(0, cookiePairs[cookiePairs.length - 1].length() - 1);

            // Set cookies for the browser
            for (String cookiePair : cookiePairs) {
                String[] cookieParts = cookiePair.split("=", 2);
                if (cookieParts.length == 2) {
                    String cookieName = cookieParts[0].trim();
                    String cookieValue = cookieParts[1].trim();
                    driver.manage().addCookie(new Cookie(cookieName, cookieValue));
                }
            }

            // Refresh the page to apply cookies
            driver.navigate().refresh();
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            driver.navigate().to("https://cntttest.vanlanguni.edu.vn:18081/Phancong02");
            Thread.sleep(1000);
            Assert.assertEquals(driver.getCurrentUrl(), "https://cntttest.vanlanguni.edu.vn:18081/Phancong02");
}
    }

    public static void main(String[] args) throws InterruptedException, IOException {
        authenticationTest login = new authenticationTest();
        login.setUp();
        login.testGetCurrentUrl();
        login.testGetCookies();
        login.testLogout();
        login.testUserCookies();
    }
}