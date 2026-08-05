# Change Log
All notable changes to this project will be documented in this file, which follows the guidelines
on [Keep a CHANGELOG](http://keepachangelog.com/). This project adheres to
[Semantic Versioning](http://semver.org/).
       

## [25.104.0-M6] - 2026-08-05
### Updated
- Bumped `maven-common-bom.version` to `25.104.0-M7` — picks up the Apache Artemis client bump `2.53.0` → `2.54.0`.

## [25.104.0-M5] - 2026-07-27
### Updated
- Update `maven-parent-pom` to 25.104.0-M7 (buildnumber-plugin warning fix)
- Update `maven-common-bom` to 25.104.0-M6 — picks up Jackson `2.21.5` (**CVE-2026-54515**) and the `org.junit:junit-bom` import

## [25.104.0-M4] - 2026-06-18
### Updated
- Update `maven-parent-pom` to 25.104.0-M6
- Update `maven-common-bom` to 25.104.0-M5

## [21.0.0-M1] - 2026-06-02
### Updated
- Update to Java 21 and Jakarta EE 10
- Update WireMock to 3.10.0 (`org.wiremock:wiremock`) — groupId changed from `com.github.tomakehurst` to `org.wiremock` in WireMock 3.x
- Update WildFly to 34.0.1.Final
- Update `maven-parent-pom` to 21.0.0-SNAPSHOT
- Update `maven-common-bom` to 21.0.0-SNAPSHOT
- Replace `javax:javaee-api` with `jakarta.platform:jakarta.jakartaee-api`
- Replace `javax.servlet:javax.servlet-api` with `jakarta.servlet:jakarta.servlet-api`
- Migrate `javax.ws.rs.*` imports to `jakarta.ws.rs.*` across all Java source files
- Update `web.xml` from J2EE Servlet 2.4 to Jakarta EE Servlet 6.0 namespace
- Update `jboss-deployment-structure.xml` for WildFly 34: remove `resteasy` subsystem exclusion (merged into `jaxrs`), replace `javax.ws.rs.api` with `jakarta.ws.rs.api`, remove stale WildFly 26 module exclusions
- Remove explicit `httpclient` 4.x dependency — superseded by WireMock 3.x's bundled Apache HttpComponents 5.x
- Add explicit version for `net.trajano.commons:commons-testing` (2.0.1) — no longer managed by the BOM

## [17.0.2] - 2025-06-03
### Updated
- Revert Wiremock to 2.x and junit 4 (as wiremock 3.x is not compatible with wildfly 26 and expecting application server to be compatible with jakarta EE 9)
- This release is functionality same as 17.0.0

## [17.0.1] - 2023-07-12
### Updated
- Update Wiremock to 3.0.0-beta-10
- Update junit5

## [17.0.0] - 2023-05-25
### Updated
- Update Wiremock to 2.27.2
- Update to Java 17
- Update integration tests to use Wildfly 26.1.2.Final
- Update Wildfly-Maven-Plugin to 4.0.0.Final
### Changed
- Bumped version to 17.0.0 to match Java and Framework versions

## [2.0.0] - 2018-11-24
### Updated
- update wiremock dependency to 2.0.19
- updated travis to new build processes

### Changed
- BREAKING CHANGES for proxy behaviour
  This is why the version is bumped to 2.0.0
- Additional changes to get this working by Shirly Tarboton

## [1.3.0] - 2017-07-04

### Added
- Static PDF file  (sample.pdf) to wiremock-service so that it can be served as streaming content

## [1.2.0] - 2016-08-08

### Updated
- Update Wiremock to the forked version able to work behind a http proxy

## [1.1.0] - 2016-08-08

### Added
- Test utils module containing stub helper for internal endpoints - currently only ping

## [1.0.0] - 2016-06-09
### Added
- Initial implementation of the WireMock service
