
package ohtu;

import java.util.Random;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class Tester {
    public static void main(String[] args) {
        WebDriver driver = new HtmlUnitDriver();

        driver.get("http://localhost:8080");
        System.out.println( driver.getPageSource() );
        WebElement element = driver.findElement(By.linkText("login"));
        element.click(); 
        element = driver.findElement(By.name("username"));
        element.sendKeys("pekka");
        element = driver.findElement(By.name("password"));
        element.sendKeys("akkeppppp");
        element.submit();
        System.out.println(driver.getPageSource().contains("wrong username or password"));
        
        element = driver.findElement(By.name("username"));
        element.clear();
        element.sendKeys("pekkaaaaa");
        element = driver.findElement(By.name("password"));
        element.sendKeys("akkep");
        element.submit();
        System.out.println(driver.getPageSource().contains("wrong username or password"));
        
        driver.get("http://localhost:8080");
        element = driver.findElement(By.linkText("register new user"));
        element.click();
        String name = Integer.toString(10000 + new Random().nextInt(100000));
        element = driver.findElement(By.id("username"));
        element.sendKeys(name);
        element = driver.findElement(By.id("password"));
        element.sendKeys("acbcd12345");
        element = driver.findElement(By.id("passwordConfirmation"));
        element.sendKeys("acbcd12345");
        element.submit();
        System.out.println(driver.getPageSource().contains("Welcome to Ohtu App!"));
        element = driver.findElement(By.linkText("continue to application mainpage"));
        element.click();
        
        
        element = driver.findElement(By.linkText("logout"));
        element.click();
        element = driver.findElement(By.linkText("login"));
        element.click();
        element = driver.findElement(By.name("username"));
        element.clear();
        element.sendKeys(name);
        element = driver.findElement(By.name("password"));
        element.sendKeys("acbcd12345");
        element.submit();
        System.out.println(driver.getPageSource().contains("Welcome to Ohtu Application!"));
        
        
        /**element.click();         
        System.out.println("==");        
        System.out.println( driver.getPageSource() );
        element = driver.findElement(By.name("username"));
        element.sendKeys("pekka");
        element = driver.findElement(By.name("password"));
        element.sendKeys("akkep");
        element = driver.findElement(By.name("login"));
        element.submit();      
        System.out.println("==");
        System.out.println( driver.getPageSource() );**/
        
    }
}
