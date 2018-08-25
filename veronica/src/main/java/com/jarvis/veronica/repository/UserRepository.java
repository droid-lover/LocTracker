package com.jarvis.veronica.repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import android.util.Log;

import com.jarvis.veronica.db.UserDB;
import com.jarvis.veronica.user.dao.UserDao;
import com.jarvis.veronica.user.entity.User;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class UserRepository {
    public UserDao mUserDao;
    private LiveData<List<User>> mUserList;
    private UserDB db;

    public UserRepository(Application application) {
        db = UserDB.getUserDatabase(application);
        mUserDao = db.getUserDao();
        mUserList = mUserDao.usersList();
    }

    public LiveData<List<User>> getAllUsers() {
        return mUserList;
    }

    public void insert(final User userObj) {
        /*-- inserting a user object --*/
        getUserObservable(userObj).subscribeOn(Schedulers.io()).
                subscribeWith(new DisposableObserver<User>() {
                    @Override
                    public void onNext(User user) {
                        Log.d("TAG", "in onNext" + user.name + "==" + user.password);
                        mUserDao.insertUser(user);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }


    private Observable<User> getUserObservable(final User userObj) {
        return Observable.create(new ObservableOnSubscribe<User>() {
            @Override
            public void subscribe(ObservableEmitter<User> emitter) throws Exception {
                emitter.onNext(userObj);
            }
        });
    }

    public void deleteAllUser() {
        new deleteDBDataAsync(db).execute();

    }

    private static class deleteDBDataAsync extends AsyncTask<Void, Void, Void> {

        private final UserDao mDao;

        deleteDBDataAsync(UserDB db) {
            mDao = db.getUserDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            mDao.deleteAll();
            return null;
        }
    }
}