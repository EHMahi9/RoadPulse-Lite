package util;

import model.Admin;
import model.Commuter;
import model.Road;
import service.AlertService;
import service.IncidentService;
import service.RoadService;
import service.UserService;

public class DataSeeder {

    private final UserService userService;
    private final RoadService roadService;
    private final IncidentService incidentService;
    private final AlertService alertService;

    public DataSeeder(UserService userService,
                      RoadService roadService,
                      IncidentService incidentService,
                      AlertService alertService) {

        this.userService = userService;
        this.roadService = roadService;
        this.incidentService = incidentService;
        this.alertService = alertService;
    }

    public void seedData() {

        seedUsers();
        seedRoads();

    }

    private void seedUsers() {

        Admin admin = new Admin(
                IdGenerator.nextUserId(),
                "System Admin",
                "admin@roadpulse.com",
                "admin123",
                1
        );

        Commuter commuter = new Commuter(
                IdGenerator.nextUserId(),
                "Mahi",
                "mahi@gmail.com",
                "123456",
                100
        );

        userService.registerUser(admin);
        userService.registerUser(commuter);
    }

    private void seedRoads() {

        roadService.addRoad(
                new Road(
                        IdGenerator.nextRoadId(),
                        "Dhaka-Chattogram Highway",
                        "Dhaka",
                        "Open"
                )
        );

        roadService.addRoad(
                new Road(
                        IdGenerator.nextRoadId(),
                        "Airport Road",
                        "Dhaka",
                        "Busy"
                )
        );

        roadService.addRoad(
                new Road(
                        IdGenerator.nextRoadId(),
                        "Mirpur Road",
                        "Dhaka",
                        "Open"
                )
        );

        roadService.addRoad(
                new Road(
                        IdGenerator.nextRoadId(),
                        "Dhaka-Mymensingh Highway",
                        "Gazipur",
                        "Under Maintenance"
                )
        );

        roadService.addRoad(
                new Road(
                        IdGenerator.nextRoadId(),
                        "Savar Road",
                        "Savar",
                        "Open"
                )
        );
    }

}
