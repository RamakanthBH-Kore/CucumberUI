
@Dialog @Kore @9.1
Feature: Dialog
  I want to use this template for my feature file

	@9.1
	Scenario: Navigating to Dialog Tasks
	Given I load page object 'Dialog'
	And I wait '10' secs for Element 'Dialog_menu' be clickable
	And I click 'Dialog_menu'

	
	@9.1
	Scenario: User should be able to Create a Dialog
	When I wait for '10' seconds
	And I click 'create_dialog_button'
	Then I should see text 'Create Dialog' present on page
	When I enter 'Test1' in field 'intent_name'
	And I enter 'Test description' in field 'intent_description'
	And I click 'dialog_proceed_button'
	And I wait for '10' seconds
	Then I should see text 'USER INTENT' present on page
	
	@9.1
	Scenario: Moving back to Dialogs homepage
	When I click 'dialog_screen_back_button'
	And I wait for '5' seconds
	Then I should see text 'Dialog Tasks' present on page
	
	
	@9.1
	Scenario: User should be able to delete dialogs
	When I enter 'Test1' in field 'dialog_search_textbox'
	And I mouse over 'dialog_description_text'
	And I mouse over 'dialog_delete_button'
	And I click 'dialog_delete_button'
	And I click 'dialog_delete_confirmation'
	And I wait for '5' seconds
	Then I should see text 'No search results found' present on page
	
   