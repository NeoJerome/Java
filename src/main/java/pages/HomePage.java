package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/* Classe HomePage :
 * Navigateur cible : Google Chrome.
 * Cette classe représente la page d'accueil "https://hightest.nc/"
 */
public class HomePage {
    private WebDriver driver;
    private By toolboxLink = By.linkText("TOOLBOX");

    //Constructeur de la classe HomePage
    public HomePage(WebDriver driver){
        this.driver = driver;
    }

    /* Méthode ToolboxPage :
     * Cette méthode permet de retourner une page objet "ToolboxPage".
     * Cette page correspond à la page "boite-outils" du site de Hightest
     */
    public ToolboxPage clickToolboxLink(){
        driver.findElement(toolboxLink).click();
        return new ToolboxPage(driver);
    }
}
