package testing;

import java.math.BigInteger;
import java.util.*;

public class Hello {
	public static void main(String[] args){
		
		Set<Integer> Deselect = new HashSet<Integer>();
		Integer i=129;
		 Set<Integer> set=new HashSet<Integer>();
		  HashMap<Integer,Set<Integer>> hm=new HashMap<Integer,Set<Integer>>();
		  Deselect.add(129);
		  hm.put(100,Deselect);
		for(Map.Entry m:hm.entrySet()){
		   set=(Set<Integer>) m.getValue();
		   System.out.println(i.equals(set));
		   for(Integer j:set)
		   System.out.println(i.equals(j));
		  }
		 }
		

		
	}

