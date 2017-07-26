import json._

@accessor case class AuthorScalaJson(name: String, age: Int)
@accessor case class BookScalaJson(name: String, pages: Option[Int], chapters: Seq[String], authors: Option[List[AuthorScalaJson]])

object ParseJsonWithScalaJsonLibSimple {
  def main(args: Array[String]): Unit = {
    val jsonAll = """[{"name":"book", "chapters":["1","2"]},{"name":"book2", "pages": 234, "chapters":["12","22"], "authors":[{"name":"A1", "age":30}, {"name":"A2", "age":50}] }]"""
    val book = JValue.fromString(jsonAll).toObject[List[BookScalaJson]]
    println(book)
  }
}