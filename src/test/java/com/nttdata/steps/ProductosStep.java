package com.nttdata.steps;

import com.nttdata.screens.ProductoDetalleScreen;
import com.nttdata.screens.ProductosScreen;
import net.serenitybdd.annotations.Step;
import org.junit.Assert;
import org.openqa.selenium.NoSuchElementException;


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
        try {
            productosScreen.findProductByName(producto).click();
        } catch (NoSuchElementException e) {
            Assert.fail("El producto '" + producto + "' no se encontró en la galería: " + e.getMessage());
        }
    }

    public void validarCarritoActualizado() {
        int cantidadActual = productoDetalleScreen.obtenerCantidadCarrito();
        int cantidadAgregada = productoDetalleScreen.obtenerCantidadAgregada();
        Assert.assertEquals("La cantidad en el carrito no es la esperada.",
                cantidadAgregada,
                cantidadActual);
    }
    public void agregarProductoAlCarrito(int cantidad) {
        try {
            productoDetalleScreen.vaciarCarrito();
            productoDetalleScreen.incrementarCantidad(cantidad);
            productoDetalleScreen.agregarAlCarrito();
        } catch (Exception e) {
            Assert.fail("Error al agregar producto al carrito: " + e.getMessage());
        }
    }
}