package controllers

import com.google.inject.Inject
import io.toolsplus.atlassian.connect.play.models.AddonProperties
import play.api.mvc.{Action, Controller}

class AddonDescriptorController @Inject()(addonProperties: AddonProperties)
    extends Controller {

  /** Renders the JSON descriptor.
    *
    * @return Rendered JSON descriptor.
    */
  def descriptor = Action {
    Ok(
      views.json.descriptor(addonProperties.key,
                            addonProperties.name,
                            addonProperties.baseUrl)).as(JSON)
  }

  /** Redirects request to the add-on descriptor.
    *
    * This is useful to easily redirect specific routes to the add-on descriptor.
    *
    * @return Rendered JSON descriptor.
    */
  def redirectToDescriptor =
    Action(Redirect(routes.AddonDescriptorController.descriptor()))

}
