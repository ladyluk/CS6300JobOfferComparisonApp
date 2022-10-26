package edu.gatech.seclass.jobcompare6300;

import android.content.Intent;
import android.os.Bundle;
//import android.view.View;
//import android.widget.EditText;
import android.widget.ImageView;

import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

@SuppressWarnings("SpellCheckingInspection")
public class ComparisonTable extends AppCompatActivity {

    ImageView homeIcon;
    ImageView backArrowIcon;

    String title1;
    String title2;
    String company1;
    String company2;
    String location1;
    String location2;
    String salary1;
    String salary2;
    String bonus1;
    String bonus2;
    String stocks1;
    String stocks2;
    String homefund1;
    String homefund2;
    String wellnessfund1;
    String wellnessfund2;
    String leave1;
    String leave2;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comparison);
        getSupportActionBar().hide();

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            title1 = extras.getString("title1");
            title2 = extras.getString("title2");
            TextView t_title1 = findViewById(R.id.title1);
            t_title1.setText(title1);
            TextView t_title2 = findViewById(R.id.title2);
            t_title2.setText(title2);

            company1 = extras.getString("company1");
            company2 = extras.getString("company2");
            TextView t_company1 = findViewById(R.id.company1);
            t_company1.setText(company1);
            TextView t_company2 = findViewById(R.id.company2);
            t_company2.setText(company2);

            location1 = extras.getString("location1");
            location2 = extras.getString("location2");
            TextView t_location1 = findViewById(R.id.location1);
            t_location1.setText(location1);
            TextView t_location2 = findViewById(R.id.location2);
            t_location2.setText(location2);
//
            salary1 = extras.getString("salary1");
            salary2 = extras.getString("salary2");
            TextView t_salary1 = findViewById(R.id.salary1);
            t_salary1.setText(salary1);
            TextView t_salary2 = findViewById(R.id.salary2);
            t_salary2.setText(salary2);
//
            bonus1 = extras.getString("bonus1");
            bonus2 = extras.getString("bonus2");
            TextView t_bonus1 = findViewById(R.id.bonus1);
            t_bonus1.setText(bonus1);
            TextView t_bonus2 = findViewById(R.id.bonus2);
            t_bonus2.setText(bonus2);
//
            stocks1 = extras.getString("stocks1");
            stocks2 = extras.getString("stocks2");
            TextView t_stocks1 = findViewById(R.id.shares1);
            t_stocks1.setText(stocks1);
            TextView t_stocks2 = findViewById(R.id.shares2);
            t_stocks2.setText(stocks2);
//
            homefund1 = extras.getString("programFund1");
            homefund2 = extras.getString("programFund2");
            TextView t_homefund1 = findViewById(R.id.homefund1);
            t_homefund1.setText(homefund1);
            TextView t_homefund2 = findViewById(R.id.homefund2);
            t_homefund2.setText(homefund2);
//
            wellnessfund1 = extras.getString("wellnessFund1");
            wellnessfund2 = extras.getString("wellnessFund2");
            TextView t_wellnessfund1 = findViewById(R.id.wellnessfund1);
            t_wellnessfund1.setText(wellnessfund1);
            TextView t_wellnessfund2 = findViewById(R.id.wellnessfund2);
            t_wellnessfund2.setText(wellnessfund2);

            leave1 = extras.getString("leave1");
            leave2 = extras.getString("leave2");
            TextView t_leave1 = findViewById(R.id.leave1);
            t_leave1.setText(leave1);
            TextView t_leave2 = findViewById(R.id.leave2);
            t_leave2.setText(leave2);

        }


        homeIcon = findViewById(R.id.homeIconOfferComparison);
        backArrowIcon = findViewById(R.id.backArrowIconOfferComparison);

        homeIcon.setOnClickListener(view -> {
            Intent i = new Intent(ComparisonTable.this, MainActivity.class);
            startActivity(i);
        });

        backArrowIcon.setOnClickListener(view -> {
            finish();
        });

    }
}
