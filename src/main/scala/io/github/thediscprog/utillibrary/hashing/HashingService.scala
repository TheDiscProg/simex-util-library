package io.github.thediscprog.utillibrary.hashing

object HashingService {

  def apply() = new PBKDF2HashingService()
}
