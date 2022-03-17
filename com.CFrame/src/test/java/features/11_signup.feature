@singup @Kore
Feature: signup

  @Land_signup
  Scenario: to Signup
    Given My WebApp 'signup' is open

  @Signup
  Scenario: to Signup for Kore
    #Then I load page object 'signup'
    And I wait for '1' seconds
    Given I click 'Signup_btn'
    And I wait for '1' seconds
    Then I should see text 'Sign up to build powerful Virtual Assistant' present on page
    And I clear field 'Enter_email'
    Then I enter value with Random email/string/number 'email' in field 'Enter_email'
    And I click 'Click_btn'
    And I wait for '1' seconds
    Then I should see text 'Get started for free' present on page
    And I compare attribute value 'Enter_email' stored value 'Enter_email'

  @Signup
  Scenario Outline: Enter details
    And I enter '<value>' in field '<field>'

    Examples: 
      | value      | field        |
      | Auto       | first_name   |
      | last       | Last_name    |
      | Account.ai | Account_name |
      | Test@1234  | Pass_secure  |
      | Test@1234  | confirm_pwd  |

  @signup
  Scenario: to Signup for Kore
    # And I enter 'Tester' in field 'Last_name'
    #And I enter 'Kore.ai' in field 'Account_name'
    #And I enter 'Test@1234' in field 'Pass_secure'
    #And I enter 'Test@1234' in field 'confirm_pwd'
    Then I should see element 'ToolTip' present on page
    And I wait for '2' seconds
   # And I click 'SignupBtn'
