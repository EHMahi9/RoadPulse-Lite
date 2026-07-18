import menu.MainMenu;
import repository.memory.InMemoryAlertRepository;
import repository.memory.InMemoryIncidentRepository;
import repository.memory.InMemoryRoadRepository;
import repository.memory.InMemoryUserRepository;
import service.AlertService;
import service.IncidentService;
import service.RoadService;
import service.UserService;
import util.DataSeeder;

public class Main {

    public static void main(String[] args) {

        // ==============================
        // Repository Layer
        // ==============================

        InMemoryUserRepository userRepository =
                new InMemoryUserRepository();

        InMemoryRoadRepository roadRepository =
                new InMemoryRoadRepository();

        InMemoryIncidentRepository incidentRepository =
                new InMemoryIncidentRepository();

        InMemoryAlertRepository alertRepository =
                new InMemoryAlertRepository();

        // ==============================
        // Service Layer
        // ==============================

        UserService userService =
                new UserService(userRepository);

        RoadService roadService =
                new RoadService(roadRepository);

        IncidentService incidentService =
                new IncidentService(incidentRepository);

        AlertService alertService =
                new AlertService(alertRepository);

        // ==============================
        // Application
        // ==============================

        // create dataseeder
        DataSeeder dataSeeder = new DataSeeder(
        userService,
        roadService,
        incidentService,
        alertService
);

        // Load demo data
        dataSeeder.seedData();

        // ==============================
        // Menu
        // ==============================

        MainMenu menu =
                new MainMenu(
                        userService,
                        roadService,
                        incidentService,
                        alertService
                );

        menu.start();

    }

}