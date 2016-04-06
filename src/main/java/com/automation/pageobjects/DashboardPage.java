package com.automation.pageobjects;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;
import ru.yandex.qatools.allure.annotations.Step;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class DashboardPage extends BasicAppLoginPage {

    protected By toggleSettingsDropdown = By.cssSelector("a[data-original-title='Settings'] i");
    protected By linkSettings = By.cssSelector("li.settings");

    @Step
    public SettingsPage openSettings() {
        $(toggleSettingsDropdown).click();
        $(linkSettings).shouldBe(Condition.visible).click();
        return page(SettingsPage.class);
    }
}
