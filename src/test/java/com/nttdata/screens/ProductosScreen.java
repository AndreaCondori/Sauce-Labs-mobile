package com.nttdata.screens;

import io.appium.java_client.pagefactory.AndroidFindBy;
import net.serenitybdd.core.pages.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProductosScreen extends PageObject {


    @AndroidFindBy(xpath = "//android.widget.ImageView[@content-desc=\"App logo and name\"]")
    public WebElement tituloApp;
    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id=\"com.saucelabs.mydemoapp.android:id/productTV\"]")
    private WebElement tituloProductos;

    public boolean isProductDisplayed(){
        waitFor(ExpectedConditions.visibilityOf(tituloApp));
        return tituloApp.isDisplayed();
    }

    public boolean isGalleryDisplayed() {
        waitFor(ExpectedConditions.visibilityOf(tituloProductos));
        return tituloProductos.isDisplayed();
    }

    public WebElement findProductByName(String productName) {
        String xpath = "//android.widget.ImageView[@content-desc='" + productName + "']";
        WebDriver driver = getDriver();
        return driver.findElement(By.xpath(xpath));
    }


}