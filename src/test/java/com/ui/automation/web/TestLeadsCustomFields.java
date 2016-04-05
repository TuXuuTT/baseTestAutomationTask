package com.ui.automation.web;

import com.ui.automation.BaseTest;
import com.ui.automation.pageobjects.BasicAppLoginPage;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Stories;

import static com.codeborne.selenide.Selenide.open;

public class TestLeadsCustomFields extends BaseTest {

    BasicAppLoginPage baseAppLoginPage;

    @Override
    @BeforeClass
    public void beforeClass() {
        super.beforeClass();
        baseAppLoginPage = open(BasicAppLoginPage.getPageURL(), BasicAppLoginPage.class)
                .loginAsAdmin();
    }

    @Features("")
    @Stories({""})
    @Test(description = "")
    public void testLogin() {
//        baseAppLoginPage
//                .loginAsAdmin();
    }
}