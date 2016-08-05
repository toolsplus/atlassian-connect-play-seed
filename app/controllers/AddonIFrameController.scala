package controllers

import com.google.inject.Inject
import io.toolsplus.atlassian.connect.play.actions.JwtAuthenticationActions
import io.toolsplus.atlassian.connect.play.auth.jwt.request.SelfAuthenticationTokenGenerator
import play.api.Logger
import play.api.mvc.Controller

import scala.concurrent.Future

class AddonIFrameController @Inject()(
    jwtAuthenticationActions: JwtAuthenticationActions,
    selfAuthenticationTokenGenerator: SelfAuthenticationTokenGenerator)
    extends Controller {

  private val logger = Logger(classOf[AddonIFrameController])

  import jwtAuthenticationActions.Implicits._

  /** Renders a page bootstrapping a client-side app.
    *
    * @param title Page title.
    * @param entry Entry point of the application.
    * @return Rendered page with all assets included.
    */
  def iframe(title: String, entry: String) =
    jwtAuthenticationActions.withAtlassianHostUser.async { implicit request =>
      selfAuthenticationTokenGenerator.createSelfAuthenticationToken(
        request.hostUser) match {
        case Right(token) =>
          Future.successful(Ok(views.html.iframe(title, entry, token)))
        case Left(error) => {
          logger.error(s"Failed to sign JWT: ${error.getMessage}")
          Future.successful(InternalServerError(error.getMessage))
        }
      }

    }

}
