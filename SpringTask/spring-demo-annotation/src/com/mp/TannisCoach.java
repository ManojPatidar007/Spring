package com.mp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("tannniscoachcom")
public class TannisCoach implements Coach {
	@Autowired
	FortuneService service;
	FortuneService service1;
	FortuneService service2;
	FortuneService service3;
	Qualifier qualifier;

	TannisCoach(){};
	@Autowired
	public void anyMethodName(FortuneService ser){
		System.out.println("any method");
		this.service3=ser;
	}
	@Autowired//setter DI
	public void setFortuneService(FortuneService servise3){
		System.out.println("setter");
		this.service2=servise3;
	}
	@Autowired//cunstrocter DI
	TannisCoach(FortuneService servise){
		System.out.println("cons");
		service1=servise;
	}
	
 
	@Override
	public String getDailyWorkout() {
		qualifier.add();
		return "heoo spring";//+service.show()+service1.show()+service2.show();
	}
	@Override
	public String show() {
		return null;
		// TODO Auto-generated method stub
		
	}
	@Autowired
	@org.springframework.beans.factory.annotation.Qualifier("qualifierServe")
	public void setQualifier(Qualifier qualifier){
		this.qualifier=qualifier;
	}
	

}
