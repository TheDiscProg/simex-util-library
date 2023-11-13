package thediscprog.utillibrary.jwt

import io.circe.syntax.EncoderOps
import pdi.jwt.{JwtAlgorithm, JwtCirce, JwtClaim}
import thediscprog.utillibrary.jwt.entities.{Principal, TokenType}

import java.time.Instant

class JwtSecurityTokenService(encryptionKey: String) extends SecurityTokenServiceAlgebra {

  private val algorithm = JwtAlgorithm.HS256

  override def generateTokenForTokenType(
      username: String,
      tokenType: TokenType,
      issuedAt: Instant,
      expiration: Option[Instant]
  ): String = {
    val principal = Principal(username, "basic")
    val claim = JwtClaim(
      content = principal.asJson.noSpaces,
      issuedAt = Some(issuedAt.getEpochSecond),
      expiration = expiration.fold(
        Some(issuedAt.plusSeconds(tokenType.ttl).getEpochSecond)
      )(e => Some(e.getEpochSecond))
    )
    JwtCirce.encode(claim, encryptionKey, algorithm)
  }

  def decodeJWTToken(jwt: String): Option[JwtClaim] =
    JwtCirce.decode(jwt, encryptionKey, Seq(algorithm)).toOption
}
