package vip.openpark.vue.controller.request;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

/**
 * @author anthony
 * @since 2024/2/21 19:30
 */
@Slf4j
@Getter
@Setter
public class UserLoginRequest implements Serializable {
	private static final long serialVersionUID = 4500425759031622553L;
	
	private String username;
	private String password;
}