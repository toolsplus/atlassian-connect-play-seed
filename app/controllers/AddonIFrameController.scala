package controllers

import com.google.inject.Inject
import io.toolsplus.atlassian.connect.play.actions.AtlassianHostUserAction
import play.api.mvc.InjectedController

class AddonIFrameController @Inject()(
                                       atlassianHostUserAction: AtlassianHostUserAction)
    extends InjectedController {

  /** Renders a standard iframe with JWT verification.
   *
   * @param title Page title.
   * @param entry Entry point of the application.
   * @param dataOptions Optional data options to add to the iframe
   * @return Rendered iframe with all assets included.
   */
  def iframe(title: String, entry: String, dataOptions: Seq[String] = Seq.empty) =
    atlassianHostUserAction { implicit request =>
      Ok(views.html.iframe(title, entry, dataOptions :+ "margin:false",
        request.hostUser.host.key))
    }

}
