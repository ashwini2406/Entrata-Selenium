package entrata.baseclass;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

public class BaseClass {
	
	public static WebDriver driver;
	public static Properties config=new Properties();
	public static Properties OR=new Properties();
	public static FileInputStream fis;
	//public static ExcelReader excel=new ExcelReader()
	public static WebDriverWait wait;
	
	

	@BeforeMethod
	@BeforeSuite
	public void setUp() {
		if(driver==null) {
			
			try {
				fis = new FileInputStream(System.getProperty("user.dir")+"/src/test/resources/properties/Config.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    try {
				config.load(fis);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    
		    try {
				fis=new FileInputStream(System.getProperty("user.dir")+"/src/test/resources/properties/OR.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    try {
				OR.load(fis);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    if(config.getProperty("browser").equals("firefox")) {
		    	driver =new FirefoxDriver();
		    }
		    else if (config.getProperty("browser").equals("chrome")) {
		    	//System.setProperty("webdriver.chrome.driver", "");
		    	driver=new ChromeDriver();
		    	
		    }
		    else if (config.getProperty("browser").equals("ie")) {
		    	System.setProperty("webdriver.ie.driver", System.getProperty("user+dir")+"/src/test/resources/executables/IEDriverServer.exe");
		    	driver=new InternetExplorerDriver();
		    	
		    }
		    driver.get(config.getProperty("url"));
		    driver.manage().window().maximize();
		    driver.manage().timeouts().implicitlyWait(Integer.parseInt((config.getProperty("implicityWait"))), TimeUnit.SECONDS);
		    driver.findElement(By.className("text-block-40")).click();
		   // Wait up to 10 seconds
		    //wait=new WebDriverWait(driver,5);
		}
		
	}
	
	public boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		}
		catch(NoSuchElementException e){
			return false;
			
		}
		
	}
	//private static final Logger logger = LogManager.getLogger(LoginToEntrata.class);
	
	@AfterMethod
	@AfterSuite
	public void tearDown() {
		if(driver==null) {
			driver.quit();
		}
		
		
	}
}
