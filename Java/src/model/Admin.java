package model;

public class Admin extends User {

    private int adminLevel;

    public Admin() {

        super();

    }

    public Admin(int userId,
                 String name,
                 String email,
                 String password,
                 int adminLevel) {

        super(userId, name, email, password);

        this.adminLevel = adminLevel;

    }

    public int getAdminLevel() {

        return adminLevel;

    }

    public void setAdminLevel(int adminLevel) {

        this.adminLevel = adminLevel;

    }

    @Override
    public String toString() {

        return super.toString() +
               "\nAdmin Level : " + adminLevel;

    }

}