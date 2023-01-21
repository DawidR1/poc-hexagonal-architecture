This project presents the implementation of Hexagonal and Clean Architecture concepts. It is worth noting that Hexagonal Architecture can be implemented in various ways and should be matched with the specific requirements. Additionally, this project includes elements from both Hexagonal and Clean Architecture and should not be treated as their representation.

## Project structure

The project is divided into 4 modules:

- infrastructure
- domain
- application
- common

### Domain
The module contains domain objects with business rules and domain-specific operations.

Example: Exam domain object containing exam-related properties (tasks, questions and answers) and methods (submitCompletedExam).

This is the most inner layer.

### Application
The module contains application-specific business rules. It contains Use Cases that orchestrate operations between different domain objects and provide some validation or other application-related operation. It also contains ports, commands or query models.

Example: SubmitExamUseCase class, which adds some validation and orchestrates operations between the Student and Exam domain objects.

The module depends on the domain module.

### Infrastructure
The module includes adapters that provide integration with infrastructure such as databases, UI or Rest endpoints.

Example: JpaAdapter class that persists an Exam domain object properties to the database using Spring Data JPA.

The module depends on the application module and is the outermost layer.

The flow of submiting exam:
![seq-diagram (2)](https://user-images.githubusercontent.com/44610628/213872670-21b481ae-86b8-495d-b025-b1c9908c2922.png)


Dependency direction:

![blok-diagram](https://user-images.githubusercontent.com/44610628/213873422-40754668-1378-4454-9501-05eb70a3add5.jpg)

