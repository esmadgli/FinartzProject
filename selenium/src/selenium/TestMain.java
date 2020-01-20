package selenium;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestMain {
	protected static String ChromePath = "C:\\Users\\TanerKahraman\\eclipse-workspace\\chromedriver.exe";
	protected static ChromeDriverService service;
	protected static WebDriver driver;
	protected static WebDriverWait wait;
	
	public static void Setup(){
	    // Chrome_driver.exe dizininden servisi oluþtur ve baþlat
	    service = new ChromeDriverService.Builder().usingDriverExecutable(new File(ChromePath)).usingAnyFreePort().build();
	     try {
	         service.start();
	     } catch (Exception e) {
	         e.printStackTrace();
	     }
	    
	     System.setProperty("webdriver.chrome.driver", ChromePath);
	     driver = new RemoteWebDriver(service.getUrl(),DesiredCapabilities.chrome());
	     // Penceri Büyüt
	     driver.manage().window().maximize();
	}
	
	public static void Stop(){
	     // Tarayýcýdan çýkýþ yapar
	     driver.quit();
	     // Servisi Durdurur
	     service.stop();
	}
	@Test
	public static void main(String[] args) throws InterruptedException {
		// Tarayýcý ve servisi baþlatýr
	      Setup();
	      // Google   sayfasýný Açar
	      driver.get("http://finartz.com/");
	      Thread.sleep(10000);
	      List<WebElement> buttons = driver.findElements(By.className("navbar-item"));
	      for(WebElement button:buttons) {
	    	  if(button.getText().contains("Solutions")) {
	    		  button.click();
	    		  break;
	    		  
	    	  }
	      }
	      Thread.sleep(15000);
	      driver.getCurrentUrl();
	      driver.getPageSource();
	      List<WebElement> titles = driver.findElements(By.className("title"));
	      ArrayList<String> mainTitles = new ArrayList<String>();
	      for(WebElement title: titles) {
	    	  if(title.getText().contains("Solutions")) {
	    		  continue;
	    	  }
	    	  else {
	    		  mainTitles.add(title.getText());
	    	  }
	      }
	      System.out.println(mainTitles);
	      Thread.sleep(5000);
	      List<WebElement> blogButton = driver.findElements(By.className("navbar-item"));
	      String windowHandle = driver.getWindowHandle();
	      for(WebElement button1:blogButton) {
	    	  if(button1.getText().contains("Blog")) {
	    		  button1.click();
	    		  break;
	    		  
	    	  }
	      }
	      Thread.sleep(15000);
	      for(String winHandle : driver.getWindowHandles()){
	    	    driver.switchTo().window(winHandle);
	      }
	      WebElement searchInput = driver.findElement(By.className("js-predictiveSearchInput"));
	      int randomTitle = new Random().nextInt(3);
	      searchInput.sendKeys(mainTitles.get(randomTitle));
	      Thread.sleep(5000);
	      WebElement searchOnMediumButton = driver.findElement(By.className("link--backgrounded"));
	      searchOnMediumButton.click();
	      Thread.sleep(5000);
	      
	      Stop();
    }
}
