# Project 2

## Overview

In this assignment, you will be introduced to a large, object-oriented codebase in the form of a cartoony virtual world.

As given, classes in this program lack **cohesion**: they perform a variety of tasks from overly general to very specific.
You will divide the present `Entity` and `Action` classes into multiple classes that exhibit high cohesion, that is each will serve a singular, well-defined purpose.
In doing so, you will create class and/or interface hierarchies for both “families”: `Action` and `Entity`.

By the end of this project, the virtual world program will be representative of a fully functioning and well-designed object-oriented program.

## Code Repository

<p><a style="display: block; border: 1pt solid; border-radius: 0.5rem; padding: 1em; text-align: center;" href="#">GitHub Classroom Link</a></p>

## Instructions

### Viewing the Virtual World

Take a moment to orient yourself with the structure of the project and familiarize yourself with the included classes.

Your entry point to the virtual world is the `VirtualWorld` class's main method.
You may run this class to start the simulation.
Additionally, if you provide one of the following command-line arguments  you can change the speed of the simulation:
* `-fast`: Two times faster simulation speed
* `-faster`: Four times faster simulation speed
* `-fastest`: Ten times faster simulation speed

You can add one of these arguments in IntelliJ IDEA by selecting the "dots" next to the Run button, clicking "Run with Parameters...", and then writing it in the "Program Arguments" field.

You can run all the included tests by running the `WorldTests` class.

### UML Diagram

Create a UML diagram for your `Entity` and `Action` class hierarchies

You will want to start by creating a diagram of the existing versions of these classes.
As is, they are monolithic and not cohesive.
Your goal is to identify common and uncommon functionality and data for prospective child classes, abstract classes, and/or interfaces.

As a guide, refer to the `EntityKind` and `ActionKind` enumeration types.
Though you can create any hierarchy you choose, these "kinds" are representative of possible concrete classes that might exist in your refactoring.
Additionally, you may expect the static "create" methods to eventually be replaced with constructors of new, concrete classes.

You must use **extends** and/or **implements** relationship arrows in your diagram.
You may use other additional arrows but are not required to.
You must indicate whether a specific class diagram represents an interface or abstract class and must indicate all appropriate access modifiers, and other method modifiers such as abstract, static, etc. You do not need to submit diagrams for any other classes, e.g. `WorldModel`, etc.

As you refactor your UML, follow these general guidelines:
- Your overall goal is to reduce redundancy and improve cohesion.
- You may choose to divide your classes either using subclasses, interfaces, or a combination of both; there is no optimal solution, but each has advantages and disadvantages.
- Use inheritance (e.g., "extends") to propagate data requirements.
- Use contracts (e.g., "implements") for shared functionality requirements.
- Use intermediate abstract classes to propagate shared data and functionality to concrete classes.
- You will want each "Entity Kind" and "ActionKind" to be represented by a class and, based on your design, one class may represent a single or several "Kinds".
- Iterate: after you’ve refactored the class, perform the process again, checking for remaining redundancies.
- No class should implement an interface method without code: i.e., a "{}" implementation.
- If a class does not make use of an instance variable defined within it or that it has inherited, you may need to redesign your hierarchy or remove that instance variable.
- Follow standard data hiding rules: make instance variables private whenever possible.

> [!NOTE]
> A UML diagram of these classes is given in the project as `UML.drawio`. You may use this digital version or can draw your own on pen and paper.

### Refactor the Codebase

Refactor the codebase in IntelliJ IDEA to mirror the work done for your UML design.

Your refactoring must not add or remove any functionality; the resulting program must work exactly as before and all tests within `WorldTests.java` must pass.

One method is to begin working from "general" to "specific", implementing classes one at a time. For example, if you are creating an immediate subclass of `Entity`, you might follow these steps:
1. Copy all code from `Entity` into the new subclass.
2. Modify it to represent the subclass.
   - This will usually involve removing more general data and functionality that should remain in `Entity`
3. In every other class, change any reference from the original `Entity` to the new subclass where appropriate.
   - For example, if you are making a "Fairy" subclass, change references to all the "Fairy" related methods and data.
4. Remove all data and functionality that should now be unique to the subclass from `Entity`:
   - For example, if you are making a "Dude" class, you might remove the "resource" related data from the original `Entity` class.

If using an interface, ensure that it has all the functionality required for an Entity, for example, then change all reference to that. Next, implement the interface in concrete classes and change references, one class at a time.

For methods that require defunct `EntityKind` or `ActionKind` enums, you will need to use either the `instanceof` operator or `getClass()` method.

Test the functionality of your program ***every time*** you introduce a new class then commit changes when the new class is successfully integrated. Also, be sure to run `VirtualWorld.java` periodically to visually inspect the program.

> [!IMPORTANT]
> It's okay if your design changes as you refactor the code, but ensure you change the UML to reflect any changes. You will be graded on how closely your UML diagram represents your code, whether the included tests pass, data hiding, and redundancy reduction.

## Validation

This assignment will be graded manually. You will not see a checkmark on GitHub.

## Submission

> [!WARNING]
> Submitting this assignment confirms that you did not use solutions or code from external, AI-generated, or peer sources.
> You also agree to have your code checked by standard plagiarism detection software.
> Violation will result in a grade of zero, a report to the University, and further potential action.

Commit your changes to this project's repository and push it to GitHub.

Upload a screenshot showing proof of your push from GitHub to the assignment Canvas page for completion.