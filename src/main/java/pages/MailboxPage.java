package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Iterator;
import java.util.List;

public class MailboxPage {
    private WebDriver driver;
    //Constructeur de la classe mailboxPage
    public MailboxPage(WebDriver driver){
        this.driver = driver;
    }
    public String setResult(){
        driver.get("http://www.yopmail.com/");
        driver.findElement(By.id("login")).sendKeys("norilus");
        driver.findElement(By.xpath("//input[@value='Vérifier les mails']")).click();
        //WebElement test  = driver.findElement(By.className("lm"));
        driver.switchTo().frame("ifmail");
        WebElement test = driver.findElement(By.xpath("//div[@id='mailmillieu']/div[2]/div/div[2]/div[1]"));
        String result = test.getText();
        return result;
    }
    public void delete(){
        driver.get("http://www.yopmail.com/");
        driver.findElement(By.id("login")).sendKeys("norilus");
        driver.findElement(By.xpath("//input[@value='Vérifier les mails']")).click();

    }

}
