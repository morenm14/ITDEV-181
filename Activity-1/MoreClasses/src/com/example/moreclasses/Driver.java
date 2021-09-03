package com.example.moreclasses;

public class Driver {
    public static void main(String[] args) {

        Student student = new Student("Bruce Lee", 123456789);
        Subject subject = new Subject("TPG10AB", "Technical Programming");

        ExamPaper exam = new ExamPaper(student, subject, 100);

        System.out.println(exam);


    }
}
