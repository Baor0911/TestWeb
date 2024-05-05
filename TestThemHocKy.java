


import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestThemHocKy {

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
	public void TC_01_CreateTerms_Correct() throws InterruptedException {
		driver.findElement(By.xpath("//span[contains(text(),'Học kỳ và ngành')]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//span[contains(text(),'Thêm học kỳ mới')]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@id='id']")).sendKeys("291");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//body[1]/div[3]/div[2]/form[1]/div[5]/input[2]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//span[contains(text(),'27')]")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("max_class")).clear();
		driver.findElement(By.id("max_class")).sendKeys("3");
		driver.findElement(By.xpath("//button[contains(text(),'Lưu')]")).click();
		// Kiểm tra thông báo lưu thành công
		String successMessage = driver.findElement(By.xpath("//div[contains(text(),'Lưu thành công!')]")).getText();
		Assert.assertTrue(successMessage.contains("Lưu thành công!"));
	}

	@Test
	public void TC_02_CreateTerms_Correct() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		driver.findElement(By.xpath("//span[contains(text(),'Học kỳ và ngành')]")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(),'Thêm học kỳ mới')]")))
				.click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Lưu')]"))).click();
		Thread.sleep(2000);
		// Chưa nhập học kì
		String message0 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[@id='id-error']")))
				.getText();
		Assert.assertTrue(message0.contains("Bạn chưa nhập học kỳ"));
		// Chưa nhập ngày bắt đầu
		Thread.sleep(2000);
		String message1 = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[@id='start_date-error']")))
				.getText();
		Assert.assertTrue(message1.contains("Bạn chưa chọn ngày bắt đầu"));
		// Học kì không thể quá 4 kí tự
		Thread.sleep(2000);
		WebElement primary = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='id']")));
		primary.sendKeys("9121");
		String message2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[@id='id-error']")))
				.getText();
		Assert.assertTrue(message2.contains("Vui lòng nhập đúng 3 kí tự!"));
		// Năm bắt đầu > Năm kết thúc
		Thread.sleep(2000);
		WebElement comboBoxYearStart = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@id='select2-start_year-container']")));
		comboBoxYearStart.click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[text()='2024']"))).click();
		Thread.sleep(2000);
		WebElement comboBoxYearEnd = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@id='select2-end_year-container']")));
		comboBoxYearEnd.click();
		Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[text()='2014']"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Lưu')]"))).click();
		Thread.sleep(2000);
		String message3 = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[@id='end_year-error']")))
				.getText();
		Assert.assertTrue(message3.contains("Năm kết thúc không thể nhỏ hơn năm bắt đầu!"));

		// Số tuần không quá 53
		WebElement startWeekInput = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='start_week']")));
		startWeekInput.clear();
		startWeekInput.sendKeys("53");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Lưu')]"))).click();
		Thread.sleep(2000);
		String message4 = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[@id='start_week-error']")))
				.getText();
		Assert.assertTrue(message4.contains("Vui lòng nhập nhỏ hơn hoặc bằng 52"));

		// Số tiết không quá 15
		WebElement maxLesson = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='max_lesson']")));
		maxLesson.clear();
		maxLesson.sendKeys("19");
		Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Lưu')]"))).click();

		String message5 = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[@id='max_lesson-error']")))
				.getText();
		Assert.assertTrue(message5.contains("Vui lòng nhập nhỏ hơn hoặc bằng 15"));
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Lưu')]"))).click();
		// Số lớp không quá 30
		WebElement maxClass = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='max_class']")));
		maxClass.clear();
		maxClass.sendKeys("50");
		Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Lưu')]"))).click();
		Thread.sleep(2000);
		String message6 = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[@id='max_class-error']")))
				.getText();
		Assert.assertTrue(message6.contains("Vui lòng nhập nhỏ hơn hoặc bằng 30"));
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='btnClose']"))).click();
	}

	@Test
	public void TC_03_CreateTerms_Correct() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		driver.findElement(By.xpath("//span[contains(text(),'Học kỳ và ngành')]")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(),'Thêm học kỳ mới')]")))
				.click();
		WebElement primary = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='id']")));
		primary.sendKeys("221");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@type='text']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//span[@aria-label='Tháng tư 26, 2024']")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Lưu')]"))).click();
		WebElement popup = wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//div[@class='swal2-popup swal2-modal swal2-icon-error swal2-show']")));
		Thread.sleep(2000);
		if (popup.isDisplayed()) {
			String successMessage = driver.findElement(By.xpath("//div[@id='swal2-html-container']")).getText();
			Assert.assertTrue(successMessage.contains("Học kỳ này đã được tạo trong hệ thống!"));
			driver.findElement(By.xpath("//button[contains(text(),'OK')]")).click();
		}
	}

	@Test
	public void TC_04_Search() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		driver.findElement(By.xpath("//span[contains(text(),'Học kỳ và ngành')]")).click();
		WebElement search = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Nhập tìm kiếm...']")));
		search.click();
		search.sendKeys("incorrect");
		Thread.sleep(2000);
		WebElement searchFalse = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//td[@class='dataTables_empty']")));
		if (searchFalse.isDisplayed()) {
			String error = searchFalse.getText();
			Assert.assertTrue(error.contains("Không tìm thấy kết quả"));
		}
		Thread.sleep(2000);
		search.clear();
		search.sendKeys("999");
		Thread.sleep(2000);
		WebElement searchCorrect = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//td[@class='text-center sorting_1']")));
		if (searchCorrect.isDisplayed()) {
			String message = searchCorrect.getText();
			Assert.assertTrue(message.contains("999"));
		}

	}

	@Test
	public void TC_05_Delete() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		driver.findElement(By.xpath("//span[contains(text(),'Học kỳ và ngành')]")).click();
		WebElement search = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Nhập tìm kiếm...']")));
		search.click();
		search.sendKeys("291");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//i[@class='feather feather-trash-2 font-medium-3 me-1']")).click();
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
			Thread.sleep(2000);
		}
		driver.quit();

	}
}
