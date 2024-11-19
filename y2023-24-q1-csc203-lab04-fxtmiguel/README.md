[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-24ddc0f5d75046c5622901739e7c5dd533143b0c8e959d652212380cedb1ea36.svg)](https://classroom.github.com/a/rpx8atHT)
# Lab 4

## Instructions

Complete all the following problems.

> [!NOTE]
> For quizzes and exams, you will be expected to understand the content required to complete all problems without guidance.

### Problem 1

Within the `src` folder, define a `Stringifiable` interface with a `stringify()` method that takes no parameters and returns a value of type `String`.

Next, within the `src` folder, define a `Spaghetti` class with two private instance variables: (1) `noodleDiameter` (a double), and (2) `sauce` (a string).
Write a constructor for this class that accepts values for and initializes these instance variables.
Have the class implement the `Stringifiable` interface.
Implement the interface's `stringify()` method to return a string containing its `sauce` and `diameter` in the format: "x, sauce" where “x” and “sauce” are the values of the instance’s `noodleDiameter` and `sauce` instance variables, respectively.

### Problem 2

In your `Spaghetti` class, override the `Object` class's `toString()`, `equals()`, and `hashCode()` methods.
The `toString()` method should return a string of the form "x, sauce", where "x" and "sauce" are the values of the instance's `noodleDiameter` and `sauce` instance variables, respectively.
**Do not** call `stringify()` in the `toString()` method.

The `equals()` method must evaluate to `true` whenever two `Spaghetti` objects have the same `noodleDiameter` and `sauce` instance variable values and `false` if the "other" object is not a `Spaghetti` object or is `null`.
Use the static `Double.compare` to check for equality between double values.

The `hashCode()` method should return a unique integer for a Spaghetti object based on the values of instance variables.
**Do not** use the `Objects.hash()` static method in your solution.
You may use `Double.hashCode()` and `String.hashCode()`, however.

### Problem 3

Write enough tests in `SpaghettiTests.java` file to cover all 100% lines of code (*not only methods*) within your `Spaghetti.java`.
Do not modify the included test(s).

> [!Note]
> You will need to submit a screenshot of your IntelliJ IDEA test coverages to Canvas.

## Validation

You may ask your instructor to check your code **before** submission.
GitHub will not display a checkmark.

## Submission

> [!Warning]
> Submitting this assignment confirms that you did not use solutions or code from external, AI-generated, or peer sources.
> You also agree to have your code checked by standard plagiarism detection software.
> Violation will result in a grade of zero, a report to the University, and further potential action.

Commit your repository changes and push them to GitHub.

Next, upload screenshots of the following to Canvas:
1. Proof of your submission to GitHub.
2. For problem 3, your 100% line test coverage.

**After submitting your code to GitHub to Canvas, you must demonstrate your code and screenshots** to an instructor during lab to receive a grade.

> [!Important]
> Even if submitted to GitHub and Canvas, your assignment will be marked as missing and receive a grade of zero until you demonstrate its completion to an instructor during lab.