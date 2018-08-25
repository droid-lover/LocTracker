package com.jarvis.loctracker.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.jarvis.loctracker.R;
import com.jarvis.loctracker.adapter.AllUsersAdapter;
import com.jarvis.veronica.user.entity.User;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AllUsersActivity extends AppCompatActivity {

    private List<User> usersList = new ArrayList<>();

    /*-- views --*/
    @BindView(R.id.all_users_toolbar)
    Toolbar mToolbar;
    @BindView(R.id.rv_all_users)
    RecyclerView rvAllUsers;
    @BindView(R.id.tv_no_users)
    TextView tvNoUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_users);
        ButterKnife.bind(this);

        initialization();
    }

    private void initialization() {
        setSupportActionBar(mToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        Intent intent = getIntent();
        if (intent.getSerializableExtra("users") != null)
            usersList = (List<User>) intent.getSerializableExtra("users");

        drawUsersLayout(usersList);
    }

    private void drawUsersLayout(List<User> usersList) {
        if (usersList.size() > 0) {
            rvAllUsers.setVisibility(View.VISIBLE);
            tvNoUsers.setVisibility(View.GONE);
            rvAllUsers.setLayoutManager(new LinearLayoutManager(this));
            AllUsersAdapter allUsersAdapter = new AllUsersAdapter(this, usersList);
            rvAllUsers.setAdapter(allUsersAdapter);
        }else{
            rvAllUsers.setVisibility(View.GONE);
            tvNoUsers.setVisibility(View.VISIBLE);
        }
    }

    /**
     * Handling Back Press Event
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }

}
