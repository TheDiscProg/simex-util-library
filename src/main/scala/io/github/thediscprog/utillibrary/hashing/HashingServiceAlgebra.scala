package io.github.thediscprog.utillibrary.hashing

trait HashingServiceAlgebra {

  def generateHash(secret: String, salt: Array[Byte], numberOfIterations: Int): String

  def validateHash(secret: String, hash: String): Boolean
}
