package products.model.tests;

import products.model.core.exceptions.ProductsException;
import products.model.core.facade.ProductsFacade;

public class Test4DeleteProducts {

	public static void main(String[] args) {

		ProductsFacade facade = ProductsFacade.getInstance();
		String productName = "Apples";

		try {
			System.out.println("products");
			System.out.println(facade.getAllProducts());
			System.out.println("deleting...");
			facade.deleteOneProduct(productName);
			System.out.println("success - product deleted: ");
			System.out.println(facade.getAllProducts());
		} catch (ProductsException e) {
			e.printStackTrace();
		}
	}

}
