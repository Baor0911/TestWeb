

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

public class ThuLaoTest {
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
		WebElement flow =  driver.findElement(By.xpath("//span[normalize-space()='Thù lao']"));
		flow.click();
		Thread.sleep(2000);
		if(flow.isDisplayed()) {
			driver.findElement(By.xpath("//span[contains(text(),'Học hàm, học vị')]")).click();
		}
		Thread.sleep(2000);
		driver.findElement(By.xpath("//span[contains(text(),'Thêm HH, HV mới')]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@id='id']")).sendKeys("VDABC");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@id='name']")).sendKeys("Tester");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@id='level']")).clear();
		driver.findElement(By.xpath("//input[@id='level']")).sendKeys("50");
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
	public void TC_01_Duplicate() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		driver.findElement(By.xpath("//span[contains(text(),'Thêm HH, HV mới')]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@id='id']")).sendKeys("VDABC");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@id='name']")).sendKeys("Tester");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@id='level']")).clear();
		driver.findElement(By.xpath("//input[@id='level']")).sendKeys("50");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[contains(text(),'Lưu')]")).click();
		Thread.sleep(2000);
		WebElement popup = driver.findElement(By.xpath("//div[@class='swal2-popup swal2-modal swal2-icon-error swal2-show']"));
		if (popup.isDisplayed()) {
			String message =  driver.findElement(By.xpath("//div[@id='swal2-html-container']")).getText();
			Assert.assertTrue(message.contains("Mã học hàm, học vị này đã tồn tại!"));
			Thread.sleep(2000);
			driver.findElement(By.xpath("//button[normalize-space()='OK']")).click();
		}
		WebElement close = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@id='btnClose']")));
		close.click();
		Thread.sleep(1000);
		
}
	@Test
	public void TC_01_ERROR() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		driver.findElement(By.xpath("//span[contains(text(),'Thêm HH, HV mới')]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[contains(text(),'Lưu')]")).click();
		Thread.sleep(2000);
		WebElement error1 = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[@id='id-error']")));
		if (error1.isDisplayed()) {
			String message = error1.getText();
			Assert.assertTrue(message.contains("Bạn chưa nhập mã học hàm, học vị"));
		}
		Thread.sleep(2000);
		WebElement error2 = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[@id='name-error']")));
		if (error2.isDisplayed()) {
			String message = error2.getText();
			Assert.assertTrue(message.contains("Bạn chưa nhập tên học hàm, học vị"));
		}
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@id='level']")).sendKeys("566");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[contains(text(),'Lưu')]")).click();
		WebElement error3 = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[@id='level-error']")));
		if (error3.isDisplayed()) {
			String message = error3.getText();
			Assert.assertTrue(message.contains("Vui lòng nhập nhỏ hơn hoặc bằng 100"));
		}
		WebElement close = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@id='btnClose']")));
		close.click();
		Thread.sleep(1000);
		
}
	@Test
	public void TC_02_Search_Edit_Delete() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		driver.findElement(By.xpath("//input[@placeholder='Nhập tìm kiếm...']")).sendKeys("VDABC");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//i[@class='feather feather-edit font-medium-3 me-1']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@id='name']")).sendKeys("155");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[contains(text(),'Lưu')]")).click();
		Thread.sleep(2000);
		WebElement toast = driver.findElement(By.xpath("//div[@class='toast-message']"));
		if (toast.isDisplayed()) {
			String message = toast.getText();
			Assert.assertTrue(message.contains("Cập nhật thành công!"));
		}
		
		Thread.sleep(1000);
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
	// Không thể xoá do học hàm, học vị này đã có dữ liệu!
	@Test
	public void Test_Search_Update_Delete_Incorrect() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    driver.findElement(By.xpath("/html/body/div[2]/div[2]/div[3]/div/section/div/div/div/div[2]/ul/li[1]/a")).click();
	    Thread.sleep(2000);
	    driver.findElement(By.xpath("//tbody/tr[1]/td[5]/a[2]/i[1]")).click();
	    Thread.sleep(2000);
	    WebElement dialog = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("swal2-popup")));

	    if(dialog.isDisplayed()) {
	        WebElement clickdelete = driver.findElement(By.xpath("//button[normalize-space()='Xoá']"));
	        clickdelete.click();
	    }
	    Thread.sleep(2000);
	    WebElement dialog2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("swal2-popup")));
	    if(dialog2.isDisplayed()) {
	        String message = driver.findElement(By.id("swal2-html-container")).getText();
	        Assert.assertTrue(message.contains("Không thể xoá do học hàm, học vị này đã có dữ liệu!"));
	        WebElement toastDelete = driver.findElement(By.xpath("//button[normalize-space()='OK']"));
		    toastDelete.click();
		    Thread.sleep(3000);
	    }

	   
	    driver.close();
	}
}
