package vip.openpark.vue.service;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.IdUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.weekend.WeekendSqls;
import vip.openpark.vue.controller.request.UserLoginRequest;
import vip.openpark.vue.controller.request.UserRegisterRequest;
import vip.openpark.vue.domain.User;
import vip.openpark.vue.mapper.UserMapper;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author anthony
 * @since 2024/2/21 19:26
 */
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired()))
public class UserService {
	private final UserMapper userMapper;
	private final ConcurrentHashMap<String, User> CACHE = new ConcurrentHashMap<>();
	
	@Transactional
	public void register(UserRegisterRequest request) {
		String username = request.getUsername();
		
		User user = select(username, null);
		Assert.isNull(user, "用户名已存在");
		
		User newUser =
			User
				.builder()
				.code(UUID.randomUUID().toString().substring(0, 32))
				.username(username)
				.password(request.getPassword())
				.build();
		Assert.isTrue(1 == userMapper.insertSelective(newUser), "注册失败");
	}
	
	public User select(String username, String password) {
		WeekendSqls<User> sql = WeekendSqls.custom();
		sql.andEqualTo(User::getUsername, username);
		if (null != password) {
			sql.andEqualTo(User::getPassword, password);
		}
		
		Example example = new Example.Builder(User.class).where(sql).build();
		return userMapper.selectByExample(example).stream().findFirst().orElse(null);
	}
	
	public String login(UserLoginRequest request) {
		User user = select(request.getUsername(), request.getPassword());
		Assert.notNull(user, "用户名不存在");
		
		String token = user.getCode() + IdUtil.getSnowflake().nextIdStr();
		CACHE.put(token, user);
		
		return token;
	}
	
	public void logout(String token) {
		CACHE.remove(token);
	}
}