# UI and API tests
## Run tests with the commands:
* mvn clean test -Papi-regression - for running all API tests
* mvn clean test -Progression -Dbrowser=chrome -Dheadless=false - run all tests in Chrome
* mvn clean test -Progression -Dbrowser=chrome -Dheadless=true - run all tests in Chrome headless mode
* mvn clean test -Progression -Dbrowser=firefox -Dheadless=false - run all tests in Firefox
## Run the commands to get Allure report
allure generate target/allure-results
allure serve target/allure-results

### Test suits
* -Plogin - login checks
* -Pproject - project check
* -Ptask - task check
* -Papi-regression - all API tests

mvn clean test -Pproject -Dbrowser=chrome -Dheadless=false