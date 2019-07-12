package products.model.core.dao.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import products.model.core.beans.Product;
import products.model.core.dao.ProductDao;
import products.model.core.exceptions.ProductsException;

public class ProductDaoDb implements ProductDao {

	public static final String DB_URL = "jdbc:derby://localhost:1527/products.db";

	static {
		try {
			String driverName = "org.apache.derby.jdbc.ClientDriver";
			Class.forName(driverName);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public int create(Product product) throws ProductsException {
		String sql = "insert into products (name, price, stock) values(?,?,?)";
		try (Connection con = DriverManager.getConnection(DB_URL);
				PreparedStatement pstmt = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);) {
			pstmt.setString(1, product.getName());
			pstmt.setDouble(2, product.getPrice());
			pstmt.setInt(3, product.getStock());
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			rs.next();
			int generatedId = rs.getInt(1);
			return generatedId;
		} catch (SQLException e) {
			throw new ProductsException("add product to the database failed", e);
		}

	}

	@Override
	public Product read(int productId) throws ProductsException {
		String sql = "select name, price, stock from products where id = ?";
		try (Connection con = DriverManager.getConnection(DB_URL);
				PreparedStatement pstmt = con.prepareStatement(sql);) {
			pstmt.setInt(1, productId);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				Product product = new Product(productId);
				product.setName(rs.getString("name"));
				product.setPrice(rs.getDouble("price"));
				product.setStock(rs.getInt("stock"));
				return product;
			} else {
				throw new ProductsException(
						"getting product with id: " + productId + " from the database failed - not found");
			}
		} catch (SQLException e) {
			throw new ProductsException("getting product with id: " + productId + " from the database failed", e);
		}

	}

	@Override
	public Product read(String productName) throws ProductsException {
		String sql = "select id, price, stock from products where name = ?";
		try (Connection con = DriverManager.getConnection(DB_URL);
				PreparedStatement pstmt = con.prepareStatement(sql);) {
			pstmt.setString(1, productName);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				Product product = new Product();
				product.setId(rs.getInt("id"));
				product.setName(productName);
				product.setPrice(rs.getDouble("price"));
				product.setStock(rs.getInt("stock"));
				return product;
			} else {
				throw new ProductsException(
						"getting product with name: " + productName + " from the database failed - not found");
			}
		} catch (SQLException e) {
			throw new ProductsException("getting product with name: " + productName + " from the database failed", e);
		}

	}

	@Override
	public void update(Product product) throws ProductsException {
		String sql = "update products set price=?, stock=? where name=?";
		try (Connection con = DriverManager.getConnection(DB_URL);
				PreparedStatement pstmt = con.prepareStatement(sql);) {
			pstmt.setDouble(1, product.getPrice());
			pstmt.setInt(2, product.getStock());
			pstmt.setString(3, product.getName());
			int rowCount = pstmt.executeUpdate();
			if (rowCount == 0) {
				throw new ProductsException("update product in the database failed - product with name: "
						+ product.getName() + " not found");
			}
		} catch (SQLException e) {
			throw new ProductsException("update product in the database failed", e);
		}

	}

	@Override
	public void delete(String productName) throws ProductsException {
		String sql = "delete from products where name=?";
		try (Connection con = DriverManager.getConnection(DB_URL);
				PreparedStatement pstmt = con.prepareStatement(sql);) {
			pstmt.setString(1, productName);
			int rowCount = pstmt.executeUpdate();
			if (rowCount == 0) {
				throw new ProductsException(
						"delete product from the database failed - product with name: " + productName + " not found");
			}
		} catch (SQLException e) {
			throw new ProductsException("delete product from the database failed", e);
		}

	}

	@Override
	public Collection<Product> readAll() throws ProductsException {
		String sql = "select * from products";
		try (Connection con = DriverManager.getConnection(DB_URL);
				PreparedStatement pstmt = con.prepareStatement(sql);) {
			ResultSet rs = pstmt.executeQuery();
			List<Product> list = new ArrayList<>();
			while (rs.next()) {
				Product product = new Product();
				product.setId(rs.getInt("id"));
				product.setName(rs.getString("name"));
				product.setPrice(rs.getDouble("price"));
				product.setStock(rs.getInt("stock"));
				list.add(product);
			}
			return list;
		} catch (SQLException e) {
			throw new ProductsException("getting all products from the database failed", e);
		}

	}

	@Override
	public void deleteAll() throws ProductsException {
		String sql = "delete from products";
		try (Connection con = DriverManager.getConnection(DB_URL);
				PreparedStatement pstmt = con.prepareStatement(sql);) {
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new ProductsException("delete all products from the database failed", e);
		}

	}

}
