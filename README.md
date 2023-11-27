# universities
Salma Ahmed Aly 900203182 Section 1 <br /> <br />
There are three different GUI interfaces, one for each user: Student, Admin, and Professor. You are prompted with a login, and if the credentials are correct, it redirects you to your home page.<br /> <br />
Link to chatgpt prompts: https://chat.openai.com/share/f4618743-3fcd-454f-bc34-171b8253598e
<br/><br/>
# Sprint 3

link to chatgpt prompts: https://chat.openai.com/share/677cd56e-56c2-4422-9064-95d39003ee30

## Refactoring and Enhancements

### MVC Pattern Implementation + Files:

- **Model:**
  - Separated the core functionality into distinct model classes representing different entities (e.g., `University`, `Student`, `Professor`).
  - Introduced methods for querying data within these model classes and inside the DataReader.

- **View:**
  - Each GUI page only contains the logic for the view.
  - I set the view for its corresponding controller so that I can add the data obtained from the model.

- **Controller:**
  - Introduced a controller class for each GUI class.
  - Implemented an `ActionListener` interface to handle button clicks, this was done via the switch statements and the setActionCommand I did in the view.
  - Writes to the files via the DataWriter

### Stream API Usage:

- **Filtering and Mapping:**
  - Utilized Java Stream API for filtering and mapping operations on collections.
  - Simplified code and made it more expressive.

### Exception Handling:

- **Try-Catch Blocks:**
  - Implemented try-catch blocks for exception handling, providing better control over potential errors.

### Method Overriding:

- **Overriding toString Method:**
  - Overrode the `toString` method in various classes to provide custom string representations.

### Regular Expressions (Regex):

- **Regex for ID Validation:**
  - Implemented a regular expression for validating student and professor IDs.

### ArrayList Usage:

- **ArrayList for Collection:**
  - Utilized `ArrayList` for storing and manipulating collections of objects.

### Improved Code Structure:

- **Code Modularity:**
  - Broke down large methods into smaller, more manageable ones.
  - Ensured consistent naming conventions for variables and methods.

### Consumer Functional Interface:

- **Use of Consumer Interface:**
  - Implemented the `Consumer` functional interface for handling events and updating UI components.

### General Coding Best Practices:

- **Consistent Formatting:**
  - Maintained consistent code formatting and indentation.
  - Followed Java coding conventions.
