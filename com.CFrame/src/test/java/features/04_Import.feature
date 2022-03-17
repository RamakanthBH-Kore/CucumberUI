@9.1 @Import @Kore
Feature: To Import a new Bot

  @Import
  Scenario: Landing on Import page
    Given I load page object 'import'
    And I wait '10' secs for Element 'Deploy_menu' be clickable
    When I click 'Deploy_menu'
    Then I wait for '2' seconds
    When I click 'Bot_MGT'
    And I wait for '2' seconds
    When I click 'Import_Export'
    And I wait for '2' seconds
    Then I verify element 'ImportandExport' is displayed
    And I wait for '2' seconds
    Then I click 'import_link'

  Scenario: import existing bot
    Given I Import the Type of file 'Bot_Definition' and its in 'Travel Desk_test/botDefinition.json'
    Then I wait for '2' seconds
    Then I wait for innertext 'Choose File' untill element 'Loading_defination' found
    And I verify value 'botDefinition.json' in field 'Defination_LOC'
    And I Import the Type of file 'Bot_Config' and its in 'Travel Desk_test/config.json'
    Then I wait for '1' seconds
    Then I wait for innertext 'Choose File' untill element 'Loading_config' found
    And I verify value 'config.json' in field 'Config_file'
    #And I Import the Type of file 'Custom_Script' and its in 'Travel Desk_test/sample.js'
    #Then I wait for '10' seconds
    #And I verify value 'sample.js' in field 'Custom_script'
    #Then I click 'Full_import'
    And I click 'Import_BTN'
    Then I wait for '2' seconds
    And I switch to default content
    Then I should see text 'Import Bot' present on page
    And I enter 'Test123' in field 'Text_area'
    Then I click 'start_import'
    And I wait for '30' sec untill element 'Status' is visible
    And I wait '2' secs for Element 'Done' be clickable
    Then I click 'Done'
    Then I wait for '3' seconds


