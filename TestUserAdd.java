import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

public class TestUserAdd {
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
	public void TC_01_AddUser() throws InterruptedException {
		// Chuyển sang thêm user
		driver.findElement(By.xpath("//body/div[2]/div[1]/div[2]/ul[1]/li[3]/a[1]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//span[contains(text(),'Thêm người dùng')]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@id='staff_id']")).sendKeys("VD203");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@id='full_name']")).sendKeys("Nguyễn Việt Đức");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("duc.2174280212010082@vanlanguni.vn");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//span[@id='select2-type-container']")).click();
		Thread.sleep(1000);
		WebElement valueToSelect = driver.findElement(By.xpath("//li[text()='Cơ hữu']"));
		valueToSelect.click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//span[@class='select2-selection__placeholder']")).click();
		Thread.sleep(1000);
		WebElement valueToSelect2 = driver.findElement(By.xpath("//li[text()='Bộ môn']"));
		valueToSelect2.click();
		driver.findElement(By.xpath("//button[contains(text(),'Lưu')]")).click();
		Thread.sleep(1000);

		WebElement toast = driver.findElement(By.xpath("//div[@class='toast-message']"));
		if (toast.isDisplayed()) {
			String message = toast.getText();
			Assert.assertTrue(message.contains("Lưu thành công!"));
		}

	}

	@Test
	public void TC_01_AddUserDuplicateID() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		driver.findElement(By.xpath("//span[contains(text(),'Thêm người dùng')]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@id='staff_id']")).sendKeys("VD203");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@id='full_name']")).sendKeys("Nguyễn Việt Đức");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("duc.21748022222010082@vanlanguni.vn");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//span[@id='select2-type-container']")).click();
		Thread.sleep(1000);
		WebElement valueToSelect = driver.findElement(By.xpath("//li[text()='Cơ hữu']"));
		valueToSelect.click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//span[@class='select2-selection__placeholder']")).click();
		Thread.sleep(1000);
		WebElement valueToSelect2 = driver.findElement(By.xpath("//li[text()='Bộ môn']"));
		valueToSelect2.click();
		driver.findElement(By.xpath("//button[contains(text(),'Lưu')]")).click();
		Thread.sleep(1000);

		WebElement popup = driver
				.findElement(By.xpath("//div[@class='swal2-popup swal2-modal swal2-icon-error swal2-show']"));
		if (popup.isDisplayed()) {
			String message = driver.findElement(By.xpath("//div[@id='swal2-html-container']")).getText();
			Assert.assertTrue(message.contains("Mã giảng viên này đã có trong hệ thống!"));
			Thread.sleep(1000);
			driver.findElement(By.xpath("//button[normalize-space()='OK']")).click();

		}
		WebElement close = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@id='btnClose']")));
		close.click();
		Thread.sleep(1000);

	}

	@Test
	public void TC_01_AddUserDuplicateEmail() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		driver.findElement(By.xpath("//span[contains(text(),'Thêm người dùng')]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@id='staff_id']")).sendKeys("VD203");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@id='full_name']")).sendKeys("Nguyễn Việt Đức");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("duc.2174802010082@vanlanguni.vn");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//span[@id='select2-type-container']")).click();
		Thread.sleep(1000);
		WebElement valueToSelect = driver.findElement(By.xpath("//li[text()='Cơ hữu']"));
		valueToSelect.click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//span[@class='select2-selection__placeholder']")).click();
		Thread.sleep(1000);
		WebElement valueToSelect2 = driver.findElement(By.xpath("//li[text()='Bộ môn']"));
		valueToSelect2.click();
		driver.findElement(By.xpath("//button[contains(text(),'Lưu')]")).click();
		Thread.sleep(1000);

		WebElement popup = driver
				.findElement(By.xpath("//div[@class='swal2-popup swal2-modal swal2-icon-error swal2-show']"));
		if (popup.isDisplayed()) {
			String message = driver.findElement(By.xpath("//div[@id='swal2-html-container']")).getText();
			Assert.assertTrue(message.contains("Người dùng đã có trong hệ thống!"));
			driver.findElement(By.xpath("//button[normalize-space()='OK']")).click();
		}
		WebElement close = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@id='btnClose']")));
		close.click();
		Thread.sleep(1000);

	}

	@Test
	public void TC_Email_Incorrect() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		driver.findElement(By.xpath("//span[contains(text(),'Thêm người dùng')]")).click();
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("Vlusaidinhdang");
		driver.findElement(By.xpath("//button[contains(text(),'Lưu')]")).click();
		WebElement toastEmail = driver.findElement(By.xpath("//label[@id='email-error']"));
		if (toastEmail.isDisplayed()) {
			String message = toastEmail.getText();
			Assert.assertTrue(message.contains("Vui lòng nhập email Văn Lang hợp lệ!"));
		}
		WebElement close = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@id='btnClose']")));
		close.click();

		Thread.sleep(2000);

	}

	@Test
	public void TC_Error_Null() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		driver.findElement(By.xpath("//span[contains(text(),'Thêm người dùng')]")).click();

		driver.findElement(By.xpath("//button[contains(text(),'Lưu')]")).click();

		WebElement error1 = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[@id='staff_id-error']")));
		if (error1.isDisplayed()) {
			String message = error1.getText();
			Assert.assertTrue(message.contains("Bạn chưa nhập mã giảng viên"));
		}
		Thread.sleep(2000);
		WebElement error2 = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[@id='full_name-error']")));
		if (error2.isDisplayed()) {
			String message = error2.getText();
			Assert.assertTrue(message.contains("Bạn chưa nhập tên giảng viên"));
		}
		Thread.sleep(2000);
		WebElement error3 = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[@id='type-error']")));
		if (error3.isDisplayed()) {
			String message = error3.getText();
			Assert.assertTrue(message.contains("Bạn chưa chọn loại giảng viên"));
		}
		Thread.sleep(2000);
		WebElement error4 = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[@id='role_id-error']")));
		if (error4.isDisplayed()) {
			String message = error4.getText();
			Assert.assertTrue(message.contains("Bạn chưa chọn role"));
		}
		Thread.sleep(2000);
		WebElement close = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@id='btnClose']")));
		close.click();

		Thread.sleep(2000);

	}

	@Test
	public void Test_Search_Update_Delete() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		driver.findElement(By.xpath("//input[@placeholder='Nhập tìm kiếm...']")).sendKeys("VD203");

		WebElement edit = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//i[@class='feather feather-edit font-medium-3 me-1']")));
		edit.click();
		Thread.sleep(2000);
		WebElement editName = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='full_name']")));
		editName.clear();
		editName.sendKeys("Edit Name");
		WebElement editSuccess = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(),'Lưu')]	")));
		editSuccess.click();
		Thread.sleep(2000);
		WebElement toast = driver.findElement(By.xpath("//div[@class='toast-message']"));
		if (toast.isDisplayed()) {
			String message = toast.getText();
			Assert.assertTrue(message.contains("Cập nhật thành công!"));
		}
		Thread.sleep(2000);
		driver.findElement(By.xpath("//i[@class='feather feather-trash-2 font-medium-3 me-1']")).click();
		WebElement dialog = driver.findElement(By.xpath("//div[@role='dialog']"));
		if(dialog.isDisplayed()) {
			WebElement clickdelete = driver.findElement(By.xpath("//button[normalize-space()='Xoá']"));
			clickdelete.click();
		}
		Thread.sleep(2000);
		WebElement toastDelete = driver.findElement(By.xpath("//div[@class='toast-message']"));
		if (toast.isDisplayed()) {
			String message = toastDelete.getText();
			Assert.assertTrue(message.contains("Xoá thành công!"));
		}
	}
	// Trường hợp xóa thất bại do giảng viên đã được phân công
	@Test
	public void Test_Search_Update_Delete_Incorrect() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    driver.findElement(By.xpath("//span[contains(text(),'Người dùng')]")).click();
	    driver.findElement(By.xpath("//body[1]/div[2]/div[2]/div[3]/div[1]/section[1]/div[2]/div[2]/div[1]/div[1]/table[1]/tbody[1]/tr[1]/td[7]/a[2]/i[1]")).click();

	    WebElement dialog = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("swal2-popup")));

	    if(dialog.isDisplayed()) {
	        WebElement clickdelete = driver.findElement(By.xpath("//button[normalize-space()='Xoá']"));
	        clickdelete.click();
	    }

	    Thread.sleep(2000);

	    WebElement dialog2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("swal2-popup")));
	    if(dialog2.isDisplayed()) {
	        String message = driver.findElement(By.id("swal2-html-container")).getText();
	        Assert.assertTrue(message.contains("Không thể xoá do giảng viên này đã được phân công trong hệ thống!"));
	        WebElement toastDelete = driver.findElement(By.xpath("//button[normalize-space()='OK']"));
		    toastDelete.click();
		    Thread.sleep(3000);
	    }

	   
	    driver.close();
	}
}