package Hooks;

import framework.StepBase;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
//import cucumber.api.Scenario;
//import cucumber.api.java.After;
//import cucumber.api.java.Before;

/**
 * @ScriptName : Hooks
 * @Description : This class contains

 */
public class Hooks {
	public static Scenario currentScenario;

	@Before
	public void applyHook(Scenario scenario) {
		try {
			currentScenario = scenario;
			StepBase.setScenario(scenario);
			
			if(System.getProperty("test.PostScenarioTearDown").equals("true")){
				//StepBase.setUp();
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@After
	public void removeHook(Scenario scenario) {
		try {
			if(System.getProperty("test.PostScenarioTearDown").equals("true")){
				StepBase.tearDown();
			}
			if(scenario.isFailed()){
				StepBase.embedScreenshot();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
