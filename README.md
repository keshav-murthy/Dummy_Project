# Project_BarcodesINC
 Main barcodes project

 This repository contains Test automation for the Barcodes INC. It uses Selenium, via a local Chrome Driver or a Remote Driver that connects to a Selenium Grid.

## Running Java and Maven on host

To run the test on host you'll need to install Java (1.8+) and Maven. For this reason it's not recommended unless you know what you're doing. Once you've installed Java, Maven and possibly the Chrome Driver, run test as above.

## Running using your host Chrome installation

To run tests against your host Chrome Browser you'll need to download the latest 
[Chrome Driver](https://chromedriver.chromium.org/downloads) for your platform. If you're running on Linux or Mac, ensure the Driver is executable.  

## Running from root

To run the test from command prompt or terminal or any other root way possible just go to the project repository and execute it through command prompt and give the following instructions :-

	mvn test
	
## After results

After completion of the tests, just refresh the project repository for once, then you can verify the results through the various reports which can be located in the following :-
1.Extent report --> project repository/target/surefire-reports/extent report.html
2.Index report -->	project repository/target/surefire-reports/extent index.html
3.Emailable report --> project repository/target/surefire-reports/emailable-report.html

If any test cases are failed then we can view the screenshots related to those failed test cases also under the following location.
 
	project repository/screenshots

For further verification point of view we can also view all the logs related to the test steps under following location.

	project repository/application.logs	