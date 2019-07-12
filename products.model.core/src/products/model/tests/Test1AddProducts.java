package products.model.tests;

import products.model.core.beans.Product;
import products.model.core.exceptions.ProductsException;
import products.model.core.facade.ProductsFacade;

public class Test1AddProducts {

	public static void main(String[] args) {

		ProductsFacade facade = ProductsFacade.getInstance();

		try {
			int id = facade.addProduct(new Product("ApplesX", 5.23, 500));
			System.out.println("added product id: " + id);
			id = facade.addProduct(new Product("Onions", 4, 2500));
			System.out.println("added product id: " + id);
			id = facade.addProduct(new Product("Potatos", 7.5, 1000));
			System.out.println("added product id: " + id);
			System.out.println("success - products added");
		} catch (ProductsException e) {
			e.printStackTrace();
		}
	}

}
