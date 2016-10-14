package connector

import models.{Penalties, Penalty}
import org.mockito.Matchers.{eq => Meq, _}
import org.mockito.Mockito._
import org.scalatest.mock.MockitoSugar
import org.scalatest.{Matchers, WordSpec}
import play.api.libs.json.Json
import play.api.libs.ws.WSResponse

import scala.concurrent.duration._
import scala.concurrent.{Await, Future}

class DvlaConnectorTest extends WordSpec with Matchers with MockitoSugar  {

  "DvlaConnector" should {

    "handle success for Fred" in {
      val dummyWSHttp = mock[WSHttp]

      val dummyWSResponse = mock[WSResponse]

      val connector = new DvlaConnector {
        override def wsInterface = dummyWSHttp
      }
      val expectedResult = Penalties(id = "Fred", penalties = List(Penalty(points = 3, offence = "blah")))
      val test = Json.toJson(expectedResult)
      val expectedURL = "http://localhost:9001/penalties/Fred"
      when(dummyWSHttp.get(Meq(expectedURL))).thenReturn(Future.successful(dummyWSResponse))
      when(dummyWSResponse.json).thenReturn(test)

      val thing : Penalties = Await.result(connector.getPenalties("Fred"), 10 seconds)


     thing shouldBe expectedResult

    }

    "handle failure for Fred" in {

      val dummyWSHttp = mock[WSHttp]

      val dummyWSResponse = mock[WSResponse]

      val connector = new DvlaConnector {
        override def wsInterface = dummyWSHttp
      }

      when(dummyWSHttp.get(anyString())).thenReturn(Future.failed(new Exception("blahh")))

      intercept[Exception] {
      Await.result(connector.getPenalties("Fred"), 10 seconds)
      }

    }
  }
}
