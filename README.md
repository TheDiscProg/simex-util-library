# simex-util-library
Uses `simex-messaging version 0.9.2`

This library provides a number of useful functions that are used across services, as described below.

## Local Caching Service
A local caching service for storing SIMEX messages. It uses Scaffeine by default.
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

If you wanted to use a different caching service, you can by extending and implementing `CachingServiceAlgebra`.

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

## Secure Token Service
A secure token service for generating authorisation and refresh security tokens. 

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