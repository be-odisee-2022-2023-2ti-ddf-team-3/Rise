Feature: Add Person

As a CRM-responsible I want to add a person to the customer base
Just after adding the person, the details of the person should be shown
When the list is shown, the person first name and last name should be visible

Scenario: Add one person - see his details
  Given I am on the page where I can introduce a new person
  When I enter "Frank" in the voornaam field
  And I enter "Deboosere" in the familienaam field
  And I enter "frank@kmi.be" in the emailadres field
  And I enter "meerweer" in the paswoord field
  And I press on the Submit button
  Then I should see the following on the screen
  | label        | data         |
  | Voornaam:    | Frank        |
  | Familienaam: | Deboosere    |
  | E-mailadres: | frank@kmi.be |
  | Paswoord:    | meerweer     |
  And I close the browser

Scenario: Add one person - see her in the list
  Given I am on the page where I can introduce a new person
  When I enter "Elise" in the voornaam field
  And I enter "Mertens" in the familienaam field
  And I enter "elise@mertens.be" in the emailadres field
  And I enter "wta-finals" in the paswoord field
  And I press on the Submit button
  And I click the Lijst van alle personen Link
  Then I should see a list containing "Elise Mertens"
  And I close the browser