package thediscprog.utillibrary.jwt

import io.circe.syntax.EncoderOps
import pdi.jwt.{JwtAlgorithm, JwtCirce, JwtClaim}
import thediscprog.utillibrary.jwt.entities.Principal

import java.time.Instant

class JwtSecurityTokenService(tokenKey: String) extends SecurityTokenServiceAlgebra {

  private val algorithm: JwtAlgorithm = JwtAlgorithm.HS256

  override def generateTokenFor(username: String, instant: Instant): String = {
    val principal = Principal(username, "basic")
    val claim = JwtClaim(
      content = principal.asJson.noSpaces,
      issuedAt = Some(instant.getEpochSecond)
    )
    JwtCirce.encode(claim, tokenKey, algorithm)
  }
}

object JwtSecurityTokenService {
  def apply(secretKey: String): SecurityTokenServiceAlgebra = new JwtSecurityTokenService(secretKey)
}
