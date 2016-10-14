package models

import play.api.libs.json.{Format, Json}

/**
  * Created by maithili on 13/10/16.
  */
case class Penalty(points:Int, offence:String) {

}


object Penalty {
  implicit val formats: Format[Penalty] = Json.format[Penalty]
}
