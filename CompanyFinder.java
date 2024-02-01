package com.example;

import com.fasterxml.jackson.databind.json.JsonMapper;

import java.net.*;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.Optional;

public class CompanyFinder {

    private static final String ENDPOINT = "https://some.api";

    // I would throw only necessary exceptions, such as URISyntaxException or JsonProcessingException rather than
    //  one general Exception. But as I expect the methods themselves throw many different ones I guess
    //  Exception is fine. It would be nice to consider some try-catch blocks instead and just return
    //  Optional.ofEmpty() if the logic would allow for such thing.
    public Optional<String> getCompanyName(String identification) throws Exception {
        HttpClient httpClient = HttpClient.newHttpClient();
        // I would have liked some builder pattern used here, but URI doesn't seem to provide any.
        //  I would have used some RestClient, where I would be able to specify URI, then path and
        //  additional query parameters as here. But that would change not only the URI building but
        //  the whole request making and sending.
        //  The best friend would be the framework, such as Quarkus, Spring Boot, where the framework
        //  would make it all this behind the scenes and we would only create a simple interface for example. https://quarkus.io/guides/rest-client-reactive#create-the-interface
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(ENDPOINT + "/company-info.json?identification=" + identification).)
                .build();
        HttpResponse<String> httpResponse = httpClient.send(request, BodyHandlers.ofString());

        //  Again, with a framework we would be able to remove this manual parsing of String Json response.
        ApiResponse body = new JsonMapper().readValue(httpResponse.body(), ApiResponse.class);

        return Optional.ofNullable(
                body.error() != null && body.companyFound()
                        ? body.companyData().companyName()
                        : null);
    }

    record ApiResponse(String error, boolean companyFound, CompanyData companyData) {

        record CompanyData(String companyName) {

        }

    }

}
