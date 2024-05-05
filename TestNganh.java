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

public class TestNganh {
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
	public void TC_01_Create() throws InterruptedException {
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    driver.findElement(By.xpath("//span[contains(text(),'Học kỳ và ngành')]")).click();
	    Thread.sleep(1000);

	    WebElement button = driver.findElement(By.xpath("//a[normalize-space()='Ngành']"));
	    button.click();
	    WebElement add = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(),'Thêm ngành mới')]")));
	    add.click();
	    Thread.sleep(2000);
	    
	    // Truong hop them thanh cong
	    WebElement id = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='id']")));
	    id.sendKeys("VLUVIETDUC");
	    Thread.sleep(2000);
	    
	    WebElement name = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='name']")));
	    name.sendKeys("Cong nghe thong tin");
	    Thread.sleep(2000);
	    
	    WebElement abbreviation = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='abbreviation']")));
	    abbreviation.sendKeys("CNTT");
	    Thread.sleep(2000);
	    
	    WebElement comboBox = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@id='select2-program_type-container']")));
	    comboBox.click();
	    Thread.sleep(2000);
	    
	    WebElement valueToSelect = driver.findElement(By.xpath("//li[text()='Tiêu chuẩn']"));
	    valueToSelect.click();
	    Thread.sleep(2000);
	    
	    WebElement save = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Lưu')]")));
	    save.click();
	    Thread.sleep(2000);
	    
	    WebElement toast = driver.findElement(By.xpath("//div[@class='toast-message']"));
	    if (toast.isDisplayed()) {
	        String message = toast.getText();
	        Assert.assertTrue(message.contains("Lưu thành công!"));
	    }
	}
	@Test
	public void TC_01_Create_Test() throws InterruptedException {
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	
	    WebElement add = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(),'Thêm ngành mới')]")));
	    add.click();
	    Thread.sleep(2000);
	    
	    // Truong hop them thanh cong
	    WebElement id = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='id']")));
	    id.sendKeys("1");
	    Thread.sleep(2000);
	    
	    WebElement name = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='name']")));
	    name.sendKeys("1");
	    Thread.sleep(2000);
	    
	    WebElement abbreviation = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='abbreviation']")));
	    abbreviation.sendKeys("1");
	    Thread.sleep(2000);
	    
	    WebElement comboBox = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@id='select2-program_type-container']")));
	    comboBox.click();
	    Thread.sleep(2000);
	    
	    WebElement valueToSelect = driver.findElement(By.xpath("//li[text()='Tiêu chuẩn']"));
	    valueToSelect.click();
	    Thread.sleep(2000);
	    
	    WebElement save = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Lưu')]")));
	    save.click();
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
	
		WebElement add = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(),'Thêm ngành mới')]")));
		add.click();
		Thread.sleep(2000);
		
		WebElement id = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='id']")));
		id.sendKeys("123");
		Thread.sleep(2000);
		WebElement name = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='name']")));
		name.sendKeys("Cong nghe thong tin");
		Thread.sleep(2000);
		WebElement abbreviation = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='abbreviation']")));
		abbreviation.sendKeys("CNTT");
		Thread.sleep(2000);
		WebElement comboBox = wait.until(
				ExpectedConditions.elementToBeClickable(By.xpath("//span[@id='select2-program_type-container']")));
		comboBox.click();
		Thread.sleep(2000);
		WebElement valueToSelect = driver.findElement(By.xpath("//li[text()='Tiêu chuẩn']"));
		valueToSelect.click();
		Thread.sleep(2000);
		WebElement save = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Lưu')]")));
		save.click();
		Thread.sleep(2000);
		WebElement popup = driver.findElement(By.xpath("//div[@class='swal2-popup swal2-modal swal2-icon-error swal2-show']"));
		if (popup.isDisplayed()) {
			WebElement toast = driver.findElement(By.xpath("//div[@id='swal2-html-container']"));
			String message = toast.getText();
			Assert.assertTrue(message.contains("Mã ngành này đã tồn tại!"));
			driver.findElement(By.xpath("//button[normalize-space()='OK']")).click();
			driver.findElement(By.xpath("//button[@id='btnClose']")).click();
		}

	}

	@Test
	public void TC_02_Create() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement button = driver.findElement(By.xpath("//a[normalize-space()='Ngành']"));
		WebElement add = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Thêm ngành mới')]")));
		add.click();
		Thread.sleep(2000);
		WebElement save = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(),'Lưu')]")));
		save.click();
		Thread.sleep(2000);
		// Truong hop trống
		WebElement error1 = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[@id='id-error']")));
		if (error1.isDisplayed()) {
			String message = error1.getText();
			Assert.assertTrue(message.contains("Bạn chưa nhập mã ngành"));
		}
		Thread.sleep(2000);
		WebElement error2 = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[@id='name-error']")));
		if (error2.isDisplayed()) {
			String message = error2.getText(); // Sửa đổi ở đây từ error1 sang error2
			Assert.assertTrue(message.contains("Bạn chưa nhập tên ngành"));
		}
		Thread.sleep(2000);
		WebElement error3 = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[@id='abbreviation-error']")));
		if (error3.isDisplayed()) {
			String message = error3.getText();
			Assert.assertTrue(message.contains("Bạn chưa nhập tên viết tắt của ngành"));
		}
		Thread.sleep(2000);
		WebElement error4 = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[@id='program_type-error']")));
		if (error4.isDisplayed()) {
			String message = error4.getText();
			Assert.assertTrue(message.contains("Bạn chưa chọn CTĐT"));
		}
		Thread.sleep(2000);
		WebElement close = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@id='btnClose']")));
		close.click();
	}

	@Test
	public void TC_03_Delete() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		
	    WebElement deleteSucces = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//tbody/tr[2]/td[6]/a[2]/i[1]")));
	    deleteSucces.click();
	    
	    WebElement deletebtn = driver.findElement(By.xpath("//button[normalize-space()='Xoá']"));
	    deletebtn.click();
	    
	    WebElement toast = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='toast-message']")));
	    if (toast.isDisplayed()) {
	        String message = toast.getText();
	        Assert.assertTrue(message.contains("Xoá thành công!"));
	    }
	    Thread.sleep(2000);
	}

	@Test
	public void TC_04_Update_Incorrect() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement update = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//tbody/tr[1]/td[6]/a[1]/i[1]")));
		update.click();
		WebElement name = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='name']")));
		name.clear();
		WebElement abbreviation = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='abbreviation']")));
		abbreviation.clear();
		WebElement save = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Lưu')]")));
		save.click();
		WebElement error2 = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[@id='name-error']")));
		if (error2.isDisplayed()) {
			String message = error2.getText();
			Assert.assertTrue(message.contains("Bạn chưa nhập tên ngành"));
		}
		Thread.sleep(2000);
		WebElement error3 = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[@id='abbreviation-error']")));
		if (error3.isDisplayed()) {
			String message = error3.getText();
			Assert.assertTrue(message.contains("Bạn chưa nhập tên viết tắt của ngành"));
		}
		WebElement close = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='btnClose']")));
		close.click();
		Thread.sleep(2000);
	}
	@Test
	public void TC_04_Update_correct() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement update = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//tbody/tr[1]/td[6]/a[1]/i[1]")));
		update.click();
		WebElement name = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='name']")));
		name.clear();
		name.sendKeys("Cong nghe thong tin");
		Thread.sleep(2000);
		WebElement abbreviation = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='abbreviation']")));
		abbreviation.clear();
		abbreviation.sendKeys("CNTT");
		Thread.sleep(2000);
		WebElement updateSuccess = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Lưu')]")));
		updateSuccess.click();
		Thread.sleep(2000);
		   WebElement toast = driver.findElement(By.xpath("//div[@class='toast-message']"));
		    if (toast.isDisplayed()) {
		        String message = toast.getText();
		        Assert.assertTrue(message.contains("Cập nhật thành công!"));
		    }
		    Thread.sleep(2000);
	}
	@Test
	public void TC_05_Search() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
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
		search.sendKeys("9999");
		Thread.sleep(2000);
		WebElement searchCorrect = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//td[@class='sorting_1']")));
		if (searchCorrect.isDisplayed()) {
			String message = searchCorrect.getText();
			Assert.assertTrue(message.contains("9999"));
			search.clear();
		}
		Thread.sleep(2000);
		// chay cuoi cung
		driver.quit();

	}
	@Test
	public void TC_05_Delete() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement search = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Nhập tìm kiếm...']")));
		search.click();
		search.sendKeys("VLUVIETDUC");
		Thread.sleep(2000);
		WebElement delete = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//i[@class='feather feather-trash-2 font-medium-3 me-1']")));
		delete.click();
		WebElement dialog = driver.findElement(By.xpath("//div[@role='dialog']"));
		if(dialog.isDisplayed()) {
			WebElement clickdelete = driver.findElement(By.xpath("//button[normalize-space()='Xoá']"));
			clickdelete.click();
		}
		Thread.sleep(2000);
		WebElement toastDelete = driver.findElement(By.xpath("//div[@class='toast-message']"));
		if (toastDelete.isDisplayed()) {
			String message = toastDelete.getText();
			Assert.assertTrue(message.contains("Xoá thành công!"));
		}

	}
	@Test
	public void TC_05_Delete_InCorrect() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    WebElement button = driver.findElement(By.xpath("//a[normalize-space()='Ngành']"));
	    button.click();
		Thread.sleep(2000);
		WebElement delete = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//tbody/tr[1]/td[6]/a[2]/i[1]")));
		delete.click();
		WebElement dialog = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("swal2-popup")));

	    if(dialog.isDisplayed()) {
	        WebElement clickdelete = driver.findElement(By.xpath("//button[normalize-space()='Xoá']"));
	        clickdelete.click();
	    }
	    Thread.sleep(2000);
	    WebElement dialog2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("swal2-popup")));
	    if(dialog2.isDisplayed()) {
	        String message = driver.findElement(By.id("swal2-html-container")).getText();
	        Assert.assertTrue(message.contains("Không thể xoá do ngành này đã có dữ liệu!"));
	        WebElement toastDelete = driver.findElement(By.xpath("//button[normalize-space()='OK']"));
		    toastDelete.click();
		    Thread.sleep(3000);
	    }


	}
	

}
