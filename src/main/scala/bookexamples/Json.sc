import java.util.Date

object Json extends App {
  trait JsWriter[A] {
    def write(data: A): JsValue[A]
  }

  object JsUtil {
    def toJson[A](data: A) (implicit writer: JsWriter[A]): JsValue = {
      writer.write(data)
    }
  }

  sealed trait Visitor {
    def id: String
    def createdAt: Date
    def age: Long = new Date().getTime() - createdAt.getTime()
  }

  final case class Anonymous(
    val id: String,
    val createdAt: Date = new Date()
  ) extends Visitor

  final case class User(
    val id: String,
    val email: String,
    val createdAt: Date = new Date()
  ) extends Visitor

  implicit object AnonymousWriter extends JsWriter {
    def write(data: Anonymous): JsValue[Anonymous] = {
      JsValue(data.id)
    }
  }

  implicit object UserWriter extends JsWriter {
    def write(data: User): JsValue[User] = {
      JsValue(data.id + data.email)
    }
  }

  implicit object VisitorWriter extends JsWriter[Visitor] { def write(value: Visitor) = value match {
    case anon: Anonymous => JsUtil.toJson(anon)
    case user: User => JsUtil.toJson(user) }
  }


}

