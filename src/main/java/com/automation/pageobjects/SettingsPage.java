package com.automation.pageobjects;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import ru.yandex.qatools.allure.annotations.Step;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.codeborne.selenide.Selenide.*;

public class SettingsPage extends DashboardPage {
    protected By linkLeadsSettings = By.cssSelector(".leads a[href='/settings/leads']");
    protected By tabCustomFields = By.cssSelector("a[href='#custom-fields']");
    protected By divCustomFieldRow = By.cssSelector("#custom-fields .named-object-lead.positionable");
    protected By buttonEdit = By.cssSelector(".btn.btn-mini.edit");

    protected By inputFieldName = By.cssSelector(".field-name.valid");
    protected By selectFieldTypeDisabled = By.cssSelector("#field_type[disabled]");
    Map<String, String> existingCustomFields;
    private By buttonCancel = By.cssSelector(".cancel-link.cancel");

    public Map<String, String> getExistingCustomFields() {
        return existingCustomFields;
    }

    @Step
    public SettingsPage navigateToLeadSettings() {
        $(linkLeadsSettings).click();
        waitForPageLoaded();
        $(tabCustomFields).click();
        return page(this);
    }

    public Map<String, String> collectCustomFieldsNamesAndTypes() {
        existingCustomFields = new HashMap<>();
        if ($$(divCustomFieldRow).size() > 0) {
            for (SelenideElement element : $$(divCustomFieldRow)) {
                element.$(buttonEdit).click();
                element.click();
                String keyType = element.$(selectFieldTypeDisabled).getText();
                String valueName = element.$(inputFieldName).getValue();
                existingCustomFields.put(keyType, valueName);
                element.$(buttonCancel).click();
            }
        } else {
            LOGGER.warn("Custom fields are not defined");
        }
        return existingCustomFields;
    }

    @Step
    public void showAndLogAvailableCustomFieldsTypesAndNames() {
        LOGGER.warn("================AVIALABLE CUSTOM FIELDS================");
        for (Map.Entry<String, String> entry : (collectCustomFieldsNamesAndTypes()).entrySet()) {
            LOGGER.info("-----------");
            LOGGER.info(String.format("Custom field type is: '%s'", entry.getKey()));
            LOGGER.info(String.format("Custom field name is: '%s'", entry.getValue()));
        }
        LOGGER.warn("=======================================================");
    }

    public List<String> getCustomFieldsNames() {
        return new ArrayList<>(getExistingCustomFields().values());
    }
}
