package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class ToolboxPage {
    private WebDriver driver;
    private String testLinkHref = "http://www.hightest.nc/ressources/test-istqb.php";

    public ToolboxPage (WebDriver driver){
        this.driver = driver;
    }

    public IstqbPage clickLinkByHref() {
        List<WebElement> links = driver.findElements(By.tagName("a"));
        Iterator<WebElement> i = links.iterator();

        while(i.hasNext()) {
            WebElement link = i.next();
            if(link.getAttribute("href").contains(testLinkHref)) {
                link.click();
                break;
            }
        }
        return new IstqbPage(driver);
    }

}
