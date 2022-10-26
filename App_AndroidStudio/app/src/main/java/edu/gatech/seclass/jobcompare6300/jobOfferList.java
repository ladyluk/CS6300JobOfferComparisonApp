package edu.gatech.seclass.jobcompare6300;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import edu.gatech.seclass.jobcompare6300.DTOs.Job;
import edu.gatech.seclass.jobcompare6300.Interface.OnOfferChangeListener;
import edu.gatech.seclass.jobcompare6300.helpers.DBHelper;
import edu.gatech.seclass.jobcompare6300.helpers.RankingHelper;
import edu.gatech.seclass.jobcompare6300.helpers.RecyclerViewOfferListHelper;

public class jobOfferList extends AppCompatActivity implements OnOfferChangeListener {

    TextView titleJobCount;
    ImageView addIcon;

    ImageView homeIcon;

    RecyclerView recyclerView;
    RecyclerViewOfferListHelper adapter;
    ImageView compareIcon;
    ImageView comparisonSettings;
    List<Job> jobList = new ArrayList<Job>();
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_offer_list);
        getSupportActionBar().hide();
        recyclerView = findViewById(R.id.allOffersRecicler);
        titleJobCount = findViewById(R.id.offerComparisonTitle);
        addIcon = findViewById(R.id.addIconJobOfferList);
        homeIcon = findViewById(R.id.homeIconOfferComparison);
        adapter = new RecyclerViewOfferListHelper(this, jobList, this::onDeleteOffer);
        recyclerView.setAdapter(adapter);
        compareIcon = findViewById(R.id.compareIconJobOfferList);
        comparisonSettings = findViewById(R.id.settingsIconJobOfferList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        compareIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<Job> selectedJobs = adapter.getSelectedJobs();
                if (selectedJobs == null) {
                    return;
                }

                Intent i = new Intent(jobOfferList.this, ComparisonTable.class);
                int counter = 1;
                for(Job job : selectedJobs){
                    i.putExtra(String.format("title%s", counter), job.getTitle());
                    i.putExtra(String.format("company%s", counter), job.getCompany());
                    i.putExtra(String.format("location%s", counter), job.getLocation());
                    i.putExtra(String.format("salary%s", counter), Float.toString(job.getSalary()));
                    i.putExtra(String.format("bonus%s", counter), Float.toString(job.getBonus()));
                    i.putExtra(String.format("stocks%s", counter), Float.toString(job.getStockShares()));
                    i.putExtra(String.format("programFund%s", counter), Float.toString(job.getHomeFund()));
                    i.putExtra(String.format("wellnessFund%s", counter), Float.toString(job.getWellnessFund()));
                    i.putExtra(String.format("leave%s", counter), Integer.toString(job.getLeaveTime()));
                    i.putExtra(String.format("isCurrentJob%s", counter), Boolean.toString(job.getIsCurrentJob()));
                    counter++;
                }

                startActivity(i);
            }
        });

        addIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(jobOfferList.this, addJob.class);
                i.putExtra("action", "add");
                startActivity(i);
            }
        });

        comparisonSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(jobOfferList.this, ComparisonSettings.class);
                startActivity(i);
            }
        });

        homeIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Intent i = new Intent(jobOfferList.this, MainActivity.class);
                startActivity(i);
            }
        });

        setVew();
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        setVew();
    }

    private void setVew (){
        jobList.clear();
        DB = new DBHelper(this);
        Cursor cursor = DB.getJobs();
        if (cursor.getCount() < 2){
            Toast.makeText(jobOfferList.this, "Caution, at least 2 jobs are required to view the list", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(jobOfferList.this, MainActivity.class);
            startActivity(i);
            return;
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

            jobList.add(new Job(id, title, company, location, salary, bonus, leaveTime, stockShares, homeFund, wellnessFund, Boolean.parseBoolean(isCurrentJob)));
        }

        RankingHelper rankingHelper = new RankingHelper();
        jobList = rankingHelper.rankJobs(jobList);

        adapter = new RecyclerViewOfferListHelper(this, jobList, this::onDeleteOffer);
        recyclerView.setAdapter(adapter);
        adapter.setJobList(jobList);
        titleJobCount.setText(String.format("Job Offers (%s)", Integer.toString(jobList.size())));
    }

    @Override
    public void onDeleteOffer(int numOfOffers) {
        setVew();
    }
}