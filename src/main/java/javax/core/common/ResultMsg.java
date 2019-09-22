package javax.core.common;

import java.io.Serializable;

/**
 * 
 * @author 全恒
 *
 * @param <T>
 */
public class ResultMsg<T> implements Serializable {

	private static final long serialVersionUID = 2635002588308355785L;

	/**
	 * 状态码，可根据自己的系统自行定义状态码常量表
	 */
	private int status;

	/**
	 * 返回结果的文字说明
	 */
	private String msg;

	/**
	 * 存放实际返回的数据
	 */
	private T data;

	public ResultMsg() {
	}

	public ResultMsg(int status) {
		this.status = status;
	}

	public ResultMsg(int status, String msg) {
		this.status = status;
		this.msg = msg;
	}

	public ResultMsg(int status, T data) {
		this.status = status;
		this.data = data;
	}

	public ResultMsg(int status, String msg, T data) {
		this.status = status;
		this.msg = msg;
		this.data = data;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

}
