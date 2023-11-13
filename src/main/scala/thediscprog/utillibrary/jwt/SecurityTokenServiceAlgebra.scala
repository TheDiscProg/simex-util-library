package thediscprog.utillibrary.jwt

import thediscprog.utillibrary.jwt.entities.TokenType
import thediscprog.utillibrary.jwt.entities.TokenType.{AuthorisationToken, RefreshToken}

import java.time.Instant

trait SecurityTokenServiceAlgebra {

  def generateRefreshToken(username: String, issuedAt: Instant = Instant.now()): String =
    generateTokenForTokenType(username, RefreshToken, issuedAt)

  def generateAuthorisationBearerToken(username: String, issuedAt: Instant): String =
    generateTokenForTokenType(username, AuthorisationToken, issuedAt)

  def generateTokenForTokenType(
      username: String,
      tokenType: TokenType,
      issuedAt: Instant,
      expiration: Option[Instant] = None
  ): String

}
