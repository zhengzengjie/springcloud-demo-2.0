package com.school.game.utils;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int s =0, p = 1;
		for(int i = 1; i <= 10; i++) {
			p = p*i;
			s = s + p;
			System.out.println("p="+p+",s="+s); 
		}
		System.out.println(s);
	}

}
