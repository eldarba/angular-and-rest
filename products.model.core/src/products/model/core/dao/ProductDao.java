package products.model.core.dao;

import java.util.Collection;

import products.model.core.beans.Product;
import products.model.core.exceptions.ProductsException;

public interface ProductDao {

	/** adds the product and return its system generated id */
	int create(Product product) throws ProductsException;

	Product read(int productId) throws ProductsException;

	Product read(String productName) throws ProductsException;

	Collection<Product> readAll() throws ProductsException;

	void update(Product product) throws ProductsException;

	void delete(String productName) throws ProductsException;

	void deleteAll() throws ProductsException;

}
