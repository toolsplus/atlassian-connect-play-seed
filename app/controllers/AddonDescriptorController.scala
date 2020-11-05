package controllers

import com.google.inject.Inject
import io.toolsplus.atlassian.connect.play.api.models.AppProperties
import play.api.mvc.InjectedController

class AddonDescriptorController @Inject()(addonProperties: AppProperties)
    extends InjectedController {

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
