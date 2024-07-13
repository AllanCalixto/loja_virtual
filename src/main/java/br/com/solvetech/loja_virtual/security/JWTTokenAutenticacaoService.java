package br.com.solvetech.loja_virtual.security;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import br.com.solvetech.loja_virtual.ApplicationContextLoad;
import br.com.solvetech.loja_virtual.model.Usuario;
import br.com.solvetech.loja_virtual.repository.UsuarioRepository;
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
		
		liberacaoCors(response);
		
		/* Usado para ver no Postman para teste */
		response.getWriter().write("{\"Authorization\": \"" + token + "\"}");
	}
	
	/* Retorna o usuário validado com token ou caso nao seja valido retorna null */
	public Authentication getAuthentication(HttpServletRequest request, HttpServletResponse response) {
		
		String token = request.getHeader(HEADER_STRING);
		if(token != null) {
			String tokenLimpo = token.replace(TOKEN_PREFIX, "").trim();
			
			/* Faz a validação do token do usuário na requisicao e obtem o USER */
			String user =  Jwts.parser()
					.setSigningKey(SECRET)
					.parseClaimsJws(tokenLimpo)
					.getBody().getSubject(); /* ADMIN ou Alex*/
			
			
			if(user != null) {
				Usuario usuario = ApplicationContextLoad.
						getApplicationContext().
						getBean(UsuarioRepository.class).findUserByLogin(user);
				
				if(usuario != null) {
					return new UsernamePasswordAuthenticationToken(
							usuario.getLogin(),
							usuario.getSenha(),
							usuario.getAuthorities());
				}
			}
		}
		
		
		liberacaoCors(response);
		return null;
	}
	
	/* Fazendo liberação contra erro de Cors no navegador */
	private void liberacaoCors(HttpServletResponse response) {
		
		if(response.getHeader("Acess-Control-Allow-Origin") == null) {
			response.addHeader("Acess-Control-Allow-Origin", "*");
		}
		if(response.getHeader("Acess-Control-Allow-Headers") == null) {
			response.addHeader("Acess-Control-Allow-Headers", "*");
		}
		if(response.getHeader("Acess-Control-Request-Headers") == null) {
			response.addHeader("Acess-Control-Request-Headers", "*");
		}
		if(response.getHeader("Acess-Control-Allow-Methods") == null) {
			response.addHeader("Acess-Control-Allow-Methods", "*");
		}
	}
	
}
