package example.test;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.configure;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.reset;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static jakarta.ws.rs.core.Response.Status.NOT_FOUND;
import static jakarta.ws.rs.core.Response.Status.OK;
import static org.apache.cxf.jaxrs.client.WebClient.create;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import jakarta.ws.rs.core.Response;

import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;

/**
 * An example test war to show how to intereact with Jee deployed WireMock server war
 */
public class WireMockServiceIT {

    private static final String RESPONSE = "{\"test\":\"working\"}";
    private static final String PATH = "/test/two";
    private static final String BASE_URL = "http://localhost:8080";
    private static final String APPLICATION_JSON = "application/json";
    private static final String CONTENT_TYPE = "Content-Type";

    @Before
    public void setup() {

        configure();
        reset();
        stubFor(get(urlEqualTo(PATH))
                .willReturn(aResponse()
                        .withStatus(OK.getStatusCode())
                        .withHeader(CONTENT_TYPE, APPLICATION_JSON)
                        .withBody(RESPONSE)));
    }

    @Test
    public void shouldStubWebService() throws Exception {
        final HttpClient client = HttpClient.newHttpClient();
        final HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + PATH))
                .build();
        final HttpResponse<String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());

        assertThat(httpResponse.statusCode(), is(OK.getStatusCode()));
        assertThat(httpResponse.body(), CoreMatchers.equalTo(RESPONSE));
    }

    @Test
    public void shouldResetAllRequests() {
        reset();
        Response response = create(BASE_URL)
                .path(PATH)
                .get();
        assertThat(response.getStatus(), is(NOT_FOUND.getStatusCode()));
    }
}
