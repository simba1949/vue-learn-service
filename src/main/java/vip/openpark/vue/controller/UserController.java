package vip.openpark.vue.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vip.openpark.vue.controller.request.UserLoginRequest;
import vip.openpark.vue.controller.request.UserRegisterRequest;
import vip.openpark.vue.controller.response.Response;
import vip.openpark.vue.service.UserService;

/**
 * @author anthony
 * @since 2024/2/21 19:26
 */
@CrossOrigin
@Slf4j
@RestController
@RequestMapping("user")
@RequiredArgsConstructor(onConstructor = @__(@Autowired()))
public class UserController {
	private final UserService userService;
	
	@PostMapping("register")
	public Response<Void> register(@RequestBody UserRegisterRequest request) {
		log.info("请求注册接口的入参：{}", request);
		
		userService.register(request);
		
		return Response.success();
	}
	
	@PostMapping("login")
	public Response<String> login(@RequestBody UserLoginRequest request) {
		log.info("请求登录接口的入参：{}", request);
		
		String token = userService.login(request);
		
		return Response.success(token);
	}
	
	@GetMapping("logout")
	public Response<String> logout(@RequestHeader("token") String token) {
		log.info("请求登出接口的入参：{}", token);
		
		userService.logout(token);
		
		return Response.success();
	}
}