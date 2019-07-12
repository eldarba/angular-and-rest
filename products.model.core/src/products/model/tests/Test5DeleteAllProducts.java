package products.model.tests;

import products.model.core.exceptions.ProductsException;
import products.model.core.facade.ProductsFacade;

public class Test5DeleteAllProducts {

	public static void main(String[] args) {

		ProductsFacade facade = ProductsFacade.getInstance();

		try {
			System.out.println("products");
			System.out.println(facade.getAllProducts());
			System.out.println("deleting...");
			facade.deleteAllProducts();
			System.out.println("success - product deleted: ");
			System.out.println(facade.getAllProducts());
		} catch (ProductsException e) {
			e.printStackTrace();
		}
	}

}
