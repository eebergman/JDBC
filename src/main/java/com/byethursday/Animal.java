package com.byethursday;

public class Animal {

	private String animalID = null;
	private String name = null;
	private String species = null;
	private String enclosure = null;
	private String food = null;

	public Animal(String animalID, String name, String species, String enclosure, String food) {
		super();
		this.animalID = animalID;
		this.name = name;
		this.species = species;
		this.enclosure = enclosure;
		this.food = food;
	}

	public Animal() {
		super();
	}

	public String getAnimalID() {
		return animalID;
	}

	public void setAnimalID(String animalID) {
		this.animalID = animalID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSpecies() {
		return species;
	}

	public void setSpecies(String species) {
		this.species = species;
	}

	public String getEnclosure() {
		return enclosure;
	}

	public void setEnclosure(String enclosure) {
		this.enclosure = enclosure;
	}

	public String getFood() {
		return food;
	}

	public void setFood(String food) {
		this.food = food;
	}

	@Override
	public String toString() {
		return "Animal [animalID=" + animalID + ", name=" + name + ", species=" + species + ", enclosure=" + enclosure
				+ ", food=" + food + "]";
	}

}
