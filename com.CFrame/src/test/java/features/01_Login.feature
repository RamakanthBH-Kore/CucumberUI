@9.1 @Kore @login
Feature: Login Functionality


	Scenario: Login to kore botbuilder
		Given My WebApp 'Kore_applications' is open
	  Then I wait for '2' seconds
    And I clear field 'username'
    And I enter 'vishnuprasath.ramanujam@kore.com' in field 'username'
    And I click 'ContinueBtn'
    And I clear field 'password'
    And I enter encrypted data 'S29yZUAxMjM=' in field 'password'
    And I click 'Login'
    And I wait for Page to Load
    And I wait '2' seconds for presence of element 'Kore_logo'
  
  Scenario: Search for Bot
    Then I wait for '2' seconds
    And  I mouse over 'sreach'
    When I enter 'Travel Desk_test' in field 'sreach'
    When I click 'My_1st'
    Then I wait for Page to Load
    Then I wait for '15' seconds
    And I mouse over 'Summary_menu'
    And I click 'Summary_menu'
