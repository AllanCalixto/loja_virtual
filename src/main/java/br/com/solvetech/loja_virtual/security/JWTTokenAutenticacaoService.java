package br.com.solvetech.loja_virtual.security;

import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/* Criar e retornar a autenticação JWT */

@Service
@Component
public class JWTTokenAutenticacaoService {
	
	/* Token de validade de 11 dias */
	private static final long EXPIRATION_TIME = 959990000;
	
	/* Chave de senha para juntar com o JWT */
	private static final String SECRET = "t003632";
	
	private static final String TOKEN_PREFIX = "Bearer";
	
	private static final String HEADER_STRING = "Authorization";
	
	
	/* Gera o token e da a resposta para o cliente com JWT */
	public void addAuthentication(HttpServletResponse response, String username) throws Exception {
		
		/* Montagem do token */
		
		String JWT = Jwts.builder() /* Chama o gerador de token */
				.setSubject(username) /* Adiciona o user */
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS512, SECRET).compact(); /* Tempo de expiração */
		
		/* Ex: Bearer t003632asiudahudihasdadakpo */
		String token = TOKEN_PREFIX + " " + JWT;
		
		/* Dá a resposta pra tela e para o cliente */
		response.addHeader(HEADER_STRING, token);
		
		/* Usado para ver no Postman para teste */
		response.getWriter().write("{\"Authorization\": \"" + token + "\"}");
	}
	
}
