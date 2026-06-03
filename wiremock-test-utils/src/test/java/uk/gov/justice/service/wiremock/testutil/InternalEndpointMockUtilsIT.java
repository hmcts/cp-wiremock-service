package uk.gov.justice.service.wiremock.testutil;

import static com.github.tomakehurst.wiremock.client.WireMock.configureFor;
import static com.github.tomakehurst.wiremock.client.WireMock.reset;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static jakarta.ws.rs.core.Response.Status.NOT_FOUND;
import static jakarta.ws.rs.core.Response.Status.OK;
import static org.apache.cxf.jaxrs.client.WebClient.create;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static uk.gov.justice.service.wiremock.testutil.InternalEndpointMockUtils.stubPingFor;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

import com.github.tomakehurst.wiremock.WireMockServer;
import org.apache.cxf.jaxrs.client.WebClient;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for the {@link InternalEndpointMockUtils} class.
 */
public class InternalEndpointMockUtilsIT {

    private static final String SERVICE_NAME = "test-command-api";
    private static final String PONG = "pong";

    private WireMockServer wireMockServer;
    private String baseUrl;

    @BeforeEach
    public void setUp() {
        wireMockServer = new WireMockServer(wireMockConfig().dynamicPort());
        wireMockServer.start();
        baseUrl = "http://localhost:" + wireMockServer.port();
        configureFor("localhost", wireMockServer.port());
        stubPingFor(SERVICE_NAME);
    }

    @AfterEach
    public void tearDown() {
        reset();
        wireMockServer.stop();
    }

    @Test
    public void shouldBeWellDefinedUtilityClass() throws Exception {
        assertThat(Modifier.isFinal(InternalEndpointMockUtils.class.getModifiers()), is(true));
        final Constructor<InternalEndpointMockUtils> constructor =
                InternalEndpointMockUtils.class.getDeclaredConstructor();
        assertThat(Modifier.isPrivate(constructor.getModifiers()), is(true));
    }

    @Test
    public void shouldStubPingForGetRequest() throws Exception {
        final HttpClient client = HttpClient.newHttpClient();
        final HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(baseUrl + "/" + SERVICE_NAME + "/internal/metrics/ping"))
                .build();
        final HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        assertThat(response.statusCode(), is(OK.getStatusCode()));
        assertThat(response.body(), equalTo(PONG));
    }

    @Test
    public void shouldStubPingForHeadRequest() {
        final Response response = buildWebClient(SERVICE_NAME).head();
        verifyStatusEquals(response, OK);
    }

    @Test
    public void shouldOnlyStubForGivingService() {
        final WebClient client = buildWebClient("aTestService");

        verifyStatusEquals(client.get(), NOT_FOUND);
        verifyStatusEquals(client.head(), NOT_FOUND);
    }

    @Test
    public void shouldResetAllRequests() {
        reset();

        final WebClient client = buildWebClient(SERVICE_NAME);

        verifyStatusEquals(client.get(), NOT_FOUND);
        verifyStatusEquals(client.head(), NOT_FOUND);
    }

    private WebClient buildWebClient(final String serviceName) {
        return create(baseUrl).path("/" + serviceName + "/internal/metrics/ping");
    }

    private void verifyStatusEquals(final Response response, final Status status) {
        assertThat(response.getStatus(), is(status.getStatusCode()));
    }
}
