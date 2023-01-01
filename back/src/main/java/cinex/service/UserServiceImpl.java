package cinex.service;

import cinex.controller.api.requests.LoginRequest;
import cinex.controller.api.requests.RegisterRequest;
import cinex.errors.AppException;
import cinex.model.User;
import cinex.repository.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.SecureRandom;
import java.util.Date;
import java.util.Objects;
import java.util.Optional;
import java.util.regex.Pattern;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    public static final String tokenSecret = "b955425f49d35aec88860e0224e579e9";

    @Override
    public User login(LoginRequest login) throws AppException {
        if (login.password == null || login.password.isBlank())
            throw new AppException("Hasło nie jest podane lub jest puste");
        if (login.username == null || login.username.isBlank())
            throw new AppException("Login nie jest podany lub jest pusty");
        var user =  userRepository.findByUsername(login.username);
        if (user.isEmpty())
            throw new AppException("Brak użytkownika o tej nazwie");
        try {
            var DBHash = user.get().getHash();
            var DBSalt = Hex.decodeHex(user.get().getSalt());
            var newHash = Hex.encodeHexString(hashPassword(login.password, DBSalt));

            if (!newHash.equals(DBHash))
                throw new AppException("Niepoprawne hasło");

            return user.get();
        }
        catch (DecoderException e){
            throw new AppException("Błąd podczas kodowania/dekodowania hasła");
        }
    }

    @Override
    public boolean register(RegisterRequest register) throws AppException {
        if (register.password == null || register.password.isBlank())
            throw new AppException("Hasło nie jest podane lub jest puste");
        if (register.email == null || register.email.isBlank())
            throw new AppException("Email nie jest podany lub jest pusty");
        if (register.username == null || register.username.isBlank())
            throw new AppException("Login nie jest podany lub jest pusty");
        if (!Objects.equals(register.confirmPassword, register.password))
            throw new AppException("Hasła się nie zgadzają");
        if (register.username.length() > 20)
            throw new AppException("Zbyt długa nazwa użytkownika");
        validateMail(register.email);
        validatePassword(register.password);
        if (userRepository.findByUsername(register.username).isPresent())
            throw new AppException("Nazwa użytkownika jest już zajęta");
        if (userRepository.findByEmail(register.email).isPresent())
            throw new AppException("Istnieje już konto z podanym adresem e-mail");

        var random = new SecureRandom();
        var salt = new byte[16];
        random.nextBytes(salt);

        var hash = hashPassword(register.password, salt);
        var newUser = new User(register.username, hash, register.email, salt);

        userRepository.save(newUser);
        return true;
    }

    @Override
    public void validatePassword(String password) throws AppException {
        if (password == null)
            throw new AppException("Brak hasła");
        if (password.length() >= 64)
            throw new AppException("Hasło jest zbyt długie");
        if (password.length() < 4)
            throw new AppException("Hasło jest zbyt krótkie");
        if (password.isBlank())
            throw new AppException("Hasło zawiera same białe znaki");
    }

    @Override
    public void validateMail(String mail) throws AppException{
        var regexPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";

        if (mail == null)
            throw new AppException("Brak emaila");
        if (mail.length() >= 64)
            throw new AppException("Email jest zbyt długi");
        if (mail.length() < 4)
            throw new AppException("Email jest zbyt krótki");
        if (mail.isBlank())
            throw new AppException("Email zawiera same białe znaki");
        if (!Pattern.compile(regexPattern).matcher(mail).matches())
            throw new AppException("Niewłaściwy adres email");
    }

    @Override
    public byte[] hashPassword(String password, byte[] salt) throws AppException {
        try {
            var spec = new PBEKeySpec(password.toCharArray(), salt, 16384, 128);
            var factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");

            return factory.generateSecret(spec).getEncoded();
        }
        catch (Exception e){
            throw new AppException("Błąd skracania hasła, użyj innego");
        }
    }

    @Override
    public Optional<User> loadByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public String generateToken(User user) {
        return Jwts.builder()
            .setIssuer("CINEX")
            .setSubject(user.getUsername())
            .claim("admin", user.isAdmin())
            .claim("name", user.getUsername())
            .claim("password", user.getHash())
            .setIssuedAt(new Date())
            .signWith(
                SignatureAlgorithm.HS256,
                tokenSecret.getBytes()
            )
            .compact();
    }
}
