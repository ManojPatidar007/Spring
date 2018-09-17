package com.mp;

import org.springframework.stereotype.Component;

@Component
public class HappyFortuneService implements FortuneService {

	@Override
	public String show() {
		System.out.println("HelloConstructor HappyFortuneService");
		// TODO Auto-generated method stub
		return "Hello";

	}
}
