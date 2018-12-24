package Seleniumtutorials;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class completewebform {

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver","C:\\Users\\ramya\\Documents\\Software\\chromedriver.exe");
		WebDriver obj;
		
		obj = new ChromeDriver();
		obj.manage().window().maximize();
		obj.manage().deleteAllCookies();
		obj.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		obj.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		
		obj.get("http://formy-project.herokuapp.com/");
		
		Actions a = new Actions(obj);
		WebElement formbutton = obj.findElement(By.linkText("Complete Web Form"));
		a.moveToElement(formbutton);
		formbutton.click();
		submitform(obj);
		waitforalert(obj);
		
		String s= getalert(obj);
		
		Assert.assertEquals("The form was successfully submitted!",s);
		
		obj.close();
		
	}
public static void submitform(WebDriver obj)
{
	//Filling the form
	
			WebElement fname = obj.findElement(By.id("first-name"));
			fname.sendKeys("Mahati");
			
			WebElement lname = obj.findElement(By.id("last-name"));
			lname.sendKeys("Adivishnu");
			
			WebElement jtitle = obj.findElement(By.id("job-title"));
			jtitle.sendKeys("Senior Consultant");
			
			WebElement degree = obj.findElement(By.xpath("//input[@value='radio-button-3']"));
			degree.click();
			
			WebElement sex = obj.findElement(By.xpath("//input[@value='checkbox-2']"));
			sex.click();
			
			Select s = new Select(obj.findElement(By.id("select-menu")));
			s.selectByIndex(3);
			
			WebElement date = obj.findElement(By.id("datepicker"));
			date.sendKeys("08/21/2016");
			date.sendKeys(Keys.RETURN);
			
			WebElement submit = obj.findElement(By.xpath("//a[@role='button']"));
			submit.click();
			
}

public static void waitforalert(WebDriver obj)
{
	WebDriverWait w = new WebDriverWait(obj,20);
	
	w.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[role='alert']")));
	
}

public static String getalert(WebDriver obj)
{
	WebElement msg = obj.findElement(By.cssSelector("div[role='alert']"));
	String text = msg.getText();
	return text;
}

}
