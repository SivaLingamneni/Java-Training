package org.example;

import java.util.ArrayList;
import java.util.Scanner;

public class StudentManagementSystem {

    static ArrayList<Student> students = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        int choice = 0;

        while (choice != 8) {
            menu();
            choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1:
                    addStudent();
                    break;

                case 2:
                    displayAll();
                    break;

                case 3:
                    searchStudent();
                    break;

                case 4:
                    displayResult();
                    break;

                case 5:
                    updateMarks();
                    break;

                case 6:
                    classStats();
                    break;

                case 7:
                    subjectMarks();
                    break;

                case 8:
                    System.out.println("Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    static void menu() {
        System.out.println("\n=== STUDENT MANAGEMENT SYSTEM ===");
        System.out.println("1. Add Student");
        System.out.println("2. Display All Students");
        System.out.println("3. Search Student");
        System.out.println("4. Display Student Result");
        System.out.println("5. Update Marks");
        System.out.println("6. Class Statistics");
        System.out.println("7. Subject-wise Marks");
        System.out.println("8. Exit");
        System.out.print("Enter choice: ");
    }

    static void addStudent() {
        System.out.print("ID: ");
        int id = Integer.parseInt(sc.nextLine());

        if (find(id) != null) {
            System.out.println("ID already exists.");
            return;
        }

        System.out.print("Name: ");
        String name = sc.nextLine();

        System.out.print("Age: ");
        int age = Integer.parseInt(sc.nextLine());

        System.out.print("Sub1: ");
        double m1 = Double.parseDouble(sc.nextLine());

        System.out.print("Sub2: ");
        double m2 = Double.parseDouble(sc.nextLine());

        System.out.print("Sub3: ");
        double m3 = Double.parseDouble(sc.nextLine());

        students.add(new Student(id, name, age, m1, m2, m3));
        System.out.println("Added.");
    }

    static void displayAll() {
        if (students.isEmpty()) {
            System.out.println("No records.");
            return;
        }

        System.out.printf("%-5s %-15s %-5s %-7s %-7s %-7s%n",
                "ID", "Name", "Age", "Sub1", "Sub2", "Sub3");

        for (Student s : students) s.display();
    }

    static void searchStudent() {
        System.out.print("Enter ID: ");
        int id = Integer.parseInt(sc.nextLine());

        Student s = find(id);
        if (s == null) System.out.println("Not found.");
        else System.out.println("Found: " + s.name);
    }

    static void displayResult() {
        System.out.print("Enter ID: ");
        int id = Integer.parseInt(sc.nextLine());

        Student s = find(id);
        if (s == null) {
            System.out.println("Not found.");
            return;
        }

        System.out.println("\n=== RESULT ===");
        System.out.println("ID: " + s.id);
        System.out.println("Name: " + s.name);
        System.out.println("Average: " + s.average());
        System.out.println("Grade: " + s.grade());
        System.out.println("Status: " + (s.isPassed() ? "PASS" : "FAIL"));
    }

    static void updateMarks() {
        System.out.print("Enter ID: ");
        int id = Integer.parseInt(sc.nextLine());

        Student s = find(id);
        if (s == null) {
            System.out.println("Not found.");
            return;
        }

        System.out.print("New Sub1: ");
        double m1 = Double.parseDouble(sc.nextLine());

        System.out.print("New Sub2: ");
        double m2 = Double.parseDouble(sc.nextLine());

        System.out.print("New Sub3: ");
        double m3 = Double.parseDouble(sc.nextLine());

        s.updateMarks(m1, m2, m3);
        System.out.println("Updated.");
    }

    static void classStats() {
        if (students.isEmpty()) {
            System.out.println("No records.");
            return;
        }

        double highest = -1, lowest = 101, sum = 0;
        int pass = 0;

        for (Student s : students) {
            double avg = s.average();
            sum += avg;
            if (avg > highest) highest = avg;
            if (avg < lowest) lowest = avg;
            if (s.isPassed()) pass++;
        }

        System.out.println("Class Avg: " + (sum / students.size()));
        System.out.println("Highest: " + highest);
        System.out.println("Lowest: " + lowest);
        System.out.println("Pass: " + pass);
        System.out.println("Fail: " + (students.size() - pass));
    }

    static void subjectMarks() {
        if (students.isEmpty()) {
            System.out.println("No records.");
            return;
        }

        System.out.printf("%-5s %-15s %-7s %-7s %-7s%n",
                "ID", "Name", "Sub1", "Sub2", "Sub3");

        for (Student s : students) {
            System.out.printf("%-5d %-15s %-7.1f %-7.1f %-7.1f%n",
                    s.id, s.name, s.sub1, s.sub2, s.sub3);
        }
    }

    static Student find(int id) {
        for (Student s : students)
            if (s.id == id) return s;
        return null;
    }
}
