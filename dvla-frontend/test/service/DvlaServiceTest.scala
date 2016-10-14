package service

import connector.DvlaConnector
import models.{Penalties, Penalty}
import org.scalatest.{Matchers, WordSpec}
import services.DvlaService
import org.scalatest.mock.MockitoSugar
import org.mockito.Matchers.{eq => Meq, _}
import org.mockito.Mockito._
import scala.concurrent.duration._
import scala.concurrent.{Await, Future}


class DvlaServiceTest extends WordSpec with Matchers with MockitoSugar{

  "DvlaService" should {


    "returns 9 points for vinni" in {

      val mockConnector = mock[DvlaConnector]

      val service = new DvlaService {
        override def connector: DvlaConnector = mockConnector
      }

      val penaltyObject : Penalties = Penalties(id = "Fred", penalties = List(Penalty(points = 3, offence = "blah"), Penalty(points = 6, offence = "blah")))

      when(mockConnector.getPenalties(anyString())).thenReturn(Future.successful(penaltyObject))

      val thing : Option[Int] = Await.result( service.getPoints("vinni"), 10 seconds)

      thing shouldBe  Some(9)


    }

    "return -1 if there is an error" in {
      val mockConnector = mock[DvlaConnector]

      val service = new DvlaService {
        override def connector: DvlaConnector = mockConnector
      }

      val penaltyObject : Penalties = Penalties(id = "Fred", penalties = List(Penalty(points = 3, offence = "blah"), Penalty(points = 6, offence = "blah")))

      when(mockConnector.getPenalties(anyString())).thenReturn(Future.failed(new Exception("blah")))

      val thing : Option[Int] = Await.result(service.getPoints("vinni"), 10 seconds)

       thing shouldBe None

    }

  }

}
