

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

public class TestProfileUser {
	
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
		WebElement flow = driver.findElement(By.xpath("//img[@alt='avatar']"));
		flow.click();
		Thread.sleep(2000);
		if (flow.isDisplayed()) {
			driver.findElement(By.xpath("//a[contains(text(),'Hồ sơ')]")).click();
		}
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@id='staff_id']")).sendKeys("753");
		
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@id='full_name']")).sendKeys("Nguyễn Việt Đức");
		Thread.sleep(2000);
		
		driver.findElement(By.xpath("//button[contains(text(),'Cập nhật')]")).click();
		WebElement dialog = driver.findElement(By.xpath("//div[@role='dialog']"));
		if(dialog.isDisplayed()) {
			WebElement success = driver.findElement(By.xpath("//div[@id='swal2-html-container']"));
			String message = success.getText();
			Assert.assertTrue(message.contains("Cập nhật thành công!"));
		}
		driver.findElement(By.xpath("//button[normalize-space()='OK']")).click();
		
	}
	// Test case phát hiện ra lỗi khi trống mà vẫn thành công
	@Test
	public void TC_01_Error_Null_ButSuccess() throws InterruptedException {
		driver.findElement(By.xpath("//input[@id='staff_id']")).clear();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@id='full_name']")).clear();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[contains(text(),'Cập nhật')]")).click();
		WebElement dialog = driver.findElement(By.xpath("//div[@role='dialog']"));
		if(dialog.isDisplayed()) {
			WebElement success = driver.findElement(By.xpath("//div[@id='swal2-html-container']"));
			String message = success.getText();
			Assert.assertTrue(message.contains("Cập nhật thành công!"));
			Thread.sleep(2000);
		}

		driver.findElement(By.xpath("//button[normalize-space()='OK']")).click();
		Thread.sleep(2000);
		
	}
	@Test
	public void TC_01_Error_Duplicate() throws InterruptedException {
		driver.findElement(By.xpath("//input[@id='staff_id']")).clear();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@id='staff_id']")).sendKeys("1");
		
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@id='full_name']")).clear();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@id='full_name']")).sendKeys("1");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[contains(text(),'Cập nhật')]")).click();
		WebElement dialog = driver.findElement(By.xpath("//div[@role='dialog']"));
		
		if(dialog.isDisplayed()) {
			WebElement success = driver.findElement(By.xpath("//div[@id='swal2-html-container']"));
			String message = success.getText();
			Assert.assertTrue(message.contains("Mã giảng viên này đã có trong hệ thống!"));
		}
		driver.findElement(By.xpath("//button[normalize-space()='OK']")).click();
		
	}
	@Test
	public void TC_02_Error() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		driver.findElement(By.xpath("//input[@id='staff_id']")).sendKeys("\"//div[@id='swal2-html-container']\"");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@id='full_name']")).sendKeys("1");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[contains(text(),'Cập nhật')]")).click();
		WebElement error1 = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[@id='staff_id-error']")));
		if (error1.isDisplayed()) {
			String message = error1.getText();
			Assert.assertTrue(message.contains("Chỉ được nhập số-chữ không dấu và không có khoảng trắng!"));
		}
		Thread.sleep(2000);
	    driver.quit();
		
	}

}
