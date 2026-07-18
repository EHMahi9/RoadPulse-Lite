package util;

import model.Admin;
import model.Commuter;
import model.PublicAlert;
import model.Road;
import model.Incident;

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

        // These will be enabled after we finish
        // IncidentService and AlertService.

        // seedIncidents();
        // seedAlerts();

    }

    private void seedUsers() {

        Admin admin = new Admin(
                1,
                "System Admin",
                "admin@roadpulse.com",
                "admin123",
                1
        );

        Commuter commuter = new Commuter(
                2,
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
                        1,
                        "Dhaka-Chattogram Highway",
                        "Dhaka",
                        "Open"
                )
        );

        roadService.addRoad(
                new Road(
                        2,
                        "Airport Road",
                        "Dhaka",
                        "Busy"
                )
        );

        roadService.addRoad(
                new Road(
                        3,
                        "Mirpur Road",
                        "Dhaka",
                        "Open"
                )
        );

        roadService.addRoad(
                new Road(
                        4,
                        "Dhaka-Mymensingh Highway",
                        "Gazipur",
                        "Under Maintenance"
                )
        );

        roadService.addRoad(
                new Road(
                        5,
                        "Savar Road",
                        "Savar",
                        "Open"
                )
        );

    }

}