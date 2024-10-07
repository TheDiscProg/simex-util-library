package io.github.thediscprog.utillibrary.jwt.entities

trait TokenType {
  val name: String
  val ttl: Long
  val header: String
}

object TokenType {

  case object AuthorisationToken extends TokenType {
    private val TTL_Seconds: Long = 300L
    override val name: String = "AuthorisationToken"
    override val ttl: Long = TTL_Seconds
    override val header: String = "access_token"
  }

  case object RefreshToken extends TokenType {
    private val TTL_Seconds: Long = 86400L
    override val name: String = "RefreshToken"
    override val ttl: Long = TTL_Seconds
    override val header: String = "refresh_token"
  }

  def values: Seq[TokenType] = Seq(AuthorisationToken, RefreshToken)

}
