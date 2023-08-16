package com.NykaaPRJ.runnerone;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;
import nykaa.base.BaseClass;

public class NykaaRunner extends BaseClass {

//	public static WebDriver driver;
	public static Set<String> windowHandles;
	public static WebDriverWait wait;

	public static void browserLaunch() {
		browserlaunch("chrome");
		urlLaunch("https://www.nykaa.com/");
		
		
	}

	public static void selectProduct() {
		WebElement prd_heading = driver
				.findElement(By.xpath("//ul[@class='MegaDropdownHeading']//li//a[text()='mom & baby']"));
		Actions action = new Actions(driver);
		action.moveToElement(prd_heading).perform();

		WebElement prd_baby_powder = driver
				.findElement(By.xpath("//ul[@class='MegaDropdownHeading']//li//a[text()='Baby Powder']"));
		prd_baby_powder.click();

		getWindowTitle(driver, "Shop For The Best Baby Powder & Get The Best Offers | Nykaa");

		WebElement himalaya_baby_powder = driver.findElement(By.xpath(
				"//div[@class='productWrapper css-17nge1h']//a//div[@class='css-1rd7vky']//div[text()='Himalaya Baby Powder']"));

		himalaya_baby_powder.click();

	}

	public static void getWindowTitle(WebDriver driver, String title_name) {

		windowHandles = driver.getWindowHandles();

		for (String title : windowHandles) {

			if (driver.switchTo().window(title).getTitle().equalsIgnoreCase(title_name)) {
				break;
			}
		}

	}

	public static void getProduct() {

		getWindowTitle(driver, "Himalaya Baby Powder: Buy Himalaya Baby Powder Online at Best Price in India | Nykaa");

		WebElement dropdown_size = driver.findElement(By.xpath("//select[@title='SIZE']"));
		Select s = new Select(dropdown_size);
		s.selectByVisibleText("200g");

		WebElement add_to_bag = driver.findElement(By.xpath("//button[@class=' css-13zjqg6'][1]"));
		add_to_bag.click();

		WebElement cart_click = driver.findElement(By.xpath("//button[@class='css-aesrxy']"));
		cart_click.click();

		WebElement iframe = driver.findElement(By.xpath("//iframe[@class='css-acpm4k']"));
		driver.switchTo().frame(iframe);

		WebElement quantity = driver.findElement(By.xpath("//div[@id='4790']//div[@class='css-1ppri07 epdmlj41']"));
		quantity.click();

		WebElement grand_total = driver.findElement(By.xpath("//div[@class='css-0 edkwwn2']//li//div[@label='3']"));
		grand_total.click();

		WebElement proceed = driver.findElement(By.xpath("//span[text()='Proceed']"));
		proceed.click();
	}

	public static void log_in() throws InterruptedException, IOException {
		WebElement login = driver.findElement(By.xpath("//button[text()='Log In']"));
		login.click();

		WebElement Enter_number = driver.findElement(By.xpath("//input[@placeholder='Enter Mobile Number or Email']"));
		Enter_number.click();

		WebElement mobileNum = driver.findElement(By.name("emailMobile"));
		mobileNum.sendKeys("8838796583");

		WebElement p_mobile = driver.findElement(By.id("submitVerification"));
		p_mobile.click();

		Scanner sc = new Scanner(System.in);
		String otp = sc.next();

		WebElement otp_num = driver.findElement(By.id("otpField"));
		otp_num.sendKeys(otp);
		sc.close();

		Thread.sleep(20000);
		WebElement verify = driver.findElement(By.xpath("//button[@type='submit']"));
		verify.click();

		WebElement deliver_add = driver.findElement(By.xpath("//button[text()='Deliver here']"));
		deliver_add.click();
		Thread.sleep(3000);

		WebElement card_num = driver.findElement(By.xpath(
				"//div[@class='CustomPaymentMethodLayout css-3u4bb3 e180kyw24']//following::input[@placeholder='Card Number']"));
		card_num.click();
		Thread.sleep(2000);
		card_num.sendKeys("4242424242424242");

		WebElement expiry_date = driver.findElement(By.xpath("//input[@placeholder='Expiry (MM/YY)']"));
		expiry_date.click();
		Thread.sleep(2000);
		expiry_date.sendKeys("05/26");

		WebElement cvv = driver.findElement(By.xpath("//input[@placeholder='CVV']"));
		cvv.sendKeys("123");

		WebElement pay_btn = driver.findElement(By.xpath("//button[@class='css-v61n2j e8tshxd0']"));
		pay_btn.click();

		WebElement secure_card_pay = driver.findElement(By.xpath("//button[@class='css-1otrbng e8tshxd2']"));
		secure_card_pay.click();

		Thread.sleep(3000);
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File des = new File("C:\\Users\\zahee\\eclipse-workspace\\NykaaPRJ\\screenShot\\f.png");
		FileUtils.copyFile(source, des);

		WebElement close_pay = driver.findElement(By.xpath("//button[@class='css-1iwzqhf emgaj5l0']"));
		close_pay.click();
	}

	public static void remove_pro() {
		getWindowTitle(driver, "Shop For The Best Baby Powder & Get The Best Offers | Nykaa");

		WebElement cart_click = driver.findElement(By.xpath("//button[@class='css-aesrxy']"));
		cart_click.click();

		WebElement iframe = driver.findElement(By.xpath("//iframe[@class='css-acpm4k']"));
		driver.switchTo().frame(iframe);

		WebElement remove_btn = driver.findElement(By.xpath("//div[@id='4790']//span[@class='css-175whwo ehes2bo0']"));
		remove_btn.click();

		WebElement remove_prd = driver.findElement(By.xpath("//button[text()='Remove']"));
		remove_prd.click();

		WebElement back_arrow = driver
				.findElement(By.xpath("//div[@class='css-x6gbsc esabwed1']//span[@class='css-175whwo ehes2bo0']"));
		back_arrow.click();
	}

	public static void wait_e(WebDriver driver, WebElement element) {
		wait = new WebDriverWait(driver, 200);
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	public static void main(String[] args) throws InterruptedException, IOException {
		browserLaunch();
		selectProduct();
		getProduct();
		log_in();
		remove_pro();

	}
}
