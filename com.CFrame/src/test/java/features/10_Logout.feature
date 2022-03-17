@9.1 @Logout @Kore
Feature: To Logout 
  @Logout
  Scenario: Logout
  	Given I load page object 'Logout'
    Then I wait for Page to Load
    Then I should see element 'Profile' present on page
    And I click 'Profile'
    Then I wait for '2' seconds
    Then I click 'Logout'
    And I wait for Page to Load
    Then I wait for '10' seconds
    Then I should see text 'One platform for automating' present on page