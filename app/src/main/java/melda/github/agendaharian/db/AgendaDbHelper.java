package melda.github.agendaharian.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class AgendaDbHelper extends SQLiteOpenHelper {
    public AgendaDbHelper(Context context){
        super(context,AgendaDbInfo.DB_NAME, null, AgendaDbInfo.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE "+
                AgendaDbInfo.TaskEntry.TABLE_NAME + "(" +
                AgendaDbInfo.TaskEntry._ID + "INTEGER PRIMARY KEY AUTOINCREMENT, " +
                AgendaDbInfo.TaskEntry.COL_TODO_TITLE + "TEXT NOT NULL);";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE NAME IF EXISTS "+
                AgendaDbInfo.TaskEntry.TABLE_NAME);
        onCreate(db);
    }
    public void tambahData(String todo){
        SQLiteDatabase mWritableDB = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(AgendaDbInfo.TaskEntry.COL_TODO_TITLE, todo);
        mWritableDB.insertWithOnConflict(AgendaDbInfo.TaskEntry.TABLE_NAME, null, values,
                SQLiteDatabase.CONFLICT_REPLACE);
        mWritableDB.close();
    }
    public void hapusData(String todo){
        SQLiteDatabase mWritableDB = getWritableDatabase();
        mWritableDB.delete(AgendaDbInfo.TaskEntry.TABLE_NAME,AgendaDbInfo.TaskEntry.COL_TODO_TITLE +
                " = ?", new String[]{todo});
        mWritableDB.close();
    }
    public ArrayList<String> ambilSemuaData(){
        ArrayList<String> taskList = new ArrayList<>();
        SQLiteDatabase mReadableDB = getReadableDatabase();
        Cursor cursor = mReadableDB.query(AgendaDbInfo.TaskEntry.TABLE_NAME,
                new String[]{AgendaDbInfo.TaskEntry._ID, AgendaDbInfo.TaskEntry.COL_TODO_TITLE},
                null, null, null, null, null);
        while (cursor.moveToNext()){
            int idx = cursor.getColumnIndex(AgendaDbInfo.TaskEntry.COL_TODO_TITLE);
            taskList.add(cursor.getString(idx));
            cursor.close();
            mReadableDB.close();
            return taskList;
        }
    }
}
