Feature: Add Demo

  As a Marketeer I want to add a demo
  Just after adding the demo, the details of the demo should be shown
  After this, the demo should be visible in the agenda


  Scenario: Add a demo - see its details
    Given the marketeer am on the page where I can schedule a demo
    When the marketeer enter "20/02/24" in the date field
    And the marketeer enter "karjesSchool" in the schoolnaam field
    And the marketeer enter "info@karjesschool.be" in the emailadres field
    And the marketeer press on the Submit button
    Then the marketeer should see the following on the screen
      | label        | data           |
      | Date:        | 20/02/2024           |
      | schoolnaam:  | karjesschool          |
      | E-mailadres: | info@karjesschooler.be |
    And the marketeer close the browser

  Scenario: Add a demo - see its details
    Given the marketeer am on the page where I can schedule a demo
    When the marketeer enter "21/02/24" in the date field
    And the marketeer enter "openschool" in the schoolnaam field
    And the marketeer enter "info@openschool.be" in the emailadres field
    And the marketeer press on the Submit button
    And the marketeer click the Lijst van alle demo Link
    Then the marketeer should see a list containing "openschool"
    And the marketeer close the browser



  Scenario: Prevent scheduling demos during holidays
    Given the marketeer is logged into the application
    When the marketeer tries to schedule a demo in the weekend
    Then the application should not allow the scheduling
    And display an error message saying planning demo's in the weekends is not possible



  Scenario: Avoid Scheduling Demos on Weekends
    Given the user is a marketeer
    When the user attempts to schedule a demo on a weekend
    Then the system should prevent the demo from being scheduled
    And inform the user that demos cannot be scheduled on weekends