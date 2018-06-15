$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("test2.feature");
formatter.feature({
  "comments": [
    {
      "line": 1,
      "value": "#Features"
    }
  ],
  "line": 2,
  "name": "feature description",
  "description": "In order to do something\r\nAs someone\r\nI want something else to happen",
  "id": "feature-description",
  "keyword": "Feature"
});
formatter.scenario({
  "line": 7,
  "name": "testForBlankUsernameAndPassword",
  "description": "",
  "id": "feature-description;testforblankusernameandpassword",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 8,
  "name": "Driver is open",
  "keyword": "Given "
});
formatter.step({
  "line": 9,
  "name": "loginPage is there",
  "keyword": "And "
});
formatter.step({
  "line": 10,
  "name": "Username is blank",
  "keyword": "When "
});
formatter.step({
  "line": 11,
  "name": "Password is blank",
  "keyword": "And "
});
formatter.step({
  "line": 12,
  "name": "we get error message",
  "keyword": "Then "
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
});