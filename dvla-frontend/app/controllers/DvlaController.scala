package controllers

import play.api.mvc.{Action, Controller}
import services.DvlaService
import scala.concurrent.ExecutionContext.Implicits.global

trait DvlaController extends Controller {

   def showPoints(id :String) = Action.async { implicit request =>

     service.getPoints(id) map { points =>
      points match {
        case Some(points) => Ok(views.html.show(id, points))
        case None => BadRequest("")
      }
    }
  }

  def service : DvlaService
}

object DvlaController extends DvlaController {
  override def service: DvlaService = DvlaService
}

