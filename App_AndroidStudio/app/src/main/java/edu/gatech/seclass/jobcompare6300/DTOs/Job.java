package edu.gatech.seclass.jobcompare6300.DTOs;

public class Job {
    private String ID;
    private String title;
    private String company;
    private String location;
    private float salary;
    private float bonus;
    private int leaveTime;
    private float stockShares;
    private float homeFund;
    private float wellnessFund;
    private boolean isCurrentJob;
    float weight;
    boolean isChecked;

    public Job (
            String id,
            String title,
            String company,
            String location,
            float salary,
            float bonus,
            int leaveTime,
            float stockShares,
            float homeFund,
            float wellnessFund,
            boolean isCurrentJob
    ){
        this.ID = id;
        this.title = title;
        this.company = company;
        this.location = location;
        this.salary = salary;
        this.bonus = bonus;
        this.leaveTime = leaveTime;
        this.stockShares = stockShares;
        this.homeFund = homeFund;
        this.wellnessFund = wellnessFund;
        this.isCurrentJob = isCurrentJob;
        isChecked = false;
    }

    public void updateJob(
            String title,
            String company,
            String location,
            float salary,
            float bonus,
            int leaveTime,
            float stockShares,
            float homeFund,
            float wellnessFund,
            boolean isCurrentJob
    ){
        this.title = title;
        this.company = company;
        this.location = location;
        this.salary = salary;
        this.bonus = bonus;
        this.leaveTime = leaveTime;
        this.stockShares = stockShares;
        this.homeFund = homeFund;
        this.wellnessFund = wellnessFund;
        this.isCurrentJob = isCurrentJob;
    }

    public String getID(){
        return this.ID;
    }
    public String getTitle(){
        return this.title;
    }
    public String getCompany() {return this.company;}
    public String getLocation() {return this.location;}
    public float getSalary() {return this.salary;}
    public float getBonus() {return this.bonus;}
    public int getLeaveTime() {return this.leaveTime;}
    public float getStockShares() {return this.stockShares;}
    public float getHomeFund() {return this.homeFund;}
    public float getWellnessFund() {return this.wellnessFund;}
    public boolean getIsCurrentJob() {return this.isCurrentJob;}
    public float getWeight() {return this.weight;}
    public boolean isChecked(){return isChecked;}

    public void setWeight (float weight) {
        this.weight = weight;
    }
    public void setChecked (boolean checked) {this.isChecked = checked;}
}
