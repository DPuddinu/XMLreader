package com.example.dario.xmlreader.ui;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dario.xmlreader.R;

import java.util.ArrayList;


public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.MyViewHolder>{

    private Context context;
    private ArrayList<String> elements;
    private LayoutInflater inflater;


    public RecycleViewAdapter(Context context, ArrayList<String> elements) {
        this.context = context;
        this.elements = elements;
        this.inflater = (LayoutInflater.from(context));

        notifyItemRangeChanged(0,elements.size());
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.row, parent, false);

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.row.setText(elements.get(position));
    }

    public void removeAt(int position) {
        elements.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(0, elements.size());
    }


    @Override
    public int getItemCount() {
        return elements.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView row;

        public MyViewHolder(View itemView) {
            super(itemView);

            row = itemView.findViewById(R.id.rowNameId);
        }
    }
}
