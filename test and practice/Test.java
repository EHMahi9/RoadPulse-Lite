class User {
    private String name;
    private int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

}

class Admin extends User    {
    private String adminId;

    public Admin(String name, int age, String adminId) {
        super(name, age);
        this.adminId = adminId;
    }

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

}

class Road {
    String name;
    String location;

    public Road(String name, String location) {
        this.name = name;
        this.location = location;
    }
}


class Alert {
    String alertId;
    String alertType;

    Alert(String alertId, String alertType) {
        this.alertId = alertId;
        this.alertType = alertType;
    }
}

public class Test {
    
    public static void main(String args[])  {

        User u1 = new User("Mahi",22);
        System.out.println(u1.getName());
        System.out.println(u1.getAge());

        Admin a1 = new Admin("Mahi", 22, "1");
        System.out.println(a1.getName());
        System.out.println(a1.getAge());
        System.out.println(a1.getAdminId());

        Road dhaka = new Road("Dhaka", "Bangladesh");
        System.out.println(dhaka.name);
        System.out.println(dhaka.location);

        Alert alert1 = new Alert("1", "Accident");

        System.out.println(alert1.alertId);
        System.out.println(alert1.alertType);

    }
    
}
