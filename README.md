# Selenium TestNG Framework

## Overview

This project is a Test Automation framework using Selenium WebDriver and TestNG. The framework is designed to test web-based applications with a structured approach, separating configurations, base classes, and test cases to enhance modularity and maintainability.

## Prerequisites

- **Java Development Kit (JDK)**: Version 8 or higher
- **Maven**: For managing dependencies
- **Selenium WebDriver**: The project uses Chrome, Firefox, and Internet Explorer drivers for cross-browser testing.
- **TestNG**: For test management and assertions.
- **Browser Drivers**: Download respective drivers for Chrome, Firefox, or Internet Explorer based on your testing needs.
- **IDE**: Preferably IntelliJ IDEA or Eclipse.

## Project Structure

```
src/test/
│
├── java/
│   ├── entrata/
│   │   ├── baseclass/
│   │   │   └── BaseClass.java
│   │   └── testcases/
│   │       ├── FooterLinkCount.java
│   │       └── LoginToEntrata.java
│
├── resources/
│   ├── properties/
│   │   ├── Config.properties
│   │   └── OR.properties
```

### 1. **BaseClass.java** (located in `entrata.baseclass`)

This class contains the setup and teardown methods for initializing the WebDriver and handling browser interactions. It uses:

- **Selenium WebDriver**: To interact with browsers (Chrome, Firefox, and Internet Explorer).
- **Properties files**: For managing configuration data and Object Repositories (OR).
- **WebDriverWait**: (commented in the current implementation) For handling explicit waits.
- **SetUp Method**:
    - Reads browser type and URL from `Config.properties`.
    - Initializes the WebDriver based on the browser mentioned in `Config.properties`.
    - Loads the URL and maximizes the window.
    - Sets implicit wait times for WebDriver.

- **isElementPresent Method**: Utility method to verify the presence of elements in the DOM.

- **tearDown Method**: Closes the WebDriver instance after each test or suite execution.

### 2. **FooterLinkCount.java** (located in `entrata.testcases`)

This test case verifies the link count of various sub-sections in the footer of the website.

- It scrolls to the footer section using `JavascriptExecutor`.
- The test compares the actual count of footer links with predefined expected counts for each section.
- Asserts whether the actual number of links in each section matches the expected count.
- Example sections include: "Property Management," "Marketing & Leasing," etc.

### 3. **LoginToEntrata.java** (located in `entrata.testcases`)

This class contains multiple test cases:

- **LoginToEntrata**:
    - Fetches the current URL and title to verify basic navigation to the Entrata page.

- **mouseHover**:
    - Performs a hover action on multiple categories like "Products," "Solutions," and "Resources."
    - Validates the number of items shown in the dropdown menu for each category.

- **watchDemo**:
    - Clicks on the "Watch Demo" button.
    - Switches to a new tab, enters demo request form details (like first name, last name, etc.), and verifies dropdown selections.
    - Switches back to the parent window after completing actions in the new tab.

## Properties Files

1. **Config.properties**:
    - Stores environment-specific data such as the URL, browser name, implicit wait times, and form field values like first name, last name, email, company name, and phone number.

2. **OR.properties**:
    - Serves as the Object Repository, storing locators for various UI elements (CSS selectors, XPath, etc.) used in the test cases.

### Example Properties

**Config.properties**:
```
browser=chrome
url=https://example.com
implicityWait=10
fn=John
ln=Doe
email=john.doe@example.com
cn=Example Company
no=1234567890
jobTitle=Tester
```

**OR.properties**:
```
firstName=input[name='first_name']
lastName=input[name='last_name']
email=input[name='email']
companyName=input[name='company_name']
phoneNumber=input[name='phone']
jobTitle=input[name='job_title']
```

## How to Execute Tests

1. Clone this repository.
2. Ensure the required dependencies are listed in the `pom.xml` (Maven users) or have the appropriate JARs in the classpath.
3. Update the `Config.properties` file with the desired browser and URL.
4. Download the respective WebDriver binaries and set their paths in the system.
5. Execute the test cases via TestNG:
    - From the command line:
      ```bash
      mvn test
      ```
    - Through IDE: Right-click on the test case and run as TestNG test.

## Future Enhancements

- **ExcelReader**: Add Excel reading capability to drive test data from external sheets.
- **Page Object Model (POM)**: Implement the Page Object Model to enhance reusability and maintainability.
- **WebDriverWait**: Utilize explicit waits where necessary for handling dynamic content.
- **Logger Integration**: Integrate logging using `Log4j` or another logging library for better traceability of test execution.
