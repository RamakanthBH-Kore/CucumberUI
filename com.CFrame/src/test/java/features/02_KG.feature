@KG @9.1 @Kore
Feature: KG Functionality validation
	
	@9.1
	Scenario: Navigating to KG
	Given I load page object 'KG'
	And I wait '10' secs for Element 'Knowledge_Graph' be clickable
	And I click 'Knowledge_Graph'
	
	@9.1
	Scenario: Opening KG task and Adding FAQ
	When I click 'Knowledge_Graph_task'
		And I wait for '1' seconds
	And I click 'Add_Intent'
	And I switch to default content
	And I wait for '1' seconds
	And I enter 'Sample Question?' in field 'Add_Question'
	And I wait for '1' seconds
	Then I scroll to element 'Add_Answer'
	And I enter 'Sample Answer' in field 'Add_Answer'
	And I wait for '1' seconds
	And I scroll PS top/bottom 'bottom' scroll class 'KG_Scroll'
	And I mouse over 'Save_button'
	And I wait '5' secs for Element 'Save_button' be clickable
	And I click 'Save_button'
	Then I enter 'Sample Question' in field 'KG_Search'
	Then I hit enter-key on element 'KG_Search'
	Then I should see text 'Sample Question?' present on page
	And I wait for '5' seconds
	

	
	@9.1
	Scenario: FAQ deletion
	When I mouse over 'Sample_Question'
	And I wait for '4' seconds
	And I mouse over 'FAQ_Delete'
	And I click 'FAQ_Delete'
	And I wait for '4' seconds
	And I switch to default content
	And I mouse over 'FAQ_Delete_Confirmation'
	And I click 'FAQ_Delete_Confirmation'
	And I switch to default content
	Then I enter 'Sample Question' in field 'KG_Search'
	Then I hit enter-key on element 'KG_Search'
	Then I should not see text 'Sample Question?' present on page
	
	@9.1
	Scenario: Moving to KG Homescreen from Questions screen
	When I click 'KG_Back_Button'
	And I wait for '5' seconds
	Then I should see text 'Knowledge Graph' present on page