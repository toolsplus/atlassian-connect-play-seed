import com.google.inject.AbstractModule
import io.toolsplus.atlassian.connect.play.api.models.AppProperties
import io.toolsplus.atlassian.connect.play.models.PlayAddonProperties

class Module extends AbstractModule {

  override def configure(): Unit = {
    bind(classOf[AppProperties])
      .to(classOf[PlayAddonProperties])
  }

}
