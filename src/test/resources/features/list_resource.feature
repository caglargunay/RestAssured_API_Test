Feature:

  Background:  Used path
    Given I use this path "api/unknown"


  Scenario Outline: Verify page, per_page, total, total_pages
    When I use get method
    Then status code should be 200
    And verify the value of "<element>" element from response is <value>

    Examples:
      | element     | value |
      | page        | 1     |
      | per_page    | 6     |
      | total       | 12    |
      | total_pages | 2     |


  Scenario Outline: List all data with 2000 and 2001 years
    Given I use this query "page" "<pageNumber>"
    When I use get method
    Then  status code should be 200
    And All data with year should be listed
      | 2008 |
      | 2009 |

    Examples:
      | pageNumber |
      | 1          |
      | 2          |

  @wip
  Scenario Outline:Verify that all color codes starts with "#" and have 7 characters (including "#")

    Given I use this query "page" "<pageNumber>"
    When I use get method
    Then  status code should be 200
    And  all color codes starts with "#" and have 7 characters
    Examples:
      | pageNumber |
      | 1          |
      | 2          |


  Scenario Outline: Verify that all the value of pantone_values in following format "##-####"

    Given I use this query "page" "<pageNumber>"
    When I use get method
    Then  status code should be 200
    And  all the value of pantone_values in following format "##-####"
    Examples:
      | pageNumber |
      | 1          |
      | 2          |


  Scenario: Verify the year of second element of data in the first page is 2001
    When I use get method
    Then status code should be 200
    And the "year" of 2. element of data should be 2001

  Scenario Outline: Verify the value of text in support is "To keep ReqRes free, contributions towards server costs are appreciated!"

    Given I use this query "page" "<pageNumber>"
    When I use get method
    Then  status code should be 200
    And   the value of text in support should be "To keep ReqRes free, contributions towards server costs are appreciated!"
    Examples:
      | pageNumber |
      | 1          |
      | 2          |

