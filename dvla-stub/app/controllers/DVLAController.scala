package controllers
import java.io.InputStream

import play.api.libs.json.{JsObject, JsString}
import play.api.mvc._

import scala.io.Source
/**
  * Created by rob on 11/10/16.
  */
class DVLAController extends Controller {
  def penalties(id: String) = Action {
    readPenalties(id) match {
      case Some(content) =>  Ok(content)
      case None => NotFound("?")
    }
  }

  private def readPenalties(id : String) : Option[String] = {
    findResourceForUser(s"/resources/penalties/${id}.json")
  }


  private def findResourceForUser(path: String): Option[String] = {
    val resource = getClass.getResourceAsStream(path)
    if (resource == null) {
      None
    } else {
      Some(readStreamToString(resource))
    }
  }

  private def readStreamToString(is: InputStream) = {
    try Source.fromInputStream(is).mkString.toString
    finally is.close()
  }

}
