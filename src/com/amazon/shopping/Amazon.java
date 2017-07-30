
// Organizing the project classes under the package. Like drawers
package com.amazon.shopping;

// Import all dependences/libraries/ingredients
import java.util.List; // Array, List, Collection
import org.openqa.selenium.By; // Search elements on Page
import org.openqa.selenium.WebDriver; // Bridge b/w program and browser
import org.openqa.selenium.WebElement; // Each element on the web page
import org.openqa.selenium.chrome.ChromeDriver; // To work with Chrome browser
// Class declaration 
public class Amazon {
	// 
	public static void findDevice(WebDriver driver) {
        WebElement searchResults = driver.findElement(By.id("s-results-list-atf"));
        List<WebElement> searchResultLinks = searchResults.findElements(By.tagName("li"));
        System.out.println (searchResultLinks.size());
        for (int i = 0; i < searchResultLinks.size(); i++)
        {
        	WebElement link = searchResultLinks.get(i);
            System.out.println(link.getText());	
            System.out.println("count :" + i);
        
            WebElement abcElement = link.findElement(By.tagName("h2"));
            String attribute = abcElement.getAttribute("data-attribute");
            if (attribute.equalsIgnoreCase("Brand New Samsung Galaxy Amp2 Wind Rogers Bell Telus Chatr Mobilicity Unlockef")) {
           
            	System.out.println("device found, TEST PASSED!");
            	System.exit(0);
            } else if (i == searchResultLinks.size() - 1) { // 9 == 9
            	
            	WebElement xyzElement = driver.findElement(By.xpath("//a[@title='Next Page']"));
            	System.out.println("&&&&&&&&&&&&&&&&&&&&&");
            	System.out.println(xyzElement.getText());
            	System.out.println(xyzElement.getAttribute("href"));
            	driver.navigate().to(xyzElement.getAttribute("href"));
            	// Recursion. Alert: can cause memory issues
            	findDevice(driver);
            }
                              
        }
	}

	public static void main(String[] args) throws InterruptedException {
		// telling Chrome Browser where the exec. file is present. Chrome reads <webdriver.chrome.driver> to find out 
		System.setProperty("webdriver.chrome.driver",
		           "/Users/vandana/Documents/workspace/exec/chromedriver");
		
		// Instantiating Chrome Driver. Programming to Interface
		WebDriver driver = new ChromeDriver();
		
		// Open the Site
		driver.get("https://www.amazon.ca");
		// Maximize the window
		driver.manage().window().maximize();
		// Page may take longer to load. So let's wait for 5 seconds
		Thread.sleep(5000);
		
		// Find clickable link on page by text.
		driver.findElement(By.linkText("Sign in")).click();
        driver.findElement(By.id("ap_email")).sendKeys("****@gmail.com");
        driver.findElement(By.id("ap_password")).sendKeys("****");	
        driver.findElement(By.id("signInSubmit")).click();
        driver.findElement(By.id("twotabsearchtextbox")).sendKeys("Samsung");
        driver.findElement(By.xpath("//input[@type='submit']")).click();
        
        // We are getting control of Search Results
        WebElement searchResults = driver.findElement(By.id("s-results-list-atf"));
        
        // That element has List of products. So we are finding list of products in searchResult element
        List<WebElement> searchResultLinks = searchResults.findElements(By.tagName("li"));
        
        // Just printing how many products/element I found
        System.out.println (searchResultLinks.size());
        
        // Let's go through each product and find the right one
        for (int i = 0; i < searchResultLinks.size(); i++)
        {
        	// Read element/product from the list one by one
        	WebElement link = searchResultLinks.get(i); // get element stored at a Index in the list
        	// print the product name
            System.out.println(link.getText());	
            //  Keep printing the counter so that we know its processing one by one
            System.out.println("count :" + i);
            
            // Now let's try to see if this product name matches our target product
            if (link.getText().equalsIgnoreCase("Brand New Samsung Galaxy Amp2 Wind Rogers Bell Telus Chatr Mobilicity Unlockef")) {
            	// Well, we have found the product of our requirement, so let's shut this test case now. Call it Passed
            	System.out.println("device found, TEST PASSED!");
            	System.exit(0);
            } else if (i == searchResultLinks.size() - 1) { // 9 == 9
            	// So at this point, we haven't found the target product. We have to now go to the next page
            	// and we will keep doing it until we find it.
            	
            	// get reference of Next Page button
            	WebElement xyzElement = driver.findElement(By.xpath("//a[@title='Next Page']"));
            	// Go to the next page's URL
            	driver.navigate().to(xyzElement.getAttribute("href"));
            	// Start to find for the product again. Pass Driver instance.
            	findDevice(driver);
            }
                              
        }

}
}
