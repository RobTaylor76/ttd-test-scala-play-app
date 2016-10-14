package models

import play.api.libs.json.{Format, Json}

/**
  * Created by maithili on 13/10/16.
  */
case class Penalties(id: String, penalties: List[Penalty]) {

}

object Penalties {
  implicit val formats: Format[Penalties] = Json.format[Penalties]
}
