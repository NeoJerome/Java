package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AnswPage {
    private WebDriver driver;

    //Constructeur de la classe AnswPage
    public AnswPage(WebDriver driver){
        this.driver = driver;
    }

    public MailboxPage emailResult(String email){
        driver.findElement(By.id("email")).sendKeys(email);
        driver.findElement(By.id("submitMail")).click();
        return  new MailboxPage(driver);
    }
}
