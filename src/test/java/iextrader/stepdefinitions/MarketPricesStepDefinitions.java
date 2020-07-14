package iextrader.stepdefinitions;


import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import net.serenitybdd.rest.SerenityRest;

import static net.serenitybdd.rest.SerenityRest.lastResponse;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class MarketPricesStepDefinitions {

    @Before
    public void setupBaseUrl(){
        RestAssured.baseURI = "http://localhost:8080/api";
    }

    @Given("^(?:.*) is an active trader on IEX$")
    public void register_user_is_an_active_trader_on_IEX() {

    }


    @Given("^(.*) is currently trading at (.*)$")
    public void share_is_currently_trading_at(String symbol, double expectedPrice) {
        RestAssured.given().body(expectedPrice)
                .contentType("application/json")
                .when().post("/stock/{symbol}/price", symbol)
                .then().statusCode(200);
    }

    @When("^(.*) requests the latest price for (.*)$")
    public void requests_the_latest_price_of(String actor, String symbol) {
        SerenityRest.when().get("/stock/{symbol}/price",symbol)
                .then().statusCode(200);
    }

    @Then("^s?he should see the price of (.*)$")
    public void should_see_the_price_of(double expectedPrice) {
        Double price = lastResponse().as(Double.class);

        assertThat(price).isEqualTo(expectedPrice);
    }

}
