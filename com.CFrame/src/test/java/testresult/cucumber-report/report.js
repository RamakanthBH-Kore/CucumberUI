$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("Kore_applications.feature");
formatter.feature({
  "line": 2,
  "name": "Kore Account applications",
  "description": "I want to use this template for my feature file",
  "id": "kore-account-applications",
  "keyword": "Feature",
  "tags": [
    {
      "line": 1,
      "name": "@Kore_applications"
    }
  ]
});
formatter.before({
  "duration": 269500,
  "status": "passed"
});
formatter.scenario({
  "line": 6,
  "name": "Login to Kore",
  "description": "",
  "id": "kore-account-applications;login-to-kore",
  "type": "scenario",
  "keyword": "Scenario",
  "tags": [
    {
      "line": 5,
      "name": "@Login_Kore"
    },
    {
      "line": 5,
      "name": "@kore"
    },
    {
      "line": 5,
      "name": "@smoke"
    }
  ]
});
formatter.step({
  "line": 7,
  "name": "I wait for \u00272\u0027 seconds",
  "keyword": "Then "
});
formatter.step({
  "line": 8,
  "name": "My WebApp \u0027Kore_applications\u0027 is open",
  "keyword": "Given "
});
formatter.step({
  "line": 9,
  "name": "I clear field \u0027username\u0027",
  "keyword": "And "
});
formatter.step({
  "line": 10,
  "name": "I enter \u0027vishnuprasath.ramanujam@kore.com\u0027 in field \u0027username\u0027",
  "keyword": "And "
});
formatter.step({
  "line": 11,
  "name": "I click \u0027ContinueBtn\u0027",
  "keyword": "And "
});
formatter.step({
  "line": 12,
  "name": "I clear field \u0027password\u0027",
  "keyword": "And "
});
formatter.step({
  "line": 13,
  "name": "I enter \u0027Kore@123\u0027 in field \u0027password\u0027",
  "keyword": "And "
});
formatter.step({
  "line": 14,
  "name": "I click \u0027Login\u0027",
  "keyword": "And "
});
formatter.step({
  "line": 15,
  "name": "I wait for Page to Load",
  "keyword": "And "
});
formatter.step({
  "line": 16,
  "name": "I wait \u00272\u0027 seconds for presence of element \u0027Kore_logo\u0027",
  "keyword": "And "
});
formatter.step({
  "line": 17,
  "name": "I wait for \u00272\u0027 seconds",
  "keyword": "Then "
});
formatter.match({
  "arguments": [
    {
      "val": "2",
      "offset": 12
    }
  ],
  "location": "CommonSteps.I_pause_for_seconds(int)"
});
formatter.result({
  "duration": 2316968700,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "Kore_applications",
      "offset": 11
    }
  ],
  "location": "CommonSteps.my_webapp_is_open(String)"
});
formatter.result({
  "duration": 5506610200,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "username",
      "offset": 15
    }
  ],
  "location": "CommonSteps.I_clear_Field(String)"
});
formatter.result({
  "duration": 1001401700,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "vishnuprasath.ramanujam@kore.com",
      "offset": 9
    },
    {
      "val": "username",
      "offset": 53
    }
  ],
  "location": "CommonSteps.I_enter_in_field(String,String)"
});
formatter.result({
  "duration": 270350900,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "ContinueBtn",
      "offset": 9
    }
  ],
  "location": "CommonSteps.I_click(String)"
});
formatter.result({
  "duration": 283958400,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "password",
      "offset": 15
    }
  ],
  "location": "CommonSteps.I_clear_Field(String)"
});
formatter.result({
  "duration": 1083535500,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "Kore@123",
      "offset": 9
    },
    {
      "val": "password",
      "offset": 29
    }
  ],
  "location": "CommonSteps.I_enter_in_field(String,String)"
});
formatter.result({
  "duration": 191483600,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "Login",
      "offset": 9
    }
  ],
  "location": "CommonSteps.I_click(String)"
});
formatter.result({
  "duration": 144031200,
  "status": "passed"
});
formatter.match({
  "location": "CommonSteps.waitForPageLoaded()"
});
formatter.result({
  "duration": 10574600,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "2",
      "offset": 8
    },
    {
      "val": "Kore_logo",
      "offset": 44
    }
  ],
  "location": "CommonSteps.I_wait_for_presence_of_element(int,String)"
});
formatter.result({
  "duration": 2598634800,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "2",
      "offset": 12
    }
  ],
  "location": "CommonSteps.I_pause_for_seconds(int)"
});
formatter.result({
  "duration": 2003975600,
  "status": "passed"
});
formatter.after({
  "duration": 174100,
  "status": "passed"
});
formatter.before({
  "duration": 84500,
  "status": "passed"
});
formatter.scenario({
  "line": 21,
  "name": "Travel Bot",
  "description": "",
  "id": "kore-account-applications;travel-bot",
  "type": "scenario",
  "keyword": "Scenario",
  "tags": [
    {
      "line": 20,
      "name": "@Travel_Bot"
    },
    {
      "line": 20,
      "name": "@kore"
    }
  ]
});
formatter.step({
  "line": 23,
  "name": "I enter \u0027Travel Desk_test\u0027 in field \u0027sreach\u0027",
  "keyword": "When "
});
formatter.step({
  "line": 24,
  "name": "I click \u0027My_1st\u0027",
  "keyword": "When "
});
formatter.step({
  "line": 25,
  "name": "I wait for Page to Load",
  "keyword": "Then "
});
formatter.step({
  "line": 26,
  "name": "I should see element \u0027mybotname\u0027 present on page",
  "keyword": "Then "
});
formatter.step({
  "comments": [
    {
      "line": 27,
      "value": "#Then I should see element \u0027Tasks\u0027 present on page"
    }
  ],
  "line": 28,
  "name": "I should see text \u0027Flight Details\u0027 present on page",
  "keyword": "Then "
});
formatter.step({
  "line": 29,
  "name": "I should see text \u0027Knowledge Graph\u0027 present on page",
  "keyword": "Then "
});
formatter.step({
  "line": 30,
  "name": "I click \u0027Paths\u0027",
  "keyword": "And "
});
formatter.step({
  "line": 31,
  "name": "I wait for Page to Load",
  "keyword": "Then "
});
formatter.step({
  "line": 32,
  "name": "I should see element \u0027KG\u0027 present on page",
  "keyword": "Then "
});
formatter.step({
  "line": 33,
  "name": "I wait for \u00273\u0027 seconds",
  "keyword": "Then "
});
formatter.step({
  "line": 34,
  "name": "I enter \u0027cruise\u0027 in field \u0027searchkg\u0027",
  "keyword": "When "
});
formatter.step({
  "line": 35,
  "name": "I click \u0027typ_cruise\u0027",
  "keyword": "Then "
});
formatter.step({
  "line": 36,
  "name": "I wait for \u00274\u0027 seconds",
  "keyword": "Then "
});
formatter.step({
  "line": 37,
  "name": "I should see element \u0027check_cruise\u0027 present on page",
  "keyword": "And "
});
formatter.match({
  "arguments": [
    {
      "val": "Travel Desk_test",
      "offset": 9
    },
    {
      "val": "sreach",
      "offset": 37
    }
  ],
  "location": "CommonSteps.I_enter_in_field(String,String)"
});
formatter.result({
  "duration": 893490800,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "My_1st",
      "offset": 9
    }
  ],
  "location": "CommonSteps.I_click(String)"
});
formatter.result({
  "duration": 178986000,
  "status": "passed"
});
formatter.match({
  "location": "CommonSteps.waitForPageLoaded()"
});
formatter.result({
  "duration": 10454600,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "mybotname",
      "offset": 22
    }
  ],
  "location": "CommonSteps.I_should_see_on_page(String)"
});
formatter.result({
  "duration": 6015742100,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "Flight Details",
      "offset": 19
    }
  ],
  "location": "CommonSteps.I_should_see_text_present_on_page(String)"
});
formatter.embedding("image/png", "embedded0.png");
formatter.result({
  "duration": 2073743000,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "Knowledge Graph",
      "offset": 19
    }
  ],
  "location": "CommonSteps.I_should_see_text_present_on_page(String)"
});
formatter.embedding("image/png", "embedded1.png");
formatter.result({
  "duration": 1517613300,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "Paths",
      "offset": 9
    }
  ],
  "location": "CommonSteps.I_click(String)"
});
formatter.result({
  "duration": 148245500,
  "status": "passed"
});
formatter.match({
  "location": "CommonSteps.waitForPageLoaded()"
});
formatter.result({
  "duration": 13014600,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "KG",
      "offset": 22
    }
  ],
  "location": "CommonSteps.I_should_see_on_page(String)"
});
formatter.result({
  "duration": 5202718000,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "3",
      "offset": 12
    }
  ],
  "location": "CommonSteps.I_pause_for_seconds(int)"
});
formatter.result({
  "duration": 3012959800,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "cruise",
      "offset": 9
    },
    {
      "val": "searchkg",
      "offset": 27
    }
  ],
  "location": "CommonSteps.I_enter_in_field(String,String)"
});
formatter.result({
  "duration": 306498700,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "typ_cruise",
      "offset": 9
    }
  ],
  "location": "CommonSteps.I_click(String)"
});
formatter.result({
  "duration": 451996900,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "4",
      "offset": 12
    }
  ],
  "location": "CommonSteps.I_pause_for_seconds(int)"
});
formatter.result({
  "duration": 4014239200,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "check_cruise",
      "offset": 22
    }
  ],
  "location": "CommonSteps.I_should_see_on_page(String)"
});
formatter.result({
  "duration": 209687100,
  "status": "passed"
});
formatter.after({
  "duration": 44000,
  "status": "passed"
});
formatter.before({
  "duration": 48800,
  "status": "passed"
});
formatter.scenario({
  "comments": [
    {
      "line": 38,
      "value": "#Then I should see text \u0027LMG Travel Booking Assist\u003ecruise\u0027 present on page"
    }
  ],
  "line": 46,
  "name": "Clear HashMap",
  "description": "",
  "id": "kore-account-applications;clear-hashmap",
  "type": "scenario",
  "keyword": "Scenario",
  "tags": [
    {
      "line": 45,
      "name": "@clear_hashmp"
    },
    {
      "line": 45,
      "name": "@kore"
    }
  ]
});
formatter.step({
  "line": 47,
  "name": "I clear hashmap",
  "keyword": "And "
});
formatter.match({
  "location": "CommonSteps.I_Clear_hasmap()"
});
formatter.result({
  "duration": 72700,
  "status": "passed"
});
formatter.after({
  "duration": 25900,
  "status": "passed"
});
});