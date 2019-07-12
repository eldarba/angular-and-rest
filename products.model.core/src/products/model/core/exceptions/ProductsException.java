package products.model.core.exceptions;

public class ProductsException extends Exception {

	private static final long serialVersionUID = 1L;

	public ProductsException() {
		super();
	}

	public ProductsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ProductsException(String message, Throwable cause) {
		super(message, cause);
	}

	public ProductsException(String message) {
		super(message);
	}

	public ProductsException(Throwable cause) {
		super(cause);
	}
	
	


}
