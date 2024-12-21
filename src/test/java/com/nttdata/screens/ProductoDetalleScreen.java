package com.nttdata.screens;

import io.appium.java_client.AppiumDriver;

import io.appium.java_client.TouchAction;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import net.serenitybdd.core.pages.PageObject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;


import java.time.Duration;

public class ProductoDetalleScreen extends PageObject {

    @AndroidFindBy(xpath = "//android.widget.Button[@content-desc='Tap to add product to cart']")
    private WebElement botonAgregarAlCarrito;

    @AndroidFindBy(xpath = "//android.widget.ImageView[@content-desc='Displays selected product']")
    private WebElement cantidadProducto;

    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id=\"com.saucelabs.mydemoapp.android:id/cartTV\"]")
    private WebElement iconoCarrito;

    @AndroidFindBy(xpath = "//android.widget.ImageView[@content-desc='Increase item quantity']")
    private WebElement botonIncrementarCantidad;
    @AndroidFindBy(xpath = "//android.widget.ImageView[@content-desc=\"Increase item quantity\"]")
    private WebElement cantidadProductoSeleccionado;



    public void validarCantidadCarrito(int cantidadEsperada) {
        String cantidadCarrito = iconoCarrito.getText();
        assert cantidadCarrito.equals(String.valueOf(cantidadEsperada)) : "La cantidad en el carrito no coincide.";
    }

    public void incrementarCantidad(int cantidad) {
        try {
            for (int i = 1; i < cantidad; i++) {
                waitFor(ExpectedConditions.elementToBeClickable(botonIncrementarCantidad)).click();
                Thread.sleep(500); // Pausa breve entre clics
            }
        } catch (Exception e) {
            throw new RuntimeException("No se pudo incrementar la cantidad.", e);
        }
    }

    public void agregarAlCarrito() {
        try {
            scrollToElement(botonAgregarAlCarrito); // Hacer scroll al botón si no es visible
            waitFor(ExpectedConditions.elementToBeClickable(botonAgregarAlCarrito)).click();
        } catch (Exception e) {
            throw new RuntimeException("No se pudo hacer clic en el botón 'Agregar al carrito'.", e);
        }
    }

    private void scrollToElement(WebElement element) {
        AppiumDriver driver = (AppiumDriver) getDriver();
        int attempts = 0;
        while (!element.isDisplayed() && attempts < 5) {
            scrollVertically(0.7, 0.3); // Scroll hacia abajo
            attempts++;
        }
        if (!element.isDisplayed()) {
            throw new RuntimeException("No se pudo hacer scroll hasta el elemento.");
        }
    }

    private void scrollVertically(double startPercentage, double endPercentage) {
        AppiumDriver driver = (AppiumDriver) getDriver();
        int screenHeight = driver.manage().window().getSize().getHeight();
        int screenWidth = driver.manage().window().getSize().getWidth();

        int startY = (int) (screenHeight * startPercentage);
        int endY = (int) (screenHeight * endPercentage);
        int startX = screenWidth / 2;

        TouchAction action = new TouchAction(driver);
        action.press(PointOption.point(startX, startY))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(500)))
                .moveTo(PointOption.point(startX, endY))
                .release()
                .perform();
    }

    public int obtenerCantidadCarrito() {
        waitFor(ExpectedConditions.visibilityOf(iconoCarrito));
        String cantidadTexto = iconoCarrito.getText();
        return Integer.parseInt(cantidadTexto);
}

    public int obtenerCantidadAgregada() {
        waitFor(ExpectedConditions.visibilityOf(cantidadProductoSeleccionado));
        String cantidadTexto = cantidadProductoSeleccionado.getText();
        return Integer.parseInt(cantidadTexto);
    }

}
