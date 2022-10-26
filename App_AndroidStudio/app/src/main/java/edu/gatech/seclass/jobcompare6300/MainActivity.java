package edu.gatech.seclass.jobcompare6300;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import edu.gatech.seclass.jobcompare6300.DTOs.Job;
import edu.gatech.seclass.jobcompare6300.Interface.OnOfferChangeListener;
import edu.gatech.seclass.jobcompare6300.helpers.DBHelper;
import edu.gatech.seclass.jobcompare6300.helpers.RankingHelper;
import edu.gatech.seclass.jobcompare6300.helpers.RecyclerViewCurrentJobHelper;
import edu.gatech.seclass.jobcompare6300.helpers.RecyclerViewHelper;

public class MainActivity extends AppCompatActivity implements OnOfferChangeListener {
    RecyclerView topOffersRecyclerView;
    RecyclerView currentRecyclerView;
    RecyclerViewHelper adapter;
    RecyclerViewCurrentJobHelper currentAdapter;
    List<Job> jobList = new ArrayList<Job>();
    List<Job> currentJob = new ArrayList<Job>();
    Button viewAll;
    DBHelper DB;
    ImageView compareIcon, settingsIcon, addJobIcon, editCurrentJob;
    TextView titleJobCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Home");
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        topOffersRecyclerView = findViewById(R.id.topOffersRecicler);
        currentRecyclerView = findViewById(R.id.currentJobRecycler);
        viewAll = (Button) findViewById(R.id.viewAllJobsBtn);
        compareIcon = (ImageView) findViewById(R.id.compareMain);
        settingsIcon = (ImageView) findViewById(R.id.settingsMain);
        addJobIcon = (ImageView) findViewById(R.id.addJobMain);
        titleJobCount = findViewById(R.id.jobOfferTitle);
        editCurrentJob = (ImageView) findViewById(R.id.editCurrentJobMain);

        viewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, jobOfferList.class);
                startActivity(i);
            }
        });

        addJobIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, addJob.class);
                i.putExtra("action", "add");
                startActivity(i);
            }
        });

        settingsIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, ComparisonSettings.class);
                startActivity(i);
            }
        });

        compareIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (jobList.size() >= 2){
                    Intent i = new Intent(MainActivity.this, jobOfferList.class);
                    startActivity(i);
                }
            }
        });

        editCurrentJob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (currentJob.size() == 1){
                    Intent i = new Intent(MainActivity.this, addJob.class);
                    i.putExtra("action", "update");
                    i.putExtra("id", currentJob.get(0).getID());
                    i.putExtra("title", currentJob.get(0).getTitle());
                    i.putExtra("company", currentJob.get(0).getCompany());
                    i.putExtra("location", currentJob.get(0).getLocation());
                    i.putExtra("salary", currentJob.get(0).getSalary());
                    i.putExtra("bonus", currentJob.get(0).getBonus());
                    i.putExtra("leaveTime", currentJob.get(0).getLeaveTime());
                    i.putExtra("stockShares", currentJob.get(0).getStockShares());
                    i.putExtra("homeFund", currentJob.get(0).getHomeFund());
                    i.putExtra("wellnessFund", currentJob.get(0).getWellnessFund());
                    i.putExtra("isCurrentJob", currentJob.get(0).getIsCurrentJob());
                    startActivity(i);
                }
                else{
                    Intent i = new Intent(MainActivity.this, addJob.class);
                    i.putExtra("action", "add");
                    i.putExtra("isCurrentJob", true);
                    startActivity(i);
                }
            }
        });

        setView();

    }

    @Override
    protected void onResume()
    {
        super.onResume();
        setView();
    }

    private void setView (){
        jobList.clear();
        currentJob.clear();
        DB = new DBHelper(this);
        Cursor cursor = DB.getJobs();
        if (cursor.getCount() < 2){
            viewAll.setVisibility(View.INVISIBLE);
        }
        else{
            viewAll.setVisibility(View.VISIBLE);
        }

        while (cursor.moveToNext()){
            int idIndex = cursor.getColumnIndex("id");
            int titleIndex = cursor.getColumnIndex("title");
            int companyIndex = cursor.getColumnIndex("company");
            int locationIndex = cursor.getColumnIndex("location");
            int salaryIndex = cursor.getColumnIndex("salary");
            int bonusIndex = cursor.getColumnIndex("bonus");
            int leaveTimeIndex = cursor.getColumnIndex("leaveTime");
            int stockSharesIndex = cursor.getColumnIndex("stockShares");
            int homeFundIndex = cursor.getColumnIndex("homeFund");
            int wellnessFundIndex = cursor.getColumnIndex("wellnessFund");
            int isCurrentJobIndex = cursor.getColumnIndex("isCurrentJob");

            String id = cursor.getString(idIndex);
            String title = cursor.getString(titleIndex);
            String company = cursor.getString(companyIndex);
            String location = cursor.getString(locationIndex);
            Float salary = Float.parseFloat(cursor.getString(salaryIndex));
            Float bonus = Float.parseFloat(cursor.getString(bonusIndex));
            int leaveTime = Integer.parseInt(cursor.getString(leaveTimeIndex));
            Float stockShares = Float.parseFloat(cursor.getString(stockSharesIndex));
            Float homeFund = Float.parseFloat(cursor.getString(homeFundIndex));
            Float wellnessFund = Float.parseFloat(cursor.getString(wellnessFundIndex));
            String isCurrentJob = cursor.getString(isCurrentJobIndex).equals("1") ? "true" : "false";

            if(isCurrentJob == "false"){
                jobList.add(new Job(id, title, company, location, salary, bonus, leaveTime, stockShares, homeFund, wellnessFund, Boolean.parseBoolean(isCurrentJob)));
            };
            if(isCurrentJob == "true"){
                currentJob.add(new Job(id, title, company, location, salary, bonus, leaveTime, stockShares, homeFund, wellnessFund, Boolean.parseBoolean(isCurrentJob)));
            };
        }

        RankingHelper rankingHelper = new RankingHelper();
        jobList = rankingHelper.rankJobs(jobList);

        adapter = new RecyclerViewHelper(this, jobList, this::onDeleteOffer);
        topOffersRecyclerView.setAdapter(adapter);
        topOffersRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        currentAdapter = new RecyclerViewCurrentJobHelper(this, currentJob);
        currentRecyclerView.setAdapter(currentAdapter);
        currentRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        titleJobCount.setText(String.format("Job Offers (%s)", Integer.toString(jobList.size())));

        if(jobList.size() < 2){
            compareIcon.setColorFilter(0xFFFF0000);
        }
        else{
            compareIcon.setColorFilter(0xFF000000);
        }
    }

    @Override
    public void onDeleteOffer(int numOfOffers) {
        setView();
    }
}