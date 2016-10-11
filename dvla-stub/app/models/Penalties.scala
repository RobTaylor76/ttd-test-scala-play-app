package models

import play.api.libs.json.{Format, Json}

case class Penalty(points: Int, offence: String)
case class Penalties(id: String, penalties: List[Penalty])

object Penalties {
  implicit val formats: Format[Penalties] = Json.format[Penalties]
}

object Penalty {
  implicit val formats: Format[Penalty] = Json.format[Penalty]
}
