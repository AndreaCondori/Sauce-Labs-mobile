package com.nttdata.steps;

import com.nttdata.screens.ProductoDetalleScreen;
import com.nttdata.screens.ProductosScreen;
import net.serenitybdd.annotations.Step;
import org.junit.Assert;


public class ProductosStep {


    ProductosScreen productosScreen = new ProductosScreen();
    ProductoDetalleScreen productoDetalleScreen = new ProductoDetalleScreen();

    public void validarCargaApp() {
        Assert.assertTrue("El título de la app no es visible.", productosScreen.isProductDisplayed());
    }

    public void validarProductosEnGaleria() {
        Assert.assertTrue("Los productos en la galería no se cargaron correctamente.", productosScreen.isGalleryDisplayed());
    }

    public void seleccionarProducto(String producto) {
        productosScreen.findProductByName(producto).click();
    }

    public void agregarUnidadesAlCarrito(int unidades) {
        productoDetalleScreen.incrementarCantidad(unidades);
        productoDetalleScreen.agregarAlCarrito();
    }

    public void validarCarritoActualizado() {
        int cantidadActual = productoDetalleScreen.obtenerCantidadCarrito();
        int cantidadAgregada = productoDetalleScreen.obtenerCantidadAgregada();
        Assert.assertEquals("La cantidad en el carrito no es la esperada.",
                cantidadAgregada,
                cantidadActual);
    }
    public void agregarProductoAlCarrito(int cantidad) {
        productoDetalleScreen.incrementarCantidad(cantidad);
        productoDetalleScreen.agregarAlCarrito();
    }
}