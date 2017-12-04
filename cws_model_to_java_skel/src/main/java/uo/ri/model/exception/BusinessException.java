package uo.ri.model.exception;

public class BusinessException extends Exception {
	private static final long serialVersionUID = 4001710687990554589L;

	/**
	 * Default constructor.
	 */
	public BusinessException() {}

	/**
	 * Creates a BusinessException with the given message.
	 * 
	 * @param message to initialize the exception.
	 */
	public BusinessException( String message ) {
		super( message );
	}

	/**
	 * Creates a BusinessException from another exception.
	 * 
	 * @param cause of the business exception to be thrown.
	 */
	public BusinessException( Throwable cause ) {
		super( cause );
	}

	/**
	 * Creates a BusinessException with the given message and a
	 * BusinessException from another exception.
	 * 
	 * @param message to initialize the exception.
	 * @param cause of the business exception to be thrown.
	 */
	public BusinessException( String message, Throwable cause ) {
		super( message, cause );
	}

}
