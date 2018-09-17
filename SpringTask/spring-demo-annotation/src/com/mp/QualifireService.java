package com.mp;

import org.springframework.stereotype.Component;

@Component("qua")
public class QualifireService implements Qualifier{

	@Override
	public void add() {
		System.out.println("inside qualifier");
		// TODO Auto-generated method stub
		
	}

}
