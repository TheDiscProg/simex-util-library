package io.github.thediscprog.utillibrary.jwt.entities

import io.circe.{Decoder, Encoder}
import io.circe.generic.semiauto.{deriveDecoder, deriveEncoder}

case class Principal(username: String, level: String)

object Principal {
  implicit val principalDecoder: Decoder[Principal] = deriveDecoder
  implicit val principalEncoder: Encoder[Principal] = deriveEncoder
}
