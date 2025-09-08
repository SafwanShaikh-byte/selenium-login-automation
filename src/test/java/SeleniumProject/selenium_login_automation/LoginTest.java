package SeleniumProject.selenium_login_automation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;

public class LoginTest {
	
	private WebDriver driver;
	private final String baseUrl = "https://the-internet.herokuapp.com/login";

	@BeforeMethod
	public void setUp() {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get(baseUrl);
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

	// Test Case 1: Valid Login
	@Test
	public void testValidLogin() {
		driver.findElement(By.id("username")).sendKeys("tomsmith");
		driver.findElement(By.id("password")).sendKeys("SuperSecretPassword!");
		driver.findElement(By.cssSelector("button[type='submit']")).click();

		WebElement successMessage = driver.findElement(By.id("flash"));
		Assert.assertTrue(successMessage.getText().contains("You logged into a secure area!"));
		Assert.assertTrue(driver.getCurrentUrl().contains("/secure"));
	}

	// Test Case 2: Invalid Login
	@Test
	public void testInvalidLogin() {
		driver.findElement(By.id("username")).sendKeys("wrongusername");
		driver.findElement(By.id("password")).sendKeys("wrongpassword");
		driver.findElement(By.cssSelector("button[type='submit']")).click();

		WebElement errorMessage = driver.findElement(By.id("flash"));
		Assert.assertTrue(errorMessage.getText().contains("Your username is invalid!"));
	}

	// Test Case 3: Empty Fields
	@Test
	public void testEmptyFields() {
		driver.findElement(By.cssSelector("button[type='submit']")).click();

		WebElement errorMessage = driver.findElement(By.id("flash"));
		Assert.assertTrue(errorMessage.getText().contains("Your username is invalid!"));
	}
}
