package com.automation.pageobjects;

import com.automation.environment.EnvironmentConfigurator;
import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;
import ru.yandex.qatools.allure.annotations.Step;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class BaseAppLoginPage extends BasicPage {
    private static final By LOADING_BAR = By.cssSelector("#feedback-progress");
    protected By inputEmail = By.cssSelector("#user_email");
    protected By inputPassword = By.cssSelector("#user_password");
    protected By buttonLogIn = By.cssSelector(".btn.btn-large.btn-primary");
    EnvironmentConfigurator instance = EnvironmentConfigurator.getInstance();

    protected void waitForPageLoaded() {
        $(LOADING_BAR).waitUntil(Condition.hasClass("hide"), 30000L, 100L);
    }

    @Step
    public DashboardPage loginAsAdmin() {
        enterCredentialsAndSubmit(instance.getAdminLogin(), instance.getPassword());
        waitForPageLoaded();
        return page(DashboardPage.class);
    }

    protected void enterCredentialsAndSubmit(String email, String password) {
        $(inputEmail).setValue(email);
        $(inputPassword).setValue(password);
        $(buttonLogIn).click();
    }
}
