package vip.openpark.vue.controller.request;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

/**
 * @author anthony
 * @since 2024/2/21 19:29
 */
@Slf4j
@Getter
@Setter
public class UserRegisterRequest implements Serializable {
	private static final long serialVersionUID = 8653418620402580450L;
	
	private String username;
	private String password;
}