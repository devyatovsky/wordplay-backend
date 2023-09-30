package yar.backend.wordplay.service.impl

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import yar.backend.wordplay.service.JwtService
import java.security.Key
import java.util.*
import kotlin.collections.HashMap

@Service
class JwtServiceImpl : JwtService {

    @Value("\${token.signing.key}")
    private val jwtSigningKey: String? = null
    override fun extractUserName(token: String): String = extractClaim(token, Claims::getSubject)

    override fun generateToken(userDetails: UserDetails): String = generateToken(HashMap(), userDetails)

    override fun isTokenValid(token: String, userDetails: UserDetails): Boolean {
        val userName = extractUserName(token)
        return userName == userDetails.username && !isTokenExpired(token)
    }

    private fun <T> extractClaim(token: String, claimsResolvers: (claims: Claims) -> T): T {
        val claims: Claims = extractAllClaims(token)
        return claimsResolvers.invoke(claims)
    }

    private fun generateToken(extraClaims: Map<String, Any>, userDetails: UserDetails) =
        Jwts.builder().setClaims(extraClaims).setSubject(userDetails.username)
            .setIssuedAt(Date(System.currentTimeMillis()))
            .setExpiration(Date(System.currentTimeMillis() + 1000 * 60 * 360))
            .signWith(signingKey, SignatureAlgorithm.HS256).compact()


    private fun isTokenExpired(token: String) = extractExpiration(token).before(Date())

    private fun extractExpiration(token: String) = extractClaim<Date>(token, Claims::getExpiration)

    private fun extractAllClaims(token: String) =
        Jwts.parserBuilder().setSigningKey(signingKey).build().parseClaimsJws(token).body

    private val signingKey: Key
        get() {
            val keyBytes: ByteArray = Decoders.BASE64.decode(jwtSigningKey)
            return Keys.hmacShaKeyFor(keyBytes)
        }
}