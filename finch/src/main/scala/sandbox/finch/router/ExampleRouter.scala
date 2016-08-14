package sandbox.finch.router

import com.twitter.finagle.Service
import com.twitter.finagle.http.{Request, Response}
import io.finch._
import sandbox.finch.router.ExampleRouter.{ExampleClass, JsonpExampleClass}
import io.finch.circe.dropNullKeys._

import io.circe._
import io.circe.generic.auto._
import io.circe.syntax._

//import printJsonp._

class ExampleRouter {

  val example1: Endpoint[String] = get("example1") {
    Ok("this is a example1")
  }

  val example2: Endpoint[Int] = get("example2") {
    Ok(100)
  }

  val example3: Endpoint[ExampleClass] = get("example3") {
    Ok(ExampleClass(100, "this is a exampleClass"))
  }

  val example4: Endpoint[ExampleClass] = get("example4") {
    Ok(ExampleClass(101, "this is a exampleClass2", Some("option!!")))
  }

  val example5: Endpoint[ExampleClass] = get("example5") {
    // 500が返る
    Ok(ExampleClass(100, null))
  }

  val example6: Endpoint[ExampleClass] = get("example6" :: paramOption("cb")) { cb: Option[String] =>
    Ok(ExampleClass(101, "this is a exampleClass2", Some("example6")))
  }

  val example7: Endpoint[String] = get("example7" :: paramOption("cb")) { cb: Option[String] =>
    val ex = JsonpExampleClass(ExampleClass(101, "this is a exampleClass2", Some("example6")), cb).asJson
    Ok(ex.toString()).withHeader("content-type", "text/plain")
  }


    /*
    cb match {
      case None =>
        Ok(ExampleClass(101, "this is a exampleClass2"))
      case Some(s) =>
        Ok(ExampleClass(101, s"this is a exampleClass2: $s"))

    }
    */


  val service: Service[Request, Response] = (example1 :+: example2 :+: example3 :+:
      /* example4 :+:*/ example5 :+: example6 :+: example7).toService


}

object ExampleRouter {
  case class ExampleClass(id: Int, text: String, text2: Option[String] = None)
  case class JsonpExampleClass(exampleClass: ExampleClass, callback: Option[String])


  def apply(): ExampleRouter = new ExampleRouter()
}
