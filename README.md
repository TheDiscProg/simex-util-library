# util-library
Standard utilities that are shared across multiple projects.

## Local Caching Service
To create a local caching service:

```scala
val cachingService = CachingService.cachingService[F]()
```
It has the following methods defined:
```scala
  def storeInCache(key: String, value: Simex): F[String]

  def getFromCache(key: String): F[Option[Simex]]

  def getAllKeys: F[List[String]]
```

## Hashing Service
To create a hashing service:
```scala
val hasingService = HashingService()
```

It creates a service that, for now uses `PBKDF2WithHmacSHA1/SHA1PRNG` algorithm. It has the following methods defined:
```scala
  def generateHash(secret: String, salt: Array[Byte], numberOfIterations: Int): String

  def validateHash(secret: String, hash: String): Boolean
```

## JWT Secure Token Service
Use the following to create an instance for generating token service:

```scala
val tokenService = SecurityTokenService("secretkey")
```
You should set the `secret key` with your specific key.

It has only one method defined:
```scala
  def generateTokenFor(username: String, instant: Instant = Instant.now()): String
```
which will generate a JWT token. 