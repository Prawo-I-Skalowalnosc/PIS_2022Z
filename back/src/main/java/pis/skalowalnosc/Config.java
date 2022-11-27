package pis.skalowalnosc;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(
        basePackages = {
                "pis.skalowalnosc.repository"
        }
)
public class Config {
}
