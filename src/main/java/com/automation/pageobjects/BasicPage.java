package com.automation.pageobjects;

import com.automation.environment.EnvironmentConfigurator;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;


public abstract class BasicPage {
    protected final Logger LOGGER = LogManager.getLogger(this);
    private final WebDriver wd;

    public BasicPage() {
        wd = WebDriverRunner.getWebDriver();
    }

    public static String getPageURL() {
        return EnvironmentConfigurator.getInstance().getAppUrl();
    }

    protected WebDriver getWebDriverCurrent() {
        return wd;
    }

    protected void openUrl(String url) {
        Selenide.open(url);
        LOGGER.info("Loading page: {}", url);
    }

    public void setWebDriverWindowSize(int width, int height) {
        wd.manage().window().setPosition(new Point(0, 0));
        wd.manage().window().setSize(new Dimension(width, height));
        try {
            Thread.sleep(2000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    protected Object executeJS(final String script, final Object... params) {
        return ((JavascriptExecutor) wd).executeScript(script, params);
    }
}
