package com.volcaniccoder.volxfastscrollsample;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.volcaniccoder.volxfastscroll.IVolxAdapter;

import java.util.ArrayList;
import java.util.List;

public class UsersOwnAdapter extends RecyclerView.Adapter<UsersOwnAdapter.ViewHolder> implements IVolxAdapter {

    private List<UserModel> mDataset;

    @Override
    public List<Object> getList() {
        return new ArrayList<Object>(mDataset);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView txt;
        public ViewHolder(View v) {
            super(v);
            txt = (TextView) v.findViewById(R.id.txt);
        }
    }

    public UsersOwnAdapter(List<UserModel> myDataset) {
        mDataset = myDataset;
    }

    @Override
    public UsersOwnAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                         int viewType) {

        View v =  LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_main, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.txt.setText(mDataset.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
