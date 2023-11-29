Feature: List User Functionality

  Background:
    Given I use this path "api/users"


  Scenario: List all users from first/second page
    Given I use this query "page" "1"
    When I use get method
    Then status code should be 200


  Scenario: List all users from first/second page
    Given I use this query "page" "2"
    When I use get method
    Then status code should be 200
    And response headers "Content-Type" should have this value "application/json; charset=utf-8"


  Scenario: Verify that host is "reqres.in" for first/second page
    Then request headers "Host" should be "reqres.in"


  Scenario:Verify that connection is "keep-alive" for first/second page
    When I use get method
    Then status code should be 200
    And response headers "Connection" should have this value "keep-alive"

  Scenario: Check if the response time is less then 950 ms for first/second page
    When I use get method
    Then status code should be 200
    And check response time less than 950 ms

  Scenario: Verify page, per_page, total, total_pages for first/second page
    Given I use this query "page" "1"
    When I use get method
    Then status code should be 200
    And verify the value of "page" element from response is 1
    And verify the value of "per_page" element from response is 6
    And verify the value of "total" element from response is 12
    And verify the value of "total_pages" element from response is 2


  Scenario Outline: List all users
    Given I use this query "page" "<pageNumber>"
    When I use get method
    Then status code should be 200
    And print each element of "data" array from response

    Examples:
      | pageNumber |
      | 1          |
      | 2          |

  Scenario: Check if support url is working for first or second page
    Given I use this query "page" "1"
    When I use get method
    Then status code should be 200
    And "support.url" url should be working

  Scenario: List all user first names from first or second page
    Given I use this query "page" "1"
    When I use get method
    Then status code should be 200
    And print each "first_name" of "data" list from response

  Scenario Outline:  List all usernames whose ids are odd
    Given I use this query "page" "<pageNumber>"
    When I use get method
    Then status code should be 200
    And print all usernames whose "id" of "data" are odd

    Examples:
      | pageNumber |
      | 1          |
      | 2          |


  Scenario Outline: Check if each email address contains first name
    Given I use this query "page" "<pageNumber>"
    When I use get method
    Then status code should be 200
    And check "email" contains user's name "first_name" under "data" from response

    Examples:
      | pageNumber |
      | 1          |
      | 2          |

    Scenario Outline:  Scenario Outline:Verify that if given ids and first_names match

      Given I use this query "page" "<pageNumber>"
      When I use get method
      Then status code should be 200
      And check "id" equals to "<id>" and "first_name" equals to "<firstName>" inside "data"

      Examples:
        | pageNumber | id | firstName |
        | 1          | 1  | George    |
        | 1          | 2  | Janet     |
        | 1          | 3  | Emma      |
        | 1          | 4  | Eve       |
        | 1          | 5  | Charles   |
        | 1          | 6  | Tracey    |
        | 2          | 7  | Michael   |
        | 2          | 8  | Lindsay   |
        | 2          | 9  | Tobias    |
        | 2          | 10 | Byron     |
        | 2          | 11 | George    |
        | 2          | 12 | Rachel    |