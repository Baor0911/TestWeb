

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

public class TestThongKeChiTiet {
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
	public void TC_01_Search_Success() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement flow =  driver.findElement(By.xpath("//span[contains(text(),'Thống kê')]"));
		flow.click();
		
		Thread.sleep(2000);
		if(flow.isDisplayed()) {
			driver.findElement(By.xpath("//span[contains(text(),'Số giờ giảng viên')]")).click();
		}
		driver.findElement(By.xpath("//a[@id='details-tab']")).click();
		Thread.sleep(2000);
		WebElement search = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@aria-controls='tblStatisticsDetails']")));
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
		search.sendKeys("2174802010701");
		Thread.sleep(2000);
		WebElement searchCorrect = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//body[1]/div[2]/div[2]/div[3]/div[1]/section[1]/div[1]/div[1]/div[1]/div[2]/div[2]/div[2]/div[3]/div[1]/div[1]/div[1]/table[1]/tbody[1]/tr[1]/td[1]")));
		if (searchCorrect.isDisplayed()) {
			String message = searchCorrect.getText();
			Assert.assertTrue(message.contains("1"));
			search.clear();
		}
		Thread.sleep(2000);
}
	  @Test(dependsOnMethods = { "TC_01_Search_Success" })
	public void TC_01_Import_Success() throws InterruptedException {
		driver.findElement(By.xpath("//a[@id='details-tab']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[@class='d-flex justify-content-between align-items-center header-actions mx-2 row']//button[@type='button']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//span[normalize-space()='Excel']")).click();
		Thread.sleep(2000);
		driver.quit();
}

}
