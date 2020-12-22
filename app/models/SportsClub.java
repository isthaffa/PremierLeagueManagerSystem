package models;

import java.io.Serializable;
import java.util.Objects;

public abstract class SportsClub implements Serializable{
    private String clubName;
    private String location;
    private String statistics;

    public SportsClub() {
    }

    public SportsClub(String clubName, String location) {
        this.clubName = clubName;
        this.location = location;
    }

    public String getClubName() {
        return clubName;
    }

    public String getStatistics() {
        return statistics;
    }
    public String getLocation() {
        return location;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
    }



    public void setLocation(String location) {
        this.location = location;
    }



    public void setStatistics(String statistics) {
        this.statistics = statistics;
    }

    @Override
    public String toString() {
        return "(Club Name: " +clubName + " | Location: " + location + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SportsClub)) return false;
        SportsClub that = (SportsClub) o;
        return Objects.equals(clubName, that.clubName) ;
    }


}
