package edu.gatech.seclass.jobcompare6300.helpers;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import edu.gatech.seclass.jobcompare6300.DTOs.Job;
import edu.gatech.seclass.jobcompare6300.Interface.OnOfferChangeListener;
import edu.gatech.seclass.jobcompare6300.R;
import edu.gatech.seclass.jobcompare6300.addJob;
import edu.gatech.seclass.jobcompare6300.jobOfferList;

public class RecyclerViewOfferListHelper extends RecyclerView.Adapter<RecyclerViewOfferListHelper.MyViewHolder2> {
    List<Job> jobList;
    Context context;
    DBHelper DB;
    private OnOfferChangeListener onOfferChangeListener;

    // Screen - 1 for Homepage, 2 for the JobList
    public RecyclerViewOfferListHelper (Context ct, List<Job> jobList, OnOfferChangeListener onOfferChangeListener){
        this.jobList = jobList;
        context = ct;
        DB = new DBHelper(ct);
        this.onOfferChangeListener = onOfferChangeListener;
    }
    @NonNull
    @Override
    public RecyclerViewOfferListHelper.MyViewHolder2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.activity_full_job, parent, false);
        return new RecyclerViewOfferListHelper.MyViewHolder2(view);

    }

    //changes side tab color for current job to green
    @Override
    public void onBindViewHolder(@NonNull RecyclerViewOfferListHelper.MyViewHolder2 holder, int position) {
        if(jobList.get(position).getIsCurrentJob()){
            holder.jobIcon.setColorFilter( 0xFF008000 );
            holder.positionLayout.setBackgroundColor(0xFF008000);
        }
        holder.title.setText(jobList.get(position).getTitle());
        holder.company.setText(jobList.get(position).getCompany());
        holder.position.setText(Integer.toString(position + 1));


        holder.deleteIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isSuccessful = DB.deleteJob(jobList.get(holder.getAdapterPosition()).getID());
                if (isSuccessful){
                    Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show();
//                    refreshList();
//                    notifyItemRemoved(position);
                    onOfferChangeListener.onDeleteOffer(jobList.size());
                }
                else{
                    Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
                }
            }
        });

        holder.editIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holder.getAdapterPosition();
                Intent i = new Intent(context, addJob.class);
                i.putExtra("action", "update");
                i.putExtra("id", jobList.get(position).getID());
                i.putExtra("title", jobList.get(position).getTitle());
                i.putExtra("company", jobList.get(position).getCompany());
                i.putExtra("location", jobList.get(position).getLocation());
                i.putExtra("salary", jobList.get(position).getSalary());
                i.putExtra("bonus", jobList.get(position).getBonus());
                i.putExtra("leaveTime", jobList.get(position).getLeaveTime());
                i.putExtra("stockShares", jobList.get(position).getStockShares());
                i.putExtra("homeFund", jobList.get(position).getHomeFund());
                i.putExtra("wellnessFund", jobList.get(position).getWellnessFund());
                i.putExtra("isCurrentJob", jobList.get(position).getIsCurrentJob());
                context.startActivity(i);
            }
        });

        holder.isSelected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                jobList.get(holder.getAdapterPosition()).setChecked(!jobList.get(holder.getAdapterPosition()).isChecked());
            }
        });

    }

    @Override
    public int getItemCount() {
        return jobList.size();
    }

    public class MyViewHolder2 extends RecyclerView.ViewHolder {
        TextView title;
        TextView company;
        TextView position;
        ImageView editIcon;
        ImageView deleteIcon;
        ImageView jobIcon;
        CheckBox isSelected;
        ConstraintLayout positionLayout;

        public MyViewHolder2(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.titleOffer);
            company = itemView.findViewById(R.id.companyOffer);
            position = itemView.findViewById(R.id.positionOffer);
            editIcon = itemView.findViewById(R.id.editIconJobOfferList);
            deleteIcon = itemView.findViewById(R.id.deleteIconJobOfferList);
            jobIcon = itemView.findViewById(R.id.jobIconFullJob);
            isSelected = itemView.findViewById(R.id.checkBox);
            positionLayout = itemView.findViewById(R.id.positionLayout);
        }
    }

    public void setJobList(List<Job> newJobList){
        this.jobList = newJobList;
        notifyDataSetChanged();
    }

    public List<Job> getSelectedJobs () {
        List<Job> selectedJobs = new ArrayList<>();
        for (Job job : jobList){
            if(job.isChecked()){
                selectedJobs.add(job);
            }
        }

        if(selectedJobs.size() != 2){
            Toast.makeText(context, "Error: Please select exactly 2 jobs to compare", Toast.LENGTH_SHORT).show();
            return null;
        }

        return selectedJobs;
    }

    private void refreshList(){
        jobList.clear();
        DB = new DBHelper(context);
        Cursor cursor = DB.getJobs();

        while (cursor.moveToNext()) {
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
    }
}
