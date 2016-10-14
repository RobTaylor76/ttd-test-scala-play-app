package controller

import controllers.DvlaController
import org.mockito.Matchers._
import org.mockito.Mockito._
import org.scalatest.mock.MockitoSugar
import org.scalatest.{Matchers, WordSpec}
import play.api.test.FakeRequest
import play.api.test.Helpers._
import services.DvlaService

import scala.concurrent.Future

class DvlaControllerTest extends WordSpec with Matchers with MockitoSugar{

  "DvalController" should {

    val mockService = mock[DvlaService]

    val controller = new DvlaController {
      override def service: DvlaService = mockService
    }

    val request = FakeRequest()

    "return 9 points for Vinni" in {

      when(mockService.getPoints(anyString())).thenReturn(Future.successful(Some(9)))
      val response =  controller.showPoints("Vinni")(request)
      status(response) shouldBe 200
      contentAsString(response) should include ("Vinni has 9 points")
    }

    "return error if no file" in {
      when(mockService.getPoints(anyString())).thenReturn(Future.successful(None))
      val response = controller.showPoints("xxx")(request)
      status(response) shouldBe 400
    }

  }


}
