package products.system.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import products.model.core.beans.Product;

/*
 * this demo of the Response type is taken from:
 * https://www.baeldung.com/jax-rs-response
 * */

@Path("test-service")
public class DemoRESTfullService {

	// Ok Text Response
	// http://localhost:8080/products.web.service/rest/test-service/ok
	@GET
	@Path("/ok")
	public Response getOkResponse() {
		String message = "This is a text response";
		return Response.status(Response.Status.OK).entity(message).build();
	}

	// Error Response
	// http://localhost:8080/products.web.service/rest/test-service/not_ok
	@GET
	@Path("/not_ok")
	public Response getNOkTextResponse() {
		String message = "There was an internal server error";
		return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(message).build();
	}

	// Plain Text Response
	// http://localhost:8080/products.web.service/rest/test-service/text_plain
	@GET
	@Path("/text_plain")
	public Response getTextResponseTypeDefined() {

		String message = "This is a plain text response";

		return Response.status(Response.Status.OK).entity(message).type(MediaType.TEXT_PLAIN).build();
	}

	/*
	 * The same outcome could also be achieved via the Produces annotation instead
	 * of using the type() method in the Response:
	 */
	// http://localhost:8080/products.web.service/rest/test-service/text_plain_annotation
	@GET
	@Path("/text_plain_annotation")
	@Produces({ MediaType.TEXT_PLAIN })
	public Response getTextResponseTypeAnnotated() {
		String message = "This is a plain text response via annotation";
		return Response.status(Response.Status.OK).entity(message).build();
	}

	// JSON Response Using POJO
	// http://localhost:8080/products.web.service/rest/test-service/pojo
	@GET
	@Path("/pojo")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getPojoResponse() {
		Product product = new Product(101, "Bannana", 35, 1500);
		return Response.status(Response.Status.OK).entity(product).build();
	}

	// JSON Response Using Simple String
	/*
	 * We can use preformatted strings to create a response, and it can be done
	 * simply.
	 * 
	 * The following endpoint is an example of how a JSON represented as a String
	 * can be sent back as a JSON in the Jersey response:
	 * 
	 * The same pattern applies for other common media types like XML or HTML. We
	 * just need to notify Jersey that it’s an XML or HTML using MediaType.TEXT_XML
	 * or MediaType.TEXT_HTML and Jersey will handle the rest.
	 */
	// http://localhost:8080/products.web.service/rest/test-service/json
	@GET
	@Path("/json")
	public Response getJsonResponse() {
		String message = "{\"hello\": \"This is a JSON response\"}";
		return Response.status(Response.Status.OK).entity(message).type(MediaType.APPLICATION_JSON).build();
	}
}
