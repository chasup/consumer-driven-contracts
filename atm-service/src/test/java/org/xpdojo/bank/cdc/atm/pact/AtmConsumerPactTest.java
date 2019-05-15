package org.xpdojo.bank.cdc.atm.pact;

import au.com.dius.pact.consumer.MockServer;
import au.com.dius.pact.consumer.Pact;
import au.com.dius.pact.consumer.dsl.DslPart;
import au.com.dius.pact.consumer.dsl.PactDslJsonBody;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.consumer.junit5.PactConsumerTestExt;
import au.com.dius.pact.consumer.junit5.PactTestFor;
import au.com.dius.pact.model.RequestResponsePact;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.web.client.RestTemplate;
import org.xpdojo.bank.cdc.atm.domain.AccountData;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(PactConsumerTestExt.class)
@PactTestFor(providerName = "account_provider")
public class AtmConsumerPactTest {

    @Pact(provider = "account_provider", consumer = "atm_consumer")
    public RequestResponsePact configureMockServer(PactDslWithProvider builder) throws IOException {
        return builder
                .given("Account with AccountNumber 2468 exists")
                .uponReceiving("Request for account information with an ID of 2468")
                .path("/accounts/2468/balance").method("GET")
                .willRespondWith().status(200).headers(expectedHeaders())
                .body(expectedAccountBody())
                .toPact();
    }

    @NotNull
    private Map<String, String> expectedHeaders() {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        return headers;
    }

    private PactDslJsonBody expectedAccountBody() {
        return new PactDslJsonBody()
                .id("accountNumber", 1234L)
                .stringType("accountDescription")
                .object("accountOverdraftFacility", expectAmountValue(23.0D))
                .object("accountBalance", expectAmountValue(1000.0D))
                .asBody();
    }

    private DslPart expectAmountValue(final double amount) {
        return new PactDslJsonBody()
                .numberValue("value", amount);
    }

    @Test void checkWeCanProcessTheAccountData(MockServer mockProvider) throws IOException {
        ResponseEntity<String> response = retrieveAccountData(mockProvider);

        assertThat(response.getStatusCode().value()).isEqualTo(200);
        assertThat(response.getHeaders().get("Content-Type")).contains("application/json");
        assertThat(response.getBody()).as("Response body from the accounts service is expected to be populated").isNotEmpty();

        AccountData accountData = Jackson2ObjectMapperBuilder.json().build().readValue(response.getBody(), AccountData.class);
        assertThat(accountData.getAccountNumber()).isEqualTo(1234L);
        assertThat(accountData.getAccountDescription()).isNotEmpty();
        assertThat(accountData.getOverdraftFacility()).isEqualTo(23.0D);
        assertThat(accountData.getBalance()).isEqualTo(1000.0D);
    }

    private ResponseEntity<String> retrieveAccountData(MockServer mockProvider) {
        return new RestTemplate().getForEntity(mockProvider.getUrl() + "/accounts/2468/balance", String.class);
    }

}
