package controllers

import io.toolsplus.atlassian.connect.play.models.AddonProperties
import org.scalatestplus.play.PlaySpec
import org.scalatestplus.play.guice.GuiceOneAppPerSuite
import play.api.Configuration
import play.api.inject.guice.GuiceApplicationBuilder
import play.api.libs.json.Json
import play.api.test.FakeRequest
import play.api.test.Helpers._

class AddonDescriptorControllerSpec extends PlaySpec with GuiceOneAppPerSuite {

  val config = Configuration.reference ++ TestData.configuration
  val addonProperties = new AddonProperties(config)
  val $ = new AddonDescriptorController(addonProperties)

  override def fakeApplication() = {
    GuiceApplicationBuilder(configuration = config).build()
  }

  "AddonDescriptorController" when {

    "GET atlassian-connect.json" should {

      "render descriptor" in {
        val descriptor = $.descriptor.apply(FakeRequest())

        status(descriptor) mustBe OK
        contentType(descriptor) mustBe Some(JSON)

        val json = Json.parse(contentAsString(descriptor))
        (json \ "key").as[String] mustBe addonProperties.key
        (json \ "baseUrl").as[String] mustBe addonProperties.baseUrl
        (json \ "name")
          .as[String] mustBe config.getString("atlassian.connect.name").get
      }

    }

    "GET to base URL" should {

      "redirect to descriptor" in {
        val redirect = $.redirectToDescriptor.apply(FakeRequest())

        redirectLocation(redirect) mustBe Some(
          routes.AddonDescriptorController
            .descriptor()
            .url)
      }

    }

  }

}
