package controllers

import io.toolsplus.atlassian.connect.play.models.PlayAddonProperties
import org.scalatestplus.play.PlaySpec
import org.scalatestplus.play.guice.GuiceOneAppPerSuite
import play.api.{Application, Configuration}
import play.api.inject.guice.GuiceApplicationBuilder
import play.api.libs.json.Json
import play.api.mvc.ControllerComponents
import play.api.test.FakeRequest
import play.api.test.Helpers._

class AddonDescriptorControllerSpec extends PlaySpec with GuiceOneAppPerSuite {

  val config: Configuration = Configuration.reference.withFallback(TestData.configuration)
  val addonProperties = new PlayAddonProperties(config)
  val controller = new AddonDescriptorController(addonProperties)

  val controllerComponents: ControllerComponents =
    app.injector.instanceOf[ControllerComponents]
  controller.setControllerComponents(controllerComponents)

  override def fakeApplication(): Application = {
    GuiceApplicationBuilder(configuration = config).build()
  }

  "AddonDescriptorController" when {

    "GET atlassian-connect.json" should {

      "render descriptor" in {
        val descriptor = controller.descriptor.apply(FakeRequest())

        status(descriptor) mustBe OK
        contentType(descriptor) mustBe Some(JSON)

        val json = Json.parse(contentAsString(descriptor))
        (json \ "key").as[String] mustBe addonProperties.key
        (json \ "baseUrl").as[String] mustBe addonProperties.baseUrl
        (json \ "name").as[String] mustBe config.get[String]("addon.name")
      }

    }

    "GET to base URL" should {

      "redirect to descriptor" in {
        val redirect = controller.redirectToDescriptor.apply(FakeRequest())

        redirectLocation(redirect) mustBe Some(
          routes.AddonDescriptorController
            .descriptor()
            .url)
      }

    }

  }

}
