import java.io.*;
import java.util.*;

public class EmployeeManagementSystem {

    // Employee class (OOP - Encapsulation)
    static class Employee {
        private int id;
        private String name;
        private double salary;

        public Employee(int id, String name, double salary) {
            this.id = id;
            this.name = name;
            this.salary = salary;
        }

        public int getId() { return id; }
        public String getName() { return name; }
        public double getSalary() { return salary; }

        public String toString() {
            return id + "," + name + "," + salary;
        }

        public static Employee fromString(String data) {
            String[] parts = data.split(",");
            return new Employee(
                    Integer.parseInt(parts[0]),
                    parts[1],
                    Double.parseDouble(parts[2])
            );
        }
    }

    static final String FILE_NAME = "employees.txt";

    // Add employee
    public static void addEmployee(Employee emp) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            bw.write(emp.toString());
            bw.newLine();
            System.out.println("✅ Employee added!");
        } catch (IOException e) {
            System.out.println("❌ Error saving employee");
        }
    }

    // Read all employees
    public static List<Employee> getAllEmployees() {
        List<Employee> list = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                list.add(Employee.fromString(line));
            }
        } catch (IOException e) {
            // ignore
        }
        return list;
    }

    // View employees
    public static void viewEmployees() {
        List<Employee> list = getAllEmployees();

        if (list.isEmpty()) {
            System.out.println("⚠ No employees found.");
            return;
        }

        for (Employee e : list) {
            System.out.println("ID: " + e.getId() +
                    " | Name: " + e.getName() +
                    " | Salary: " + e.getSalary());
        }
    }

    // Search employee
    public static void searchEmployee(int id) {
        for (Employee e : getAllEmployees()) {
            if (e.getId() == id) {
                System.out.println("✅ Found: " + e.getName());
                return;
            }
        }
        System.out.println("❌ Employee not found.");
    }

    // Delete employee
    public static void deleteEmployee(int id) {
        List<Employee> list = getAllEmployees();
        boolean removed = list.removeIf(e -> e.getId() == id);

        if (!removed) {
            System.out.println("❌ Employee not found.");
            return;
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Employee e : list) {
                bw.write(e.toString());
                bw.newLine();
            }
            System.out.println("✅ Employee deleted.");
        } catch (IOException e) {
            System.out.println("❌ Error deleting employee.");
        }
    }

    // Main menu
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Employee Management System ---");
            System.out.println("1. Add Employee");
            System.out.println("2. View Employees");
            System.out.println("3. Search Employee");
            System.out.println("4. Delete Employee");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");

            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();

                    System.out.print("Enter Salary: ");
                    double salary = sc.nextDouble();

                    addEmployee(new Employee(id, name, salary));
                    break;

                case 2:
                    viewEmployees();
                    break;

                case 3:
                    System.out.print("Enter ID: ");
                    searchEmployee(sc.nextInt());
                    break;

                case 4:
                    System.out.print("Enter ID: ");
                    deleteEmployee(sc.nextInt());
                    break;

                case 5:
                    System.out.println("👋 Exiting...");
                    return;

                default:
                    System.out.println("❌ Invalid choice");
            }
        }
    }
}