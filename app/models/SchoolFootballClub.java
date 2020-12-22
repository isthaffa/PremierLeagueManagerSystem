package models;

public class SchoolFootballClub extends FootballClub {
    private String schoolName;
    private static final int BELOW_AGE=18;

    public SchoolFootballClub( String schoolName,String clubName, String location) {
        super(clubName, location);
        this.schoolName = schoolName;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }
}
