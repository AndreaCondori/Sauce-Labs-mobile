package com.nttdata.stepsdefinitions;

import com.nttdata.steps.ProductosStep;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.annotations.Steps;


public class ProductoStepDefinition {

    @Steps
    ProductosStep productosStep;

    @Given("estoy en la aplicación de SauceLabs")
    public void estoyEnLaAplicacionDeSauceLabs() {
        productosStep.validarCargaApp();
    }

    @And("valido que carguen correctamente los productos en la galeria")
    public void validoQueCarguenCorrectamenteLosProductosEnLaGaleria() {
        productosStep.validarCargaApp();
        productosStep.validarProductosEnGaleria();
    }

    @When("agrego {int} del siguiente producto {string}")
    public void agregoDelSiguienteProducto(int cantidad ,String producto) {
        productosStep.seleccionarProducto(producto);
        productosStep.agregarProductoAlCarrito(cantidad);
    }


    @Then("valido el carrito de compra actualice correctamente")
    public void validoElCarritoDeCompraActualiceCorrectamente() {
        productosStep.validarCarritoActualizado();
    }
}
