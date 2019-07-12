package products.model.core.facade;

import java.util.Collection;

import products.model.core.beans.Product;
import products.model.core.dao.ProductDao;
import products.model.core.dao.db.ProductDaoDb;
import products.model.core.exceptions.ProductsException;

public class ProductsFacade {

	private static ProductsFacade instance = new ProductsFacade();

	private ProductsFacade() {

	}

	public static ProductsFacade getInstance() {
		return instance;
	}

	private ProductDao productDao = new ProductDaoDb();

	/** adds the product and return its system generated id */
	public int addProduct(Product product) throws ProductsException {
		return productDao.create(product);
	}

	/**
	 * @throws ProductsException
	 *             if the specified product was not found in the system
	 */
	public Product getOneProduct(String productName) throws ProductsException {
		return productDao.read(productName);
	}

	/**
	 * @throws ProductsException
	 *             if the specified product was not found in the system
	 */
	public Product getOneProduct(int productId) throws ProductsException {
		return productDao.read(productId);
	}

	public Collection<Product> getAllProducts() throws ProductsException {
		return productDao.readAll();
	}

	/**
	 * update product with specified name with new price and stock
	 * 
	 * @throws ProductsException
	 *             if the specified product was not found in the system
	 */
	public void updateProductDetails(Product product) throws ProductsException {
		productDao.update(product);
	}

	/**
	 * @throws ProductsException
	 *             if the specified product was not found in the system
	 */
	public void deleteOneProduct(String productName) throws ProductsException {
		productDao.delete(productName);
	}

	public void deleteAllProducts() throws ProductsException {
		productDao.deleteAll();
	}

}
