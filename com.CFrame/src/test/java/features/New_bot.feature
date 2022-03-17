
@New_bot @flow
Feature: create a new bot
	
	@login_newbot
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

  @create_new_bot
  Scenario: Creating New bot
  