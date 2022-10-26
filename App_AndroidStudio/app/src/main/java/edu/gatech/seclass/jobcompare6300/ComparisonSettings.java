package edu.gatech.seclass.jobcompare6300;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

import edu.gatech.seclass.jobcompare6300.DTOs.ComparisonSettingsSingleton;

public class ComparisonSettings extends AppCompatActivity {
    Button saveSettings;
    Button exitPage;
    ImageView homeIcon;
    ImageView backArrowIcon;
    EditText salaryWeight;
    EditText bonusWeight;
    EditText leaveTimeWeight;
    EditText sharesOfferedWeight;
    EditText homeBuyingProgramFundWeight;
    EditText WellnessFundWeight;

    int salary;
    int bonus;
    int leave;
    int shares;
    int homeBuyingProgram;
    int wellnessFund;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comparison_settings);
        getSupportActionBar().hide();
        ComparisonSettingsSingleton settings = ComparisonSettingsSingleton.getInstance();
        salary = settings.getComparisonSettings().get("yearlySalary");
        bonus = settings.getComparisonSettings().get("yearlyBonus");
        leave = settings.getComparisonSettings().get("leaveTime");
        shares = settings.getComparisonSettings().get("numOfSharesOffered");
        homeBuyingProgram = settings.getComparisonSettings().get("homeBuyingProgramFund");
        wellnessFund = settings.getComparisonSettings().get("wellnessFund");

        saveSettings = findViewById(R.id.saveComparisonButton);
        exitPage = findViewById(R.id.exitComparisonBtn);
        homeIcon = findViewById(R.id.homeIconComparisonSettings);
        backArrowIcon = findViewById(R.id.backarrowComparisonSettings);
        salaryWeight = findViewById(R.id.yearlySalaryWt);
        bonusWeight = findViewById(R.id.yearlyBonusWt);
        leaveTimeWeight = findViewById(R.id.leaveTimeWt);
        sharesOfferedWeight = findViewById(R.id.sharesOfferedWt);
        homeBuyingProgramFundWeight = findViewById(R.id.homeFundWt);
        WellnessFundWeight = findViewById(R.id.wellnessFundWt);


        salaryWeight.setText(Integer.toString(salary));
        bonusWeight.setText(Integer.toString(bonus));
        leaveTimeWeight.setText(Integer.toString(leave));
        sharesOfferedWeight.setText(Integer.toString(shares));
        homeBuyingProgramFundWeight.setText(Integer.toString(homeBuyingProgram));
        WellnessFundWeight.setText(Integer.toString(wellnessFund));

        homeIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Intent i = new Intent(ComparisonSettings.this, MainActivity.class);
                startActivity(i);
            }
        });

        backArrowIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                finish();
            }
        });

        exitPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        saveSettings.setOnClickListener(new View.OnClickListener() {

            public void errorToast(String errorMessage){
                Toast toast = Toast.makeText(ComparisonSettings.this, errorMessage, Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER_VERTICAL|0,0,0);
                View toastView = toast.getView();

                TextView text = toastView.findViewById(android.R.id.message);
                text.setTextColor(Color.parseColor("#EE4B2B"));

                toast.show();
            }

            public void successToast(String successMessage){
                Toast toast = Toast.makeText(ComparisonSettings.this, successMessage, Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER_VERTICAL|0,0,0);
                View toastView = toast.getView();

                TextView text = toastView.findViewById(android.R.id.message);
                text.setTextColor(Color.parseColor("#007500"));

                toast.show();
            }

            @Override
            public void onClick(View view) {
                int numOfErrors = 0;

                try{
                    salary = Integer.parseInt(salaryWeight.getText().toString());
                }
                catch (Exception ex){
                    numOfErrors++;
                    salaryWeight.setError("Please provide correct value.");
                }
                try{
                    bonus = Integer.parseInt(bonusWeight.getText().toString());
                }
                catch (Exception ex){
                    numOfErrors++;
                    bonusWeight.setError("Please provide correct value.");
                }
                try{
                    leave = Integer.parseInt(leaveTimeWeight.getText().toString());
                }
                catch (Exception ex){
                    numOfErrors++;
                    leaveTimeWeight.setError("Please provide correct value.");
                }
                try{
                    shares = Integer.parseInt(sharesOfferedWeight.getText().toString());
                }
                catch (Exception ex){
                    numOfErrors++;
                    sharesOfferedWeight.setError("Please provide correct value.");
                }
                try{
                    homeBuyingProgram = Integer.parseInt(homeBuyingProgramFundWeight.getText().toString());
                }
                catch (Exception ex){
                    numOfErrors++;
                    homeBuyingProgramFundWeight.setError("Please provide correct value.");
                }
                try{
                    wellnessFund = Integer.parseInt(WellnessFundWeight.getText().toString());
                }
                catch (Exception ex){
                    numOfErrors++;
                    WellnessFundWeight.setError("Please provide correct value.");
                }

                if (numOfErrors == 0){
                   settings.setComparisonSettings(
                           new HashMap<String, Integer>(){{
                               put("yearlySalary", salary);
                               put("yearlyBonus", bonus);
                               put("leaveTime", leave);
                               put("numOfSharesOffered", shares);
                               put("homeBuyingProgramFund", homeBuyingProgram);
                               put("wellnessFund", wellnessFund);
                           }}
                   );
                    successToast("Success: Setting successfully applied.");
                }
                else{
                   errorToast("Error: Please input all weight in the correct format (Integer)");
                }
            }
        });
    }
}