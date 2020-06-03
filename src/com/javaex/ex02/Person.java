package com.javaex.ex02;

public class Person {
	private String name, hp, company;

	public Person() {}
	public Person(String name, String hp, String company) {
		this.name = name;
		this.hp = hp;
		this.company = company;
	}
	
	@Override
	public String toString() {
		return name + "," + hp + "," + company;
	}
	public void showInfo() {
		System.out.println(name + "     " + hp + "     " + company);
	}
}
