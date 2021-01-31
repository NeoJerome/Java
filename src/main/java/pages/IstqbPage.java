package pages;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;



public class IstqbPage {
    private WebDriver driver;
    private String testLinkHref = "https://www.hightest.nc/ressources/test-istqb.php";
    private String path = "./src/Test_ISTQB/quizdata/cpc.json";

    public IstqbPage(WebDriver driver){
        this.driver = driver;
    }


    public AnswPage clickRadio() {
        Set<String> openWindows = driver.getWindowHandles();
        Iterator<String> nbrOpenWin = openWindows.iterator();
        JSONParser parser = new JSONParser();
        WebDriverWait wait = new WebDriverWait(driver,5);
        int sizeQuiz = 20;
        int idAnsw =0;
        int[] goodAnsw = new int[sizeQuiz];
        try {
            JSONArray jsonArray = (JSONArray) parser.parse(new FileReader(path));
            for(int i=0;i<jsonArray.size();i++){
                JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                long buffer = (long) jsonObject.get("quest");
                goodAnsw[i] = (int) buffer;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        while(nbrOpenWin.hasNext()) {
            String openWindow = nbrOpenWin.next();
            driver.switchTo().window(openWindow);
            if(driver.getCurrentUrl().contains(testLinkHref)){
                break;
            }
        }
        driver.manage().window().fullscreen();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        List<WebElement> radios = driver.findElements(By.tagName("input"));

        while(idAnsw<radios.size()) {
            WebElement radio = radios.get(idAnsw);
            if(radio.getAttribute("value").contains("5")) {
                radios.remove(idAnsw);
            }
            idAnsw++;
        }
        Actions action = new Actions(driver);
        for(int i=0;i<sizeQuiz;i++) {
            goodAnsw[i] = 4*i + goodAnsw[i]-1;
            try{
                radios.get(goodAnsw[i]).click();
            } catch (Exception e1) {
                if (goodAnsw[i] < radios.size() - 4) {
                    action.moveToElement(radios.get(goodAnsw[i] + 4)).perform();
                    wait.until(ExpectedConditions.visibilityOf(radios.get(goodAnsw[i] + 4)));
                } else {
                    action.moveToElement(radios.get(radios.size() - 1)).perform();
                    wait.until(ExpectedConditions.visibilityOf(radios.get(radios.size() - 1)));
                }
                radios.get(goodAnsw[i]).click();
            }
        }
        action.moveToElement(driver.findElement(By.xpath("//input[@id='submit']"))).perform();
        action.sendKeys(Keys.PAGE_DOWN).perform();
        driver.findElement(By.xpath("//input[@id='submit']")).click();
        return new AnswPage(driver);
    }
}
