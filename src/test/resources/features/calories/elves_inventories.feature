Feature: Elves inventories calories

  Scenario: Find elf inventory with biggest amount of calories
    When the inventory list
    """
    1000
    2000
    3000

    4000

    5000
    6000

    7000
    8000
    9000

    10000
    """
    When I ask wich elf inventory has the most calories in this list
    Then I find this is inventory of elf number 4 with 24000 calories

  Scenario: Find top N elves inventories ranked by biggest amount of calories
    Given the inventory list
    """
    1000
    2000
    3000

    4000

    5000
    6000

    7000
    8000
    9000

    10000
    """
    When I ask the top 3 inventories with the biggest amount of calories
    Then I have this inventory ranking
      | position | elfNumber | calories |
      | 1        | 4         | 24000    |
      | 2        | 3         | 11000    |
      | 3        | 5         | 10000    |