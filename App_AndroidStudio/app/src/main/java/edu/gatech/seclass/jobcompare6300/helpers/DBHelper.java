package edu.gatech.seclass.jobcompare6300.helpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import edu.gatech.seclass.jobcompare6300.DTOs.Job;


public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context) {
        super(context, "Job_Compare_DB", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL(
                "create Table Job(id TEXT primary key, title TEXT, company TEXT, location TEXT, salary FLOAT, bonus FLOAT, leaveTime INTEGER, stockShares FLOAT, homeFund FLOAT, wellnessFund FLOAT, isCurrentJob BIT)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("drop Table if exists Job");
    }

    public boolean insertJob(Job job){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("id", job.getID());
        contentValues.put("title", job.getTitle());
        contentValues.put("company",job.getCompany());
        contentValues.put("location",job.getLocation());
        contentValues.put("salary",job.getSalary());
        contentValues.put("bonus",job.getBonus());
        contentValues.put("leaveTime",job.getLeaveTime());
        contentValues.put("stockShares",job.getStockShares());
        contentValues.put("homeFund",job.getHomeFund());
        contentValues.put("wellnessFund",job.getWellnessFund());
        contentValues.put("isCurrentJob",job.getIsCurrentJob() ? 1 : 0);

        return DB.insert("Job", null, contentValues) == -1 ? false : true;
    }

    public boolean updateJob(Job job){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("title", job.getTitle());
        contentValues.put("company",job.getCompany());
        contentValues.put("location",job.getLocation());
        contentValues.put("salary",job.getSalary());
        contentValues.put("bonus",job.getBonus());
        contentValues.put("leaveTime",job.getLeaveTime());
        contentValues.put("stockShares",job.getStockShares());
        contentValues.put("homeFund",job.getHomeFund());
        contentValues.put("wellnessFund",job.getWellnessFund());
        contentValues.put("isCurrentJob",job.getIsCurrentJob() ? 1 : 0);

        // Selecting rows with cursor (if there exist entry - update, otherwise return false)
        Cursor cursor = DB.rawQuery("Select * FROM Job WHERE id = ?", new String [] {job.getID()});

        if (cursor.getCount() > 0){
            return DB.update("Job", contentValues, "id=?", new String[] {job.getID()}) == -1 ? false : true;
        }
        else {
            return false;
        }
    }

    public boolean deleteJob(String jobId){
        SQLiteDatabase DB = this.getWritableDatabase();

        // Selecting rows with cursor (if there exist entry - update, otherwise return false)
        Cursor cursor = DB.rawQuery("Select * FROM Job WHERE id = ?", new String [] {jobId});

        if (cursor.getCount() > 0){
            return DB.delete("Job", "id=?", new String[] {jobId}) == -1 ? false : true;
        }
        else {
            return false;
        }
    }

    public Cursor getJobs(){
        SQLiteDatabase DB = this.getWritableDatabase();

        // Selecting rows with cursor (if there exist entry - update, otherwise return false)
        Cursor cursor = DB.rawQuery("Select * FROM Job", null);
        return cursor;
    }

    public Cursor getCurrentJobIfExists(){
        SQLiteDatabase DB = this.getWritableDatabase();

        /// Selecting rows with cursor (if there exist entry - update, otherwise return false)
        Cursor cursor = DB.rawQuery("Select * FROM Job WHERE isCurrentJob = 1", null);
        return cursor;
    }
}
