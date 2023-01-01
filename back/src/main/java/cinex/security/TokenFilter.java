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
import org.springframework.security.core.GrantedAuthority;
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
        if (token.isBlank() || token.equals("undefined")) {
            filterChain.doFilter(request, response);
            return;
        }

        List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();
        grantedAuthorityList.add(new SimpleGrantedAuthority(UserRoles.USER.toString()));

        var chunks = token.split("\\.");
        if (chunks.length != 3) {
            filterChain.doFilter(request, response);
            return;
        }

        var secretKeySpec = new SecretKeySpec(UserServiceImpl.tokenSecret.getBytes(), SignatureAlgorithm.HS256.getJcaName());
        var validator = new DefaultJwtSignatureValidator(SignatureAlgorithm.HS256, secretKeySpec);

        if (!validator.isValid(chunks[0] + '.' + chunks[1], chunks[2])){
            filterChain.doFilter(request, response);
            return;
        }

        String name, password;
        boolean isAdmin;

        try {
            var decoder = Base64.getDecoder();
            var payload = new String(decoder.decode(chunks[1]));
            var json = new JSONObject(payload);

            name = json.getString("name");
            password = json.getString("password");
            isAdmin = json.getBoolean("admin");

            if (name.isBlank() || password.isBlank()) {
                filterChain.doFilter(request, response);
                return;
            }
        }
        catch (JSONException e) {
            filterChain.doFilter(request, response);
            return;
        }

        var user = userService.loadByUsername(name);
        if (user.isEmpty()) {
            filterChain.doFilter(request, response);
            return;
        }

        if (!password.equals(user.get().getHash())) {
            filterChain.doFilter(request, response);
            return;
        }

        if (isAdmin)
            grantedAuthorityList.add(new SimpleGrantedAuthority(UserRoles.ADMIN.toString()));

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                user, token, grantedAuthorityList);
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(request, response);
    }
}
