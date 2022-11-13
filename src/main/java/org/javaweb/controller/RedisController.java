package org.javaweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

import java.util.*;

@RestController
@RequestMapping("/Redis/")
public class RedisController {

	@Autowired
	private RedisTemplate redisTemplate;

	@RequestMapping("/query")
	public Map<Object, Object> testRedis() throws Exception {
		Map<String, Object> properties = new HashMap<>();
		properties.put("rasp", "hello");
		properties.put("test", 1);
		redisTemplate.opsForHash().putAll("rasp", properties);
		return (Map<Object, Object>) redisTemplate.opsForHash().entries("rasp");
	}

	@RequestMapping("jedisQuery")
	public List<String> testJedis() {
		List<String> list  = new ArrayList<>();
		Jedis        jedis = new Jedis("localhost");
		jedis.auth("test");
		Iterator<String> iterator = jedis.keys("*").iterator();
		while (iterator.hasNext()) {
			String key = iterator.next();
			list.add(key);
		}
		return list;
	}

}
