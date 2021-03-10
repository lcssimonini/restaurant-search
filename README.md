[![Build Status](https://travis-ci.org/lcssimonini/restaurant-search.svg?branch=main)](https://travis-ci.org/lcssimonini/restaurant-search)
[![codecov](https://codecov.io/gh/lcssimonini/restaurant-search/branch/main/graph/badge.svg?token=E6NB6BGJJL)](https://codecov.io/gh/lcssimonini/restaurant-search)

# Restaurant search application

## Running the application

* Having Docker installed on your machine, run the run.sh script, it will
  1. Build the application, generating the .jar file;
  2. Build the docker image described on the Dockerfile;
  3. Run the application, printing on the terminal the URL to access the swagger UI with the API documentation;

### Data assumptions

* Numbers are all integers;
* Spicy Palace record is incorrect, name was corrected for this solution;

### General considerations

* This solution was designed for a test, it does not cover aspects that are necessary for production ready applications such as:
    * monitoring;
    * log search;
    * alarms;
    * configuration management;
    * integrated and automated delivery management;

