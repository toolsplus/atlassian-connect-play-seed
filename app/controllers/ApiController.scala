package controllers

import com.google.inject.Inject
import io.toolsplus.atlassian.connect.play.actions.JwtAuthenticationActions
import io.toolsplus.atlassian.connect.play.api.models.AtlassianHostUser
import io.toolsplus.atlassian.connect.play.auth.jwt.request.SelfAuthenticationTokenGenerator
import play.api.Logger
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.libs.json._
import play.api.mvc.{Controller, Result}

import scala.concurrent.Future

/** Controller exposing REST API endpoints.
  *
  * Requests to all endpoints in this controller have to be made with a valid JWT token.
  */
class ApiController @Inject()(
    jwtAuthenticationActions: JwtAuthenticationActions,
    selfAuthenticationTokenGenerator: SelfAuthenticationTokenGenerator)
    extends Controller {

  private val logger = Logger(classOf[ApiController])

  import jwtAuthenticationActions.Implicits._

  /** Returns the Hello message.
    *
    * @return Response containing the Hello message.
    */
  def helloMessage = jwtAuthenticationActions.withAtlassianHostUser.async {
    implicit request =>
      val json =
        Json.obj("message" -> s"Hello ${request.hostUser.host.baseUrl}")
      Future
        .successful(json)
        .map(json => withJwtAuthorizationHeaders(Ok(json)))
  }

  private def withJwtAuthorizationHeaders(result: Result)(
      implicit hostUser: AtlassianHostUser): Result = {
    selfAuthenticationTokenGenerator.createSelfAuthenticationToken(hostUser) match {
      case Right(token) => result.withHeaders(AUTHORIZATION -> s"JWT $token")
      case Left(error) => {
        logger.error(s"Failed to sign JWT: ${error.getMessage}")
        InternalServerError(error.getMessage)
      }
    }
  }
}
