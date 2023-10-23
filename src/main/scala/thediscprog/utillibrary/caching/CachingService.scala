package thediscprog.utillibrary.caching

import cats.Applicative
import com.github.blemale.scaffeine.Scaffeine
import dapex.messaging.DapexMessage

object CachingService {

  def cachingService[F[_]: Applicative](): CachingServiceAlgebra[F] = {
    val cache = Scaffeine().recordStats().build[String, DapexMessage]()
    new ScaffeineCachingService[F](cache)
  }
}
