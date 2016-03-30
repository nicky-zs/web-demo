package com.zhongsheng.demo.web;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

	@RequestMapping("/")
	public Map<String, Object> sayHello() {
		Map<String, Object> result = new HashMap<>();
		result.put("msg", "hello");
		return result;
	}
}
