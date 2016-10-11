package controllers

import org.scalatest.{Matchers, WordSpec}
import play.api.libs.json.{Format, JsObject, JsString, Json}
import play.api.test._
import play.api.test.Helpers._

import scala.io.Source
/**
  * Created by rob on 11/10/16.
  */

case class Penalty(points: Int, offence: String)
case class Penalties(id: String, penalties: List[Penalty])

object Penalties {
  implicit val formats: Format[Penalties] = Json.format[Penalties]
}

object Penalty {
  implicit val formats: Format[Penalty] = Json.format[Penalty]
}


class DVLAControllerTest extends WordSpec with Matchers {

  val request = FakeRequest()

  val testClass = new DVLAController

  def readTestFile(id: String) : String = {
    val resource = getClass.getResourceAsStream(s"/resources/penalties/${id}.json")
    try Source.fromInputStream(resource).mkString.toString
    finally resource.close()
  }

  "Get penalities" should {

    "return response from the file for supplied id" in {

      val response = testClass.penalties("vinnie")(request)

      status(response) shouldBe 200

      contentAsJson(response) shouldBe Json.parse(readTestFile("vinnie"))

      val parsedResponse = contentAsJson(response).as[Penalties]

      parsedResponse shouldBe Penalties(id = "vinnie", penalties = List(Penalty(points = 3, offence = "speeding")))

    }

    "return 404 if no file" in {
      val response = testClass.penalties("wtf")(request)

      status(response) shouldBe 404
    }
  }

}
