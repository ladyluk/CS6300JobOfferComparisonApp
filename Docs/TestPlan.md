# Test Plan

**Author**: Rijad Logo

## 1 Testing Strategy

### 1.1 Overall strategy

The team will focus entirely on testing the functional requirements of the system. We will not be preforming any non-functional testing such as performance testing. The strategy we will use is as follows:

**Unit Tests**

The team will implement a number of Unit tests to cover the methods used within the project.  The unit tests will make up the majority of our test cases. The unit tests will verify that the individual methods are behaving as expected. They will be implemented in a way to verify desirable behaviors, but will also verify how the methods behave and handle undesirable behaviors and restriction. The example of this would be how does the "addJobOffer()" method behave when the "Wellness Fund" attribute is greater then 5000 or less then 0. We will try to have high test coverage with the unit test, and every team member that implements a certain method will make sure that it is covered with appropriate unit tests. However, the team will not cover each and every unit (method) with unit tests, since some methods (like get & set methods) do not require testing. We will put more focus on more complex methods with calculations and restrictions (ex. "addJovOffer()" or "sortJobOffers()").  The unit tests will not use real data nor the database. The data will be mocked.

**Integration Tests**

We will attempt to cover the most crucial behaviors and activities within out system with the automated UI Integration tests. Unlike the unit tests which tested individual methods, our integration tests will test how these methods are working together with the UI to get some expected result. The tests will open and navigate the app, preforming certain actions and verifying the expected result. Another difference is that these tests will not mock any data or methods that are not being tested as will the unit tests. This means that our integration tests will not only test how different methods are behaving together and the UI, but will also be testing the database.   

**Regression Testing**

The team will not execute nor create any regression tests. Even though we understand that regression testing is highly important and crucial for systems to ensure that new features do not cause problems to existing functionalities, we find that for the purpose of the project, they are not necessary. We do not have existing functionalities that we should verify are working as expected. However, we also know that any automated test can be promoted to regression and included in regression testing. This implies that all of the automated tests that we create can potentially be used for regression testing. 

**System Testing**

Towards the end of the implementation, the team will start performing manual system tests. This means that we will test how the entire system works as a whole and how all activities, classes and methods work together to meet the requirements


### 1.2 Test Selection

As mentioned in the overview the team will attempt to do the following:

 - Unit tests
 - Automated UI Integration tests
 - Manual System testing
 - Inspection
 
**Note:** The team considers unit tests as a form of the white-box testing. Unit tests will only look at the code and cover the code behavior. Furthermore, the automated integration tests are a part of the black-box testing. Here, we do not test, or even look at the code. We only verify that the different parts of the system are acting according to the requirements. Furthermore, team will conduct white-box testing while preforming system tests.

### 1.3 Adequacy Criterion
The team intends to assess the quality of the test cases based off the coverage of the system by those test cases and the pass rate of the tests. The team also intends to cover roughly 70% to 80% of the system with unit and integration tests and aim for a 100% pass rate with every execution. 


### 1.4 Bug Tracking
The team will track issues and bugs using GitHub. Each and every issue that is found by the team members and/or failed tests will be reported within the GitHub. The issues can further be labeled as Bugs. The Git can be used to see who caused the issue and GitHub enables mentioning of team members under created issue to draw their attention. Furthermore, GitHub enables a variety of other helpful features that the team will utilize, such as organizing and prioritizing the reported issues based off of severity. Once the issues are fixed, the issues will be closed. 

### 1.5 Technology
The team will use the following technologies for testing:

 - **JUnit**  *- Will be used for creating the Unit tests* 
 - **Mockito** *- Will be used with while creating Unit tests for mocking different data and methods* 
 - **MockitoKotlin** *- Will be used with "Mockito"* 
 - **Espresso** *- Will be used to create automated Integration tests* 

## 2 Test Cases


| Test Case | Purpose | Steps | Expected Result | Actual Result | Status (Pass/Fail) |
|--|--|--|--|--|--|
| Test_001_AddJob | Verify that a job can be added successfully |Open the app, click on the + icon, populate required information, click on the "Add Job" button   | The message "Success" is displayed | Job is added successfully | Pass  |
| Test_002_AddJob_MisingFields | Verify that a job cannot be added if fields not populated | Open the app, click on the + icon, do **not** populate all information, click on the "Add Job" button   | The fields that are not populated get emphasized and Job is not added | Job is not added and missing fields are emphasized | Pass |
| Test_003_AddJob_WellnessFund_IncorrectValue | Verify that a job cannot be added if Wellness Fund value is greater than 5000 |Open the app, click on the + icon, populate Wellness Fund with value "6500", populate rest of the fields, click on the "Add Job" button   | The message "Error - Wellness Fund cannot be gerater than 5000 | Job is not added and error message is displayed | Pass |
| Test_004_Homepage_VerifyJobs | Verify that top 5 jobs are displayed on home page | Open the app   | The top 5 rated jobs are displayed in order from best to worst | Top 5 jobs are displayed in order | Pass |
| Test_005_Homepage_CurrentJob | Verify that current job is displayed on the Homepage |Open the app   | The current job is displayed in the bottom section of the app | Current job is displayed if current job exists | Pass |
| Test_006_Homepage_AddCurrentJob | Verify that current job can be added successfully |Open the app, click on the edit icon under the "Currrent Job" section, populate all of the fields, click on the "Add Current Job" button   | The message "Current Job is added successfully" is displayed  | Current job is added  | Pass |
| Test_007_Homepage_UpdateCurrentJob | Verify that current job can be updated successfully |Open the app, click on the edit icon under the "Currrent Job" section, update all of the fields, click on the "Update Current Job" button   | The message "Current Job is updated successfully" is displayed  | Current Job is updated | Pass |
| Test_008_CompareTwoJobOffers | Verify that Job Offers can be compared |Open the app, click on the compare icon under Job Offers section, select 2 jobs, click on the compare icon | The user is navigated to the new screen showing all of the Job attributes of the selected Job Offers   | The jobs are compared successfully | Pass |
| Test_009_CompareMoreThanTwoJobOffers | Verify that Job Offers cannot be compared if more than two are selected |Open the app, click on the compare icon under Job Offers section, select 3 jobs, click on the compare icon | The message "Error: Please select exactly 2 jobs to compare"   | The error message is displayed | Pass |
| Test_010_Homepage_AddNewJobOffer | Verify that Job Offers can be added through Homepage |Open the app, click on the plus icon under the "Job Offers" section, populate all of the fields, click on the "Add Job Offer" button | The message "Job Offer is added successfully" is displayed"   | The job offer is added successfully | Pass |
| Test_011_JobOfferList_AddNewJobOffer | Verify that Job Offers can be added through Job Offer List screen |Open the app, click on the compare button under the "Job Offer" section click on the plus icon, populate all of the fields, click on the "Add Job Offer" button | The message "Job Offer is added successfully" is displayed"   | The job offer is added successfully | Pass |
| Test_012_JobOfferList_AddNewJobOffer | Verify that added Job Offers are immediately displayed on the Job Offer List screen |Open the app, click on the compare button under the "Job Offer" section click on the plus icon, populate all of the fields, click on the "Add Job Offer" button, navigate back to the "Job Offer List" screen | The newly added Job Offer is visible on the screen   | The newly added job is displayed | Pass |
| Test_013_CompareOnlyOneJob | Verify that Job Offer cannot be compared if only one is selected |Open the app, click on the compare icon under Job Offers section, select 1 jobs, click on the compare icon | The message "Error: Please select exactly 2 jobs to compare"   | The error message is displayed | Pass |
| Test_014_NoJobOffers | Verify that Job List cannot be opened if there are no Job Offers |Open the app, click on the compare icon under Job Offers section | The message "Add at least one Job Offer to open the Job List" appears   | Job List cannot be opened without Job Offers | Pass |
| Test_015_CompareJobToJobOffer | Verify that Job Offer can be compared with the Current Job |Open the app, click on the compare icon under Job Offers section, select one Job Offer and select The current Job (displayed in green) | The user is navigated to the new screen showing all of the Job attributes of the selected Job Offer and Current Job| The jobs are compared successfully | Pass |
