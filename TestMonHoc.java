

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestMonHoc {
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
			driver.findElement(By.xpath("//span[contains(text(),'Môn học')]")).click();
		}

		driver.findElement(By.xpath("//input[@placeholder='Nhập tìm kiếm...']")).sendKeys("Công nghệ thông tin");
		Thread.sleep(2000);
		WebElement search =  driver.findElement(By.xpath("//td[normalize-space()='1']"));
		if(search.isDisplayed()) {
			String message = search.getText();
			Assert.assertTrue(message.contains("1"));
		}
		Thread.sleep(2000);
		
	}
	@Test
	public void TC_01_InCorrect() throws InterruptedException {
		driver.findElement(By.xpath("//input[@placeholder='Nhập tìm kiếm...']")).clear();
		driver.findElement(By.xpath("//input[@placeholder='Nhập tìm kiếm...']")).sendKeys("áddsa");
		Thread.sleep(2000);
		WebElement search =  driver.findElement(By.xpath("//td[@class='dataTables_empty']"));
		if(search.isDisplayed()) {
			String message = search.getText();
			Assert.assertTrue(message.contains("Không tìm thấy kết quả"));
		}
		Thread.sleep(2000);
		driver.quit();
		
	}

}
