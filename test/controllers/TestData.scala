package controllers

import play.api.Configuration

object TestData {

  val configuration: Configuration = Configuration.from(
    Map(
      "slick.dbs.default.profile" -> "slick.jdbc.H2Profile$",
      "slick.dbs.default.db.driver" -> "org.h2.Driver",
      "slick.dbs.default.db.url" -> "jdbc:h2:mem:default;DB_CLOSE_DELAY=-1;DATABASE_TO_UPPER=FALSE",
      "play.evolutions.db.default.enabled" -> true
    )
  )

}
