package com.example.dario.xmlreader.ui;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dario.xmlreader.CurrencyDB;
import com.example.dario.xmlreader.R;

import java.util.ArrayList;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.MyViewHolder>{

    private Context context;
    private ArrayList<String> selectedElements = new ArrayList<>();
    private LayoutInflater inflater;
    private ArrayList<String> elements;
    private boolean multiSelect = false;


    public boolean isMultiSelect() {
        return multiSelect;
    }

    public RecycleViewAdapter(Context context, ArrayList<String> elements) {
        this.elements=elements;
        this.context = context;
        inflater=LayoutInflater.from(context);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v =  inflater.inflate(R.layout.row, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.update(elements.get(position));
    }

    @Override
    public int getItemCount() {
        return elements.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView row;
        String string;

        public MyViewHolder(View itemView) {
            super(itemView);
            row = itemView.findViewById(R.id.rowNameId);
        }

        void update(final String value) {
            row.setText(value);
            string=value;

            itemView.setOnClickListener(v-> selectItem(value) );
            if(selectedElements.contains(value))row.setBackgroundColor(Color.LTGRAY);
            else row.setBackground(context.getDrawable(R.drawable.back));
        }
        void selectItem(String item) {
            if (multiSelect) {
                if (selectedElements.contains(item)) {
                    selectedElements.remove(item);
                    row.setBackground(context.getDrawable(R.drawable.back));
                } else {
                    selectedElements.add(item);
                    row.setBackgroundColor(Color.LTGRAY);
                }
            }
            if(selectedElements.contains(string))row.setBackgroundColor(Color.LTGRAY);
            else row.setBackground(context.getDrawable(R.drawable.back));
        }
    }

    public void setMultiSelect(boolean multiSelect) {
        this.multiSelect = multiSelect;
    }

    public ArrayList<String> getSelectedElements() {
        return selectedElements;
    }

    public ArrayList<String> getElements() {
        return elements;
    }
}
