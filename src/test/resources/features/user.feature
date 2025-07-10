Feature: User API testing

  Scenario: Validate single To-Do item
    Given I have the to-do endpoint
    When I retrieve the to-do by id 1
    Then the response status should be 200
    And the response should contain:
      | userId    | 1                     |
      | id        | 1                     |
      | title     | delectus aut autem    |
      | completed | false                 |

  Scenario: Create a new To-Do item
    Given I have the to-do endpoint
    When I create a to-do with title "foo" body "bar" and userId 1
    Then the response status should be 200
    And the response should contain:
      | id     | 201 |
      | title  | foo |
      | body   | bar |
      | userId | 1   |
