package com.ciandt.cursoandroid.worldwondersapp.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.ciandt.cursoandroid.worldwondersapp.database.table.PlaceTable;

public class Database extends SQLiteOpenHelper {
    private static String databaseName = "worldwonders.db";
    private static int databaseVersion = 1;

    public Database(Context context) {
        super(context, databaseName, null, databaseVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL(PlaceTable.SQL_CREATE);

        String sqlMock1 = "INSERT INTO place VALUES(1, 'Machu Picchu', 'Peru', " +
                "'Da hora d+ Machu Picchu', 'http://atp.cx/wp-content/uploads/2011/02/Machu-Picchu1.jpg');";
        String sqlMock2 = "INSERT INTO place VALUES(2, 'Chichen Itza', 'Mexico', " +
                "'Da hora d+ Chichen Itza', 'http://www.chichenitza.com/images/chichenitza.jpg');";
        String sqlMock3 = "INSERT INTO place VALUES(3, 'Christ Redeemer', 'Brazil', " +
                "'Da hora d+ Christ Redeemer', 'http://www.thewondersoftheworld.net/images/ChristTheRedeemer1.jpg');";
        String sqlMock4 = "INSERT INTO place VALUES(4, 'Great Wall', 'China', " +
                "'Da hora d+ Great Wall', 'http://img.timeinc.net/time/photoessays/2008/beijing_travel/great_wall_beijing.jpg');";
        String sqlMock5 = "INSERT INTO place VALUES(5, 'Taj Mahal', 'India', " +
                "'Da hora d+ Taj Mahal', 'http://www.tajmahal.com/images/taj_mahal_india.jpg');";
        String sqlMock6 = "INSERT INTO place VALUES(6, 'Petra', 'Jordan', " +
                "'Da hora d+ Petra', 'http://www.culturefocus.com/jordan/pictures/petra-26small.jpg');";
        String sqlMock7 = "INSERT INTO place VALUES(7, 'Colosseum', 'Italy', " +
                "'Da hora d+ Colosseum', 'http://www.altiusdirectory.com/Arts/images/Colosseum.jpg');";

        //sqLiteDatabase.execSQL(sqlMock1);
        //sqLiteDatabase.execSQL(sqlMock2);
        //sqLiteDatabase.execSQL(sqlMock3);
        //sqLiteDatabase.execSQL(sqlMock4);
        //sqLiteDatabase.execSQL(sqlMock5);
        //sqLiteDatabase.execSQL(sqlMock6);
        //sqLiteDatabase.execSQL(sqlMock7);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {
    }
}
