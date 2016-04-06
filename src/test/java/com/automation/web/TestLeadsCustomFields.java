package com.automation.web;

import com.automation.BaseTest;
import com.automation.pageobjects.BasicAppLoginPage;
import com.automation.pageobjects.DashboardPage;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Stories;

import static com.codeborne.selenide.Selenide.open;

public class TestLeadsCustomFields extends BaseTest {

    DashboardPage dashboardPage;

    @Override
    @BeforeClass(alwaysRun = true)
    public void beforeClass() {
        super.beforeClass();
        dashboardPage = open(BasicAppLoginPage.getPageURL(), BasicAppLoginPage.class)
                .loginAsAdmin();
    }

    @Features("")
    @Stories({""})
    @Test(description = "")
    public void testLogin() {
        dashboardPage.openSettings()
                .navigateToLeadSettings()
                .collectCustomFieldsNamesAndTypes();
    }
}