package views

import org.apache.commons.lang3.StringEscapeUtils

case class JsDataOptions(options: Map[String, Any] = Map()) {
  def +(entry: (String, Any)): JsDataOptions = {
    require(entry._1 != null)
    JsDataOptions(options + entry)
  }
  def margin(value: Boolean): JsDataOptions = this + ("margin" -> value)
  def base(value: Boolean): JsDataOptions = this + ("base" -> value)
  def sizeToParent(value: Boolean): JsDataOptions = this + ("sizeToParent" -> value)
  def resize(value: Boolean): JsDataOptions = this + ("resize" -> value)
  lazy val encoded: String = {
    val asString: Any => String = Option(_).fold("")(_.toString)
    val encodeAsHtmlAttribute: String => String = StringEscapeUtils.escapeHtml4(_).replaceAll("'", "&#39;")
    options.map {
      case (k, v) => encodeAsHtmlAttribute(k) + ":" + (asString andThen encodeAsHtmlAttribute)(v)
    }.toSeq.mkString(";")
  }
}
