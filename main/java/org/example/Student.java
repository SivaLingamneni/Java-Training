package org.example;

public class Student {

    int id;
    String name;
    int age;
    double sub1;
    double sub2;
    double sub3;

    public Student(int id, String name, int age, double sub1, double sub2, double sub3) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.sub1 = sub1;
        this.sub2 = sub2;
        this.sub3 = sub3;
    }

    public void updateMarks(double m1, double m2, double m3) {
        this.sub1 = m1;
        this.sub2 = m2;
        this.sub3 = m3;
    }

    public void display() {
        System.out.printf("%-10d %-15s %-5d %-10.1f %-10.1f %-10.1f%n",
                id, name, age, sub1, sub2, sub3);
    }

    public double average() {
        return (sub1 + sub2 + sub3) / 3.0;
    }

    public boolean isPassed() {
        return sub1 >= 40 && sub2 >= 40 && sub3 >= 40;
    }

    public String grade() {
        double avg = average();
        if (avg >= 90) return "A+";
        if (avg >= 80) return "A";
        if (avg >= 70) return "B";
        if (avg >= 60) return "C";
        if (avg >= 50) return "D";
        if (avg >= 40) return "E";
        return "F";
    }
}
