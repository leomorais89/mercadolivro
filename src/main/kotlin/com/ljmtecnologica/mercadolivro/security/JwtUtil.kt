package com.ljmtecnologica.mercadolivro.security

import com.ljmtecnologica.mercadolivro.enums.ErrorEnum
import com.ljmtecnologica.mercadolivro.exception.AuthenticationException
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.lang.Exception
import java.util.*

@Component
class JwtUtil {

    @Value("\${jwt.expiration}")
    private val expiration: Long? = null

    @Value("\${jwt.secret}")
    private val secret: String? = null

    fun generateToken(email: String): String{
        return Jwts.builder()
            .setSubject(email)
            .setExpiration(Date(System.currentTimeMillis() + this.expiration!!))
            .signWith(SignatureAlgorithm.HS512, this.secret!!.toByteArray())
            .compact()
    }

    fun isValidToken(token: String): Boolean{
        val claims = this.getClaims(token)
        if(claims.subject.isNullOrEmpty() || claims.expiration == null || Date().after(claims.expiration)){
            return false
        }
        return true
    }

    fun getClaims(token: String): Claims{
        try {
            return Jwts.parser().setSigningKey(this.secret!!.toByteArray()).parseClaimsJws(token).body
        } catch (e: Exception){
            throw AuthenticationException(ErrorEnum.ML0003.message, ErrorEnum.ML0003.internalCode)
        }
    }

    fun getSubject(token: String): String{
        return this.getClaims(token).subject
    }

}