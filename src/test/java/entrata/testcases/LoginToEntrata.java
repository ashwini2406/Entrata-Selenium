package entrata.testcases;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;
import entrata.baseclass.BaseClass;

public class LoginToEntrata extends BaseClass {
	@Test
	public void LoginToEntrata() throws InterruptedException {
		
		Thread.sleep(3000);
		String currentUrl= driver.getCurrentUrl();
		String title=driver.getTitle();
		System.out.println(title);
		System.out.println(currentUrl);
	}
	
	@Test
	public void mouseHover(){
         String[] categoriesXpath = {
            "//div[text()='Products']", 
            "//div[text()='Solutions']",  
            "//div[text()='Resources']"   
        };

            for (String categoryXpath : categoriesXpath) {
            WebElement category= driver.findElement(By.xpath(categoryXpath));
            Actions act = new Actions(driver);
            act.moveToElement(category).perform();
            List<WebElement> dropdownItems = driver.findElements(By.cssSelector(".black-list.w-dropdown-list .footer-link"));
            int expectedItemCount = 68;
            Assert.assertEquals(dropdownItems.size(), expectedItemCount, "Dropdown item count is incorrect");
            //.black-list.w-dropdown-list .footer-link
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
		
		 
	}

	@Test
	public void watchDemo() throws InterruptedException {
        WebElement clkOnBtn = driver.findElement(By.cssSelector("a.red-button.red.w-inline-block"));
	    Assert.assertTrue(clkOnBtn.isDisplayed());
	    Assert.assertTrue(clkOnBtn.isEnabled());
	    clkOnBtn.click();
	    Thread.sleep(3000);
	    String parentWin = driver.getWindowHandle();
	    Set<String> allHandles = driver.getWindowHandles();
	    Iterator<String> itr = allHandles.iterator();
	    String newTab = null;
	    while (itr.hasNext()) {
	        String handle = itr.next();
	        if (!handle.equals(parentWin)) {
	            newTab = handle;
	            break;
	        }
	    }
	    Assert.assertNotNull(newTab,"No New tab was opened");

	    driver.switchTo().window(newTab);
	    driver.findElement(By.cssSelector(OR.getProperty("firstName"))).sendKeys(config.getProperty("fn"));
	    driver.findElement(By.cssSelector(OR.getProperty("lastName"))).sendKeys(config.getProperty("ln"));
	    driver.findElement(By.cssSelector(OR.getProperty("email"))).sendKeys("email");
	    driver.findElement(By.cssSelector(OR.getProperty("companyName"))).sendKeys(config.getProperty("cn"));
	    driver.findElement(By.cssSelector(OR.getProperty("phoneNumber"))).sendKeys(config.getProperty("no"));
	    driver.findElement(By.cssSelector(OR.getProperty("jobTitle"))).sendKeys(config.getProperty("jobTitle"));
	  
	    WebElement dropdwn = driver.findElement(By.xpath("//select[@id='Unit_Count__c']"));
	    Select drop = new Select(dropdwn);
	    Assert.assertTrue(dropdwn.isDisplayed());
	    Assert.assertTrue(dropdwn.isEnabled());
	    drop.selectByIndex(1);
	    Assert.assertEquals(drop.getFirstSelectedOption().getText(), "1 - 10", "Dropdown selection is incorrect");
	    
	    driver.switchTo().window(parentWin);
	    Assert.assertEquals(driver.getWindowHandle(), parentWin, "Failed to switch back to the parent window");

	
	}
	
	
}

