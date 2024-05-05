
import java.time.Duration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestDangNhap {
    private static final String KEY = "webdriver.chrome.driver";
    private static final String VALUE = "C:\\chromedriver-win64\\chromedriver.exe";
    private static final String URL = "https://cntttest.vanlanguni.edu.vn:18081/Phancong02/Account/Login";
    private static final String MAIN_URL = "https://cntttest.vanlanguni.edu.vn:18081/Phancong02/";
    private WebDriver webDriver;
    private static String cookiesCurrent;
    private static boolean test1Completed = false;
    private static boolean test2Completed = false;
  
    @BeforeEach
    public void setUp() throws Exception {
        System.out.println("Create");
        System.setProperty(KEY, VALUE);
        ChromeOptions options = new ChromeOptions();
        options.setAcceptInsecureCerts(true);
        webDriver = new ChromeDriver(options);
        webDriver.get(URL);
        webDriver.manage().window().maximize();
    }

    @AfterEach
    public void tearDown() {
        if (webDriver != null) {
            webDriver.quit();
        }
    }

    @Test
    @Order(1)
    public void testLoginCorrect() throws InterruptedException {
        if (webDriver == null) {
            return;
        }
        
        System.out.print("Login");
        try {
        	
            WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(15));
            WebElement clickToLogin = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='OpenIdConnect']")));
            clickToLogin.click();

            WebElement email = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='i0116']")));
            if (email.isDisplayed()) {
                enterDigitsIndividually(email, "Duc.2174802010082@vanlanguni.vn");
            }

            WebElement buttonNextLogin = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='idSIButton9']")));
            buttonNextLogin.click();

            WebElement password = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='i0118']")));
            if (password.isDisplayed()) {
                enterDigitsIndividually(password, "VLU18052003");
            }
            WebElement buttonLogin = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='idSIButton9']")));
            if (buttonLogin.isDisplayed()) {
                buttonLogin.click();
            }
            WebElement buttonAccept = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='idSIButton9']")));
            if(buttonAccept.isDisplayed()) {
                buttonAccept.click();
            }
            cookiesCurrent = webDriver.manage().getCookieNamed(".AspNet.Cookies").getValue();
            wait.until(ExpectedConditions.urlToBe(MAIN_URL));
            System.out.print(cookiesCurrent);
            System.out.println("Đăng nhập thành công!");
            test1Completed = true;
        } catch (UnhandledAlertException e) {
            e.printStackTrace();
        }
    }
   
    public void enterDigitsIndividually(WebElement element, String digits) {
        for (int i = 0; i < digits.length(); i++) {
            char digit = digits.charAt(i);
            String digitAsString = String.valueOf(digit);
            element.sendKeys(digitAsString);

            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
