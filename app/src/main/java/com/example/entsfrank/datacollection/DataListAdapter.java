package com.example.entsfrank.datacollection;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;
import android.support.v7.widget.CardView;

/**
 * Created by frank on 9/23/16.
 */
public class DataListAdapter extends RecyclerView.Adapter<DataListAdapter.ViewHolder> {

    private List<DataContract> dataList;
    private Context context;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView eventTitle, eventTime;
        public CardView eventContainer;
        public ViewHolder(View itemView) {
            super(itemView);
            this.eventTitle = (TextView)itemView.findViewById(R.id.eventTitle);
            this.eventTime = (TextView)itemView.findViewById(R.id.eventTime);
            this.eventContainer = (CardView)itemView.findViewById(R.id.eventCard);
        }
    }

    public DataListAdapter(List<DataContract> dataList, Context c){
        this.dataList = dataList;
        this.context=c;
    }

    @Override
    public DataListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.single_event_view, parent, false);
        ViewHolder vH = new ViewHolder(v);
        vH.eventContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, SingleCallActivity.class);
                i.putExtra("eventId", (long)(int)view.getTag());
                context.startActivity(i);
            }
        });
        return vH;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        DataContract current = dataList.get(position);
        holder.eventTitle.setText(current.getTitle());
        holder.eventTime.setText(current.getTime());
        holder.eventContainer.setTag(current.getId());
    }


    @Override
    public int getItemCount() {
        return dataList.size();
    }

}
