import java.util.List;
import menu.MainMenu;
import model.Road;
import model.User;
import repository.memory.InMemoryAlertRepository;
import repository.memory.InMemoryIncidentRepository;
import repository.memory.InMemoryRoadRepository;
import repository.memory.InMemoryUserRepository;
import service.AlertService;
import service.IncidentService;
import service.RoadService;
import service.UserService;
import util.DataFileManager;
import util.DataSeeder;
import util.IdGenerator;

public class Main {

    public static void main(String[] args) {

        try {

            // Ensure data directory exists
            DataFileManager.ensureDataDir();

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
            // Load or seed data
            // ==============================

            if (DataFileManager.dataExists()) {

                // Load from files
                loadData(userService, roadService);
                System.out.println("\nData loaded from files.\n");

            } else {

                // Seed demo data
                DataSeeder dataSeeder = new DataSeeder(
                        userService,
                        roadService,
                        incidentService,
                        alertService
                );

                dataSeeder.seedData();
                System.out.println("\nDemo data seeded.\n");

            }

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

            // ==============================
            // Save data on exit
            // ==============================

            saveData(userService, roadService);

        } catch (Exception e) {

            System.out.println("\nAn error occurred: " + e.getMessage());
            e.printStackTrace();

        }

    }

    private static void loadData(UserService userService, RoadService roadService) throws Exception {

        // Restore ID generator state
        int[] ids = DataFileManager.loadIdState();
        IdGenerator.initialize(ids[0], ids[1], ids[2], ids[3]);

        // Load users (passwords already hashed in file, use loadUser not registerUser)
        List<User> users = DataFileManager.loadUsers();
        for (User user : users) {
            userService.loadUser(user);
        }

        // Load roads
        List<Road> roads = DataFileManager.loadRoads();
        for (Road road : roads) {
            roadService.addRoad(road);
        }

    }

    private static void saveData(UserService userService, RoadService roadService) throws Exception {

        // Save users and roads
        DataFileManager.saveUsers(userService.getAllUsers());
        DataFileManager.saveRoads(roadService.getAllRoads());

        // Save ID generator state
        DataFileManager.saveIdState(
                IdGenerator.getCurrentUserId(),
                IdGenerator.getCurrentRoadId(),
                IdGenerator.getCurrentIncidentId(),
                IdGenerator.getCurrentAlertId()
        );

        System.out.println("\nData saved successfully.\n");

    }

}
