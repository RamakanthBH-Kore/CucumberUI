@9.1 @small_talk @Kore
Feature: Smalltalk validations

  @9.1
  Scenario: Navigating to Smalltalk
    Given I load page object 'SmallTalk'
    And I wait '10' secs for Element 'SmallTalk' be clickable
    And I click 'SmallTalk'
    Then I mouse over 'SmallTalk_Title'

  @9.1
  Scenario: User should be able to add new group to smalltalk
    When I click 'New_Group'
    And I wait for '3' seconds
    And I enter 'automation' in field 'Group_Name'
    And I click 'Proceed_Button'

  @9.1
  Scenario Outline: User should be able to add smalltalk conversations to existing group
    #When I click 'Existing_SmallTalk_Group'
    #Then I should see text 'Groups' present on page
    When I enter '<UserInput>' in field 'SmallTalk_User_Input'
    And I enter '<BotInput>' in field 'SmallTalk_Bot_Input'
    And I click 'Add_Button'
    Then I should see element 'SmallTalk_Bot_Response' present on page
    And I wait for '1' seconds
    And I should see text 'Question and Answer added successfully' present on page at 'Noty_text'
    And I wait for '3' seconds

    Examples: 
      | UserInput         | BotInput            |
      | Test User Input 1 | Test Bot Response 1 |
      | Test User Input 2 | Test Bot Response 2 |

  @9.1
  Scenario: moving back and select the created group
    When I click 'SmallTalk_Back_Button'
    And I wait for '2' seconds
    #Then I should see element 'SmallTalk_Bot_Response' present on page
    When I click 'Existing_SmallTalk_Group'

  @9.1
  Scenario Outline: Delete the Small talk
    And I wait for '2' seconds
    And I click '<ToSelect>'
    And I click 'SM_Delete'
    And I wait for '1' seconds
    Then I should see text 'Are you sure you want to delete selected question?' present on page
    And I wait for '1' seconds
    And I click 'SM_Delete_btn'
    Then I should see text 'Group deleted successfully' present on page at 'Noty_text'
    And I wait for '2' seconds

    Examples: 
      | ToSelect |
      | select   |
      | select2  |

  @9.1
  Scenario: User should be able to delete the Smalltalk group
    When I click 'SmallTalk_Back_Button'
    And I wait for '2' seconds
    When I enter 'auto' in field 'Search_Component'
    And I mouse over 'Existing_SmallTalk_Group'
    And I mouse over 'Group_Delete_Button'
    And I click 'Group_Delete_Button'
    And I click 'Group_Delete_Confirmation'
    And I wait for '5' seconds
    And I enter 'auto' in field 'Search_Component'
    Then I should not see text 'automation' present on page
