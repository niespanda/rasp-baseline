package org.javaweb.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;

@Configuration
@PropertySource("classpath:application.properties")
public class RedisConfig {

	@Value("${spring.redis.host}")
	private String redisHost;

	@Value("${spring.redis.port}")
	private int port;

	@Value("${spring.redis.password}")
	private String password;

	@Value("${spring.redis.lettuce.pool.max-active}")
	private int maxActive;

	@Value("${spring.redis.lettuce.pool.max-idle}")
	private int maxIdle;

	@Resource
	private Environment env;

	@Bean
	public RedisConnectionFactory redisConnectionFactory() {
		JedisConnectionFactory fac = new JedisConnectionFactory();
		fac.setHostName(env.getProperty("spring.redis.host"));
		fac.setPort(Integer.parseInt(env.getProperty("spring.redis.port")));
		fac.setPassword(env.getProperty("spring.redis.password"));
		fac.getPoolConfig().setMaxIdle(Integer.parseInt(env.getProperty("spring.redis.lettuce.pool.max-idle")));
		fac.getPoolConfig().setMaxTotal(Integer.parseInt(env.getProperty("spring.redis.lettuce.pool.max-active")));
		return fac;
	}

	@Bean
	public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
		RedisTemplate<String, String> redis = new RedisTemplate<>();
		redis.setConnectionFactory(redisConnectionFactory);
		redis.afterPropertiesSet();
		return redis;
	}
}
