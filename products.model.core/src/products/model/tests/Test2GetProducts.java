package products.model.tests;

import java.util.Collection;

import products.model.core.beans.Product;
import products.model.core.exceptions.ProductsException;
import products.model.core.facade.ProductsFacade;

public class Test2GetProducts {

	public static void main(String[] args) {

		ProductsFacade facade = ProductsFacade.getInstance();

		try {
			Product product = facade.getOneProduct("Apples");
			System.out.println("success - product found: " + product);
			Collection<Product> products = facade.getAllProducts();
			System.out.println("success - products found: " + products);
		} catch (ProductsException e) {
			e.printStackTrace();
		}
	}

}
