import spray.json._
import DefaultJsonProtocol._ // if you don't supply your own Protocol (see below)

case class Color(name: String, red: Int, green: Int, blue: Int)

case class AuthorSprayJson(name: String, age: Int)

case class BookSprayJson(name: String, pages: Int, chapters: Vector[String], authors: Vector[AuthorSprayJson])

object ParseJsonWithSprayJsonLibSimple {
  def main(args: Array[String]): Unit = {


    // THIS WORKS!
    object MyJsonProtocol extends DefaultJsonProtocol {

      implicit object ColorJsonFormat extends RootJsonFormat[Color] {
        def write(c: Color) = JsObject(
          "name" -> JsString(c.name),
          "red" -> JsNumber(c.red),
          "green" -> JsNumber(c.green),
          "blue" -> JsNumber(c.blue)
        )

        def read(value: JsValue) = {
          value.asJsObject.getFields("name", "red", "green", "blue") match {
            case Seq(JsString(name), JsNumber(red), JsNumber(green), JsNumber(blue)) =>
              new Color(name, red.toInt, green.toInt, blue.toInt)
            case _ => throw new DeserializationException("Color expected")
          }
        }
      }

    }

    import MyJsonProtocol._
    val json2 = Color("CadetBlue", 95, 158, 160).toJson
    val color2 = json2.convertTo[Color]
    println(color2)
    val json1 = """{"name":"CadetBlue", "red":955, "green":1581, "blue":1602}""".parseJson
    val color1 = json1.convertTo[Color]
    println(color1)

  }
}