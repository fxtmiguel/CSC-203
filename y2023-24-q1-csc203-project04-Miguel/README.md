[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-24ddc0f5d75046c5622901739e7c5dd533143b0c8e959d652212380cedb1ea36.svg)](https://classroom.github.com/a/0rQvY7gy)
# Project 4

## Course Information
- **Course Name:** CSC 203
- **Instructor:** Vanessa Rivera
- **Term:** 2023-24 Fall Quarter

## Overview
In this project, you will add entirely new functionality to the virtual world in the form of a "world-changing event" that creates brand new entities to interact and change the world.

## Learning Objectives
In completing this assessment, you will be able to:
- Create and integrate new classes as extensions to an existing class hierarchy.
- Utilize code that interacts with external non-text, image data files.
- Write a function that performs real-time functionality through a user's mouse press.

## Instructions
### Task 1: New Entities
- **Description:** Design and implement *at least* two new concrete sub-types that extend your `Entity` class hierarchy. Some examples include a green "dude" that travels the world, randomly planting stumps, a "zombie" that seeks out and "zombifies" dudes and fairies through transformations, or a "dragon" that travels the land transforming Houses into "fire" entities and is attacked by Fairies.
- **Requirements:**
  - You must have already divided the Entity class into subclasses. You may not use the base Project 2 Entity class for this project.
  - Both entities must use entirely new graphics. You may not use any given image files.
    - Note: You must commit and push any new image files for your program to be properly graded.
  - Both entities must schedule and perform actions.
  - One entity must perform movement and this movement must be unique. For example, you should not make a new entity that simply moves toward trees and saplings like a dude or toward a stump like a fairy.
  - One entity must schedule and perform animations, having at least 2 image frames of animation.


### Task 2: World-Changing Event
- **Description:** You will add your entities to the world via a "world-changing event". This world-changing event will lead to your task 1 entities being created, background tiles changing, and at least one entity transformation. Clicking in the virtual world window will cause this event to occur, localized at the position of the mouse click. You will implement your world-changing event by modifying logic within the `VirtualWorld.mousePressed()` method.
- **Requirements:**
  - A world-changing event must occur with respect to the cursor position when you click inside the virtual world window.
  - Your world-changing event must create one of your new entities from task 1 within the world.
  - When the world-changing event occurs, at least 9 background tiles must be changed to at least one entirely new graphic. This change can be done around the mouse click area when the event occurs or can happen over time through the newly created entity. You may not use any given image files.
    - Note: You must commit and push any new image files for your program to be properly graded.
  - An entity must be transformed into another as a result of the world-changing event. You may change some entities when the mouse is clicked or your new entity may transform other entities as part of its behavior. Existing entities may be transformed, e.g. a Dude can be transformed into something new.
  - Your second entity must be created either when the world-changing event occurs or as a result of it. Your first entity may create or transform other entities into your second entity, for example.
  - Both entities must have actions scheduled.

### Task 3: Event Description and Instructions
- **Description:** In your Canvas submission, write a short description of what your world-changing event does and any instructions on how to perform it (e.g., click near a Dude, etc.). If I cannot determine how to properly execute your world-changing event, you may not receive full credit for your submission.

## Resources
- [The GNU Image Manipulation Program](https://www.gimp.org/): Can be used to modify and create new graphics.
- [OpenGameArt.org](https://opengameart.org/): Contains free, open source graphics intended for video games.
- [PImage Documentation](https://processing.org/reference/PImage.html): Documentation for the PImage class used in the virtual world program.
- [mousePressed() Documentation](https://processing.org/reference/mousePressed_.html): Documentation for the `mousePressed()` method used in the virtual world program.

## Submission Guidelines
- **Due Date(s):** December 8 at 11:59 PM
- **Submission Format(s):**
  - **GitHub:** Commit and push both code changes and new images added to the project.
  - **Canvas:** Screenshot of your updated GitHub repository (in .png, .bmp, .pdf, or .jpg format) and your world-changing event description.
- **Late Submission Policy:** Late submissions will **not** be accepted for this final project.

## Academic Integrity
> [!Warning]
>
> Submitting this assignment confirms that you did not use solutions or code from external, AI-generated, or peer sources.
>
> You also agree to have your code checked by standard plagiarism detection software.
>
> Violation will result in a grade of zero, a report to the University, and further potential action.


## Additional Notes
### The `imagelist` File
The `imagelist` file specifies key/value pairs of.

`VirtualWorld`'s `ImageStore imageStore` instance variable (which is passed to entities throughout the program).

For example, calling `imageStore.getImageList("dude")` will return a `List` of images associated with the key "dude" based on the following lines of the imagelist file:

```
dude images/dude0.png
dude images/dude1.png
dude images/dude2.png
dude images/dude3.png
```

A resultant list is typically passed directly to Entity constructors. This is where.

To add new images to the program, you may introduce new lines with new keys that point to new images in the `images` folder.
For example, if you were adding a two-frame animation for a "penguin" entity, you might add these lines (assuming the corresponding image files were also added `images`):
```
penguin images/penguin0.png
penguin images/penguin1.png
```

For best results, the images you use should match the size of the `VirtualWorld` grid tiles: 32 pixels by 32 pixels.


### The `Background` Class
The `WorldModel` class represents its background (the grass, flowers, etc.) as a 2D grid of `Background` objects.
Each `Background` object is associated with an image list from an `ImageStore`, similar to entities as described above.
This list is passed in when creating a new background object.

For example, creating a new `Background` object using, for example, `imageStore.getImageList("grass")` will create a background object that uses the following image from `imagelist`:
```
grass images/grass.png
```

You can add new images for backgrounds, just like for entities.
Note that backgrounds will only require a single image; they are not animated.

Be sure to look at `WorldModel.setBackgroundCell()`, which takes a `Background` object as a parameter.
This method is used to change the background grid using a `Background` object.

### Mouse Click Behavior
You will implement mouse click behavior by modifying the `VirtualWorld.mousePressed()` method.
As is, this method converts the mouse location to a world position as a `Point` object, and then prints information for any entities at that location.
You will change this functionality to interact with the world according to your design.
Within this method, you have access to the virtual world's world model and data through the `VirtualWorld` object's instance variables, e.g. `public WorldModel world`.

To see examples of adding new entities to the world, look at your existing functionalities.
Specifically, the "transform" method and "parse" methods provide examples.
Note that actions must be scheduled for newly created entities, or they will remain inactive.

You must also perform validation to ensure that the mouse press produces no errors.
Properly removing any present entities, ensuring positions are within bounds, etc., for example.

## Success Checklist
- [ ] Created at least two new concrete Entity sub-types.
  - [ ] Both sub-types schedule and execute actions.
  - [ ] At least one subclass performs unique movement.
  - [ ] Both sub-types use entirely new graphics.
    - [ ] At least one sub-type is animated with at least two images.
    - [ ] The new entity graphics are committed and pushed to GitHub.
- [ ] A mouse click creates a world-changing event.
  - [ ] At least one of your new entities is created with the mouse press.
  - [ ] At least 9 background tiles are changed to an entirely new graphic as a result of the event.
    - [ ] The new background graphic(s) are committed and pushed to GitHub.
  - [ ] Your second new entity is created as a result of the event.
  - [ ] At least one entity is transformed as a result of the event.
- [ ] Your Canvas submission describes both what your event does and instructions on how to perform it.
- [ ] Your Canvas submission includes a screenshot of your updated GitHub repository.