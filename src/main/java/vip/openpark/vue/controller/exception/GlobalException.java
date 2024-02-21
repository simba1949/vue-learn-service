package vip.openpark.vue.controller.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import vip.openpark.vue.controller.response.Response;

/**
 * @author anthony
 * @since 2024/2/21 22:43
 */
@RestControllerAdvice
public class GlobalException {
	
	@ExceptionHandler
	public Response<?> handleException(Exception e) {
		return Response.fail("500", "服务器内部错误", e.getMessage());
	}
}