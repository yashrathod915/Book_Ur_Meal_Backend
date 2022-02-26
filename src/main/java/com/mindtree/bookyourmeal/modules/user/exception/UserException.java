package com.mindtree.bookyourmeal.modules.user.exception;

import com.mindtree.bookyourmeal.exception.ServiceException;

public class UserException extends ServiceException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
		// TODO Auto-generated constructor stub
	}

	public UserException(String arg0, Throwable arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	public UserException(String arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public UserException(Throwable arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

}
