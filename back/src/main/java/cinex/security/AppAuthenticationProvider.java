package cinex.security;

import cinex.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class AppAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    private UserService userService;

    public AppAuthenticationProvider(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String login = authentication.getName();
        String password = authentication.getCredentials().toString();

        return userService.loadByUsername(login)
            .filter(user -> Objects.equals(user.getHash().trim(), password))
            .map(user -> new UsernamePasswordAuthenticationToken(user, password, prepareAuthorities(user.isAdmin())))
            .orElse(null);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

    private List<GrantedAuthority> prepareAuthorities(boolean isAdmin) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(UserRoles.USER.toString()));
        if (isAdmin)
            authorities.add(new SimpleGrantedAuthority(UserRoles.ADMIN.toString()));
        return authorities;
    }
}