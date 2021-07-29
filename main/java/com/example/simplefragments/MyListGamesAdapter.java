package com.example.simplefragments;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class MyListGamesAdapter extends BaseAdapter {

    private final Context context;
    ArrayList<Games> myGames;

    public MyListGamesAdapter(Context context, ArrayList<Games> myGames) {
        // TODO Auto-generated constructor stub
        this.context=context;
        this.myGames = myGames;

    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater= (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View rowView=inflater.inflate(R.layout.games_listview, null,true);// CHECK THIS ATTACH TO ROOT

        TextView teamA =rowView.findViewById(R.id.textTeamA);
        TextView teamB =rowView.findViewById(R.id.textTeamB);
        TextView score = rowView.findViewById(R.id.textScore);

        teamA.setText(myGames.get(position).getTeamA());
        teamB.setText(myGames.get(position).getTeamB());
        score.setText(myGames.get(position).getGameScore());

        return rowView;

    }

    @Override
    public long getItemId(int position){
        return position;
    }

    @Override
    public Object getItem(int position){
        return this.myGames.get(position);
    }

    @Override
    public int getCount(){
        return this.myGames.size();
    }
}