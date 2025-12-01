package com.solano.pos.backend.config;

import com.solano.pos.backend.model.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {
    // Clave secreta para firmar los tokens JWT (debe ser segura y almacenada adecuadamente)
    @Value("${jwt.secret}")
    private static final String SECRET_KEY = "";

    @Value("${jwt.expiration}")
    private static final Long EXPIRATION_TIME_MS = 0L;

    // Metodo para generar un token JWT para un usuario dado
    public String generateToken(Usuario usuario) {
        Map<String, Object> extraClaims = new HashMap<>(); // Mapa para claims adicionales
        /*
        SAAS MAGIC: Guardar el ID del negocio en los claims del token JWT
         */
        extraClaims.put("negocioId", usuario.getNegocio().getId()); // ID del negocio asociado al usuario
        extraClaims.put("rol", usuario.getRol()); // Rol del usuario

        return buildToken(extraClaims, usuario); // Construir y retornar el token JWT
    }

    // Metodo privado para construir el token JWT con claims adicionales
    private String buildToken(Map<String, Object> extraClaims, UserDetails userDetails) {
        return Jwts.builder()
                .setClaims(extraClaims) // Establecer claims adicionales
                .setSubject(userDetails.getUsername()) // Establecer el sujeto del token (email del usuario)
                .setIssuedAt(new Date(System.currentTimeMillis())) // Establecer la fecha de emisión del token
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME_MS)) // Expiración en 30 días
                .signWith(getSignInKey(), SignatureAlgorithm.HS256) // Firmar el token con la clave secreta
                .compact(); // Construir el token JWT
    }

    // Metodo para extraer el nombre de usuario (email) del token JWT
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject); // Extraer el sujeto (email) del token
    }

    // Metodo para extraer el ID del negocio del token JWT
    public Long extractNegocioId(String token) {
        return extractClaim(token, claims -> claims.get("negocioId", Long.class)); // Extraer el ID del negocio del token
    }

    // Metodo para validar si el token JWT es válido para un usuario dado
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token); // Extraer el nombre de usuario del token
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token); // Verificar validez del token
    }

    // Metodo privado para verificar si el token ha expirado
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date()); // Verificar si el token ha expirado
    }

    // Metodo privado para extraer la fecha de expiración del token JWT
    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration); // Extraer la fecha de expiración del token
    }

    // Metodo genérico para extraer un claim específico del token JWT
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token); // Extraer todos los claims del token
        return claimsResolver.apply(claims); // Aplicar la función para obtener el claim específico
    }

    // Metodo privado para extraer todos los claims del token JWT
    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignInKey()) // Establecer la clave de firma para validar el token
                .build() // Crear el parser
                .parseClaimsJws(token) // Parsear el token JWT
                .getBody(); // Obtener el cuerpo (claims) del token
    }

    // Metodo privado para obtener la clave de firma a partir de la clave secreta
    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY); // Decodificar la clave secreta en bytes
        return Keys.hmacShaKeyFor(keyBytes); // Crear la clave de firma HMAC SHA
    }
}
