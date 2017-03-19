package sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 姓名:胡文帅
 * 时间:2017/3/18
 * 邮箱：
 * 备注：
 */

public class MySqlite extends SQLiteOpenHelper{
    public MySqlite(Context context) {
        super(context, "yunifang", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table yunifang( _id integer primary key autoincrement , name varchar(20),image varchar(20))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
