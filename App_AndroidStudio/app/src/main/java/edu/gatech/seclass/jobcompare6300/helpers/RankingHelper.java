package edu.gatech.seclass.jobcompare6300.helpers;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import edu.gatech.seclass.jobcompare6300.DTOs.ComparisonSettingsSingleton;
import edu.gatech.seclass.jobcompare6300.DTOs.Job;

public class RankingHelper {
    ComparisonSettingsSingleton settings = ComparisonSettingsSingleton.getInstance();

    public RankingHelper() {};

    public List<Job> rankJobs (List<Job> jobList) {
        int total = getTotalWeights();
        Map<String, Integer> weights = settings.getComparisonSettings();
        List<Job> rankedList = jobList;

        for (Job job : rankedList){

            float salary = ((float) weights.get("yearlySalary") / (float) total) * job.getSalary();
            float bonus = ((float) weights.get("yearlyBonus") / (float) total) * job.getBonus();
            float leaveTime = ((float) weights.get("leaveTime") / (float) total) * (job.getLeaveTime() * (job.getSalary()/260));
            float shares = ((float) weights.get("numOfSharesOffered") / (float) total) * (job.getStockShares()/2);
            float buyingProgram = ((float) weights.get("homeBuyingProgramFund") / (float) total) * job.getHomeFund();
            float wellnessFund = ((float) weights.get("wellnessFund") / (float) total) * job.getWellnessFund();

            float totalWeight = salary + bonus + leaveTime + shares + buyingProgram + wellnessFund;
            job.setWeight(totalWeight);
        }

        rankedList.sort(Comparator.comparing(Job::getWeight).reversed());
        return jobList;
    }

    private int getTotalWeights(){
        int total = 0;
        for (Integer weight : settings.getComparisonSettings().values()) {
            total += weight;
        }

        return total;
    }

}
