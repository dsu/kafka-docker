package com;

import com.infosys.consumer.model.Subscription;
import com.infosys.consumer.service.EmailService;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertEquals;

public class SnakeMeasureTest extends CucumberStepDefinitions {

    @Autowired
    EmailService service;

    @Given("^I have hungry snake")
    public void i_have_hungry_snake() {
        service.sendTheEmail(new Subscription());
    }

    @When("^When I feed my snake$")
    public void when_i_feed_my_snake() {
        ;
    }

    @Then("^I receive snek snek$")
    public void receive_snek_snek() {

    }

}