package model; // This line is already correct. No change needed.

public class Commuter extends User {

    private int trustScore;

    public Commuter() {

        super();

    }

    public Commuter(int userId,
                    String name,
                    String email,
                    String password,
                    int trustScore) {

        super(userId, name, email, password);

        this.trustScore = trustScore;

    }

    public int getTrustScore() {

        return trustScore;

    }

    public void setTrustScore(int trustScore) {

        this.trustScore = trustScore;

    }

    @Override
    public String toString() {

        return super.toString() +
               "\nTrust Score : " + trustScore;

    }

}