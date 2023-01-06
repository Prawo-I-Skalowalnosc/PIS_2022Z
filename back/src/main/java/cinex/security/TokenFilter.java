package cinex.security;

import cinex.service.UserService;
import cinex.service.UserServiceImpl;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.DefaultJwtSignatureValidator;
import lombok.NonNull;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.spec.SecretKeySpec;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Component
public class TokenFilter extends OncePerRequestFilter {
    @Autowired
    public UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {
        final String token = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (request.getMethod().equals("GET") || request.getRequestURI().startsWith("/account/")) {
            doFilter(request, response, filterChain);
            return;
        }

        var chunks = token.split("\\.");
        if (chunks.length != 3) {
            unauthorizedResponse(response);
            return;
        }

        var secretKeySpec = new SecretKeySpec(UserServiceImpl.tokenSecret.getBytes(), SignatureAlgorithm.HS256.getJcaName());
        var validator = new DefaultJwtSignatureValidator(SignatureAlgorithm.HS256, secretKeySpec);

        if (!validator.isValid(chunks[0] + '.' + chunks[1], chunks[2])){
            unauthorizedResponse(response);
            return;
        }

        String name, password, roles;

        try {
            var decoder = Base64.getDecoder();
            var payload = new String(decoder.decode(chunks[1]));
            var json = new JSONObject(payload);

            name = json.getString("name");
            password = json.getString("password");
            roles = json.getString("admin");

            if (name.isBlank() || password.isBlank() || roles.isBlank()) {
                unauthorizedResponse(response);
                return;
            }
        }
        catch (JSONException e) {
            unauthorizedResponse(response);
            return;
        }

        var user = userService.loadByUsername(name);
        if (user.isEmpty()) {
            unauthorizedResponse(response);
            return;
        }

        if (!password.equals(user.get().getHash())) {
            unauthorizedResponse(response);
            return;
        }

        var grantedAuthorityList = new ArrayList<SimpleGrantedAuthority>();
        var separated = new ArrayList<>(List.of(roles.split("\\s*")));

        separated.forEach(x -> {
            try {
                UserRoles.valueOf(x);
                grantedAuthorityList.add(new SimpleGrantedAuthority(x));
            }
            catch (IllegalArgumentException ignored) {}
        });

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                user, token, grantedAuthorityList);
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(request, response);
    }

    private void unauthorizedResponse(HttpServletResponse response) {
        response.setStatus(401);
    }
}
