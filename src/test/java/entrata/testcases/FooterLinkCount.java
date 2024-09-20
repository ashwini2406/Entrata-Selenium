package entrata.testcases;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import entrata.baseclass.BaseClass;

public class FooterLinkCount extends BaseClass {
    @Test
    public void footerLinkCount() {
        WebElement element = driver.findElement(By.cssSelector(".footer-nav")); 
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
        
        List<WebElement> subLinks = driver.findElements(By.cssSelector(".footer-column.no-bg")); 
        System.out.println("Total sub-footers: " + subLinks.size());

        Map<String, Integer> expectedCounts = new HashMap<>();
        expectedCounts.put("Property Management", 11); 
        expectedCounts.put("Marketing & Leasing", 13);
        expectedCounts.put("Accounting", 5);
        expectedCounts.put("Utilities", 6);
        expectedCounts.put("Solutions", 11);
        expectedCounts.put("Company", 11);
        
        for (WebElement footer : subLinks) {
            String subFooterValue = footer.getText().trim(); 
           // System.out.println("Value: " + subFooterValue);
            
            List<WebElement> links = footer.findElements(By.tagName("a"));
            int linkCount = links.size();
            System.out.println("Count of links: " + linkCount);
            
            Integer expectedCount = expectedCounts.get(subFooterValue); 
            if (expectedCount != null) {
               
                Assert.assertEquals(linkCount, expectedCount.intValue(), 
                    "Link count is invalid for sub-footer: " + subFooterValue);
            } else {
                System.out.println("No expected count found for sub-footer: " + subFooterValue);
            }
            
            System.out.println(); 
        }

       
        driver.quit();
    }
}
