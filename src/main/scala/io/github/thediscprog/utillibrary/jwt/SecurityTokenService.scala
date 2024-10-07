package io.github.thediscprog.utillibrary.jwt

object SecurityTokenService {

  def apply(secretKey: String): SecurityTokenServiceAlgebra = new JwtSecurityTokenService(secretKey)
}
