

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestCapBacGiangVien {
	WebDriver driver;
	readinput read = new readinput();
	String[] credentials = read.readCredentials();
	String username = credentials[0];
	String password = credentials[1];

	@BeforeTest
	public void beforeTest() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "C:\\chromedriver-win64\\chromedriver.exe");
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
		driver.findElement(By.id("i0116")).sendKeys(username);
		WebElement nextButton = driver.findElement(By.id("idSIButton9"));
		nextButton.click();
		Thread.sleep(5000);
		driver.findElement(By.id("i0118")).sendKeys(password);
		WebElement submitButton1 = driver.findElement(By.id("idSIButton9"));
		submitButton1.click();
		Thread.sleep(8000);
		WebElement enterButton = driver.findElement(By.xpath(
				"/html/body/div/form/div/div/div[2]/div[1]/div/div/div/div/div/div[3]/div/div[2]/div/div[3]/div[2]/div/div/div[2]/input"));
		enterButton.click();
		Thread.sleep(8000);
	}

	@Test
	public void TC_01() throws InterruptedException {
		WebElement flow = driver.findElement(By.xpath("//span[normalize-space()='Thù lao']"));
		flow.click();
		Thread.sleep(2000);
		if (flow.isDisplayed()) {
			driver.findElement(By.xpath("//span[contains(text(),'Cấp bậc GV')]")).click();
		}

		driver.findElement(By.xpath("//span[contains(text(),'Sửa tất cả')]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//span[@id='select2-academic_degree_rank_id-container']")).click();
		Thread.sleep(2000);
		WebElement valueToSelect2 = driver.findElement(By.xpath("//li[text()='23']"));
		valueToSelect2.click();
		driver.findElement(By.xpath("//button[contains(text(),'Lưu')]")).click();

		Thread.sleep(2000);
		WebElement toast = driver.findElement(By.xpath("//div[@class='toast-message']"));
		if (toast.isDisplayed()) {
			String message = toast.getText();
			Assert.assertTrue(message.contains("Lưu thành công!"));
		}
		Thread.sleep(2000);
		
	}
	@Test
	public void TC_02() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		driver.findElement(By.xpath("//span[contains(text(),'Sửa tất cả')]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[contains(text(),'Lưu')]")).click();
		WebElement error1 = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[@id='academic_degree_rank_id-error']")));
		if (error1.isDisplayed()) {
			String message = error1.getText();
			Assert.assertTrue(message.contains("Bạn chưa chọn cấp bậc cho giảng viên"));
		}
		Thread.sleep(2000);
		driver.quit();
		
	}
}
