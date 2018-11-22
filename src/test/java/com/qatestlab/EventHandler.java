package com.qatestlab;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverEventListener;
import org.testng.Reporter;

public class EventHandler implements WebDriverEventListener {
    public void log(String message) {
        Reporter.log(message + "<br>");
    }
    @Override
    public void beforeAlertAccept(WebDriver webDriver) {

    }

    @Override
    public void afterAlertAccept(WebDriver webDriver) {

    }

    @Override
    public void afterAlertDismiss(WebDriver webDriver) {

    }

    @Override
    public void beforeAlertDismiss(WebDriver webDriver) {

    }

    @Override
    public void beforeNavigateTo(String s, WebDriver webDriver) {
        System.out.println("Navigate to: " + s);
        log("Navigate to: " + s);
    }

    @Override
    public void afterNavigateTo(String s, WebDriver webDriver) {
        System.out.println("Current URL: " + webDriver.getCurrentUrl());
        log("Current URL: " + webDriver.getCurrentUrl());
    }

    @Override
    public void beforeNavigateBack(WebDriver webDriver) {
        System.out.println("Navigate back");
        log("Navigate back");
    }

    @Override
    public void afterNavigateBack(WebDriver webDriver) {
        System.out.println("Current URL: " + webDriver.getCurrentUrl());
        log("Current URL: " + webDriver.getCurrentUrl());
    }

    @Override
    public void beforeNavigateForward(WebDriver webDriver) {
        System.out.println("Navigate forward");
        log("Navigate forward");
    }

    @Override
    public void afterNavigateForward(WebDriver webDriver) {
        System.out.println("Current URL: " + webDriver.getCurrentUrl());
        log("Current URL: " + webDriver.getCurrentUrl());
    }

    @Override
    public void beforeNavigateRefresh(WebDriver webDriver) {
        System.out.println("Refresh page");
        log("Refresh page");
    }

    @Override
    public void afterNavigateRefresh(WebDriver webDriver) {
        System.out.println("Current URL: " + webDriver.getCurrentUrl());
        log("Current URL: " + webDriver.getCurrentUrl());
    }

    @Override
    public void beforeFindBy(By by, WebElement webElement, WebDriver webDriver) {
        System.out.println("Search for element: " + by.toString());
        log("Search for element: " + by.toString());
    }

    @Override
    public void afterFindBy(By by, WebElement webElement, WebDriver webDriver) {
        /*if (webElement != null) {
            System.out.println("Found element " + webElement.getTagName());
            log("Found element " + webElement.getTagName());
        }*/
    }

    @Override
    public void beforeClickOn(WebElement webElement, WebDriver webDriver) {
        System.out.println("Click on " + webElement.getTagName());
        log("Click on " + webElement.getTagName());
    }

    @Override
    public void afterClickOn(WebElement webElement, WebDriver webDriver) {
        /*System.out.println( webElement.getTagName() + " was clicked!");
        log( webElement.getTagName() + " was clicked!");*/
    }

    @Override
    public void beforeChangeValueOf(WebElement webElement, WebDriver webDriver, CharSequence[] charSequences) {
        System.out.println("Change value to " + charSequences);
        log("Change value to " + charSequences);
    }

    @Override
    public void afterChangeValueOf(WebElement webElement, WebDriver webDriver, CharSequence[] charSequences) {
        /*System.out.println("Changed value of " + webElement.getTagName());
        log("Changed value of " + webElement.getTagName());*/
    }

    @Override
    public void beforeScript(String s, WebDriver webDriver) {

    }

    @Override
    public void afterScript(String s, WebDriver webDriver) {

    }

    @Override
    public void beforeSwitchToWindow(String s, WebDriver webDriver) {

    }

    @Override
    public void afterSwitchToWindow(String s, WebDriver webDriver) {

    }

    @Override
    public void onException(Throwable throwable, WebDriver webDriver) {

    }

    @Override
    public <X> void beforeGetScreenshotAs(OutputType<X> outputType) {

    }

    @Override
    public <X> void afterGetScreenshotAs(OutputType<X> outputType, X x) {

    }

    @Override
    public void beforeGetText(WebElement webElement, WebDriver webDriver) {

    }

    @Override
    public void afterGetText(WebElement webElement, WebDriver webDriver, String s) {

    }
}
