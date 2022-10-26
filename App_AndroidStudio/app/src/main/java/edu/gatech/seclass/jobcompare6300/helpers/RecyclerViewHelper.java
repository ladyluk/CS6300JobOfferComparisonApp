package edu.gatech.seclass.jobcompare6300.helpers;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import edu.gatech.seclass.jobcompare6300.DTOs.Job;
import edu.gatech.seclass.jobcompare6300.Interface.OnOfferChangeListener;
import edu.gatech.seclass.jobcompare6300.MainActivity;
import edu.gatech.seclass.jobcompare6300.R;
import edu.gatech.seclass.jobcompare6300.addJob;
import edu.gatech.seclass.jobcompare6300.jobOfferList;

public class RecyclerViewHelper extends RecyclerView.Adapter<RecyclerViewHelper.MyViewHolder> {
    List<Job> jobList;
    Context context;
    LayoutInflater inflater;
    DBHelper DB;
    private OnOfferChangeListener onOfferChangeListener;

    public RecyclerViewHelper (Context ct, List<Job> jobList, OnOfferChangeListener onOfferChangeListener){
        this.jobList = jobList;
        context = ct;
        DB = new DBHelper(ct);
        this.onOfferChangeListener = onOfferChangeListener;

        inflater = LayoutInflater.from(context);
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.acivity_simple_job, parent, false);
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

    }


    @Override
    public int getItemCount() {
        return jobList.size() > 5 ? 5 : jobList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, PopupMenu.OnMenuItemClickListener {

        TextView title, company;
        ImageButton dropDownIcon;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            title = itemView.findViewById(R.id.jobTitle);
            company = itemView.findViewById(R.id.jobCompany);

            dropDownIcon = itemView.findViewById(R.id.dropDownIcon);
            dropDownIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showPopupMenu(view);
                }
            });
        }

        @Override
        public void onClick(View view){
            Toast.makeText(context, jobList.get(getAdapterPosition()).getTitle(), Toast.LENGTH_SHORT).show();
        }

        private void showPopupMenu(View view){
            PopupMenu popupMenu = new PopupMenu(view.getContext(), view);
            popupMenu.inflate(R.menu.popup_menu);
            popupMenu.setOnMenuItemClickListener(this);
            popupMenu.show();
        }


        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            switch(menuItem.getItemId()){
                case R.id.action_popup_edit:
                    int position = getAdapterPosition();
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
                    return true;
                case R.id.action_popup_delete:
                    boolean isSuccessful = DB.deleteJob(jobList.get(getAdapterPosition()).getID());
                    if (isSuccessful){
                        Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show();
                        onOfferChangeListener.onDeleteOffer(jobList.size());
                    }
                    else{
                        Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
                    }
                    return true;
                default:
                    return false;
            }
        }

    }

}
