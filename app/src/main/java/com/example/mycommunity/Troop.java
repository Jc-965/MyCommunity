package com.example.mycommunity;

public class Troop {
    private String name;
    private int startDate;
    private String leaderName;

    public Troop(String name, int startDate, String leaderName){
        this.name = name;
        this.startDate = startDate;
        this.leaderName = leaderName;
    }

    //Troop t = new Troop("Cool Eagles", 2000, "Joe Bob");
    //t.setName("Not cool eagles");
    //t.getName()

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStartDate() {
        return startDate;
    }

    public void setStartDate(int startDate) {
        this.startDate = startDate;
    }

    public String getLeaderName() {
        return leaderName;
    }

    public void setLeaderName(String leaderName) {
        this.leaderName = leaderName;
    }
}