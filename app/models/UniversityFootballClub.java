package models;

public class UniversityFootballClub extends FootballClub {
    private String universityName;
    private static final int BELOW_AGE=23;

    public UniversityFootballClub(String universityName,String clubName, String location ) {
        super(clubName, location);
        this.universityName = universityName;
    }

    public String getUniversityName() {
        return universityName;
    }

    public void setUniversityName(String universityName) {
        this.universityName = universityName;
    }


}
