

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

public class TestDonGiaVaHeSo {

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
			driver.findElement(By.xpath("//span[contains(text(),'Đơn giá & hệ số')]")).click();
		}

		driver.findElement(By.xpath(
				"//body[1]/div[2]/div[2]/div[3]/div[1]/section[1]/div[1]/div[1]/div[1]/div[2]/div[2]/div[2]/div[1]/div[1]/div[1]/div[1]/table[1]/tbody[1]/tr[1]/td[3]/a[1]/i[1]"))
				.click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@id='price']")).sendKeys("22");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[contains(text(),'Lưu')]")).click();

		Thread.sleep(2000);
		WebElement toast = driver.findElement(By.xpath("//div[@class='toast-message']"));
		if (toast.isDisplayed()) {
			String message = toast.getText();
			Assert.assertTrue(message.contains("Lưu thành công!"));
		}
	}

	@Test
	public void TC_01_EDIT() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		driver.findElement(By.xpath(
				"//body[1]/div[2]/div[2]/div[3]/div[1]/section[1]/div[1]/div[1]/div[1]/div[2]/div[2]/div[2]/div[1]/div[1]/div[1]/div[1]/table[1]/tbody[1]/tr[1]/td[3]/a[1]/i[1]"))
				.click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[contains(text(),'Lưu')]")).click();

		WebElement error1 = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[@id='price-error']")));
		if (error1.isDisplayed()) {
			String message = error1.getText();
			Assert.assertTrue(message.contains("Bạn chưa nhập đơn giá"));
		}
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[@id='btnClose']")).click();
		Thread.sleep(1000);
	}

	@Test
	public void TC_01_Delete() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		driver.findElement(By.xpath(
				"//body[1]/div[2]/div[2]/div[3]/div[1]/section[1]/div[1]/div[1]/div[1]/div[2]/div[2]/div[2]/div[1]/div[1]/div[1]/div[1]/table[1]/tbody[1]/tr[1]/td[3]/a[2]/i[1]"))
				.click();
		Thread.sleep(2000);
		WebElement dialog = driver.findElement(By.xpath("//div[@role='dialog']"));
		if (dialog.isDisplayed()) {
			WebElement clickdelete = driver.findElement(By.xpath("//button[normalize-space()='Xoá']"));
			clickdelete.click();
		}
		Thread.sleep(2000);
		WebElement toastDelete = driver.findElement(By.xpath("//div[@class='toast-message']"));
		if (toastDelete.isDisplayed()) {
			String message = toastDelete.getText();
			Assert.assertTrue(message.contains("Xoá thành công!"));
		}
		Thread.sleep(1000);
	}

	@Test
	public void TC_02_Edit() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		driver.findElement(By.xpath("//*[@id=\"coefficient-tab\"]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//tbody/tr/td[5]/a[1]/i[1]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@id='vietnamese_coefficient']")).clear();
		driver.findElement(By.xpath("//input[@id='vietnamese_coefficient']")).sendKeys("5.43");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[contains(text(),'Lưu')]")).click();
		WebElement toast = driver.findElement(By.xpath("//div[@class='toast-message']"));
		if (toast.isDisplayed()) {
			String message = toast.getText();
			Assert.assertTrue(message.contains("Lưu thành công!"));
		}
	}

	@Test
	public void TC_02_Edit_Error() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		driver.findElement(By.xpath("//*[@id=\"coefficient-tab\"]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//tbody/tr/td[5]/a[1]/i[1]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@id='vietnamese_coefficient']")).clear();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@id='theoretical_coefficient']")).clear();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@id='foreign_coefficient']")).clear();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@id='practice_coefficient']")).clear();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[contains(text(),'Lưu')]")).click();
		WebElement error1 = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[@id='vietnamese_coefficient-error']")));
		if (error1.isDisplayed()) {
			String message = error1.getText();
			Assert.assertTrue(message.contains("Bạn chưa nhập hệ số tiếng Việt"));
		}
		Thread.sleep(2000);
		WebElement error2 = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[@id='foreign_coefficient-error']")));
		if (error2.isDisplayed()) {
			String message = error2.getText();
			Assert.assertTrue(message.contains("Bạn chưa nhập hệ số tiếng Anh"));
		}
		Thread.sleep(2000);
		WebElement error3 = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//label[@id='theoretical_coefficient-error']")));
		if (error3.isDisplayed()) {
			String message = error3.getText();
			Assert.assertTrue(message.contains("Bạn chưa nhập hệ số lý thuyết"));
		}
		Thread.sleep(2000);
		WebElement error4 = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[@id='practice_coefficient-error']")));
		if (error4.isDisplayed()) {
			String message = error4.getText();
			Assert.assertTrue(message.contains("Bạn chưa nhập hệ số thực hành"));
		}
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[@id='btnClose']")).click();
	}

	@Test
	public void TC_03_Edit_Error() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		driver.findElement(By.xpath("//*[@id=\"coefficient-tab\"]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//tbody/tr/td[5]/a[1]/i[1]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@id='vietnamese_coefficient']")).clear();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@id='vietnamese_coefficient']")).sendKeys("555");
		driver.findElement(By.xpath("//button[contains(text(),'Lưu')]")).click();
		WebElement error4 = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[@id='vietnamese_coefficient-error']")));
		if (error4.isDisplayed()) {
			String message = error4.getText();
			Assert.assertTrue(message.contains("Vui lòng nhập nhỏ hơn hoặc bằng 9.99"));
		}
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[@id='btnClose']")).click();

	}

}
