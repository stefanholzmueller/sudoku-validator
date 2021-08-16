## Sudoku Validator

### Build
```
mvn compile assembly:single surefire-report:report
```
This will compile the project, create an executable JAR file, run the tests, and generate a test report.

### Run (Windows)
```
validate.bat <file>
```
There must be one command-line argument: the path to the file to validate.

### Tests

The test report can be found in `site/surefire-report.html`.
