package com.example.simplefragments;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class MyListTeamsAdapter extends BaseAdapter {

    private final Context context;
    ArrayList<Teams> myTeams;

    public MyListTeamsAdapter(Context context, ArrayList<Teams> myTeams) {
        // TODO Auto-generated constructor stub
        this.context=context;
        this.myTeams = myTeams;

    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater= (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View rowView=inflater.inflate(R.layout.teams_listview, null,true);// CHECK THIS ATTACH TO ROOT

        TextView teamName =rowView.findViewById(R.id.textTeamName);
        TextView averageWin = rowView.findViewById(R.id.textAverageWin);

        //Added by Kgotso


        teamName.setText(myTeams.get(position).getTeamName());
        averageWin.setText(myTeams.get(position).getAverageWins());

        return rowView;

    }

    @Override
    public long getItemId(int position){
        return position;
    }

    @Override
    public Object getItem(int position){
        return this.myTeams.get(position);
    }

    @Override
    public int getCount(){
        return this.myTeams.size();
    }
}