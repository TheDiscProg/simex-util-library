package thediscprog.utillibrary.jwt

object SecurityTokenService {

  def apply(secretKey: String): SecurityTokenServiceAlgebra = new JwtSecurityTokenService(secretKey)
}
