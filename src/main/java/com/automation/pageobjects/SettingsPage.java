package com.automation.pageobjects;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;

import java.util.HashMap;
import java.util.Map;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class SettingsPage extends DashboardPage {
    protected By linkLeadsSettings = By.cssSelector(".leads a[href='/settings/leads']");
    protected By tabCustomFields = By.cssSelector("a[href='#custom-fields']");
    protected By divCustomFieldRow = By.cssSelector("#custom-fields .named-object-lead.positionable");
    protected By buttonEdit = By.cssSelector(".btn.btn-mini.edit");

    protected By inputFieldName = By.cssSelector(".field-name.valid");
    protected By selectFieldTypeDisabled = By.cssSelector("#field_type[disabled]");
    private By buttonCancel = By.cssSelector(".cancel-link.cancel");

    public SettingsPage navigateToLeadSettings() {
        waitForPageLoaded();
        $(linkLeadsSettings).click();
        waitForPageLoaded();
        $(tabCustomFields).click();
        return this;
    }

    public Map collectCustomFieldsNamesAndTypes() {
        Map<String, String> result = new HashMap<>();
        for (SelenideElement element : $$(divCustomFieldRow)) {
            element.$(buttonEdit).click();
            element.click();
            String keyType = element.$(selectFieldTypeDisabled).getText();
            String valueName = element.$(inputFieldName).getValue();
            result.put(keyType, valueName);
            element.$(buttonCancel).click();
        }
        LOGGER.info(result);
        return result;
    }
}
