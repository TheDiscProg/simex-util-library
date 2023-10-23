package thediscprog.utillibrary.jwt

import java.time.Instant

trait SecurityTokenServiceAlgebra {

  def generateTokenFor(username: String, instant: Instant = Instant.now()): String

}
