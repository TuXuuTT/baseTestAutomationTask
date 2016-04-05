package com.ui.automation.pageobjects;

import com.ui.automation.environment.EnvironmentConfigurator;
import org.openqa.selenium.By;
import ru.yandex.qatools.allure.annotations.Step;

import static com.codeborne.selenide.Selenide.*;
import static org.testng.Assert.assertTrue;

public class BasicAppLoginPage extends BasicPage {
    EnvironmentConfigurator instance = EnvironmentConfigurator.getInstance();

    protected By inputEmail= By.cssSelector("#user_email");
    protected By inputPassword = By.cssSelector("#user_password");
    protected By buttonLogIn = By.cssSelector(".btn.btn-large.btn-primary");

//    @Step
//    public BaseAppLoginPage searchFor(String textToSeach) {
//        inputSearch.setValue(textToSeach).pressEnter();
//        return page(this);
//    }
//
//    @Step
//    public BaseAppLoginPage verifyPageTitle() {
//        assertTrue(title().contains(pageTitle), String.format("Not on the search page, but on %s", getWebDriverCurrent().getCurrentUrl()));
//        return page(this);
//    }

    @Step
    public BasicAppLoginPage loginAsAdmin(){
        enterCredentialsAndSubmit(instance.getAdminLogin(),instance.getPassword());
        return page(this);
    }

    protected void enterCredentialsAndSubmit(String email, String password){
        $(inputEmail).setValue(email);
        $(inputPassword).setValue(password);
        $(buttonLogIn).click();
    }
}
