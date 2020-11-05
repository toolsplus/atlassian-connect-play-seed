package controllers

import com.google.inject.Inject
import io.toolsplus.atlassian.connect.play.actions.AtlassianHostUserAction
import javax.inject.Singleton
import play.api.libs.json._
import play.api.mvc.{Action, AnyContent, InjectedController}

import scala.concurrent.{ExecutionContext, Future}

/** Controller exposing REST API endpoints.
  *
  * Requests to all endpoints in this controller have to be made with a valid JWT token.
  */
@Singleton
class ApiController @Inject()(
                               atlassianHostUserAction: AtlassianHostUserAction, implicit val executionContext: ExecutionContext)
    extends InjectedController {

  /** Returns the Hello message.
    *
    * @return Response containing the Hello message.
    */
  def helloMessage: Action[AnyContent] = atlassianHostUserAction.async {
    implicit request =>
      val json =
        Json.obj("message" -> s"Hello ${request.hostUser.host.baseUrl}")
      Future
        .successful(Ok(json))
  }
}
