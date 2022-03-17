@9.1 @Kore @CreateNewBot
Feature: Login and Create a Bot Functionality

  Scenario: Login to kore botbuilder
    Given My WebApp 'test' is open
    Then I wait for '2' seconds
    And I clear field 'username'
    And I enter 'sangya.mahapatra@kore.com' in field 'Uname'
    And I click 'ContinueBtn'
    And I clear field 'password'
    And I enter encrypted data 'R3VsdUAxOTk0' in field 'password'
    And I click 'Login'
    And I wait for Page to Load
    And I wait '2' seconds for presence of element 'Kore_logo'

  @ignore
  Scenario: Create a new bot
    Then I wait for '5' seconds
    And I wait for Page to Load
    And I click 'NewBotButton2'
    And I wait for '2' seconds
    And I click 'StartFromScratchButton'
    When I enter value with Random email/string/number 'string' in field 'BotNameField'
    #And I enter 'Test Bot' in field 'BotNameField'
    And I wait for '5' seconds
    And I click 'CreateButton'
    And I wait for '10' seconds
    And I should see element 'Build' present on page

  Scenario: Search for Bot
    Then I wait for '2' seconds
    And I mouse over 'search'
    When I enter 'testbot' in field 'search'
    When I click 'testbot'
    Then I wait for Page to Load
    Then I wait for '15' seconds

  Scenario: Create a dialog task
    Then I wait for '5' seconds
    And I wait for Page to Load
    And I click 'DialogTaskButton'
    Then I wait for '2' seconds
    And I click 'CreateDialogButton'
    And I wait for Page to Load
    Then I wait for '2' seconds
    And I enter 'Test Intent 01' in field 'IntentNameField'
    #Then I wait '5' secs for Element 'ProceedButton' be clickable
    And I enter 'Test Description 01' in field 'DescriptionField'
    And I wait for '2' seconds
    And I click 'ProceedButton'
    And I wait for '2' seconds

  Scenario: Build a dialog task flow
    Then I wait for '3' seconds
    And I wait for Page to Load
    #And I click 'BuildTab'
    #And I switch to default content
    Then I switch to iFrame 'dialogFrame'
    And I click 'AddNodePlusButton'
    And I wait for '2' seconds
    And I click 'EntityOption'
    And I wait for '2' seconds
    And I click 'NewEntityButton'
    And I wait for '5' seconds
    And I click 'NewEntity'
    And I wait for '5' seconds
    #Then I switch to default content
    #And I click 'EntityName'
    Then I clear field 'EntityName'
    #Then I wait '2' secs for Element 'EntityName' be clickable
    Then I enter 'ColorEntity05' in field 'EntityName'
    And I wait for '2' seconds
    #And I click 'EntityDisplayName'
    Then I clear field 'EntityDisplayName'
    #Then I wait '2' secs for Element 'EntityDisplayName02' be clickable
    Then I enter 'ColorEntity05' in field 'EntityDisplayName'
    And I wait for '2' seconds
    #Then I select element 'Color' from Dropdown 'TypeDropdown'
    #And I should see text 'Are you sure?' present on page at 'Typechange'
    #Then I should see text 'String will be changed to Color' present on page
    #And I click 'OK'
    And I wait for '2' seconds

  #Then I clear field 'UserPrompts'
  #Then I switch to iFrame 'dialogFrame'
  Scenario: Build a dialog task flow
    Then I focus on element 'UPrompts'
    And I click 'UPrompts'
    And I enter 'Please enter your search here' in field 'UserPrompts'
    And I wait for '2' seconds
