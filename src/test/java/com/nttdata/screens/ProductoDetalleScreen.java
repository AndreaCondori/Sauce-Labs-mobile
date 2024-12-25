package com.nttdata.screens;

import io.appium.java_client.pagefactory.AndroidFindBy;
import net.serenitybdd.core.pages.PageObject;
import org.junit.Assert;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProductoDetalleScreen extends PageObject {

    @AndroidFindBy(xpath = "//android.widget.Button[@content-desc='Tap to add product to cart']")
    private WebElement botonAgregarAlCarrito;

    @AndroidFindBy(xpath = "//android.widget.ImageView[@content-desc='Displays selected product']")
    private WebElement cantidadProducto;

    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='com.saucelabs.mydemoapp.android:id/cartTV']")
    private WebElement iconoCarrito;

    @AndroidFindBy(xpath = "//android.widget.ImageView[@content-desc='Increase item quantity']")
    private WebElement botonIncrementarCantidad;

    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id=\"com.saucelabs.mydemoapp.android:id/noTV\"]")
    private WebElement cantidadAgregada;

    @AndroidFindBy(xpath = "//android.widget.TextView[@content-desc=\"Removes product from cart\"]")
    private WebElement botonRemover;

    @AndroidFindBy(xpath = "//android.widget.Button[@resource-id=\"com.saucelabs.mydemoapp.android:id/shoppingBt\"]")
    private WebElement botonGoShopping;


    public void validarCantidadCarrito(int cantidadEsperada) {
        waitFor(ExpectedConditions.visibilityOf(iconoCarrito));
        String cantidadCarrito = iconoCarrito.getText();
        Assert.assertEquals(
                String.valueOf(cantidadEsperada),
                cantidadCarrito,
                "La cantidad en el carrito no coincide. Esperado: " + cantidadEsperada + ", Actual: " + cantidadCarrito
        );
    }

    public void incrementarCantidad(int cantidad) {
        for (int i = 1; i < cantidad; i++) {
            waitFor(ExpectedConditions.elementToBeClickable(botonIncrementarCantidad)).click();
        }
    }

    public void agregarAlCarrito() {
        waitFor(ExpectedConditions.elementToBeClickable(botonAgregarAlCarrito)).click();
    }

    public int obtenerCantidadCarrito() {
        waitFor(ExpectedConditions.visibilityOf(iconoCarrito));
        String cantidadTexto = iconoCarrito.getText();
        return Integer.parseInt(cantidadTexto);
    }

    public int obtenerCantidadAgregada() {
        waitFor(ExpectedConditions.visibilityOf(cantidadAgregada));
        String cantidadTexto = cantidadAgregada.getText();
        return Integer.parseInt(cantidadTexto);
    }
    public void vaciarCarrito() {
        try {
            waitFor(ExpectedConditions.visibilityOf(iconoCarrito));
            if (iconoCarrito.getText().equals("0")) {
                System.out.println("El carrito ya está vacío. No se necesita vaciar.");
                return;
            }
            iconoCarrito.click();
            waitFor(ExpectedConditions.elementToBeClickable(botonRemover)).click();
            waitFor(ExpectedConditions.visibilityOf(botonGoShopping)).click();
            System.out.println("Carrito vaciado exitosamente.");
        } catch (Exception e) {
            System.out.println("Error al intentar vaciar el carrito: " + e.getMessage());
        }
    }

}
