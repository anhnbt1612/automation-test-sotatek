Feature: Data test

  @displayed
  Scenario: Get iPhone 11 price
    Given Open "https://www.shopee.vn/" website
    When Search keyword
      | Web    | Keyword   |
      | shopee | iPhone 11 |
    And verify first product on result page
      | Web    | Keyword   |
      | shopee | iPhone 11 |

    Given Open "https://www.lazada.vn/" website
    When Search keyword
      | Web    | Keyword   |
      | lazada | iPhone 11 |
    And verify first product on result page
      | Web    | Keyword   |
      | lazada | iPhone 11 |

    And Print and close browser