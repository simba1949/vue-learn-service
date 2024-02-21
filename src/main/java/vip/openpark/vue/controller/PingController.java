package vip.openpark.vue.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author anthony
 * @version 2024/2/21 17:40
 */
@RestController
@RequestMapping
public class PingController {
	@RequestMapping("ping")
	public String ping() {
		return "pong";
	}
}