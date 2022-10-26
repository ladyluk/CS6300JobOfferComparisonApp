package edu.gatech.seclass.jobcompare6300.DTOs;

import java.util.HashMap;
import java.util.Map;

public class ComparisonSettingsSingleton {
    private static volatile ComparisonSettingsSingleton INSTANCE = null;
    int yearlySalary = 1;
    int yearlyBonus = 1;
    int leaveTime = 1;
    int numOfSharesOffered = 1;
    int homeBuyingProgramFund = 1;
    int wellnessFund = 1;

    private ComparisonSettingsSingleton() {}

    public static ComparisonSettingsSingleton getInstance() {
        if(INSTANCE == null) {
            synchronized (ComparisonSettingsSingleton.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ComparisonSettingsSingleton();
                }
            }
        }
        return INSTANCE;
    }

    void setYearlySalary (int yearlySalary){
        this.yearlySalary = yearlySalary;
    }

    void setYearlyBonus (int yearlyBonus){
        this.yearlyBonus = yearlyBonus;
    }

    void setLeaveTime (int leaveTime){
        this.leaveTime = leaveTime;
    }

    void setNumOfSharesOffered (int numOfSharesOffered){
        this.numOfSharesOffered = numOfSharesOffered;
    }

    void setHomeBuyingProgramFund (int homeBuyingProgramFund){
        this.homeBuyingProgramFund = homeBuyingProgramFund;
    }

    void setWellnessFund (int wellnessFund){
        this.wellnessFund = wellnessFund;
    }

    public Map<String, Integer> getComparisonSettings() {
        return new HashMap<String, Integer>(){{
            put("yearlySalary", yearlySalary);
            put("yearlyBonus", yearlyBonus);
            put("leaveTime", leaveTime);
            put("numOfSharesOffered", numOfSharesOffered);
            put("homeBuyingProgramFund", homeBuyingProgramFund);
            put("wellnessFund", wellnessFund);
        }};
    }

    public void setComparisonSettings(Map<String, Integer> settings){
        this.yearlySalary = settings.containsKey("yearlySalary") ? settings.get("yearlySalary") : this.yearlySalary;
        this.yearlyBonus = settings.containsKey("yearlyBonus") ? settings.get("yearlyBonus") : this.yearlyBonus;
        this.leaveTime = settings.containsKey("leaveTime") ? settings.get("leaveTime") : this.leaveTime;
        this.numOfSharesOffered = settings.containsKey("numOfSharesOffered") ? settings.get("numOfSharesOffered") : this.numOfSharesOffered;
        this.homeBuyingProgramFund = settings.containsKey("homeBuyingProgramFund") ? settings.get("homeBuyingProgramFund") : this.homeBuyingProgramFund;
        this.wellnessFund = settings.containsKey("wellnessFund") ? settings.get("wellnessFund") : this.wellnessFund;
    }

}
