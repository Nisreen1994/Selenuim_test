package com.example;

//import org.junit.Assert;
import org.testng.Assert;
import java.util.List;

import org.junit.FixMethodOrder;
//import org.junit.Test;
import org.testng.annotations.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.*;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.html5.LocalStorage;
import org.openqa.selenium.html5.SessionStorage;
import org.openqa.selenium.html5.WebStorage;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.swing.*;
import java.awt.*;
import java.util.Collections;
import java.util.List;
import java.util.function.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Main {





    //public static void main(String[] args) {}
	@Test
        public void test1_StartWebDriver(){
        System.setProperty("webdriver.chrome.driver", "C://Chromedriver/chromedriver.exe");
            WebDriver driver = new ChromeDriver();
            driver.navigate().to("https://www.google.com/");
            String title = driver.getTitle();
            System.out.println("title is: "+ title);
            String expected_title = "Google";
            Assert.assertEquals(title,expected_title);
            System.out.println("test finished");
            //driver.close();

        }
   @Test
   public void test2_Login(){

            System.setProperty("webdriver.chrome.driver", "C://Chromedriver/chromedriver.exe");
            WebDriver driver = new ChromeDriver();
       driver.navigate().to("http://localhost:3000/sign-in");
       driver.findElement(By.xpath("//div/div/div[2]/div/div/div/div/form/div/div[1]/input")).sendKeys("nisreen94@gmail.com");
       driver.findElement(By.xpath("//div/div/div[2]/div/div/div/div/form/div/div[2]/input")).sendKeys("123");
       driver.findElement(By.xpath("//div/div/div[2]/div/div/div/div/form/div/div[3]/button")).click();


   }


    @Test
    public void test3_AddNewProject(){
        System.setProperty("webdriver.chrome.driver","C://Chromedriver/chromedriver.exe");
        WebDriver driver =new ChromeDriver();
        driver.navigate().to("http://localhost:3000/home");
        //driver.findElement(By.xpath("//*[@id=\"select-project\"]")).click();
        driver.navigate().to("http://localhost:3000/nav");
        driver.findElement(By.xpath("//*[@id=\"add-project\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"add-project-text\"]")).sendKeys("Test");
        driver.findElement(By.xpath("//*[@id=\"add\"]")).click();
        //Alert alert = driver.switchTo().alert();
        //alert.accept();
        //driver.navigate().to("http://localhost:3000/home");
        //driver.findElement(By.xpath("//*[@id=\"select-project\"]")).click();
        //driver.findElement(By.id("8")).click();
        try{
            //Wait 10 seconds till alert is present
            WebDriverWait wait = new WebDriverWait(driver, 50);
            Alert alert = wait.until(ExpectedConditions.alertIsPresent());

            //Accepting alert.
            alert.accept();
            System.out.println("Accepted the alert successfully.");
        }catch(Throwable e){
            System.err.println("Error came while waiting for the alert popup. "+e.getMessage());
        }

        driver.navigate().to("http://localhost:3000/home");
        WebElement dropDownButton = driver.findElement(By.id("select-project"));
        dropDownButton.click();
        List<WebElement> options = driver.findElements(By.xpath("//*[@id=\"simple-menu\"]/div[3]/ul/li"));
        int actual_No_Options = options.size();
        //Assert.assertEquals(3, );
        Assert.assertEquals(actual_No_Options,3);
        System.out.println("options "+  options.size());
        //driver.findElement(By.id("8")).click();
        //WebElement text_field = driver.findElement(By.xpath("//*[@id=\"standard-basic\"]"));
        //String actual_text = text_field.getAttribute("defaultValue");
        //Assert.assertEquals("Test", actual_text);









    }
    @Test
    public void test4_SelectProject(){
        System.setProperty("webdriver.chrome.driver","C://Chromedriver/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.navigate().to("http://localhost:3000/home");
        WebElement dropDownButton = driver.findElement(By.id("select-project"));
        List<WebElement> rows = driver.findElements(By.xpath("//*[@id=\"root\"]/div/div[2]/div/div/div/div[2]/div/table/tbody/tr"));
        int NoRows= rows.size();
        System.out.println("Norows "+NoRows);
        dropDownButton.click();
        driver.findElement(By.id("3")).click();
        List<WebElement> expected_rows = driver.findElements(By.xpath("//*[@id=\"root\"]/div/div[2]/div/div/div/div[2]/div/table/tbody/tr"));
        int actual_NoRows = expected_rows.size();
        System.out.println("Norows "+ actual_NoRows);
        if (actual_NoRows>NoRows){
            System.out.println("test finished");
        }
        else {
            System.out.println("test failed");
        }
        //Assert.assertEquals(3, actual_NoRows);
        Assert.assertTrue(actual_NoRows>NoRows);




    }

    @Test
    public void test5_AddDataToSelectedProject(){
        System.setProperty("webdriver.chrome.driver", "C://Chromedriver/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.navigate().to("http://localhost:3000/home");
        WebElement dropDownButton = driver.findElement(By.id("select-project"));
        dropDownButton.click();
        driver.findElement(By.id("4")).click();
        WebElement addBoutten = driver.findElement(By.id("Add-Data"));
        addBoutten.click();
        driver.findElement(By.name("activity")).sendKeys("Test-Activity");
        driver.findElement(By.name("date")).sendKeys("19-05-2020");
        driver.findElement(By.name("startTime")).sendKeys("11:21");
        driver.findElement(By.name("endTime")).sendKeys("12:21");
        driver.findElement(By.id("description")).sendKeys("Test");
        driver.findElement(By.id("add-Data-Form")).click();
        try{
            //Wait 10 seconds till alert is present
            WebDriverWait wait = new WebDriverWait(driver, 50);
            Alert alert = wait.until(ExpectedConditions.alertIsPresent());

            //Accepting alert.
            alert.accept();
            System.out.println("Accepted the alert successfully.");
        }catch(Throwable e){
            System.err.println("Error came while waiting for the alert popup. "+e.getMessage());
        }
        driver.findElement(By.id("Close-form")).click();
        List<WebElement> expected_rows = driver.findElements(By.xpath("//*[@id=\"root\"]/div/div[2]/div/div/div/div[2]/div/table/tbody/tr"));
        int actual_NoRows = expected_rows.size();
        System.out.println("Norows "+ actual_NoRows);
        if (actual_NoRows>0){
            System.out.println("test finished");
        }
        else {
            System.out.println("test failed");
        }
        Assert.assertEquals(1, actual_NoRows);



    }

    @Test
    public void test6_EditDataFromSelectedProject(){
        System.setProperty("webdriver.chrome.driver", "C://Chromedriver/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.navigate().to("http://localhost:3000/home");
        WebElement dropDownButton = driver.findElement(By.id("select-project"));
        dropDownButton.click();
        driver.findElement(By.id("3")).click();

        WebElement editButton =driver.findElement(By.xpath("//table/tbody/tr[1]/td[7]/div/button[1]"));
        editButton.click();
       WebElement activity = driver.findElement(By.xpath("//body/div[8]/div/div/div[2]/div/div/div/form/div[2]/input"));
        activity.sendKeys(Keys.CONTROL + "a");
        activity.sendKeys(Keys.DELETE);
        activity.sendKeys("Test-test");
        WebElement description = driver.findElement(By.xpath("//body/div[8]/div/div/div[2]/div/div/div/form/div[6]/input"));
        description.sendKeys(Keys.CONTROL + "a");
        description.sendKeys(Keys.DELETE);
        description.sendKeys("Test-description");
        driver.findElement(By.xpath("//body/div[8]/div/div/div[2]/div/div/div/form/div[7]/button")).click();

        try{
            //Wait 10 seconds till alert is present
            WebDriverWait wait = new WebDriverWait(driver, 50);
            Alert alert = wait.until(ExpectedConditions.alertIsPresent());

            //Accepting alert.
            alert.accept();
            System.out.println("Accepted the alert successfully.");
        }catch(Throwable e){
            System.err.println("Error came while waiting for the alert popup. "+e.getMessage());
        }
        driver.findElement(By.xpath("//body/div[8]/div/div/div[3]/button")).click();
        WebElement text_field = driver.findElement(By.xpath("//body/div[1]/div/div[2]/div/div/div/div[2]/div/table/tbody/tr/td[2]"));
        String actual_text_activity = text_field.getText();
        System.out.println(actual_text_activity);
        Assert.assertEquals("Test-test",actual_text_activity);
        WebElement text_field_description = driver.findElement(By.xpath("//body/div[1]/div/div[2]/div/div/div/div[2]/div/table/tbody/tr/td[6]"));
        String actual_text_description = text_field_description.getText();
        System.out.println(actual_text_description);
        Assert.assertEquals("Test-description", actual_text_description);







    }
    @Test
    public void test7_DeleteDataFromSelectedProject(){
        System.setProperty("webdriver.chrome.driver", "C://Chromedriver/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.navigate().to("http://localhost:3000/home");
        WebElement dropDownButton = driver.findElement(By.id("select-project"));
        dropDownButton.click();
        driver.findElement(By.id("3")).click();
         driver.findElement(By.xpath("//table/tbody/tr[3]/td[7]/div/button[2]")).click();

        try{
            //Wait 10 seconds till alert is present
            WebDriverWait wait = new WebDriverWait(driver, 50);
            Alert alert = wait.until(ExpectedConditions.alertIsPresent());

            //Accepting alert.
            alert.accept();
            System.out.println("Accepted the alert successfully.");
        }catch(Throwable e){
            System.err.println("Error came while waiting for the alert popup. "+e.getMessage());
        }
        List<WebElement> expected_rows = driver.findElements(By.xpath("//*[@id=\"root\"]/div/div[2]/div/div/div/div[2]/div/table/tbody/tr"));
        int actual_NoRows = expected_rows.size();
        System.out.println("Norows "+ actual_NoRows);
        if (actual_NoRows==2){
            System.out.println("test finished");
        }
        else {
            System.out.println("test failed");
        }
        Assert.assertEquals(2, actual_NoRows);





    }



}
