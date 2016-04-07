package com.automation.web;

import com.automation.BaseTest;
import com.automation.api.RestApiExecutor;
import com.automation.pageobjects.BaseAppLoginPage;
import com.automation.pageobjects.LeadsPage;
import com.automation.pageobjects.SettingsPage;
import com.getbase.models.Lead;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Stories;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static com.codeborne.selenide.Selenide.open;

public class TestLeadsCustomFields extends BaseTest {

    SettingsPage settingsPage;
    LeadsPage leadsPage;
    Lead testLeadEntity;
    String randomStr;

    @Override
    @BeforeClass(alwaysRun = true)
    public void beforeClass() {
        super.beforeClass();
        settingsPage = open(BaseAppLoginPage.getPageURL(), BaseAppLoginPage.class)
                .loginAsAdmin().openSettings();
        randomStr = getRandomString(3);

        testLeadEntity = new Lead();
        testLeadEntity.setFirstName("Bruce" + randomStr);
        testLeadEntity.setLastName("Wayne" + randomStr);
    }

    @Features("Lead custom fields functionality")
    @Stories({"Verify custom fields defined in Settings are available to new Leads"})
    @Test(description = "")
    public void testLeadWithCustomFields() {
        settingsPage.navigateToLeadSettings()
                .showAndLogAvailableCustomFieldsTypesAndNames();

        List<String> leadCustomFieldNames = settingsPage.getCustomFieldsNames();
        List<Object> leadCustomFieldValues = Arrays.asList(String.format("bruce%s@mail.com", randomStr), String.format("+125201550%s", new Random().nextInt(999)), String.format("%s/4/1991", new Random().nextInt(30)));
        testLeadEntity.setCustomFields(createMapFromTwoLists(leadCustomFieldNames, leadCustomFieldValues));


        leadsPage = settingsPage.openLeads();
        leadsPage.verifyNewLeadFormCustomFieldsPresence(leadCustomFieldNames);

        RestApiExecutor.getInstance().createNewLeadViaApi(testLeadEntity);

        leadsPage.verifyLeadIsPresentOnThePage(testLeadEntity);
    }

}