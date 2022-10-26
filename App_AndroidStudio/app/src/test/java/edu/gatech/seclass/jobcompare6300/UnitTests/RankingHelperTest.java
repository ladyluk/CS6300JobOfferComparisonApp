package edu.gatech.seclass.jobcompare6300.UnitTests;
import org.junit.Assert;
import org.junit.Before;
import org.mockito.Mock;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import edu.gatech.seclass.jobcompare6300.ComparisonSettings;
import edu.gatech.seclass.jobcompare6300.DTOs.ComparisonSettingsSingleton;
import edu.gatech.seclass.jobcompare6300.DTOs.Job;
import edu.gatech.seclass.jobcompare6300.helpers.RankingHelper;

public class RankingHelperTest {
    ComparisonSettingsSingleton settings;
    List<Job> jobList = new ArrayList<>();

    @Before
    public void setUp(){
        settings = Mockito.mock(ComparisonSettingsSingleton.class);
        settings = ComparisonSettingsSingleton.getInstance();

        Job job = new Job(
                "125874599",
                "Job",
                "Microsoft",
                "CA",
                100000,
                5000,
                15,
                100,
                1250,
                300,
                false
        );
        jobList.add(job);
    }

    @Test
    public void Test_001_VerifyThatJobIsRankedCorrectly(){
        settings.setComparisonSettings(
                new HashMap<String, Integer>(){{
                    put("yearlySalary", 1);
                    put("yearlyBonus", 2);
                    put("leaveTime", 1);
                    put("numOfSharesOffered", 1);
                    put("homeBuyingProgramFund", 2);
                    put("wellnessFund", 3);
                }}
        );

        RankingHelper helper = new RankingHelper();
        List<Job> result = helper.rankJobs(jobList);
        Assert.assertEquals(11921, result.get(0).getWeight(), 1);
    }

    @Test
    public void Test_002_VerifyThatJobIsRankedCorrectly(){
        settings.setComparisonSettings(
                new HashMap<String, Integer>(){{
                    put("yearlySalary", 2);
                    put("yearlyBonus", 3);
                    put("leaveTime", 4);
                    put("numOfSharesOffered", 2);
                    put("homeBuyingProgramFund", 1);
                    put("wellnessFund", 1);
                }}
        );

        RankingHelper helper = new RankingHelper();
        List<Job> result = helper.rankJobs(jobList);
        Assert.assertEquals(18440, result.get(0).getWeight(), 1);
    }

    @Test
    public void Test_003_VerifyThatJobIsRankedCorrectly(){
        settings.setComparisonSettings(
                new HashMap<String, Integer>(){{
                    put("yearlySalary", 1);
                    put("yearlyBonus", 1);
                    put("leaveTime", 1);
                    put("numOfSharesOffered", 1);
                    put("homeBuyingProgramFund", 1);
                    put("wellnessFund", 1);
                }}
        );

        RankingHelper helper = new RankingHelper();
        List<Job> result = helper.rankJobs(jobList);
        Assert.assertEquals(18728, result.get(0).getWeight(), 1);
    }

    @Test
    public void Test_004_VerifyThatJobIsRankedCorrectly(){
        settings.setComparisonSettings(
                new HashMap<String, Integer>(){{
                    put("yearlySalary", 3);
                    put("yearlyBonus", 3);
                    put("leaveTime", 3);
                    put("numOfSharesOffered", 10);
                    put("homeBuyingProgramFund", 12);
                    put("wellnessFund", 5);
                }}
        );

        RankingHelper helper = new RankingHelper();
        List<Job> result = helper.rankJobs(jobList);
        Assert.assertEquals(9702, result.get(0).getWeight(), 1);
    }
}