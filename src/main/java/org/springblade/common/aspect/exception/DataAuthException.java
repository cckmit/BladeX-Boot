package org.springblade.common.aspect.exception;

/**
 * 数据鉴权异常
 *
 * @author zhuyilong
 */
public class DataAuthException extends RuntimeException{

	public DataAuthException() {
		super();
	}

	public DataAuthException(String message) {
		super(message);
	}
}
