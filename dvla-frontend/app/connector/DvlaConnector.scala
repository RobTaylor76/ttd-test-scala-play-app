package connector

import models.Penalties
import play.api.libs.ws.{WS, WSResponse}
import play.api.Play.current

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

trait DvlaConnector {


  def wsInterface : WSHttp = WSHttp

  def getPenalties(id: String) : Future[Penalties] = {
    wsInterface.get(s"http://localhost:9001/penalties/${id}").map { response =>
      response.json.as[Penalties]
    }
  }
}

object DvlaConnector extends DvlaConnector {

}


trait WSHttp {
   def get(url :String) : Future[WSResponse]
}

object WSHttp extends WSHttp {
  override def get(url: String): Future[WSResponse] =  WS.url(url).get()
}