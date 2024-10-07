package io.github.thediscprog.utillibrary.caching

import cats.Applicative
import com.github.blemale.scaffeine.Scaffeine
import io.github.thediscprog.simexmessaging.messaging.Simex

object CachingService {

  def cachingService[F[_]: Applicative](): CachingServiceAlgebra[F] = {
    val cache = Scaffeine().recordStats().build[String, Simex]()
    new ScaffeineCachingService[F](cache)
  }
}
