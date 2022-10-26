package edu.gatech.seclass.jobcompare6300.helpers;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import edu.gatech.seclass.jobcompare6300.DTOs.Job;
import edu.gatech.seclass.jobcompare6300.R;
import edu.gatech.seclass.jobcompare6300.addJob;

public class RecyclerViewCurrentJobHelper extends RecyclerView.Adapter<RecyclerViewCurrentJobHelper.MyViewHolder> {
    List<Job> jobList;
    Context context;

    public RecyclerViewCurrentJobHelper(Context ct, List<Job> jobList){
        this.jobList = jobList;
        context = ct;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.current_job_recycler_view, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        String  jobTitle;
        jobTitle = jobList.get(position).getTitle();
        if (jobTitle.length()> 30){
            jobTitle = jobTitle.substring(0,30) + "...";
            holder.title.setText(jobTitle);
        }
        else{
            holder.title.setText(jobList.get(position).getTitle());
        }
        holder.company.setText(jobList.get(position).getCompany());
        holder.location.setText(jobList.get(position).getLocation());

//        holder.editCurrentJobIcon.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                int position = holder.getAdapterPosition();
//                Intent i = new Intent(context, addJob.class);
//                i.putExtra("action", "update");
//                i.putExtra("id", jobList.get(position).getID());
//                i.putExtra("title", jobList.get(position).getTitle());
//                i.putExtra("company", jobList.get(position).getCompany());
//                i.putExtra("location", jobList.get(position).getLocation());
//                i.putExtra("salary", jobList.get(position).getSalary());
//                i.putExtra("bonus", jobList.get(position).getBonus());
//                i.putExtra("leaveTime", jobList.get(position).getLeaveTime());
//                i.putExtra("stockShares", jobList.get(position).getStockShares());
//                i.putExtra("homeFund", jobList.get(position).getHomeFund());
//                i.putExtra("wellnessFund", jobList.get(position).getWellnessFund());
//                i.putExtra("isCurrentJob", jobList.get(position).getIsCurrentJob());
//                context.startActivity(i);
//            }
//        });

    }

    @Override
    public int getItemCount() {
        return jobList.size() > 5 ? 5 : jobList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        TextView company;
        TextView location;
        ImageView editCurrentJobIcon;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.titleCurrent);
            company = itemView.findViewById(R.id.companyCurrent);
            location = itemView.findViewById(R.id.locationCurrent);
            editCurrentJobIcon = itemView.findViewById(R.id.editCurrentJobMain);
        }
    }

}
