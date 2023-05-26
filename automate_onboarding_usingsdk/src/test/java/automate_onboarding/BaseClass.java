 package automate_onboarding;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeSuite;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
// Running with Automate
public class BaseClass{
	
	public WebDriver driver;
	
	@BeforeMethod(alwaysRun = true)
    public void setUp() throws Exception {
       ChromeOptions options = new ChromeOptions();
       options.addArguments("start-maximized");
       driver = new ChromeDriver(options);
       driver.manage().window().maximize();
   }
    
	
    @Test(priority=1)
    public void LogintoPortal() throws InterruptedException {
        driver.get("https://www.browserstack.com/users/sign_in");
        // Thread.sleep(25000);
    	// driver.findElement(By.id("user_email_login")).sendKeys(Keys.CONTROL + "a");
        //driver.findElement(By.id("user_password")).sendKeys(Keys.CONTROL + "a");
    //	 driver.findElement(By.id("user_email_login")).clear();
    //	 driver.findElement(By.id("user_password")).clear();
    	  Thread.sleep(1000);
        driver.findElement(By.id("user_email_login")).sendKeys(System.getenv("browserstack_username"));
        driver.findElement(By.id("user_password")).sendKeys(System.getenv("browserstack_password"));
        driver.findElement(By.id("user_submit")).click();
        Thread.sleep(1000);
        String loginSuccessMsg = "You are already part of  team";
        String checkLoginSuccess = driver.findElement(By.xpath("/html/body/main/div[2]/div[3]/section/h3")).getText();
        assert loginSuccessMsg.equals(checkLoginSuccess);
    }
    
	
	  @Test(priority=2) public void LaunchLive() throws InterruptedException {
		  Thread.sleep(1000); 
		  driver.get("https://live.browserstack.com/dashboard");
		  Thread.sleep(1000); 
	  }
	 
    
    @Test(priority=3)
    public void LaunchBrowser() throws InterruptedException {
    	   // 	driver.findElement(By.xpath("/html/body/main/div/div[21]/div/div[2]/div/div[2]/div[2]/div[1]/div[1]/div/div/span")).click() ;
    		driver.findElement(By.xpath("//*[@id=\"platform-list-react\"]/div/div[2]/div/div[2]/div[3]/div[1]/div[1]/div/div")).click();
    		Thread.sleep(5000);
    }
    
	@Test(priority=4)
	    public void LoginNegativeTest() throws InterruptedException {
			 driver.quit();
			 ChromeOptions options = new ChromeOptions();
		     options.addArguments("start-maximized");
		     driver = new ChromeDriver(options);
		     driver.manage().window().maximize();
	    	 Thread.sleep(1000);
	         driver.findElement(By.id("user_email_login")).sendKeys("bhavykarimikonda98@gmail.com");
	         driver.findElement(By.id("user_password")).sendKeys("invalidpassword");
	         driver.findElement(By.id("user_submit")).click();
	    	 String errMsg = "Invalid password" ;
	    	 String checkErrMsg = driver.findElement(By.xpath("/html/body/main/div[4]/section/form/div[1]/div/div[1]/fieldset/div[5]/div/div/div/span")).getText();
	    	 assert errMsg.equals(checkErrMsg);
	    }

	@AfterMethod(alwaysRun = true)
    public void tearDown() throws Exception {
        driver.quit();
    }

}
