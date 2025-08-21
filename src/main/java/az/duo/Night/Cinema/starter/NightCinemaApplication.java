package az.duo.Night.Cinema.starter;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@SpringBootApplication(scanBasePackages = "az.duo.Night.Cinema")
@EnableJpaRepositories(basePackages = "az.duo.Night.Cinema")
@EnableScheduling
@EntityScan(basePackages = "az.duo.Night.Cinema")
@ComponentScan(basePackages = "az.duo.Night.Cinema")
public class NightCinemaApplication {

	public static void main(String[] args) throws SQLException {
//        Dotenv dotenv = Dotenv.load();
//
//        System.setProperty("DB_URL", dotenv.get("DB_URL"));
//
//        System.setProperty("SECRET_KEY", dotenv.get("SECRET_KEY"));
//
//        System.setProperty("DB_USERNAME", dotenv.get("DB_USERNAME"));
//        System.setProperty("DB_PASSWORD", dotenv.get("DB_PASSWORD"));

		SpringApplication.run(NightCinemaApplication.class, args);
	}

}