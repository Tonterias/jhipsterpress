import _root_.io.gatling.core.scenario.Simulation
import ch.qos.logback.classic.{Level, LoggerContext}
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import org.slf4j.LoggerFactory

import scala.concurrent.duration._

/**
 * Performance test for the Frontpageconfig entity.
 */
class FrontpageconfigGatlingTest extends Simulation {

    val context: LoggerContext = LoggerFactory.getILoggerFactory.asInstanceOf[LoggerContext]
    // Log all HTTP requests
    //context.getLogger("io.gatling.http").setLevel(Level.valueOf("TRACE"))
    // Log failed HTTP requests
    //context.getLogger("io.gatling.http").setLevel(Level.valueOf("DEBUG"))

    val baseURL = Option(System.getProperty("baseURL")) getOrElse """http://localhost:8080"""

    val httpConf = http
        .baseURL(baseURL)
        .inferHtmlResources()
        .acceptHeader("*/*")
        .acceptEncodingHeader("gzip, deflate")
        .acceptLanguageHeader("fr,fr-fr;q=0.8,en-us;q=0.5,en;q=0.3")
        .connectionHeader("keep-alive")
        .userAgentHeader("Mozilla/5.0 (Macintosh; Intel Mac OS X 10.10; rv:33.0) Gecko/20100101 Firefox/33.0")
        .silentResources // Silence all resources like css or css so they don't clutter the results

    val headers_http = Map(
        "Accept" -> """application/json"""
    )

    val headers_http_authenticated = Map(
        "Accept" -> """application/json""",
        "X-XSRF-TOKEN" -> "${xsrf_token}"
    )

    val keycloakHeaders = Map(
        "Accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8",
        "Upgrade-Insecure-Requests" -> "1"
    )

    val scn = scenario("Test the Frontpageconfig entity")
        .exec(http("First unauthenticated request")
        .get("/api/account")
        .headers(headers_http)
        .check(status.is(401))
        .check(headerRegex("Set-Cookie", "XSRF-TOKEN=(.*);[\\s]").saveAs("xsrf_token"))
        ).exitHereIfFailed
        .pause(10)
        .exec(http("Authentication")
        .post("/api/authentication")
        .headers(headers_http_authenticated)
        .formParam("username", "admin")
        .formParam("password", "admin")
        .formParam("remember-me", "true")
        .formParam("submit", "Login")
        .check(headerRegex("Set-Cookie", "XSRF-TOKEN=(.*);[\\s]").saveAs("xsrf_token"))).exitHereIfFailed
        .pause(2)
        .exec(http("Authenticated request")
        .get("/api/account")
        .headers(headers_http_authenticated)
        .check(status.is(200)))
        .pause(10)
        .repeat(2) {
            exec(http("Get all frontpageconfigs")
            .get("/api/frontpageconfigs")
            .headers(headers_http_authenticated)
            .check(status.is(200)))
            .pause(10 seconds, 20 seconds)
            .exec(http("Create new frontpageconfig")
            .post("/api/frontpageconfigs")
            .headers(headers_http_authenticated)
            .body(StringBody("""{
                "id":null
                , "creationDate":"2020-01-01T00:00:00.000Z"
                , "topNews1":null
                , "topNews2":null
                , "topNews3":null
                , "topNews4":null
                , "topNews5":null
                , "latestNews1":null
                , "latestNews2":null
                , "latestNews3":null
                , "latestNews4":null
                , "latestNews5":null
                , "breakingNews1":null
                , "recentPosts1":null
                , "recentPosts2":null
                , "recentPosts3":null
                , "recentPosts4":null
                , "featuredArticles1":null
                , "featuredArticles2":null
                , "featuredArticles3":null
                , "featuredArticles4":null
                , "featuredArticles5":null
                , "featuredArticles6":null
                , "featuredArticles7":null
                , "featuredArticles8":null
                , "featuredArticles9":null
                , "featuredArticles10":null
                , "popularNews1":null
                , "popularNews2":null
                , "popularNews3":null
                , "popularNews4":null
                , "popularNews5":null
                , "popularNews6":null
                , "popularNews7":null
                , "popularNews8":null
                , "weeklyNews1":null
                , "weeklyNews2":null
                , "weeklyNews3":null
                , "weeklyNews4":null
                , "newsFeeds1":null
                , "newsFeeds2":null
                , "newsFeeds3":null
                , "newsFeeds4":null
                , "newsFeeds5":null
                , "newsFeeds6":null
                , "usefulLinks1":null
                , "usefulLinks2":null
                , "usefulLinks3":null
                , "usefulLinks4":null
                , "usefulLinks5":null
                , "usefulLinks6":null
                , "recentVideos1":null
                , "recentVideos2":null
                , "recentVideos3":null
                , "recentVideos4":null
                , "recentVideos5":null
                , "recentVideos6":null
                }""")).asJSON
            .check(status.is(201))
            .check(headerRegex("Location", "(.*)").saveAs("new_frontpageconfig_url"))).exitHereIfFailed
            .pause(10)
            .repeat(5) {
                exec(http("Get created frontpageconfig")
                .get("${new_frontpageconfig_url}")
                .headers(headers_http_authenticated))
                .pause(10)
            }
            .exec(http("Delete created frontpageconfig")
            .delete("${new_frontpageconfig_url}")
            .headers(headers_http_authenticated))
            .pause(10)
        }

    val users = scenario("Users").exec(scn)

    setUp(
        users.inject(rampUsers(Integer.getInteger("users", 100)) over (Integer.getInteger("ramp", 1) minutes))
    ).protocols(httpConf)
}
