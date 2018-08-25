package com.jarvis.veronica.db;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.jarvis.veronica.user.dao.UserDao;
import com.jarvis.veronica.user.entity.User;

@Database(entities = {User.class},version = 1,exportSchema = false)
public abstract class UserDB extends RoomDatabase {

    private static UserDB dbInstance;

    public abstract UserDao getUserDao();

    public static UserDB getUserDatabase(final Context context){
        if(dbInstance==null){
            synchronized (UserDB.class){
                if(dbInstance==null){
                    dbInstance= Room.databaseBuilder(context,UserDB.class ,"user_db" ).build();
                }
            }
        }
        return dbInstance;
    }

//    public static RoomDatabase.Callback sRoomDatabaseCallback =
//            new RoomDatabase.Callback(){
//
//                @Override
//                public void onOpen (@NonNull SupportSQLiteDatabase db){
//                    super.onOpen(db);
//                    saveData(dbInstance);
//                }
//            };
//
//    private static void saveData(UserDB dbInstance) {
//        final UserDao mDao;
//        mDao= dbInstance.getUserDao();
//    }

}
