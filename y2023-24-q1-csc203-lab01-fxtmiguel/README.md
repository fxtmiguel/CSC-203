[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-24ddc0f5d75046c5622901739e7c5dd533143b0c8e959d652212380cedb1ea36.svg)](https://classroom.github.com/a/UWs5NQDC)
# Lab 1

## Instructions

Complete **all** the following parts.

## Additional Resources

- **Java String Methods:** [String (Java SE 20 & JDK 20)](https://docs.oracle.com/en/java/javase/20/docs/api/java.base/java/lang/String.html#method-summary)
- **Java List Methods:** [List (Java SE 20 & JDK 20)](https://docs.oracle.com/en/java/javase/20/docs/api/java.base/java/util/List.html#method-summary)

## Part 1: Installing IntelliJ IDEA

In this part, you will install IntelliJ IDEA on your system. IntelliJ IDEA is popular Java IDE. Though you can write Java code using a basic text-editor, it is highly recommended to work in IntelliJ IDEA for this course.

Complete the following steps:

1. In a web browser, navigate to [https://www.jetbrains.com/edu-products/download/#section=idea](https://www.jetbrains.com/edu-products/download/#section=idea).
2. There, ensure that the correct operating system is selected, i.e., `.exe` for Windows, `.dmg` for macOS, etc., then download the installer.
   * ⚠️ On macOS, you will need to select the `.dmg` appropriate for your system: Intel or Apple Silicon. If you install IntelliJ IDEA, but it fails to run, try the other version.
3. When your download is complete, run the installer, using the default options for each step.

Once finished, IntelliJ IDEA should now be installed on your system and can be run.

## Part 2: Installing Git

In this part, you will install Git on your system. Git is **version control** software, it allows you to easily backup changes to your code files over time and distribute them online. To submit assignments in this course, you will use Git to **commit**, or save, your code then **push**, or upload, it to GitHub, an online platform for managing Git-based projects.

Complete the following steps:

1. Open the IntelliJ IDEA application on your system.
2. In the startup window, press the "Get from VCS" button.
3. Aligned with the message "Git is not installed", click the text "Download and Install".
   * ⚠️ On macOS, this may take a *significant* amount of time to download and install, be prepared.
   * 💡 If you do not see this message, Git is already installed, and you may proceed to Part 3.
4. Once the Git has been downloaded and installed, close IntelliJ IDEA.

Once finished, Git should now be installed on your system and can be used within IntelliJ IDEA.

## Part 3: Completing the Lab Assignment

In this part, you will acquire your first **lab assignment repository**. For each lab assignment, you will acquire a **repository**, or file set, from GitHub to work with in IntelliJ IDEA in order to complete small programming tasks. When you've completed the assignment, you will submit it back to GitHub.

⚠️ If you have not done so already, please complete the "GitHub Account" task as the next steps require your GitHub account.

### Part 3.1: Setting up Your Lab Repository on GitHub

Complete the following steps:

1. In a web browser, navigate to the GitHub invitation link for this lab assignment.
2. There, press the "Accept this assignment" link.
3. Wait several moments before refreshing the page, then click the displayed link.

You should now be presented with your Lab Codebook repository page on GitHub.com. This page contains your most recently submitted Lab Codebook, or in this case the initial Lab Codebook. 🗒️ You will take screenshots of this webpage for submission of this and future lab assignments.

On this page, click the green "Code" button ensure that "Local" and "HTTPS" are highlighted, then click the copy button to copy your unique URL that has the format: `https://github.com/username/repository.git`.


### Part 3.2: Opening the Lab Assignment Repository in IntelliJ IDEA

Open IntelliJ IDEA and complete the following steps:

1. Click the "Get from VCS" button.
2. In the URL field, paste the URL you copied from GitHub.
3. Click the "Clone" button.

Once finished, your lab assignment repository should be opened within IntelliJ IDEA. Take a moment to navigate the included files and folders in the Project pane located on the left of the IntelliJ IDEA window.

⚠️ Before continuing, open the `src/Lab01.java` file. You will see a "Project JDK is not defined" warning. Click the "Setup SDK" option, then click "Download JDK...". In the dialog that opens, the default option for Oracle OpenJDK version 20 should be selected. Press "Download". to download the Java Development Kit (JDK). A progress bar will be displayed at the bottom of the IntelliJ IDEA window. Once complete, you will be able to run Java programs in this repository.

🗒️ You will need to do this *every* time you clone a repository.

### Part 3.3: Completing the Lab 1 Assignment

For this assignment, you will rewrite an existing Python program as an equivalent Java program.

The Python program in `lab01.py` contains various operations. In `src/Lab01.java`, rewrite each line of Python code, including conditionals, loops, etc. as its Java counterpart.You will need to write a main function in addition to any other function(s) from the original program. 💡 The Module 1 reading and additional resources listed in this document contain examples of all relevant code.

For completion, the output of your Java program should be identical to the Python program, including all whitespace (tabs, spaces, new lines, etc.). This expected output is given in the `output.txt` file.

### Part 3.4: Saving and Submitting Your Assignment

IntelliJ IDEA saves changes to your code automatically. However, it **does not** upload your changes to GitHub automatically.

In order to submit changes to GitHub and earn a grade for your assignment, you must make a commit, or checkpoint, of your work and then push, or upload, your changes to GitHub. To do so, complete the following steps:

1. Open the Commit window by pressing the button on the left side of the IntelliJ IDEA window.
2. Ensure that the file you changed, `src/Lab01.java`, is checked.
3. Type a message in the text box containing the text "Commit Message". This message can be *anything*, but try to be descriptive. Something like "Completed lab 1 assignment" would be reasonable.
4. Press "Commit and Push". A secondary dialog will open, press the Push button.
   - ⚠️ If you accidentally press the Commit button instead of the "Commit and Push" button, you can push your project by pressing "main" at the top of the IntelliJ IDEA window and selecting "Push" to open the dialog in order to push your project.
5. Once complete, navigate back to your GitHub repository in a web-browser. If you successfully completed the assignment, you should see a green check mark. Take a screenshot of this page and submit it to Canvas.

🗒️ For every future lab assignment, you will: (1) set up a new repository specific to that assignment, (2) load it using IntelliJ IDEA and Set up the SDK, (3) complete it, (4) commit and push changes to GitHub, and (5) finally upload a screenshot confirming your submission to Canvas.

Once you've completed the above steps, you have finished this week's lab assignment. If you are experiencing any issues using IntelliJ IDEA or GitHub, please let me know as soon as possible so that I may help you.