package org.example;

import java.util.Scanner;

public class StudentManagementSystem {

    private static final int MAX = 10;
    private static Student[] students = new Student[MAX];
    private static int count = 0;

    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        int choice = 0;

        while (choice != 8) {
            printMenu();
            choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1 -> addStudent();
                case 2 -> displayAll();
                case 3 -> searchStudent();
                case 4 -> displayResult();
                case 5 -> updateMarks();
                case 6 -> classStats();
                case 7 -> subjectWiseMarks();
                case 8 -> System.out.println("Goodbye!");
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    private static void printMenu() {
        System.out.println("\n========================================");
        System.out.println("       STUDENT MANAGEMENT SYSTEM        ");
        System.out.println("========================================");
        System.out.println("1. Add Student");
        System.out.println("2. Display All Students");
        System.out.println("3. Search Student");
        System.out.println("4. Display Student Result");
        System.out.println("5. Update Student Marks");
        System.out.println("6. Class Statistics");
        System.out.println("7. Subject-wise Marks");
        System.out.println("8. Exit");
        System.out.print("Enter your choice: ");
    }

    private static void addStudent() {
        if (count >= MAX) {
            System.out.println("Cannot add more students.");
            return;
        }

        System.out.print("Enter ID: ");
        int id = Integer.parseInt(sc.nextLine());

        if (find(id) != -1) {
            System.out.println("ID already exists.");
            return;
        }

        System.out.print("Enter Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Age: ");
        int age = Integer.parseInt(sc.nextLine());

        System.out.print("Enter Sub1 Marks: ");
        double m1 = Double.parseDouble(sc.nextLine());

        System.out.print("Enter Sub2 Marks: ");
        double m2 = Double.parseDouble(sc.nextLine());

        System.out.print("Enter Sub3 Marks: ");
        double m3 = Double.parseDouble(sc.nextLine());

        students[count++] = new Student(id, name, age, m1, m2, m3);
        System.out.println("Student added.");
    }

    private static void displayAll() {
        if (count == 0) {
            System.out.println("No records.");
            return;
        }

        System.out.println("-------------------------------------------------------------");
        System.out.printf("%-10s %-15s %-5s %-10s %-10s %-10s%n",
                "ID", "Name", "Age", "Sub1", "Sub2", "Sub3");
        System.out.println("-------------------------------------------------------------");

        for (int i = 0; i < count; i++) {
            students[i].display();
        }
    }

    private static void searchStudent() {
        System.out.print("Enter ID: ");
        int id = Integer.parseInt(sc.nextLine());

        int idx = find(id);
        if (idx == -1) {
            System.out.println("Not found.");
        } else {
            System.out.println("Found: " + students[idx].name);
        }
    }

    private static void displayResult() {
        System.out.print("Enter ID: ");
        int id = Integer.parseInt(sc.nextLine());

        int idx = find(id);
        if (idx == -1) {
            System.out.println("Not found.");
            return;
        }

        Student s = students[idx];

        System.out.println("========================================");
        System.out.println("             STUDENT RESULT             ");
        System.out.println("========================================");
        System.out.println("ID: " + s.id);
        System.out.println("Name: " + s.name);
        System.out.println("Age: " + s.age);
        System.out.println("Average: " + s.average());
        System.out.println("Grade: " + s.grade());
        System.out.println("Status: " + (s.isPassed() ? "PASS" : "FAIL"));
    }

    private static void updateMarks() {
        System.out.print("Enter ID: ");
        int id = Integer.parseInt(sc.nextLine());

        int idx = find(id);
        if (idx == -1) {
            System.out.println("Not found.");
            return;
        }

        System.out.print("New Sub1: ");
        double m1 = Double.parseDouble(sc.nextLine());

        System.out.print("New Sub2: ");
        double m2 = Double.parseDouble(sc.nextLine());

        System.out.print("New Sub3: ");
        double m3 = Double.parseDouble(sc.nextLine());

        students[idx].updateMarks(m1, m2, m3);
        System.out.println("Updated.");
    }

    private static void classStats() {
        if (count == 0) {
            System.out.println("No records.");
            return;
        }

        double highest = -1, lowest = 101, sum = 0;
        int pass = 0, fail = 0;

        for (int i = 0; i < count; i++) {
            Student s = students[i];
            double avg = s.average();
            sum += avg;

            highest = Math.max(highest, avg);
            lowest = Math.min(lowest, avg);

            if (s.isPassed()) pass++;
            else fail++;
        }

        System.out.println("Class Average: " + (sum / count));
        System.out.println("Highest: " + highest);
        System.out.println("Lowest: " + lowest);
        System.out.println("Pass: " + pass);
        System.out.println("Fail: " + fail);
    }

    private static void subjectWiseMarks() {
        if (count == 0) {
            System.out.println("No records.");
            return;
        }

        System.out.println("-------------------------------------------------------------");
        System.out.printf("%-10s %-15s %-10s %-10s %-10s%n",
                "ID", "Name", "Sub1", "Sub2", "Sub3");
        System.out.println("-------------------------------------------------------------");

        for (int i = 0; i < count; i++) {
            Student s = students[i];
            System.out.printf("%-10d %-15s %-10.1f %-10.1f %-10.1f%n",
                    s.id, s.name, s.sub1, s.sub2, s.sub3);
        }
    }

    private static int find(int id) {
        for (int i = 0; i < count; i++) {
            if (students[i].id == id) return i;
        }
        return -1;
    }
}
