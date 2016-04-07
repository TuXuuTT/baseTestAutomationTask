package com.automation.pageobjects;

import com.codeborne.selenide.Condition;
import com.getbase.models.Lead;
import org.openqa.selenium.By;

import java.util.List;

import static com.codeborne.selenide.Selenide.*;
import static org.testng.Assert.assertEqualsNoOrder;
import static org.testng.Assert.assertTrue;

public class LeadsPage extends DashboardPage {
    protected By buttonNewLead = By.cssSelector("#leads-new");
    protected By formNewLead = By.cssSelector("div.lead-new");
    protected By customFieldRows = By.cssSelector("div.custom-fields-items>div");
    protected By leadFullNameInList = By.cssSelector(".lead-name");

    public void verifyNewLeadFormCustomFieldsPresence(List customFieldsNames) {
        openCreateNewLeadForm();
        assertEqualsNoOrder($$(customFieldRows).getTexts(), customFieldsNames.toArray());
    }

    protected LeadsPage openCreateNewLeadForm() {
        $(buttonNewLead).click();
        $(formNewLead).shouldBe(Condition.visible);
        waitForPageLoaded();
        return page(this);
    }

    public LeadsPage verifyLeadIsPresentOnThePage(Lead testLeadEntity) {
        openLeads();
        refresh();
        waitForPageLoaded();
        boolean presence = false;
        for (String s : $$(leadFullNameInList).getTexts()) {
            if (s.contains(testLeadEntity.getFirstName()) && s.contains(testLeadEntity.getLastName())) {
                presence = true;
                break;
            }
        }
        assertTrue(presence);
        LOGGER.info(String.format("Lead %s %s is displayed", testLeadEntity.getFirstName(), testLeadEntity.getLastName()));
        return page(this);
    }
}
