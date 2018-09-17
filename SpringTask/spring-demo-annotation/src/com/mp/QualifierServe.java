package com.mp;

import org.springframework.stereotype.Component;

@Component
public class QualifierServe implements Qualifier {

	@Override
	public void add() {
		System.out.println("inside qualifieserver");
		// TODO Auto-generated method stub

	}

}
