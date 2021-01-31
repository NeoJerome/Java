package com.projet.istqb;


import org.testng.annotations.Test;
import pages.AnswPage;
import pages.IstqbPage;
import pages.MailboxPage;
import pages.ToolboxPage;

import static org.testng.Assert.assertEquals;

public class TestCpc extends BaseTests{
    private String expResult = "Vous avez bien répondu à 20 question(s) sur 20, soit 100 % de réussite. Félicitations, vous avez obtenu le score maximal !";
    private String email = "norilus@yopmail.com";

    @Test
    public void testSucessfulCpc(){
        ToolboxPage toolboxPage = homePage.clickToolboxLink();
        IstqbPage istqbPage = toolboxPage.clickLinkByHref();
        AnswPage answPage = istqbPage.clickRadio();
        MailboxPage mailboxPage = answPage.emailResult(email);
        String result = mailboxPage.setResult();
        assertEquals(result,expResult);
    }
}
