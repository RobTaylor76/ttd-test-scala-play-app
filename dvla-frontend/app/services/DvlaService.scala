package services

import connector.DvlaConnector
import models.Penalty
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

trait DvlaService {

  def connector : DvlaConnector

  def countPoints(penalties : List[Penalty]) : Int = {
    penalties.foldLeft(0)(_ + _.points)
  }

  def getPoints(id: String) : Future[Option[Int]] = {
    connector.getPenalties(id).map { penalties =>
      Some(countPoints(penalties.penalties))
    }.recover {
      case e => None
    }
  }

}

object DvlaService extends DvlaService{
  override def connector: DvlaConnector = DvlaConnector
}
