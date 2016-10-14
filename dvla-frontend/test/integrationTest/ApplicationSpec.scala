import org.scalatestplus.play._
import play.api.test.Helpers._
import play.api.test._


class ApplicationSpec extends PlaySpec with OneAppPerTest  {


  "DVLA Controller" should {

    "send 400 on a bad request" in  {
      val result = route(app, FakeRequest(GET, "/points/martin")).get

      status(result) mustBe OK

      contentAsString(result) must include ("martin has 3 points")
    }
  }
}
