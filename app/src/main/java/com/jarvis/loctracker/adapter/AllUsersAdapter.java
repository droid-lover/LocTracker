package com.jarvis.loctracker.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jarvis.loctracker.R;
import com.jarvis.veronica.user.entity.User;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AllUsersAdapter extends RecyclerView.Adapter<AllUsersAdapter.ViewHolder> {
    private Context context;
    private List<User> usersList;

    public AllUsersAdapter(Context context, List<User> usersList) {
        this.usersList = usersList;
        this.context = context;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        User user = usersList.get(position);
        holder.tvUserName.setText(user.name != null ? user.name : "");
//        holder.tvUserRecentLocation.setText(productBenefitObj.getDesc() != null ? productBenefitObj.getDesc() : "");

    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }


    @Override
    public int getItemCount() {
        return usersList.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_user_name)
        TextView tvUserName;
        @BindView(R.id.tv_user_recent_location)
        TextView tvUserRecentLocation;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
