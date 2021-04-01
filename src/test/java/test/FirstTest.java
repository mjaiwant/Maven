package test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.testng.Assert;

public class FirstTest {
	
	@Test(priority=2)
	public void login() throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("http://magento.com");
		driver.findElement(By.xpath("//*[@id=\"block-header\"]/ul/li[9]/a/span[1]/div")).click();
		driver.findElement(By.id("email")).sendKeys("manoj.jaiwant@gmail.com");
		driver.findElement(By.id("pass")).sendKeys("welcome");
		driver.findElement(By.id("send2")).click();
		
		Thread.sleep(3000);
		
		String error_msg = driver.findElement(By.xpath("//*[@id=\"maincontent\"]/div[1]/div[2]/div/div/div")).getText();
		
		Assert.assertEquals(error_msg, "Invalid login or password.");
		
		/*
		if(error_msg.equals("Invalid login or password."))
		{
			System.out.println("Test Pass");
		}
		*/
		driver.quit();
	}
		@Test(priority=1)  //enabled=false if needed
		public void register() {
			WebDriverManager.chromedriver().setup();
			WebDriver driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.get("http://magento.com");
			driver.findElement(By.className("account-icon")).click();
			driver.findElement(By.id("register")).click();
			driver.findElement(By.id("firstname")).sendKeys("Manoj");
			driver.findElement(By.id("lastname")).sendKeys("Jaiwant");
			driver.findElement(By.id("email_address")).sendKeys("manoj.jaiwant@gmail.com");
			driver.findElement(By.id("self_defined_company")).sendKeys("YesMSystems");
			
			Select cp = new Select(driver.findElement(By.id("company_type")));
			//cp.selectByValue("tech_partner");
			cp.selectByIndex(5);
			
			Select mp = new Select(driver.findElement(By.id("individual_role")));
			mp.selectByValue("technical/developer");
			cp.selectByIndex(2);
			
			Select cc = new Select(driver.findElement(By.id("country")));
			cc.selectByValue("IN"); 
			
			driver.findElement(By.id("password")).sendKeys("Welcome@123");
			driver.findElement(By.id("password-confirmation")).sendKeys("Welcome@123"); 
			
			driver.switchTo().frame(driver.findElement(By.xpath("//*[@id=\"recaptcha-f979c2ff515d921c34af9bd2aee8ef076b719d03\"]/div/div/iframe")));
			driver.findElement(By.className("recaptcha-checkbox-border")).click();
			driver.switchTo().defaultContent();
			
			if(!driver.findElement(By.id("agree_terms")).isSelected()) {
				driver.findElement(By.id("agree_terms")).click();
			}  
			
			driver.quit();
		}
	}

