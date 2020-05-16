# MkBills

Welcome in MkBills project. This project provide api in whitch we can with rest create new bill, display
multiple bills or just one bill and also we can delete bills. Also in application is provided simple-auth-factory
so client/consumer can create account (with system of confirmation email, every user need to confirm his email to be accepted by system),
login to system, so he can have access to his own bills. Every user of site can see only his bills!.
Application includes MySQL database, Spring Boot, JavaMailSender,Spring Seciurity and Test's(JUnit + Mockito) etc.

## Paths and explanation of folders (every path start's with bill-logic folder)
* `/src/main/java/com/matthewksc/billlogic/Controllers:` Folder provide RestController's and Controller's to catch request 
on specifics endpoints. Consume methods from Services folder.
* `src\main\java\com\matthewksc\billlogic\Dao:` Data Access Object folder to isolate accessing data from rest of logic
api(Repositories and Entity's).
* `src\main\java\com\matthewksc\billlogic\Exeptions:` Folder for custom exeptions for bills and users (Simple outputs).
  _//todo better outputs to user_
* `src\main\java\com\matthewksc\billlogic\security:` Security  folder to load user's and secure endpoints in api (simple-auth).
* `src\main\java\com\matthewksc\billlogic\Services:` Folder with main logic (methods for user's, bill's and javamailsender ).
* `src\main\resources\templates:` Simple front-end just to see how api is working.
  _//todo Angular as front_
* `src\test\java\com\matthewksc\billlogic\Services:` Simple testing with mockito and JUnit.
