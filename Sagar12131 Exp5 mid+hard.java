/**
Create a Java program to serialize and deserialize a Student object. The program should:

Serialize a Student object (containing id, name, and GPA) and save it to a file.

Deserialize the object from the file and display the student details.

Handle FileNotFoundException, IOException, and ClassNotFoundException using exception handling.
*/

import java.io.*;
import java.util.Scanner;

class Student implements Serializable {
    private int id;
    private String name;
    private double gpa;

    public Student(int id, String name, double gpa) {
        this.id = id;
        this.name = name;
        this.gpa = gpa;
    }

    @Override
    public String toString() {
        return "Student{id=" + id + ", name='" + name + "', gpa=" + gpa + "}";
    }
}

public class SerializeDeserializeStudent {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter student ID:");
        int id = Integer.parseInt(scanner.nextLine());

        System.out.println("Enter student name:");
        String name = scanner.nextLine();

        System.out.println("Enter student GPA:");
        double gpa = Double.parseDouble(scanner.nextLine());

        Student student = new Student(id, name, gpa);
        String filename = "student.ser";

        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
            out.writeObject(student);
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("IO error: " + e.getMessage());
        }

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
            Student deserializedStudent = (Student) in.readObject();
            System.out.println("Deserialized Student: " + deserializedStudent);
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("IO error: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.err.println("Class not found: " + e.getMessage());
        }

        scanner.close();
    }
}


/**
Create a menu-based Java application with the following options. 1.Add an Employee 2. Display All 3. Exit If option 1 is selected, 
the application should gather details of the employee like employee name, employee id, designation and salary and store it in a file. 
If option 2 is selected, the application should display all the employee details. If option 3 is selected the application should exit.
*/

import java.io.*;
import java.util.*;

class Employee implements Serializable {
    private int id;
    private String name;
    private String designation;
    private double salary;

    public Employee(int id, String name, String designation, double salary) {
        this.id = id;
        this.name = name;
        this.designation = designation;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee{id=" + id + ", name='" + name + "', designation='" + designation + "', salary=" + salary + "}";
    }
}

public class MenuBasedEmployeeApp {
    private static final String FILENAME = "employees.ser";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Employee> employees = new ArrayList<>();

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILENAME))) {
            employees = (List<Employee>) in.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("No existing employee records found.");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error reading employee records: " + e.getMessage());
        }

        while (true) {
            System.out.println("Menu:");
            System.out.println("1. Add an Employee");
            System.out.println("2. Display All");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            int option = Integer.parseInt(scanner.nextLine());

            switch (option) {
                case 1:
                    System.out.print("Enter employee ID: ");
                    int id = Integer.parseInt(scanner.nextLine());
                    System.out.print("Enter employee name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter employee designation: ");
                    String designation = scanner.nextLine();
                    System.out.print("Enter employee salary: ");
                    double salary = Double.parseDouble(scanner.nextLine());

                    employees.add(new Employee(id, name, designation, salary));

                    try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILENAME))) {
                        out.writeObject(employees);
                    } catch (IOException e) {
                        System.err.println("Error saving employee records: " + e.getMessage());
                    }
                    break;
                case 2:
                    for (Employee employee : employees) {
                        System.out.println(employee);
                    }
                    break;
                case 3:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
