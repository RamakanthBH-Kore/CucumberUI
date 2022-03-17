@Kore @Talk_facebook @9.1
Feature: To chat from Fasebook channel
	@ignore
  Scenario: Login to kore botbuilder
    Given My WebApp 'Facebook' is open
    Then I wait for '2' seconds
    And I clear field 'username'
    And I enter 'vishnuprasath.ramanujam@kore.com' in field 'username'
    And I click 'ContinueBtn'
    And I clear field 'password'
    And I enter encrypted data 'S29yZUAxMjM=' in field 'password'
    And I click 'Login'
    And I wait for Page to Load

  @Land_on_FB
  Scenario: To land on Channel page
    Given I load page object 'Facebook'
    When I click 'Kore_logo'
    Then I wait for '5' seconds
    And I mouse over 'sreach'
    When I enter 'My 1st bot' in field 'sreach'
    When I click 'My_bot'
    Then I wait for Page to Load
    And I wait '3' secs for Element 'Deploy_menu' be clickable
    Then I mouse over 'Deploy_menu'
    When I click 'Deploy_menu'
    Then I wait for '2' seconds
    And I click 'Channel'
    Then I wait for '2' seconds
    And I enter 'facebook messenger' in field 'CH_Search'
    Then I mouse over 'facebook_channel'
    And I click 'FB_Test'
    Then I wait for '5' seconds
    And I switch to window with title 'Messenger'
    Then I wait for '2' seconds
    When I enter '' in field 'FB_user'
    And I wait for '1' seconds
    When I enter encrypted data '' in field 'FB_pass'
    And I wait for '1' seconds
    Then I click 'FB_Login'
    And I wait for '5' seconds

  @Chat_from_FB
  Scenario: Chat from FB page
    Given I type 'Hi' in field 'FB_chat' and click enter
    And I wait for '2' seconds
    And I type 'Help' in field 'FB_chat' and click enter
    Then I wait for '2' seconds
    Then I compare responces message 'Get me the temperature' from Channel type 'FB'
    And I wait for '3' seconds
    Then I type 'Get me the temperature' in field 'FB_chat' and click enter
    And I wait for '2' seconds
    Then I type 'bye' in field 'FB_chat' and click enter
    
    @close_FB
  Scenario: close FB page  
    Given I close Tab
    And I switch back to default content
    And I wait for '2' seconds
