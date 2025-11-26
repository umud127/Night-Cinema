package az.duo.Night.Cinema.jwt;

import az.duo.Night.Cinema.entity.User;
import az.duo.Night.Cinema.exception.NotFoundException;
import az.duo.Night.Cinema.repository.RestUserRepo;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JWTAuthenticationFilter extends OncePerRequestFilter {
    private final JWTService jwtService;
    private final RestUserRepo userRepo;

    private static final Logger logger = LoggerFactory.getLogger(JWTAuthenticationFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String header;
        String token;
        Long id;

        header = request.getHeader("Authorization");
        String requestPath = request.getServletPath();

        logger.info("Request path: {}", requestPath);
        logger.info("Authorization header: {}", header);

        if (requestPath.startsWith("/api/auth/register") ||
                requestPath.startsWith("/api/auth/authentication") ||
                requestPath.startsWith("/v3/api-docs") ||
                requestPath.startsWith("/swagger-ui") ||
                requestPath.equals("/swagger-ui.html") ||
                requestPath.equals("/swagger-ui/index.html")
        ) {
            filterChain.doFilter(request, response);
            return;
        }

        if (header == null || !header.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        token = header.substring(7);

        try{
            id = jwtService.extractIdFromToken(token);

            if(id != null && SecurityContextHolder.getContext().getAuthentication() == null){
                User user = userRepo.findById(id)
                        .orElseThrow(() -> new NotFoundException("User Not Found", request.getServletPath()));

                if(user != null && !jwtService.isTokenExpired(token)) {
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                            user,
                            null,
                            user.getAuthorities());

                    authentication.setDetails(user);

                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        } catch (ExpiredJwtException e) {
            logger.warn("JWT expired: {}", e.getMessage());
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "JWT expired");
            return;
        } catch (Exception e) {
            logger.error(e.getMessage());
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "JWT invalid");
            return;
        }

        filterChain.doFilter(request, response);
    }
}
