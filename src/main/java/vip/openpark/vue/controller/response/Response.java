package vip.openpark.vue.controller.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author anthony
 * @since 2024/2/21 19:35
 */
@Getter
@Setter
@ToString
public class Response<T> implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final String SUCCESS_CODE = "000000";
	private static final String SUCCESS_MESSAGE = "SUCCESS";
	private static final String FAIL_CODE = "999999";
	
	/**
	 * 响应状态：true 表示请求成功，false 表示请求失败
	 */
	private Boolean status;
	/**
	 * 响应码
	 */
	private String code;
	/**
	 * 响应信息，例如错误提示
	 */
	private String message;
	/**
	 * 响应数据
	 */
	private T data;
	
	private Response(Boolean status, String code, String message, T data) {
		this.status = status;
		this.code = code;
		this.message = message;
		this.data = data;
	}
	
	/**
	 * 响应成功
	 *
	 * @return 响应
	 */
	public static <T> Response<T> success() {
		return success(null);
	}
	
	/**
	 * 响应成功
	 *
	 * @param data 响应数据
	 * @param <T>  泛型
	 * @return Response
	 */
	public static <T> Response<T> success(T data) {
		return success(SUCCESS_CODE, SUCCESS_MESSAGE, data);
	}
	
	/**
	 * 响应成功
	 *
	 * @param code    错误码 code
	 * @param message 错误码 message
	 * @param data    响应数据
	 * @param <T>     泛型
	 * @return Response
	 */
	public static <T> Response<T> success(String code, String message, T data) {
		return new Response<>(Boolean.TRUE, code, message, data);
	}
	
	
	/**
	 * 响应失败
	 *
	 * @param message 错误码 message
	 * @return Response
	 */
	public static Response<?> fail(String message) {
		return fail(FAIL_CODE, message);
	}
	
	/**
	 * 响应失败
	 *
	 * @param code    错误码 code
	 * @param message 错误码 message
	 * @return Response
	 */
	public static Response<?> fail(String code, String message) {
		return fail(code, message, null);
	}
	
	/**
	 * 响应失败
	 *
	 * @param code    错误码
	 * @param message 错误码 message
	 * @param data    响应数据
	 * @param <T>     泛型
	 * @return Response
	 */
	public static <T> Response<T> fail(String code, String message, T data) {
		return new Response<>(Boolean.FALSE, code, message, data);
	}
}