package inventoryapp;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "inventory.db";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE admin (username TEXT PRIMARY KEY, password TEXT)");
        db.execSQL("CREATE TABLE user (username TEXT PRIMARY KEY, password TEXT)");
        db.execSQL("CREATE TABLE equipment (id TEXT PRIMARY KEY, name TEXT)");
        db.execSQL("CREATE TABLE issued (username TEXT, equipment_id TEXT, issue_date TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS admin");
        db.execSQL("DROP TABLE IF EXISTS user");
        db.execSQL("DROP TABLE IF EXISTS equipment");
        db.execSQL("DROP TABLE IF EXISTS issued");
        onCreate(db);
    }

    // Admin login
    public boolean checkAdminCredentials(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM admin WHERE username=? AND password=?", new String[]{username, password});
        boolean result = cursor.getCount() > 0;
        cursor.close();
        return result;
    }

    // User login
    public boolean checkUserCredentials(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM user WHERE username=? AND password=?", new String[]{username, password});
        boolean result = cursor.getCount() > 0;
        cursor.close();
        return result;
    }

    // Add admin
    public boolean addAdmin(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("username", username);
        values.put("password", password);
        long result = db.insert("admin", null, values);
        return result != -1;
    }

    // Add user
    public boolean addUser(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("username", username);
        values.put("password", password);
        long result = db.insert("user", null, values);
        return result != -1;
    }

    // Add equipment
    public boolean addEquipment(String name, String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("id", id);
        long result = db.insert("equipment", null, values);
        return result != -1;
    }

    // Issue equipment
// Issue equipment
    public boolean issueEquipment(String username, String equipmentId) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("username", username);
        values.put("equipment_id", equipmentId);
        values.put("issue_date", System.currentTimeMillis()); // Adjusted to include issue date
        long result = db.insert("issued", null, values);
        return result != -1;
    }


    // Get issued equipment with usernames from user table
    public List<IssuedEquipment> getIssuedEquipments() {
        List<IssuedEquipment> issuedEquipments = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT u.username, i.equipment_id, i.issue_date " +
                "FROM issued i " +
                "INNER JOIN user u ON i.username = u.username", null);
        if (cursor.moveToFirst()) {
            do {
                String username = cursor.getString(cursor.getColumnIndex("username"));
                String equipmentId = cursor.getString(cursor.getColumnIndex("equipment_id"));
                String issueDate = cursor.getString(cursor.getColumnIndex("issue_date"));

                IssuedEquipment equipment = new IssuedEquipment(username, equipmentId, issueDate);
                issuedEquipments.add(equipment);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return issuedEquipments;
    }




    // Get available equipment
    @SuppressLint("Range")
    public List<String> getAvailableEquipments() {
        List<String> availableEquipments = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT id, name FROM equipment WHERE id NOT IN (SELECT equipment_id FROM issued)", null);
        if (cursor.moveToFirst()) {
            do {
                availableEquipments.add(cursor.getString(cursor.getColumnIndex("name")) + " (ID: " + cursor.getString(cursor.getColumnIndex("id")) + ")");
            } while (cursor.moveToNext());
        }
        cursor.close();
        return availableEquipments;
    }
}
