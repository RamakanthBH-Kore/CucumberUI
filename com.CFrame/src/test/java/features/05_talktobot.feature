@Talk_with_bot @Kore @9.1
Feature: Talk to Bot

  @9.1
  Scenario: Talk to Bot
    Given I load page object 'Talk_to_Bot'
    And I click 'Talk2Bot'
    And I wait for '2' seconds

  @9.1
  Scenario: Chat with Bot
    And I switch to window with title ''
    And I wait for '5' sec untill element 'Tittle' is visible
    And I type 'Hi' in field 'Chat' and click enter
    And I wait for '1' seconds
    When I type 'Flight Fare' in field 'Chat' and click enter
    And I wait for '2' seconds
    Then I compare responces message 'May I know where will you be travelling from?' from Channel type 'Talk2Bot'
    And I wait for '2' seconds
    When I type 'Hyderabad' in field 'Chat' and click enter
    And I wait for '2' seconds
    Then I compare responces message 'May I know where will you be travelling to?' from Channel type 'Talk2Bot'
    And I wait for '2' seconds
    When I type 'Chicago' in field 'Chat' and click enter
    And I wait for '2' seconds
    Then I compare responces message 'May I also know your departure date?' from Channel type 'Talk2Bot'
    And I wait for '2' seconds
    And I add '10' number of days to Today in format 'MMM-dd-yyyy' and store as 'start_date'
    Then I enter desired date 'start_date' in field 'Chat'
    And I wait for '2' seconds
    Then I compare responces message 'May I know your return date?' from Channel type 'Talk2Bot'
    And I wait for '2' seconds
    And I add '20' number of days to Today in format 'dd-MM-yyyy' and store as 'return_date'
    Then I enter desired date 'return_date' in field 'Chat'
    And I wait for '2' seconds
    Then I compare responces message 'Alright! Your flight fare to travel from Hyderabad, Telangana, India to Chicago, IL, USA' from Channel type 'Talk2Bot'
    Then I compare responces message '3200' from Channel type 'Talk2Bot'
    And Stored date 'start_date' compared from Channel type 'Talk2Bot' in expected format 'yyyy-MM-dd'
    And Stored date 'return_date' compared from Channel type 'Talk2Bot' in expected format 'yyyy-MM-dd'
    When I type 'bye' in field 'Chat' and click enter

  @9.1 @close_bot
  Scenario: close the Bot
    And I wait for '2' seconds
    Then I click 'Chat_close'
    And I wait for '2' seconds
    Then I switch back to default content
