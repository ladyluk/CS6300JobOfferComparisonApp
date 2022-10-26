package edu.gatech.seclass.jobcompare6300;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.Checkable;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.Switch;

import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;
import java.util.UUID;

import edu.gatech.seclass.jobcompare6300.DTOs.Job;
import edu.gatech.seclass.jobcompare6300.helpers.DBHelper;

public class addJob extends AppCompatActivity {

    EditText title, company, location, salary, bonus, leaveTime, stockShares, homeFund, wellnessFund;
    Checkable isCurrentJob;
    Button addJob, exitJob;
    DBHelper DB;
    ImageView homeIcon, backArrowIcon;
    Bundle extras;
    ScrollView scrollView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        setContentView(R.layout.activity_add_job);
        getSupportActionBar().hide();

        title = (EditText) findViewById(R.id.titleFromInput);
        company = (EditText) findViewById(R.id.companyFromInput);
        location = (EditText) findViewById(R.id.locationFromIInput);
        salary = (EditText) findViewById(R.id.salaryFromInput);
        bonus = (EditText) findViewById(R.id.bonusFromInput);
        leaveTime = (EditText) findViewById(R.id.leaveTimeFromInput);
        stockShares = (EditText) findViewById(R.id.stockSharesFromInput);
        homeFund = (EditText) findViewById(R.id.homeFundFromInput);
        wellnessFund = (EditText) findViewById(R.id.wellnessFundFromInput);
        isCurrentJob = (Switch) findViewById(R.id.isCurrentJobFromInput);
        addJob = (Button) findViewById(R.id.saveJobBtn);
//        exitJob = (Button) findViewById(R.id.exitJobBtn);
        homeIcon = findViewById(R.id.homeIconAddJob);
        backArrowIcon = findViewById(R.id.backarrowAddJob);
        scrollView = findViewById(R.id.addJobScrollView);

        DB = new DBHelper(this);

        extras = getIntent().getExtras();
        if (extras != null) {
            if (Objects.equals(extras.getString("action"), "update")){
                title.setText(extras.getString("title"));
                company.setText(extras.getString("company"));
                location.setText(extras.getString("location"));
                salary.setText(String.valueOf(extras.getFloat("salary")));
                bonus.setText(String.valueOf(extras.getFloat("bonus")));
                leaveTime.setText(String.valueOf(extras.getInt("leaveTime")));
                stockShares.setText(String.valueOf(extras.getFloat("stockShares")));
                homeFund.setText(String.valueOf(extras.getFloat("homeFund")));
                wellnessFund.setText(String.valueOf(extras.getFloat("wellnessFund")));
                isCurrentJob.setChecked(extras.getBoolean("isCurrentJob"));
            }

            if (extras.containsKey("isCurrentJob")){
                isCurrentJob.setChecked(extras.getBoolean("isCurrentJob"));
            }
        }

        addJob.setOnClickListener(new View.OnClickListener() {
            public void errorToast(String errorMessage){
                scrollView.smoothScrollTo(0, 0);
                Toast toast = Toast.makeText(addJob.this, errorMessage, Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER_VERTICAL|0,0,0);
                View toastView = toast.getView();

                TextView text = toastView.findViewById(android.R.id.message);
                text.setTextColor(Color.parseColor("#EE4B2B"));

                toast.show();
            }

            public void successToast(String successMessage){
                scrollView.smoothScrollTo(0, 0);
                Toast toast = Toast.makeText(addJob.this, successMessage, Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER_VERTICAL|0,0,0);
                View toastView = toast.getView();

                TextView text = toastView.findViewById(android.R.id.message);
                text.setTextColor(Color.parseColor("#007500"));

                toast.show();
            }

            @Override
            public void onClick(View view) {
                {
                boolean returnEmpty = false;
                if (Objects.equals(title.getText().toString(), "")){
                    title.setError("Required field");
                    returnEmpty = true;
                }
                if (Objects.equals(company.getText().toString(), "")){
                    company.setError("Required field");
                    returnEmpty = true;
                }
                if (Objects.equals(location.getText().toString(), "")){
                    location.setError("Required field");
                    returnEmpty = true;
                }
                if (Objects.equals(salary.getText().toString(), "")){
                    salary.setError("Required field");
                    returnEmpty = true;
                }
                if (Objects.equals(bonus.getText().toString(), "")){
                    bonus.setError("Required field");
                    returnEmpty = true;
                }
                if (Objects.equals(leaveTime.getText().toString(), "")){
                    leaveTime.setError("Required field");
                    returnEmpty = true;
                }
                if (Objects.equals(stockShares.getText().toString(), "")){
                    stockShares.setError("Required field");
                    returnEmpty = true;
                }
                if (Objects.equals(homeFund.getText().toString(), "")){
                    homeFund.setError("Required field");
                    returnEmpty = true;
                }
                if (Objects.equals(wellnessFund.getText().toString(), "")){
                    wellnessFund.setError("Required field");
                    returnEmpty = true;
                }
                if (returnEmpty) {
                    errorToast("Error. Please check Fields.");
                    return;
                }
                if (!salary.getText().toString().matches("\\d+(\\.\\d+)?")){
                    salary.setError("Please input numerical values only");
                    returnEmpty = true;
                }
                if (!bonus.getText().toString().matches("\\d+(\\.\\d+)?")){
                    bonus.setError("Please input numerical values only");
                    returnEmpty = true;
                }
                if (!leaveTime.getText().toString().matches("^[0-9]*$")){
                    leaveTime.setError("Please input numerical values only");
                    returnEmpty = true;
                }
                if (!stockShares.getText().toString().matches("\\d+(\\.\\d+)?")){
                    stockShares.setError("Please input numerical values only");
                    returnEmpty = true;
                }
                if (!homeFund.getText().toString().matches("\\d+(\\.\\d+)?")){
                    homeFund.setError("Please input numerical values only");
                    returnEmpty = true;
                }
                if (!wellnessFund.getText().toString().matches("\\d+(\\.\\d+)?")){
                    wellnessFund.setError("Please input numerical values only");
                    returnEmpty = true;
                }
                if (returnEmpty) {
                    errorToast("Error. Please check Fields.");
                    return;
                }
                if (Float.valueOf(salary.getText().toString()) < 0){
                    salary.setError("Please input positive values");
                    returnEmpty = true;
                }
                if (Float.valueOf(bonus.getText().toString()) < 0){
                    bonus.setError("Please input positive values");
                    returnEmpty = true;
                }
                if (Float.valueOf(leaveTime.getText().toString()) < 0){
                    leaveTime.setError("Please input positive values");
                    returnEmpty = true;
                }
                if (Float.valueOf(stockShares.getText().toString()) < 0){
                    stockShares.setError("Please input positive values");
                    returnEmpty = true;
                }
                if (Float.valueOf(homeFund.getText().toString()) > 5000 || Float.valueOf(homeFund.getText().toString()) < 0){
                    homeFund.setError("Please input values between $0 and $5000");
                    returnEmpty = true;
                }
                if (Float.valueOf(wellnessFund.getText().toString()) > 0.15*Float.valueOf(salary.getText().toString()) || Float.valueOf(wellnessFund.getText().toString()) < 0){
                    wellnessFund.setError("Value must not be larger than 15% of yearly salary");
                    returnEmpty = true;
                }
                if (returnEmpty) {
                    errorToast("Error. Please check Fields.");
                    return;
                }}

                // TODO Note: Do below only if the "isCurrentJob" of job being added/updated is true AND the job being updated is not THE current job.
                DB = new DBHelper(addJob.this);
                Cursor cursor = DB.getCurrentJobIfExists();
//                if (cursor.getCount() == 0 || (cursor.getCount() == 1 && !isCurrentJob.isChecked())){
                if (!isCurrentJob.isChecked()){
                    // TODO No Current Job or isCurrentJob unchecked - Add/Update normally

                    Bundle extras = getIntent().getExtras();
                    if (Objects.equals(extras.getString("action"), "add")) {
                        String id = UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
                        Job job = new Job(
                                id,
                                title.getText().toString(),
                                company.getText().toString(),
                                location.getText().toString(),
                                Float.parseFloat(salary.getText().toString()),
                                Float.parseFloat(bonus.getText().toString()),
                                Integer.parseInt(leaveTime.getText().toString()),
                                Float.parseFloat(stockShares.getText().toString()),
                                Float.parseFloat(homeFund.getText().toString()),
                                Float.parseFloat(wellnessFund.getText().toString()),
                                isCurrentJob.isChecked()
                        );
                        boolean isSuccessful = DB.insertJob(job);
                        if (isSuccessful){
                            successToast("Success");
                            title.setText("");
                            company.setText("");
                            location.setText("");
                            salary.setText("");
                            bonus.setText("");
                            leaveTime.setText("");
                            stockShares.setText("");
                            homeFund.setText("");
                            wellnessFund.setText("");
                            isCurrentJob.setChecked(false);
                            scrollView.smoothScrollTo(0, 0);
                        }
                        else{
                            errorToast("Error");
                        }
                    }
                    else if (Objects.equals(extras.getString("action"), "update")){
                        String id = extras.getString("id");
                        Job job = new Job(
                                id,
                                title.getText().toString(),
                                company.getText().toString(),
                                location.getText().toString(),
                                Float.parseFloat(salary.getText().toString()),
                                Float.parseFloat(bonus.getText().toString()),
                                Integer.parseInt(leaveTime.getText().toString()),
                                Float.parseFloat(stockShares.getText().toString()),
                                Float.parseFloat(homeFund.getText().toString()),
                                Float.parseFloat(wellnessFund.getText().toString()),
                                isCurrentJob.isChecked()
                        );
                        boolean isSuccessful = DB.updateJob(job);
                        if (isSuccessful){
                            successToast("Update Successful");
                            title.setText("");
                            company.setText("");
                            location.setText("");
                            salary.setText("");
                            bonus.setText("");
                            leaveTime.setText("");
                            stockShares.setText("");
                            homeFund.setText("");
                            wellnessFund.setText("");
                            isCurrentJob.setChecked(false);
                            scrollView.smoothScrollTo(0, 0);
                        }
                        else{
                            Toast.makeText(addJob.this, "Update Error", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                else if (isCurrentJob.isChecked()){
                    // TODO: 1 current job, prompt user if he wants to switch and handle here
                    // TODO: If yes, set with DB.updateJob the "isCurrentJob" of currentJob to false, and the "updateJob" of displayed job to true

                    if (cursor.getCount() == 1 && !extras.getBoolean("isCurrentJob")){
                        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                switch (which){
                                    case DialogInterface.BUTTON_POSITIVE:
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

                                            Job jobCurrent = new Job(cursor.getString(idIndex),
                                                    cursor.getString(titleIndex),
                                                    cursor.getString(companyIndex),
                                                    cursor.getString(locationIndex),
                                                    Float.parseFloat(cursor.getString(salaryIndex)),
                                                    Float.parseFloat(cursor.getString(bonusIndex)),
                                                    Integer.parseInt(cursor.getString(leaveTimeIndex)),
                                                    Float.parseFloat(cursor.getString(stockSharesIndex)),
                                                    Float.parseFloat(cursor.getString(homeFundIndex)),
                                                    Float.parseFloat(cursor.getString(wellnessFundIndex)),
                                                    false);
                                            DB.updateJob(jobCurrent);

                                            String id;
                                            Bundle extras = getIntent().getExtras();
                                            if (extras == null || Objects.equals(extras.getString("action"), "add")) {
                                                id = UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
                                            }
                                            else {
                                                id = extras.getString("id");
                                            }

                                            Job job = new Job(
                                                    id,
                                                    title.getText().toString(),
                                                    company.getText().toString(),
                                                    location.getText().toString(),
                                                    Float.parseFloat(salary.getText().toString()),
                                                    Float.parseFloat(bonus.getText().toString()),
                                                    Integer.parseInt(leaveTime.getText().toString()),
                                                    Float.parseFloat(stockShares.getText().toString()),
                                                    Float.parseFloat(homeFund.getText().toString()),
                                                    Float.parseFloat(wellnessFund.getText().toString()),
                                                    isCurrentJob.isChecked()
                                            );
                                            boolean isSuccessful;
                                            if (extras == null || Objects.equals(extras.getString("action"), "add")) {
                                                isSuccessful = DB.insertJob(job);
                                            }
                                            else {
                                                isSuccessful = DB.updateJob(job);
                                            }
                                            if (isSuccessful){
                                                successToast("Success");
                                                title.setText("");
                                                company.setText("");
                                                location.setText("");
                                                salary.setText("");
                                                bonus.setText("");
                                                leaveTime.setText("");
                                                stockShares.setText("");
                                                homeFund.setText("");
                                                wellnessFund.setText("");
                                                isCurrentJob.setChecked(false);
                                                scrollView.smoothScrollTo(0, 0);
                                            }
                                            else{
                                                errorToast("Error");
                                            }
                                        }
                                        break;

                                    case DialogInterface.BUTTON_NEGATIVE:
                                        String id;
                                        Bundle extras = getIntent().getExtras();
                                        if (extras == null || Objects.equals(extras.getString("action"), "add")) {
                                            id = UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
                                        }
                                        else {
                                            id = extras.getString("id");
                                        }
                                        Job job = new Job(
                                                id,
                                                title.getText().toString(),
                                                company.getText().toString(),
                                                location.getText().toString(),
                                                Float.parseFloat(salary.getText().toString()),
                                                Float.parseFloat(bonus.getText().toString()),
                                                Integer.parseInt(leaveTime.getText().toString()),
                                                Float.parseFloat(stockShares.getText().toString()),
                                                Float.parseFloat(homeFund.getText().toString()),
                                                Float.parseFloat(wellnessFund.getText().toString()),
                                                false
                                        );
                                        boolean isSuccessful;
                                        if (extras == null || Objects.equals(extras.getString("action"), "add")) {
                                            isSuccessful = DB.insertJob(job);
                                        }
                                        else {
                                            isSuccessful = DB.updateJob(job);
                                        }
                                        if (isSuccessful){
                                            successToast("Success");
                                            title.setText("");
                                            company.setText("");
                                            location.setText("");
                                            salary.setText("");
                                            bonus.setText("");
                                            leaveTime.setText("");
                                            stockShares.setText("");
                                            homeFund.setText("");
                                            wellnessFund.setText("");
                                            isCurrentJob.setChecked(false);
                                            scrollView.smoothScrollTo(0, 0);
                                        }
                                        else{
                                            errorToast("Error");
                                        }
                                        break;
                                }
                            }
                        };

                        AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                        builder.setMessage("Do you want to replace this job with the current job?").setPositiveButton("Yes", dialogClickListener)
                                .setNegativeButton("No", dialogClickListener).show();
                    }
                    else if (cursor.getCount() == 0 || extras.getBoolean("isCurrentJob")){
                        String id;
                        Bundle extras = getIntent().getExtras();
                        if (extras == null || Objects.equals(extras.getString("action"), "add")) {
                            id = UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
                        }
                        else {
                            id = extras.getString("id");
                        }
                        Job job = new Job(
                                id,
                                title.getText().toString(),
                                company.getText().toString(),
                                location.getText().toString(),
                                Float.parseFloat(salary.getText().toString()),
                                Float.parseFloat(bonus.getText().toString()),
                                Integer.parseInt(leaveTime.getText().toString()),
                                Float.parseFloat(stockShares.getText().toString()),
                                Float.parseFloat(homeFund.getText().toString()),
                                Float.parseFloat(wellnessFund.getText().toString()),
                                isCurrentJob.isChecked()
                        );
                        boolean isSuccessful;
                        if (extras == null || Objects.equals(extras.getString("action"), "add")) {
                            isSuccessful = DB.insertJob(job);
                        }
                        else {
                            isSuccessful = DB.updateJob(job);
                        }
                        if (isSuccessful){
                            successToast("Success");
                            title.setText("");
                            company.setText("");
                            location.setText("");
                            salary.setText("");
                            bonus.setText("");
                            leaveTime.setText("");
                            stockShares.setText("");
                            homeFund.setText("");
                            wellnessFund.setText("");
                            isCurrentJob.setChecked(false);
                            scrollView.smoothScrollTo(0, 0);
                        }
                        else{
                            errorToast("Error");
                        }
                    }
                    else {
                        errorToast("Error: more than one current job exist");
                    }
                }


//                String id = UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
//                Job job = new Job(
//                                id,
//                                title.getText().toString(),
//                                company.getText().toString(),
//                                location.getText().toString(),
//                                Float.parseFloat(salary.getText().toString()),
//                                Float.parseFloat(bonus.getText().toString()),
//                                Float.parseFloat(leaveTime.getText().toString()),
//                                Float.parseFloat(stockShares.getText().toString()),
//                                Float.parseFloat(homeFund.getText().toString()),
//                                Float.parseFloat(wellnessFund.getText().toString()),
//                                isCurrentJob.isChecked()
//                        );


//                boolean isSuccessful = DB.insertJob(job);
//                if (isSuccessful){
//                    Toast.makeText(addJob.this, "Success", Toast.LENGTH_SHORT).show();
//                    title.setText("");
//                    company.setText("");
//                    location.setText("");
//                    salary.setText("");
//                    bonus.setText("");
//                    leaveTime.setText("");
//                    stockShares.setText("");
//                    homeFund.setText("");
//                    wellnessFund.setText("");
//                    isCurrentJob.setChecked(false);
//                }
//                else{
//                    Toast.makeText(addJob.this, "Error", Toast.LENGTH_SHORT).show();
//                }
            }
        });



        homeIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Intent i = new Intent(addJob.this, MainActivity.class);
                startActivity(i);
            }
        });

        backArrowIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                finish();
            }
        });

//        exitJob.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i = new Intent(addJob.this, MainActivity.class);
//                startActivity(i);
//            }
//        });
    }
}
