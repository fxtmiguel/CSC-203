[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-24ddc0f5d75046c5622901739e7c5dd533143b0c8e959d652212380cedb1ea36.svg)](https://classroom.github.com/a/n-OqOK-m)
# Lab 3

## Instructions

Complete all the following parts.

⚠️ **Attention:** For quizzes and exams, you will be expected to understand the content required to complete all problems without guidance.

## Problem 1

Create a `Student` class in `src/Student.java` with private instance variables: `String name`, `int age`, `double gpa`.
Provide a constructor that accepts values for and initializes each variable.
Create getter and setter methods for each variable.
Implement an instance method `getStudentInfo()` that returns a `String` of the student's name, age, and GPA, separated by a space character.
Implement another instance method `getLetterGrade()` that returns the student's letter grade as a string based on their GPA according to the following rules:
* "A" for gpa >= 4.0,
* "B" for 4.0 > gpa >= 3.0
* "C" for 3.0 > gpa >= 2.0
* "D" for 2.0 > gpa >= 1.0
* "F" for gpa < 1.0

## Problem 2

Create a `StudentFunctions` class in `src/StudentFunctions.java` with two static methods that accept a `Student` object as a parameter.
The first method, `getStudentInfo()`, should return a string containing the student's name, age, and GPA.
The second method, `getLetterGrade()`, should return the student's letter grade as a string based on their GPA according to the rules in Problem 1.

## Problem 3

Write 8 JUnit test cases in `src/StudentTests.java` to test each of the four methods described above (two test cases for each method).

## Validation

This lab will be **graded by demonstrating your code to an instructor during lab**. GitHub will not display a checkmark.

## Submission

<span style="color:crimson; font-size:large;">🛑 **Integrity Reminder: All work that you submit must represent your own individual effort.** 🛑</span>

Commit your lab repository changes and push it to GitHub.

Upload a screenshot containing proof of your submission to GitHub.

Demonstrate your GitHub submission, Canvas submission, and completed code to an instructor for completion.

⚠️ **Attention:** You must demonstrate your code to an instructor during lab to receive a grade for this assignment.
