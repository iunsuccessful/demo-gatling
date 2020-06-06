package iunsuccessful.demo

import io.gatling.core.Predef._
import io.gatling.core.scenario.Simulation
import io.gatling.http.Predef._

import scala.concurrent.duration._
/**
  * 依韵 2019-11-04
  */
class HarvestSimulationTest extends Simulation {
  val httpProtocol = http // 4
    .baseURL("http://harvest-yc.3songshu.com") // 5
    .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8") // 6
    .acceptLanguageHeader("en-US,en;q=0.5")
    .acceptEncodingHeader("gzip, deflate")
    .userAgentHeader("Mozilla/5.0 (Windows NT 5.1; rv:31.0) Gecko/20100101 Firefox/31.0")
  val scn = scenario("BasicSimulation") // 7
    .exec(
    http("request_1") // 8
      .post("/api/order/order_no")
      .body(StringBody("""{"orderNo": "20191104171309", "shop": "TB"}""")).asJSON
  ) // 9
  setUp( // 11
//    scn.inject(heavisideUsers(36000) over (360 seconds)) // 12
    scn.inject(
      constantUsersPerSec(140) during (150 seconds)
    ) // 12
  ).protocols(httpProtocol) // 13

}

