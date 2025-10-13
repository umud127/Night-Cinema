package az.duo.Night.Cinema.schedule;

import az.duo.Night.Cinema.entity.User;
import az.duo.Night.Cinema.repository.RestUserRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ScheduleTasks {

    private final RestUserRepo userRepo;

//    @Transactional
//    @Scheduled(cron = "15 13 13 * * ?") // Hər gün saat 03:00
//    public void migratePhoneNumbers() {
//        System.out.println("Phone migration started...");
//
//        List<User> users = userRepo.findAll();
//
//        users.forEach(user -> {
//           user.setPhoneE164("+994" + user.getPhoneNumber());
//        });
//
//        userRepo.saveAll(users);
//    }

//    @Scheduled(cron = "0 0 0 * * *")
//    public void deleteExpiredMovies() {
//
//    }
}
