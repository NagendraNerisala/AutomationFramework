Feature: To validate brit insurance web portal

  @UI @Sanity @Regression
  Scenario Outline: validate search results
    Given open chrome browser
    And launch Brit Insurance portal
    And Accept Cookies Dialog
    When click on search button
    And enter search term "<term>"
    Then validate search results
    And close browser

    Examples:
    |term     |
    |IFRS 17  |