# Change Log
All notable changes to this project will be documented in this file, which follows the guidelines
on [Keep a CHANGELOG](http://keepachangelog.com/). This project adheres to
[Semantic Versioning](http://semver.org/).
       

## [25.104.1] - 2026-09-11
### Changed
- Updated the parent `maven-parent-pom` to 25.104.1 to take the changes from it
- Updated `maven-common-bom` to 25.104.1

## [25.104.0] - 2026-09-07
First official (non-milestone) release of the Java 25 / WildFly 40 / Jakarta EE 11 line,
consolidating milestones `25.104.0-M1` to `25.104.0-M6`.

### Updated
- Update to Java 25 / WildFly 40 / Jakarta EE 11 (25.104.x release line)
- Update WildFly from `34.0.1.Final` to `40.0.0.Final`, and `wildfly-maven-plugin` from `4.0.0.Final` to `6.0.0.Final`
- Update `maven-parent-pom` and `maven-common-bom` to the released `25.104.0` — Java 25 / Jakarta EE 11 targeting (`java.major.version=25`, `enforcer.java.version.range=[25,)`), Jakarta EE 11 API set, Weld 6, RESTEasy 7, Apache Artemis `2.54.0` under the new `org.apache.artemis` groupId, Jackson `2.21.5` (**CVE-2026-54515**) and the `org.junit:junit-bom` import
- Replace Galleon provisioning with an unpack of the WildFly zip — provisioning was failing on version constraints under the WildFly 40 community universe
- `ejb-jar.xml`: update namespace from `javaee` to `jakartaee` (EJB 4.0)
- `web.xml`: add `load-on-startup` to both WireMock servlets for eager initialisation, and remove the unused `welcome-file-list`
- `wildfly-maven-plugin` `copy-jboss-config`: set `filtering=false` so Maven no longer mangles JBoss `${...}` property expressions in `standalone.xml`
- Move the `stop-server` execution from the `install` phase to `post-integration-test`
- CI agent demand from `ubuntu-j21` to `ubuntu-j25-postgres`

### Added
- `wiremock-service-test` `wildfly-config/standalone.xml`: a custom integration-test server configuration for WildFly 40 — no welcome-content handler, with Elytron and Undertow settings matching the WildFly 40 community schema 20.0
- `beans.xml` with `bean-discovery-mode=none` in `wiremock-service`, to suppress CDI scanning of the Jetty classes bundled inside WireMock
- Exclusion of `org.eclipse.jetty.toolchain:jetty-jakarta-servlet-api`, which clashes with the container's Jakarta Servlet API

### Fixed
- WireMock returned **405 for every POST** on WildFly 40. `maven-parent-pom` excludes `web.xml` from all WARs because CPP services use annotation-based configuration, but WireMock registers its mock and admin handler servlets exclusively through `web.xml` — so neither was registered and Undertow's `DefaultServlet` answered instead. `maven-war-plugin` is now overridden in `wiremock-service` to retain `web.xml` in the WAR while keeping the RESTEasy exclusion

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
