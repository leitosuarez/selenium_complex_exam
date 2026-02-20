# selenium_complex_exam
This project is a small automation framework built with Java, Selenium WebDriver, and JUnit 5. The main goal was to practice real-world automation by applying good practices such as the Page Object Model and parallel execution.

The project automates the login functionality of the SauceDemo website and validates different scenarios: empty login, username only, and valid login. User data is read from a CSV file to simulate more realistic test data instead of hardcoding everything.

To support parallel execution, I implemented WebDriver handling using JUnit’s @ParameterizedTest and @ValueSource, which allows iterating over each parameter and executing the tests for both browsers. This way, each execution runs with its own WebDriver instance, preventing conflicts when tests are executed on multiple browsers at the same time.

The tests can run on Edge and Firefox. Parallel configuration is controlled through the junit-platform.properties file, where the number of threads is limited to avoid opening too many browsers simultaneously and consuming excessive memory.

The goal of the project was not only to make the tests pass, but also to understand how to manage drivers in parallel environments, how to properly structure an automation framework, and how to scale it in the future by adding more browsers or additional test scenarios.

The project is configured to run with JUnit 5 and can be executed either directly from the IDE or from the command line using Maven.
