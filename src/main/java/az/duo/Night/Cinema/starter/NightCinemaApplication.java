package az.duo.Night.Cinema.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.sql.SQLException;

@SpringBootApplication(scanBasePackages = "az.duo.Night.Cinema")
@EnableJpaRepositories(basePackages = "az.duo.Night.Cinema")
@EnableScheduling
@EntityScan(basePackages = "az.duo.Night.Cinema")
@ComponentScan(basePackages = "az.duo.Night.Cinema")
public class NightCinemaApplication {

	public static void main(String[] args) throws SQLException {

		SpringApplication.run(NightCinemaApplication.class, args);
	}

}