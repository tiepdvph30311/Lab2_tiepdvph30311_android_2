package tiepdvph30311.fpoly.lab2.lab2;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import java.util.List;

public class Dao2 {
    private DBHelper2 dbHelper2;
    private SQLiteDatabase db;

    public Dao2(Context context) {
        dbHelper2 = new DBHelper2(context);
    }

    public long addModel(Model model) {
        db = dbHelper2.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DBHelper2.COLUMN_TITLE, model.getTitle());
        values.put(DBHelper2.COLUMN_CONTENT, model.getContent());
        values.put(DBHelper2.COLUMN_DATE, model.getDate());
        values.put(DBHelper2.COLUMN_TYPE, model.getType());
        return db.insert(DBHelper2.TABLE_NAME, null, values);
    }

    public int updateModel(Model model) {
        db = dbHelper2.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DBHelper2.COLUMN_TITLE, model.getTitle());
        values.put(DBHelper2.COLUMN_CONTENT, model.getContent());
        values.put(DBHelper2.COLUMN_DATE, model.getDate());
        values.put(DBHelper2.COLUMN_TYPE, model.getType());
        return db.update(DBHelper2.TABLE_NAME, values, DBHelper2.COLUMN_ID + "=?", new String[]{String.valueOf(model.getId())});
    }

    public void deleteModel(int id) {
        db = dbHelper2.getWritableDatabase();
        db.delete(DBHelper2.TABLE_NAME, DBHelper2.COLUMN_ID + "=?", new String[]{String.valueOf(id)});
    }

    @SuppressLint("Range")
    public List<Model> getAllModels() {
        List<Model> modelList = new ArrayList<>();
        db = dbHelper2.getReadableDatabase();
        Cursor cursor = db.query(DBHelper2.TABLE_NAME, null, null, null, null, null, DBHelper2.COLUMN_ID + " DESC");

        if (cursor.moveToFirst()) {
            do {
                Model model = new Model();
                model.setId(cursor.getInt(cursor.getColumnIndex(DBHelper2.COLUMN_ID)));
                model.setTitle(cursor.getString(cursor.getColumnIndex(DBHelper2.COLUMN_TITLE)));
                model.setContent(cursor.getString(cursor.getColumnIndex(DBHelper2.COLUMN_CONTENT)));
                model.setDate(cursor.getString(cursor.getColumnIndex(DBHelper2.COLUMN_DATE)));
                model.setType(cursor.getString(cursor.getColumnIndex(DBHelper2.COLUMN_TYPE)));
                modelList.add(model);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return modelList;
    }
}
