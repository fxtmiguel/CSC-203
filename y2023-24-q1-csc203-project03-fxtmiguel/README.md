[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-24ddc0f5d75046c5622901739e7c5dd533143b0c8e959d652212380cedb1ea36.svg)](https://classroom.github.com/a/8EeoIdCw)
# Project 3

## Overview

In this assignment, you will improve the pathing behavior of entities within your virtual world program.
As you've likely witnessed, dudes' and fairies' movements are very simplistic and often cause the entity to be stuck on an obstacle or other entity.

Pathing algorithms are a field of study in and of themselves.
Here, you will implement the widely used A* pathfinding algorithm after unifying the movement functionality of your entities through the use of an interface.
This `PathingStrategy` interface leverages functional programming features such as streams and lambda functions for you to practice working with higher-order coding concepts.

## Instructions

Complete **all** the following parts.

### Part 1: Copying Your Project 2 Code

The Project 3 repository contains identical classes to the initial Project 2 repository with several exceptions:
- The `Point` class has been modified to better aid in pathfinding.
- The `PathingStrategy` interface has been included.
- The `SingleStepPathingStrategy` and (the unimplemented) `AStarPathingStrategy` classes are included.

To begin, you must copy over your `Entity` and `Action` hierarchies from Project 2.
You can do so by opening both projects, selecting your classes from Project 2's `src` directory, copying, and then pasting your Project 3 `src` directory.

> [!Warning]
> New files are included for you as well.
> **Do not** delete or overwrite the included `Point.java`, `PathingStrategy.java`, `SingleStepPathingStrategy.java`, or `AStarPathingStrategy.java` files.
> If you accidentally overwrite one of these files, you can right-click the file, e.g., `Point.java` and select `Git > Rollback`.

You should also copy over any changes to your non-`Entity` and non-`Action` classes such as `WorldModel` and `VirtualWorld`, so that the project runs correctly.

## Part 2: The Strategy Design Pattern

### Design Patterns

Design patterns represent solutions to common problems in software design.
They are like templates that can be applied to real-world programming scenarios.
Patterns help you build reliable and maintainable code by following proven principles and methodologies.
One such pattern we will explore in this project is the "Strategy" pattern.

### The Strategy Pattern
The Strategy pattern is a behavioral design pattern that defines a family of algorithms, encapsulates each one of them, and makes them interchangeable.
The pattern lets the algorithm vary independently of clients that use it.
In other words, it enables an object to change its behavior at runtime by switching out one strategy for another.
In this project, we will focus on implementing a single strategy and using that throughout the codebase.

### The PathingStrategy Interface
In the context of our pathfinding project, `PathingStrategy` is an interface representing the strategy for navigating through a virtual world.
Each strategy is a different way for entities in your virtual world program to calculate paths.

This interface declares the `computePath` method that any pathfinding strategy must implement.
It takes a starting point and a goal point and returns a list of points that represent a path from start to end.
The method uses functional interfaces (`Predicate`, `BiPredicate`, and `Function`) to encapsulate the logic needed for the pathfinding operation.
This approach leans on Java’s functional programming-based lambda and stream features.

```java
/* PathingStrategy's computePath method */
List<Point> computePath(
    Point start,
    Point end,
    Predicate<Point> canPassThrough,
    BiPredicate<Point, Point> withinReach,
    Function<Point, Stream<Point>> potentialNeighbors);
```

Included in Project 3 are two pathing strategies that implement `PathingStrategy`:
1. `SingleStepPathingStrategy`: This is a simple implementation that calculates the path as a series of next steps towards the goal. It provides behavior identical to the existing virtual world movement. If the starting point is within reach of the end, it returns an empty list.
2. `AStarPathingStrategy` (you’ll implement this): This will be a more complex strategy using the A* search algorithm to find the best path to the goal.

### Refactoring to Use PathingStrategy

You must refactor your project to use the PathingStrategy.

Begin by examining the "nextPosition" methods in your entities.
Understand how these methods currently determine the next move.
Look for patterns and repeated logic that could be encapsulated by a pathfinding strategy.
Recognizing this will help you abstract the pathfinding logic away from your entities and delegate it to `PathingStrategy` implementations.

Once you've isolated the movement logic, instantiate a `PathingStrategy` object within your entities.
This object will be responsible for computing the path.
You may do so as an instance variable, or immediately before constructing the path.
You should start with the `SingleStepPathingStrategy` to test the refactoring before moving on to the `AStarPathingStrategy`.

```java
// A pathing strategy instantiation
PathingStrategy pathingStrategy = new SingleStepPathingStrategy();
```
To call the `computePath()` method, you need to define lambda functions that match its expected parameters.
These functions are crucial for determining which points can be passed through, which are within reach, and what the potential neighbors are.

- `canPassThrough` should represent points that are not occupied and within the bounds of the world. You will use `WorldModel` methods to create this predicate.
- `withinReach` defines when the goal has been reached. In our world, this is as simple as checking if two points are adjacent.
- `potentialNeighbors` should use the provided `CARDINAL_NEIGHBORS` function or a custom one if needed.

Note that different entities may have different conditions. For example, dudes can "trample" stumps.

```java
// An example "canPassthrough" predicate.
// Note that each entity may have a different predicate.
Predicate<Point> canPassThrough = point -> !world.isOccupied(point) && world.withinBounds(point);
```

The list returned by `computePath` is a sequence of points that represent the calculated path.
The first point in this list is typically the **next** step your entity should take towards its destination.
However, make sure to understand the nature of this list.
For example, it should **not** include the starting point or the destination point.

> [!IMPORTANT]
>
> This can potentially be a problem when working on your A* pathfinding, as **you** must enforce these requirements on the resultant list.

After calling `computePath`, your "nextPosition" methods should analyze the list and decide which point to return as the next move.
If the list is empty, the entity is either at the destination or there is no valid path.
Otherwise, extract the next step from the list and return it as the new position.

```java
List<Point> path = pathingStrategy.computePath(currentPosition, destination, canPassThrough, withinReach, potentialNeighbors);

if (path.isEmpty()) {
    // Logic if there is no path or at the destination
} else {
    // Logic if there is a path
}
```

You are **strongly encouraged** to start by refactoring your code to use `SingleStepPathingStrategy`.
Upon successful refactoring, **all** of your world tests should pass.
After confirming that `SingleStepPathingStrategy` works correctly, you can begin working on the `AStarPathingStrategy`.
Further details are provided in the next part section.
You will refactor your entities to use the new strategy once it is implemented, like so:

```java
// A pathing strategy instantiation
PathingStrategy pathingStrategy = new AStarPathingStrategy();
```

## Part 3: Implementing the AStarPathingStrategy

The `AStarPathingStrategy` class will be your implementation of the A* pathfinding algorithm.

Start by deciding which data structures will be required.
For example, the open set, a "came from" map, g-scores, f-scores, etc.
You may choose to store these properties in a `Node` class, add such information to the `Point` class, or you may initialize data structures in the `computePath()` method as in the example below.
Note that if using classes, proper overrides of the `equals()` and `hashCode()` are necessary for data structures to work properly. 

```java
// Some possible example structures
List<Point> openSet;
Map<Point, Point> cameFrom;
Map<Point, Integer> gscore;
```

For sorting or prioritizing, as is needed for the open-set, you will need a comparator that accepts points and uses their f-scores.
A `Comparator` in Java is a functional interface used to define the order of objects in a collection.
As a lambda method, it returns a negative integer, zero, or a positive integer if the first argument is less than, equal to, or greater than the second.

```java
Comparator<Integer> ascendingComparator = (num1, num2) -> num1.compareTo(num2);
```

(Recall, lower f-scores are better)

Additionally, a way to obtain a "default" value from a `Map` (e.g., in the case that a point hasn't been considered yet) is:

```java
gscore.getOrDefault(point, Integer.MAX_VALUE); // Returns a very large number if the point isn't in the map
```

These can be combined to create a priority queue:

```java
// A priority queue for the open set
// Requires an appropriate comparator that sorts by ascending f-score
PriorityQueue<Point> openSet = new PriorityQueue<>(fScoreComparator);
```

After initializing data structures, you must populate them with initial values where appropriate, such as setting the g-score of the start to 0 or adding the start to the open set.

Here are some guidelines to refer to as you implement the standard steps of the A* algorithm:
- **Heuristic:** Recall that common heuristics, which estimate the cost from a point to the goal, for a grid are the (preferably) Manhattan distance and Euclidean distance.
- **Neighbors:** Utilize the `potentialNeighbors` lambda to iterate over the neighbors of the "current" node. Remember to filter the neighbors using the `canPassThrough` predicate.
- **Scores:** For each neighbor, calculate the tentative g-score, check if this path to the neighbor is better than any previously known path, and update the relevant data structures accordingly.
- **Termination:** Implement the correct logic for when the goal has been reached or when there is no possible path (i.e., the open set is empty).
- **Path Reconstruction:** Once the goal is reached, you should return the reconstructed path. You will likely do so by iterating over your "came from" data. Be sure to **not** include the starting point or the node, and ensure the list is properly ordered.

After completion, you can change references in your code from using `SingleStepPathingStrategy` to `AStarPathingStrategy`.
Run all the included tests to see if they pass.
Run the virtual world itself and ensure that it continues to run indefinitely.

## Submission

> [!WARNING]
>
> Submitting this assignment confirms that you did not use solutions or code from external, AI-generated, or peer sources.
>
> You also agree to have your code checked by standard plagiarism detection software.
>
> Violation will result in a grade of zero, a report to the University, and further potential action.

Commit and push your changes to GitHub.

On Canvas, upload a screenshot showing proof of your submission from GitHub. **Your repository name must be visible.**
