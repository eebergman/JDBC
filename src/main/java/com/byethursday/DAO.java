package com.byethursday;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class DAO {

	// jdbc driver
	static final String DB_URL = "jdbc:mysql://localhost:3306/?user=root&autoReconnect=true&useSSL=false";
	static final String USER = "root";
	static final String PASSWORD = "sesame";

	static Connection CONN = null;
	static Statement STMT = null;
	static PreparedStatement PREP_STMT = null;
	static ResultSet RES_SET = null;

	public static void connToDB() {

		try {

			System.out.println("Trying to connect to the DB...");
			CONN = DriverManager.getConnection(DB_URL, USER, PASSWORD);
			System.out.println("Connected to DB.");

		} catch (SQLException e) {
			System.out.println("Connection failed");
			e.printStackTrace();
		}

	}

	public static void readFromDB() {

		connToDB();

		ArrayList<Animal> ourZoo = new ArrayList<>();

		try {

			STMT = CONN.createStatement();
			RES_SET = STMT.executeQuery("SELECT * FROM our_schema.zoo;");

			while (RES_SET.next()) {

				Animal animalInDB = new Animal();

				animalInDB.setName(RES_SET.getString("name"));
				animalInDB.setSpecies(RES_SET.getString("species"));
				animalInDB.setEnclosure(RES_SET.getString("enclosure"));
				animalInDB.setFood(RES_SET.getString("food"));

				ourZoo.add(animalInDB);

			}

		for (Animal animal : ourZoo) {
			System.out.println(animal);
		}
		
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static void writeToDatabase() {

		Animal animalToAdd = new Animal();

		animalToAdd = aboutTheStudent();

		try {

			connToDB();

			PREP_STMT = CONN.prepareStatement(insertIntoTable);

			PREP_STMT.setString(1, animalToAdd.getName());
			PREP_STMT.setString(2, animalToAdd.getSpecies());
			PREP_STMT.setString(3, animalToAdd.getEnclosure());
			PREP_STMT.setString(4, animalToAdd.getFood());

			System.out.println(PREP_STMT);

			PREP_STMT.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static String insertIntoTable = "INSERT INTO `our_schema`.`zoo`" 
			+ "(name, species, enclosure, food)"
			+ " VALUES " 
			+ "(?, ?, ?, ?)";

	private static Animal aboutTheStudent() {

		Scanner sc = new Scanner(System.in);

		Animal animalToAdd = new Animal();

		System.out.println("What is the animal's name?");

		animalToAdd.setName(sc.nextLine());

		System.out.println("What is the animal's species?");

		animalToAdd.setSpecies(sc.nextLine());

		System.out.println("What kind of enclosure does the animal live in?");

		animalToAdd.setEnclosure(sc.nextLine());
		
		System.out.println("What kind of food does the animal eat?");
		
		animalToAdd.setFood(sc.nextLine());
		
		sc.close();
		return animalToAdd;
	}

} // class
