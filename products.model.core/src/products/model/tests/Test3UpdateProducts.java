package products.model.tests;

import products.model.core.beans.Product;
import products.model.core.exceptions.ProductsException;
import products.model.core.facade.ProductsFacade;

public class Test3UpdateProducts {

	public static void main(String[] args) {

		ProductsFacade facade = ProductsFacade.getInstance();
		String productName = "Apples";

		try {
			Product product = facade.getOneProduct(productName);
			System.out.println(product);
			System.out.println("updating...");
			product.setPrice(product.getPrice() * 1.1);
			product.setStock(product.getStock() - 10);
			facade.updateProductDetails(product); // updating
			product = facade.getOneProduct(productName);
			System.out.println("success - product updated: ");
			System.out.println(product);
		} catch (ProductsException e) {
			e.printStackTrace();
		}
	}

}
