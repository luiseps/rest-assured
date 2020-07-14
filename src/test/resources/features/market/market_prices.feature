Feature: Market Prices

  In order to make sensible buy and sell decisions
  As a portfolio holder
  I want to be able to see the current market prices for shares I am interested in

  Scenario: Should be able to see the current market prices when the market is open
    Given Patricia is an active trader on IEX
    And AAPL is currently trading at 175.00
    When Patricia requests the latest price for AAPL
    Then she should see the price of 175.00
