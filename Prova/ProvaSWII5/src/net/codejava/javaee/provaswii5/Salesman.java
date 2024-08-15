package net.codejava.javaee.provaswii5;

public class Salesman {
	private int id;
	private String name;
	private String city;
	private double commission;
	
	public Salesman() {
		
	}
	public Salesman(int id) {
		this.id = id;
	}
	public Salesman(String name, String city, double commission) {
		this.name = name;
		this.city = city;
		this.commission = commission;
	}
	public Salesman(int id, String name, String city, double commission) {
		this.id = id;
		this.name = name;
		this.city = city;
		this.commission = commission;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public double getCommission() {
		return commission;
	}
	public void setCommission(double commission) {
		this.commission = commission;
	}
}

