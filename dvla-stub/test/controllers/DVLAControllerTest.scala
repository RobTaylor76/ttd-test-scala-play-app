package controllers

import helpers.FileHelper
import org.scalatest.{Matchers, WordSpec}
import play.api.libs.json.{Format, JsObject, JsString, Json}
import play.api.test._
import play.api.test.Helpers._
import models.{Penalties, Penalty}

import scala.io.Source
/**
  * Created by rob on 11/10/16.
  */

class DVLAControllerTest extends WordSpec with Matchers {

  val request = FakeRequest()

  val testClass = new DVLAController



  "Get penalities" should {

    "return response from the file for supplied id" in {

      val response = testClass.penalties("vinnie")(request)

      status(response) shouldBe 200

      contentAsJson(response) shouldBe Json.parse(FileHelper.readTestFile("penalties","vinnie"))

      val parsedResponse = contentAsJson(response).as[Penalties]

      parsedResponse shouldBe Penalties(id = "vinnie", penalties = List(Penalty(points = 3, offence = "speeding")))

    }

    "return 404 if no file" in {
      val response = testClass.penalties("wtf")(request)

      status(response) shouldBe 404
    }
  }

}
