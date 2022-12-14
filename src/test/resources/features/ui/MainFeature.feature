Feature: Main Feature

  Scenario: Verify Error Page title after division by zero
    Given open the Main Page of application
    Then verify that title on page 'Main Page' equals to 'Test Data Management Tool'

    When user set '0' value in 2nd field on the Main Page
    And user selects math operation '/' on the Main Page
    And user clicks Calculate button on Main Page
    Then verify that title on page 'Error Page' equals to 'There are some errors in expression. Try again.'


  Scenario: Verify several exact calculations on History Page
    Given open the Main Page of application
    When user deletes all data from Calculator Database
    And user set '10' value in 1st field on the Main Page
    And user set '10' value in 2nd field on the Main Page
    And user selects math operation '*' on the Main Page
    And user clicks Calculate button on Main Page
    And user clicks Main Page link on Result Page
    And user set '20' value in 1st field on the Main Page
    And user set '20' value in 2nd field on the Main Page
    And user selects math operation '+' on the Main Page
    And user clicks Calculate button on Main Page
    And user clicks Main Page link on Result Page
    And user set '40' value in 1st field on the Main Page
    And user set '10' value in 2nd field on the Main Page
    And user selects math operation '/' on the Main Page
    And user clicks Calculate button on Main Page
    And user clicks Main Page link on Result Page
    And user set '10' value in 1st field on the Main Page
    And user set '10' value in 2nd field on the Main Page
    And user selects math operation '-' on the Main Page
    And user clicks Calculate button on Main Page
    And user clicks Main Page link on Result Page
    And user clicks History link on Main Page
    Then verify that History table is equals to following data:
      | firstNumber | mathSign | secondNumber | result | date   |
      | 10          | *        | 10           | 100.0  | $TODAY |
      | 20          | +        | 20           | 40.0   | $TODAY |
      | 40          | /        | 10           | 4.0    | $TODAY |
      | 10          | -        | 10           | 0.0    | $TODAY |


  Scenario Outline: Verify correct calculations
    Given open the Main Page of application
    Then verify that title on page 'Main Page' equals to 'Test Data Management Tool'
    When user set '<firstNumber>' value in 1st field on the Main Page
    When user set '<secondNumber>' value in 2nd field on the Main Page
    And user selects math operation '<mathOperation>' on the Main Page
    And user clicks Calculate button on Main Page
    Then verify that title on page 'Result Page' equals to 'Result of Calculation'
    And verify that Result on Result Page is equals to following data:
      | expression   | result   |
      | <expression> | <result> |

    Examples:
      | firstNumber | secondNumber | mathOperation | expression | result |
      | 10          | 90           | *             | 10*90      | 900.0  |
      | 20          | 80           | +             | 20+80      | 100.0  |
      | 30          | 70           | -             | 30-70      | -40.0  |
      | 40          | 60           | *             | 40*60      | 2400.0 |
      | 50          | 50           | +             | 50+50      | 100.0  |
      | 50          | 50           | /             | 50/50      | 1.0    |
      | 50          | 10           | /             | 50/10      | 5.0    |
      | 50          | 7            | /             | 50/7       | 7.0    |

  Scenario: Verify that History Table is empty when DB is empty
    Given open the Main Page of application
    When user deletes all data from Calculator Database
    And user clicks History link on Main Page
    Then verify that History Table is empty
