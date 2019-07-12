package products.system.service;

import java.util.Collection;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import products.model.core.beans.Product;
import products.model.core.exceptions.ProductsException;
import products.model.core.facade.ProductsFacade;

@Path("products-service")
public class ProductsService {

	ProductsFacade facade = ProductsFacade.getInstance();

	// @Context
	// HttpServletRequest req;

	// @PostConstruct
	// public void getSession() {
	// HttpSession session = req.getSession(false);
	// }

	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("add-product")
	@POST
	public Response addProduct(Product product) {
		int id;
		try {
			id = facade.addProduct(product);
			product.setId(id);
			return Response.status(Response.Status.CREATED).entity(product).build();
		} catch (Exception e) {
			return getErrorResponse(e);
		}
	}

	@Path("get-all-products")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllProducts() {
		System.out.println("get all products from the system...");
		try {
			Thread.sleep(400);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		try {
			Collection<Product> products = facade.getAllProducts();
			return Response.status(Response.Status.OK).entity(products).build();
		} catch (Exception e) {
			return getErrorResponse(e);
		}
	}

	@Path("get-one-product/{name}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getOneProduct(@PathParam("name") String productName) {
		try {
			Product product = facade.getOneProduct(productName);
			return Response.status(Response.Status.OK).entity(product).build();
		} catch (Exception e) {
			return getErrorResponse(e);
		}
	}

	@Path("get-one-product")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getOneProduct(@QueryParam("id") int productId) {
		try {
			Product product = facade.getOneProduct(productId);
			return Response.status(Response.Status.OK).entity(product).build();
		} catch (Exception e) {
			return getErrorResponse(e);
		}
	}

	@Path("update-product")
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateProduct(Product product) {
		try {
			facade.updateProductDetails(product);
			product = facade.getOneProduct(product.getName());
			return Response.status(Response.Status.OK).entity("updated: " + product).build();
		} catch (Exception e) {
			return getErrorResponse(e);
		}
	}

	@Path("delete-one-product/{name}")
	@DELETE
	public Response deleteOneProduct(@PathParam("name") String productName) {
		try {
			facade.deleteOneProduct(productName);
			return Response.status(Response.Status.OK).entity("deleted: " + productName).build();
		} catch (Exception e) {
			return getErrorResponse(e);
		}
	}

	@Path("delete-all-products")
	@DELETE
	public Response deleteAllProducts() {
		try {
			facade.deleteAllProducts();
			return Response.status(Response.Status.OK).entity("deleted all products").build();
		} catch (Exception e) {
			return getErrorResponse(e);
		}
	}

	private Response getErrorResponse(Exception e) {
		if (e instanceof ProductsException) {
			e.printStackTrace(System.out);
		} else {
			e.printStackTrace();
		}
		String errorMsg = e.getMessage();
		if (e.getCause() != null) {
			errorMsg += " - cause: " + e.getCause().getMessage();
		}
		return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(errorMsg).type(MediaType.TEXT_PLAIN)
				.build();
	}

}
