package co.simplon.exercise.core.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;


public class Laptop{
	
	@Column(name = "brand")
	private String brand;
	
	// Constructors
	public Laptop() {
		super();
	}

	public Laptop(String brand) {
		super();
		this.brand = brand;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

}
