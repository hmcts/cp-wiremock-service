# Wiremock Service

[![Coverage Status](https://coveralls.io/repos/github/hmcts/cp-wiremock-service/badge.svg?branch=main)](https://coveralls.io/github/hmcts/cp-wiremock-service?branch=main)

This project packages [WireMock](http://wiremock.org/) as a .war file, so it can be deployed to a container as a standalone service.

## Usage

1. Deploy the `wiremock-service` .war file to your container. It contains a `jboss-web.xml` that sets the
context root to "/" so if deployed to JBoss or WildFly it will appear at the server root. Deployment to
other containers will require configuration to achieve the same result.

2. Add a WireMock rule to your integration tests and set up your stub endpoints just like you would if
starting a local WireMock server directly in your tests.

## Modules

* `wiremock-service` - the mocking service
* `wiremock-service-test` - an example of how to use the mocking service as part of an integration test using an embedded WildFly instance
* `wiremock-test-utils` - utility module for tests
