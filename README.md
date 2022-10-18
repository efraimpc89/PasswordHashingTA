
# Password Hashing API
The purpose of this repository is to test the Password Hashing API following the requirements described in JumpCloud's QA Assigment.
### Assumptions

- The assignment contains raw requirements and are not meant to be final, meaning they might contain inconsistencies/typos.
- PasswordHashing API can be executed locally, but it would get deployed to QA and Prod environments eventually.
- The API should follow the best practices and those would be considered as if they were requirements.
- API has intentional bugs that might hide other bugs.
- Test Automation might not contain all the test cases.

### Testing Sequence
- Requirement Analysis.
- Local Environment Setup.
- Exploratory testing.
- Test Case Creation.
- Manual Test Case Execution in Postman.
- Test Run Reporting.
- Defect Reporting.
- Test Automation Demo.

## Test Cases
The test case repository is on [test rails](https://efraimpc.testrail.io/), but here is a quick list all of them:

Last updated: Oct/17/2022
Total Test Cases: 23
| Section | ID  | Title                                                                    |
| ------- | --- | ------------------------------------------------------------------------ |
| Hash    | C1  | POST to /hash returns job identifier                                     |
| Hash    | C2  | POST to /hash returns 201 status code                                    |
| Hash    | C3  | POST to /hash with no body returns 400 status code                       |
| Hash    | C22 | POST to /hash with blank password returns 400 status code                |
| Hash    | C23 | POST to /hash with empty password returns 400 status code                |
| Hash    | C4  | POST to /hash response time is greater or equal to 5 seconds             |
| Hash    | C5  | POST to /hash returns 201 status code when request sent before shut down |
| Hash    | C6  | POST to /hash returns job identifier when request sent before shut down  |
| Hash    | C7  | POST to /hash after shut down return are not allowed                     |
| Hash    | C16 | GET to /hash returns 405 status code                                     |
| Hash    | C17 | PUT to /hash returns 405 status code                                     |
| Hash    | C18 | DELETE to /hash returns 405 status code                                  |
| Hash    | C8  | GET to /hash/{id} returns base64 encoded response                        |
| Hash    | C9  | GET to /hash/{id} returns 200 status code                                |
| Hash    | C10 | GET to /hash/{id} base64 decoded password hash is SHA512 encrypted       |
| Hash    | C15 | GET to /hash/{id} Non existent job id returns 404 status code            |
| Stats   | C11 | GET to /stats returns 200 status code                                    |
| Stats   | C12 | GET to /stats returns JSON content type header                           |
| Stats   | C13 | GET to /stats TotalRequests increment by 1 with each hash request        |
| Stats   | C14 | GET to /stats AverageTime is greater or equal to 5000 ms                 |
| Stats   | C19 | POST to /stats returns 405 status code                                   |
| Stats   | C20 | PUT to /stats returns 405 status code                                    |
| Stats   | C21 | DELETE to /stats returns 405 status code                                 |

[Here](https://docs.google.com/spreadsheets/d/1sZcv1Zpa-JPGugMtFQBQ_485Vj5Ybv8c/edit?usp=sharing&ouid=111040932277366908344&rtpof=true&sd=true)  you could also find a list of these **test cases with steps**.

## Postman Test Case Execution
An online **Postman Collection** to run these test cases locally can be found [here](https://www.postman.com/efraimpc/workspace/passwordhashing/collection/4547980-32b65350-5234-496c-a0f6-59ef9be330ae?action=share&creator=4547980).

**Requisites for Postman execution:**
- Install and import the collection to Desktop Postman Agent.
- Set PORT environment variable to 8088.
- Make sure you have your Password Hashing Server application running.

*IMPORTANT: Some of the test cases are a combination or a sequence of other test cases and might not be found in the collection as requests but they can be reproduced in postman flows.*

## Test Run Results and Defects Found
The test run results are a combination of manual and automated testing.

A **complete Test Run Execution with comments** can be found [here](https://docs.google.com/spreadsheets/d/17XBc9m2efwak6BuZV7IKYuuZPa3Loka5/edit?usp=sharing&ouid=111040932277366908344&rtpof=true&sd=true).
A detailed **Defect Report** can be found [here](https://docs.google.com/document/d/19OBAUYfhCkfAIS36N820D-0A5axtAvnVSo3AW-hVibY/edit?usp=sharing).
### Test Run Summary - 10/17/2022

```
	-Total Test Cases Executed: 23
		12 Passed	52% set to Passed
		11 Failed	48% set to Failed
	-Defects Reported: 7
		Low - 5
		Medium - 1
		High - 1
		Critical - 0
```

| Hash (16)                                                    |                                                                                                                                  |        |
| ------------------------------------------------------------ | -------------------------------------------------------------------------------------------------------------------------------- | ------ |
| Case ID                                                      | Title                                                                                                                            | Status |
| [C1](https://efraimpc.testrail.io/index.php?/cases/view/1)   | [POST to /hash returns job identifier](https://efraimpc.testrail.io/index.php?/tests/view/1)                                     | Passed |
| [C2](https://efraimpc.testrail.io/index.php?/cases/view/2)   | [POST to /hash returns 201 status code](https://efraimpc.testrail.io/index.php?/tests/view/2)                                    | Failed |
| [C3](https://efraimpc.testrail.io/index.php?/cases/view/3)   | [POST to /hash with no body returns 400 status code](https://efraimpc.testrail.io/index.php?/tests/view/3)                       | Passed |
| [C22](https://efraimpc.testrail.io/index.php?/cases/view/22) | [POST to /hash with blank password returns 400 status code](https://efraimpc.testrail.io/index.php?/tests/view/23)               | Failed |
| [C23](https://efraimpc.testrail.io/index.php?/cases/view/23) | [POST to /hash with empty password returns 400 status code](https://efraimpc.testrail.io/index.php?/tests/view/24)               | Failed |
| [C4](https://efraimpc.testrail.io/index.php?/cases/view/4)   | [POST to /hash response time is greater or equal to 5 seconds](https://efraimpc.testrail.io/index.php?/tests/view/4)             | Passed |
| [C5](https://efraimpc.testrail.io/index.php?/cases/view/5)   | [POST to /hash returns 201 status code when request sent before shut down](https://efraimpc.testrail.io/index.php?/tests/view/5) | Failed |
| [C6](https://efraimpc.testrail.io/index.php?/cases/view/6)   | [POST to /hash returns job identifier when request sent before shut down](https://efraimpc.testrail.io/index.php?/tests/view/6)  | Passed |
| [C7](https://efraimpc.testrail.io/index.php?/cases/view/7)   | [POST to /hash after shut down return are not allowed](https://efraimpc.testrail.io/index.php?/tests/view/7)                     | Passed |
| [C16](https://efraimpc.testrail.io/index.php?/cases/view/16) | [GET to /hash returns 405 status code](https://efraimpc.testrail.io/index.php?/tests/view/17)                                    | Passed |
| [C17](https://efraimpc.testrail.io/index.php?/cases/view/17) | [PUT to /hash returns 405 status code](https://efraimpc.testrail.io/index.php?/tests/view/18)                                    | Passed |
| [C18](https://efraimpc.testrail.io/index.php?/cases/view/18) | [DELETE to /hash returns 405 status code](https://efraimpc.testrail.io/index.php?/tests/view/19)                                 | Passed |
| [C8](https://efraimpc.testrail.io/index.php?/cases/view/8)   | [GET to /hash/{id} returns base64 encoded response](https://efraimpc.testrail.io/index.php?/tests/view/8)                        | Passed |
| [C9](https://efraimpc.testrail.io/index.php?/cases/view/9)   | [GET to /hash/{id} returns 200 status code](https://efraimpc.testrail.io/index.php?/tests/view/9)                                | Passed |
| [C10](https://efraimpc.testrail.io/index.php?/cases/view/10) | [GET to /hash/{id} base64 decoded password hash is SHA512 encrypted](https://efraimpc.testrail.io/index.php?/tests/view/10)      | Failed |
| [C15](https://efraimpc.testrail.io/index.php?/cases/view/15) | [GET to /hash/{id} Non existent job id returns 404 status code](https://efraimpc.testrail.io/index.php?/tests/view/16)           | Failed |
| Stats (7)                                                    |                                                                                                                                  |        |
| Case ID                                                      | Title                                                                                                                            | Status |
| [C11](https://efraimpc.testrail.io/index.php?/cases/view/11) | [GET to /stats returns 200 status code](https://efraimpc.testrail.io/index.php?/tests/view/11)                                   | Passed |
| [C12](https://efraimpc.testrail.io/index.php?/cases/view/12) | [GET to /stats returns JSON content type header](https://efraimpc.testrail.io/index.php?/tests/view/12)                          | Failed |
| [C13](https://efraimpc.testrail.io/index.php?/cases/view/13) | [GET to /stats TotalRequests increment by 1 with each hash request](https://efraimpc.testrail.io/index.php?/tests/view/13)       | Passed |
| [C14](https://efraimpc.testrail.io/index.php?/cases/view/14) | [GET to /stats AverageTime is greater or equal to 5000 ms](https://efraimpc.testrail.io/index.php?/tests/view/14)                | Failed |
| [C19](https://efraimpc.testrail.io/index.php?/cases/view/19) | [POST to /stats returns 405 status code](https://efraimpc.testrail.io/index.php?/tests/view/20)                                  | Failed |
| [C20](https://efraimpc.testrail.io/index.php?/cases/view/20) | [PUT to /stats returns 405 status code](https://efraimpc.testrail.io/index.php?/tests/view/21)                                   | Failed |
| [C21](https://efraimpc.testrail.io/index.php?/cases/view/21) | [DELETE to /stats returns 405 status code](https://efraimpc.testrail.io/index.php?/tests/view/22)                                | Failed |


## Test Automation Project Structure

The project it's following the Service Object design pattern and its following a four package structure to make the code easy to understand and as reusable as possible.

- **Helpers**: Similar to Page Object Model, Helpers package contains the different service objects with their corresponding properties and functionality required to execute test layer.
- **Models**: Contains the POJOs required for test scenarios.
- **Tests**: Contains the tests divided by features and endpoints.
- **Utils**: Auxiliar classes to create personalized exceptions, annotations, constants and initialize local server.

There is an extra folder for resources which contains the properties files for the corresponding environments.
```
passwordHashingTA
	src
	   |-- test
	   |   |-- java
	   |   |   |-- com
	   |   |   |   |-- efraim
	   |   |   |   |   |-- phta
	   |   |   |   |   |   |-- helpers
	   |   |   |   |   |   |   |-- BaseServiceHelper.java
	   |   |   |   |   |   |   |-- HashServiceHelper.java
	   |   |   |   |   |   |   |-- StatsServiceHelper.java
	   |   |   |   |   |   |-- models
	   |   |   |   |   |   |   |-- Password.java
	   |   |   |   |   |   |   |-- Stats.java
	   |   |   |   |   |   |-- tests
	   |   |   |   |   |   |   |-- hash
	   |   |   |   |   |   |   |   |-- DELETE_Hash_Test.java
	   |   |   |   |   |   |   |   |-- GET_HashId_Test.java
	   |   |   |   |   |   |   |   |-- GET_Hash_Test.java
	   |   |   |   |   |   |   |   |-- POST_Hash_Test.java
	   |   |   |   |   |   |   |   |-- PUT_Hash_Test.java
	   |   |   |   |   |   |   |-- stats
	   |   |   |   |   |   |   |   |-- DELETE_Stats.java
	   |   |   |   |   |   |   |   |-- GET_Stats.java
	   |   |   |   |   |   |   |   |-- POST_Stats.java
	   |   |   |   |   |   |   |   |-- PUT_Stats.java
	   |   |   |   |   |   |-- utils
	   |   |   |   |   |   |   |-- constants
	   |   |   |   |   |   |   |   |-- Constants.java
	   |   |   |   |   |   |   |   |-- Endpoints.java
	   |   |   |   |   |   |   |   |-- Regex.java
	   |   |   |   |   |   |   |-- exceptions
	   |   |   |   |   |   |   |   |-- TestPrerequisiteException.java
	   |   |   |   |   |   |   |-- other
	   |   |   |   |   |   |   |   |-- OpenLocalHashServer.java
	   |   |   |   |   |   |   |   |-- ResourceBundleUtil.java
	   |   |   |   |   |   |   |   |-- TestCaseInformation.java
	   |   |-- resources
	   |   |   |-- environment
	   |   |   |   |-- prod.properties
	   |   |   |   |-- qa.properties
	   |   |   |   |-- local.properties

```

## Pre-requisites (Windows)

1. Install latest [Java JDK 19](https://www.oracle.com/java/technologies/downloads/#java19)

2. Download [Maven](https://maven.apache.org/download.cgi)  and add the bin folder to your PATH environment variable and then open your terminal and make sure you can run:
   >mvn -version

   [Maven install - Video Guide](https://www.youtube.com/watch?v=oTRmukUvB50)

3. Download and install [IntelliJ IDE](https://code.visualstudio.com/)
4. [Install Git](https://git-scm.com/downloads)
5. Set PORT system environment variable to 8088.

## How to run
Open Git bash and clone github repository:

		 git clone https://github.com/efraimpc89/passwordHashingTA.git
**IntelliJ**

1. Open *IntelliJ*
2. Import *passwordHashingTA* as maven project
3. Make sure it downloads all dependencies and builds correctly
4. Right-click over *src/test/java/com.efraim.phta.test* package
5. Click on run tests in "com.efraim.phta.test"

**Command Line**
1. Open windows command line
2. Navigate to *passwordHashingTA* folder and execute following command:

   >mvn test


## Contact

If you have any questions, feel free to send me an email:
efraimpc89@gmail.com
