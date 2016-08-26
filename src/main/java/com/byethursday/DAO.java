package com.byethursday;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class DAO {

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

		ArrayList<Student> ourStudents = new ArrayList<>();

		try {

			STMT = CONN.createStatement();
			RES_SET = STMT.executeQuery("SELECT * FROM students.students;");

			while (RES_SET.next()) {

				Student studentInDB = new Student();

				studentInDB.setStudentID(RES_SET.getString("students_id"));
				studentInDB.setLastName(RES_SET.getString("last_name"));
				studentInDB.setFirstName(RES_SET.getString("first_name"));
				studentInDB.setGpa(RES_SET.getDouble("student_gpa"));

				ourStudents.add(studentInDB);

			}

			for (Student student : ourStudents) {
				System.out.println(student.toString());
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static void writeToDatabase() {
		
		Student studentToAdd = new Student();
		
		studentToAdd = aboutTheStudent();
		
		try {
			
			connToDB();
			
			PREP_STMT = CONN.prepareStatement(insertIntoTable);
			
			PREP_STMT.setString(1, studentToAdd.getLastName());
			PREP_STMT.setString(2, studentToAdd.getFirstName());
			PREP_STMT.setDouble(3, studentToAdd.getGpa());
			
			System.out.println(PREP_STMT);
			
			PREP_STMT.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		
	}
	
	private static String insertIntoTable = "INSERT INTO `students`.`students`"
			+ "(last_name, first_name, student_gpa)"
			+ " VALUES " 
			+ "(?, ?, ?)";

	private static Student aboutTheStudent() {
		
		Scanner sc = new Scanner(System.in);
		
		Student studentToAdd = new Student();

		System.out.println("What is the student's last name?");
		 
		studentToAdd.setLastName(sc.nextLine());
		
		System.out.println("What is the student's first name?");
		
		studentToAdd.setFirstName(sc.nextLine());

		System.out.println("What is the student's GPA?");
		String gpaInput = sc.nextLine();
		
		studentToAdd.setGpa(Double.parseDouble(gpaInput));
		
		return studentToAdd;
	}










} //class
