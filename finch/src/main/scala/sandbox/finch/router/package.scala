package sandbox.finch

import io.circe.jackson._
import io.circe.{Json, Printer}
import io.finch.circe.{Decoders, Encoders}
/*
package object router extends Encoders with Decoders {

  override protected def print(json: Json): String = Printer.noSpaces.pretty(json)

  /**
    * Provides a [[Printer]] that drops null keys.
    */
  object dropNullKeys extends Encoders with Decoders {
    //private val printer: Printer = Printer.noSpaces.copy(dropNullKeys = true)
    override protected def print(json: Json): String = Printer.noSpaces.pretty(json)
    //override protected def print(json: Json): String = printer.pretty(json)
  }

  /**
    * Provides Jackson Serializer.
    */
  object jacksonSerializer extends Encoders with Decoders {
    override protected def print(json: Json): String = jacksonPrint(json)
  }
}
*/