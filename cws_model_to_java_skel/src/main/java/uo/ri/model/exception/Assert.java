package uo.ri.model.exception;

public class Assert {

	/**
	 * Asserts the the object is not null, else throws the error message.
	 * 
	 * @param o to assert not null.
	 * @param errorMsg to throw if the object is null.
	 * @throws BusinessException if the object is null.
	 */
	public static void isNotNull( Object o, String errorMsg ) throws BusinessException {
		isTrue( o != null, errorMsg );
	}

	/**
	 * Asserts the the object is not null.
	 * 
	 * @param o to assert not null.
	 * @throws BusinessException if the object is null.
	 */
	public static void isNotNull( Object o ) throws BusinessException {
		isTrue( o != null, o.getClass().getName() + " cannot be null here" );
	}

	/**
	 * Asserts that the condition is false.
	 * 
	 * @param condition to assert false.
	 * @throws BusinessException if the condition is not true.
	 */
	public static void isFalse( boolean condition ) throws BusinessException {
		isTrue( !condition, "Invalid assertion" );
	}

	/**
	 * Asserts that the condition is false. Else throws the error message.
	 * 
	 * @param condition to assert false.
	 * @param errorMsg to throw if the condition is not false.
	 * @throws BusinessException if the condition is not false.
	 */
	public static void isFalse( boolean condition, String errorMsg ) throws BusinessException {
		isTrue( !condition, errorMsg );
	}

	/**
	 * Asserts that the condition is true.
	 * 
	 * @param condition to assert true.
	 * @throws BusinessException if the condition is not true.
	 */
	public static void isTrue( boolean condition ) throws BusinessException {
		isTrue( condition, "Invalid assertion" );
	}

	/**
	 * Asserts that the condition is true. Else throws the error message.
	 * 
	 * @param condition to assert true.
	 * @param errorMsg to throw if the condition is not true.
	 * @throws BusinessException thrown if the condition is not true.
	 */
	public static void isTrue( boolean condition, String errorMsg ) throws BusinessException {
		if (condition == true)
			return;
		throw new BusinessException( errorMsg );
	}

}
