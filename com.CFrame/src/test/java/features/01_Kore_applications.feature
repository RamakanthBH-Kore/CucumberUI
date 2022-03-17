@Kore_applications
Feature: Kore Account applications
  I want to use this template for my feature file

  @Login_Kore @smoke
  Scenario: Login to Kore
    Then I wait for '2' seconds
    Given My WebApp 'Kore_applications' is open
     @Login_Kore @enter_username
  Scenario Outline: Login
    And I clear field 'username'
    And I enter '<usname>' in field 'username'
    And I click 'ContinueBtn'
    And I clear field 'password'
    And I enter encrypted data '<pwd>' in field 'password'
    And I click 'Login'
    And I wait for Page to Load
    And I wait '2' seconds for presence of element 'Kore_logo'
    Then I wait for '2' seconds
    And  I mouse over 'sreach'
    When I enter 'Travel Desk_test' in field 'sreach'
    When I click 'My_1st'
    Then I wait for Page to Load
    Then I wait for '10' seconds
    And I mouse over 'Summary_menu'
    And I click 'Summary_menu'
    Then I wait for '3' seconds
    Then I should see element 'mybotname' present on page 

 Examples:
	| usname |pwd|
	|vishnuprasath.ramanujam@kore.com|S29yZUAxMjM=|
	
  @Travel_Bot
  Scenario: Travel Bot
    #Then I should see element 'Tasks' present on page   
    Then I should see text 'Flight Details' present on page 
    Then I should see text 'Knowledge Graph' present on page 
    And I click 'Paths'
    Then I wait for Page to Load
    Then I should see element 'KG' present on page 
    Then I wait for '3' seconds
    When I enter 'cruise' in field 'searchkg'
    Then I click 'typ_cruise'
    Then I wait for '4' seconds
    And I should see element 'check_cruise' present on page
    #Then I should see text 'LMG Travel Booking Assist>cruise' present on page 
    And I click 'KG_back'
    Then I wait for Page to Load
    Then I wait for '10' seconds
    And  I mouse over 'Summary_menu'
    Then I click 'Summary_menu' 
    

  
  
 

  @clear_hashmp 
  Scenario: Clear HashMap
    And I clear hashmap