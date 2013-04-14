import ohtu.*
import ohtu.authentication.*
import org.openqa.selenium.*
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

description '''A new user account can be created 
              if a proper unused username 
              and a proper password are given'''

scenario "creation succesfull with correct username and password", {
    given 'command new user is selected', {
       driver = new HtmlUnitDriver();
       driver.get("http://localhost:8080");
       element = driver.findElement(By.linkText("register new user"));
       element.click();
    }
 
    when 'a valid username and password are entered', {
        element = driver.findElement(By.id("username"));
        element.sendKeys("aapeli3");
        element = driver.findElement(By.id("password"));
        element.sendKeys("acbcd12345");
        element = driver.findElement(By.id("passwordConfirmation"));
        element.sendKeys("acbcd12345");
        element.submit();
    }

    then 'new user is registered to system', {
      driver.getPageSource().contains("Welcome to Ohtu App!").shouldBe true
    }
}

scenario "can login with succesfully generated account", {
    given 'command new user is selected', {
       driver = new HtmlUnitDriver();
       driver.get("http://localhost:8080");
       element = driver.findElement(By.linkText("register new user"));
       element.click();
    }
 
    when 'a valid username and password are entered', {
        element = driver.findElement(By.id("username"));
        element.sendKeys("aapeli7");
        element = driver.findElement(By.id("password"));
        element.sendKeys("acbcd12345");
        element = driver.findElement(By.id("passwordConfirmation"));
        element.sendKeys("acbcd12345");
        element.submit();
    }

    then  'new credentials allow logging in to system', {
        element = driver.findElement(By.linkText("continue to application mainpage"));
        element.click();      
        element = driver.findElement(By.linkText("logout"));
        element.click();
        element = driver.findElement(By.linkText("login"));
        element.click();
        
        element = driver.findElement(By.name("username"));
        element.sendKeys("aapeli7");
        element = driver.findElement(By.name("password"));
        element.sendKeys("acbcd12345");
        element = driver.findElement(By.name("login"));
        element.submit();

        driver.getPageSource().contains("Welcome to Ohtu Application!").shouldBe true
    }
}

scenario "creation fails with correct username and too short password", {
    given 'command new user is selected', {
    	driver = new HtmlUnitDriver();
        driver.get("http://localhost:8080");
        element = driver.findElement(By.linkText("register new user"));
        element.click();
    }
    
    when 'a valid username and too short password are entered', {
    	element = driver.findElement(By.id("username"));
        element.sendKeys("aapeli8");
        element = driver.findElement(By.id("password"));
        element.sendKeys("acb");
        element = driver.findElement(By.id("passwordConfirmation"));
        element.sendKeys("acb");
        element.submit();
    }
    
    then 'new user is not be registered to system', {
        driver.getPageSource().contains("Welcome to Ohtu App!").shouldBe false
    }
}

scenario "creation fails with correct username and pasword consisting of letters", {
    given 'command new user is selected', {
    	driver = new HtmlUnitDriver();
        driver.get("http://localhost:8080");
        element = driver.findElement(By.linkText("register new user"));
        element.click();
    }
    when 'a valid username and password consisting of letters are entered', {
    	element = driver.findElement(By.id("username"));
        element.sendKeys("aapeli9");
        element = driver.findElement(By.id("password"));
        element.sendKeys("abcdefghi");
        element = driver.findElement(By.id("passwordConfirmation"));
        element.sendKeys("abcdefghi");
        element.submit();
    }
    then 'new user is not be registered to system', {
    	driver.getPageSource().contains("Welcome to Ohtu App!").shouldBe false
    }
}

scenario "creation fails with too short username and valid pasword", {
    given 'command new user is selected', {
    	driver = new HtmlUnitDriver();
        driver.get("http://localhost:8080");
        element = driver.findElement(By.linkText("register new user"));
        element.click();
    }
    when 'a too sort username and valid password are entered', {
    	element = driver.findElement(By.id("username"));
        element.sendKeys("aa");
        element = driver.findElement(By.id("password"));
        element.sendKeys("abcd12345");
        element = driver.findElement(By.id("passwordConfirmation"));
        element.sendKeys("abcd12345");
        element.submit();
    }
    then 'new user is not be registered to system', {
    	driver.getPageSource().contains("Welcome to Ohtu App!").shouldBe false
    }
}

scenario "creation fails with already taken username and valid pasword", {
    given 'command new user is selected', {
    	driver = new HtmlUnitDriver();
        driver.get("http://localhost:8080");
        element = driver.findElement(By.linkText("register new user"));
        element.click();
    }
    when 'a already taken username and valid password are entered', {
    	element = driver.findElement(By.id("username"));
        element.sendKeys("pekka");
        element = driver.findElement(By.id("password"));
        element.sendKeys("abcd12345");
        element = driver.findElement(By.id("passwordConfirmation"));
        element.sendKeys("abcd12345");
        element.submit();
        
    }
    then 'new user is not be registered to system', {
    	driver.getPageSource().contains("Welcome to Ohtu App!").shouldBe false
    }
}

scenario "can not login with account that is not succesfully created", {
    given 'command new user is selected', {
    	driver = new HtmlUnitDriver();
        driver.get("http://localhost:8080");
        element = driver.findElement(By.linkText("register new user"));
        element.click();
    }
    when 'a invalid username/password are entered', {
    	element = driver.findElement(By.id("username"));
        element.sendKeys("aa");
        element = driver.findElement(By.id("password"));
        element.sendKeys("abcd12345");
        element = driver.findElement(By.id("passwordConfirmation"));
        element.sendKeys("abcd12345");
        element.submit();
        driver.get("http://localhost:8080");
        element = driver.findElement(By.linkText("login"));
        element.click();
        
    }
    then  'new credentials do not allow logging in to system', {
    	element = driver.findElement(By.name("username"));
        element.sendKeys("aa");
        element = driver.findElement(By.name("password"));
        element.sendKeys("abcd12345");
        element.submit();
        driver.getPageSource().contains("Welcome to Ohtu Application!").shouldBe false
    }
}