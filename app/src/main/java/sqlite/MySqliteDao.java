package sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * 姓名:胡文帅
 * 时间:2017/3/18
 * 邮箱：
 * 备注：
 */

public class MySqliteDao {

    private final MySqlite ms;
    private final SQLiteDatabase sd;

    public MySqliteDao(Context context) {
        ms = new MySqlite(context);
        sd = ms.getWritableDatabase();
    }

    public void add(String name,String image){
        ContentValues values=new ContentValues();
        values.put("name",name);
        values.put("image",image);
        sd.insert("yunifang",null,values);
        sd.close();
    }

    public void delete(String name){
        sd.delete("yunifang"," name = ?",new String []{name});
        sd.close();
    }
    public Cursor query(){

        Cursor cursor=sd.query("yunifang", null, null, null, null, null, null);
        return cursor;

    }
}
