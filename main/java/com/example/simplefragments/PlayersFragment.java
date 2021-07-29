package com.example.simplefragments;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import okhttp3.HttpUrl;

import static android.graphics.Typeface.BOLD;
import static com.example.simplefragments.R.drawable.card;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PlayersFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PlayersFragment extends Fragment {

    View view;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public PlayersFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ChatsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PlayersFragment newInstance(String param1, String param2) {
        PlayersFragment fragment = new PlayersFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {// write your code in here
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_players, container, false);

        String localhost = getString(R.string.getPlayersUrl);

        ArrayList<Players> players = new ArrayList<>();

        //request data from database
        AsyncNetwork getPlayers = new AsyncNetwork();
        HttpUrl.Builder urlBuilder = HttpUrl.parse(localhost).newBuilder();
        String url = urlBuilder.build().toString();
        getPlayers.execute(url);

        while( getPlayers.Result.equals("Waiting")){
        }
        try{
            JSONObject jsonObject =  new JSONObject(getPlayers.Result);

            if(jsonObject.getString("success").equals("1")){

                JSONArray myteams = new JSONArray(jsonObject.getJSONArray("player_stats").toString());
                for(int i = 0; i < myteams.length(); i++){
                    JSONObject currentPlayer = myteams.getJSONObject(i);
                    Players firstPlayer = new Players();
                    int playerID = currentPlayer.getInt("player_id");
                    firstPlayer.setPlayerID(playerID);
                    String player_first_name = currentPlayer.getString("player_first_name");
                    firstPlayer.setPlayerFirstName(player_first_name);

                    String player_last_name = currentPlayer.getString("player_last_name");
                    firstPlayer.setPlayerLastName(player_last_name);
                    int gamesPlayed = currentPlayer.getInt("player_games_played");
                    firstPlayer.setPlayerGamesPlayed(gamesPlayed);
                    int gamesWon = currentPlayer.getInt("player_games_won");
                    firstPlayer.setGamesWon(gamesWon);
                    int gamesLost = currentPlayer.getInt("player_games_lost");
                    firstPlayer.setGamesLost(gamesLost);
                    int goals_for = currentPlayer.getInt("player_goals_for");
                    firstPlayer.setGoalsFor(goals_for);
                    int goals_against = currentPlayer.getInt("player_goals_against");
                    firstPlayer.setGoalsAgainst(goals_against);
                    players.add(firstPlayer);

                }

            }else{
                Toast.makeText(getContext(), "error occured", Toast.LENGTH_SHORT).show();
            }

        }catch (Exception e ){
            e.printStackTrace();
        }


        TableLayout stk = (TableLayout)view.findViewById(R.id.table_main);
        TableRow tbrow0 = new TableRow(getContext());

        // Table Headers

        TextView name = new TextView(getContext());
        name.setText("Player");
        name.setTextColor(getResources().getColor(R.color.purple_700));
        name.setPadding(32, 32,0,32);
        name.setTypeface(null, BOLD);
        name.setTextSize(18);
        tbrow0.addView(name);

        TextView played = new TextView(getContext());
        played.setText(" P ");
        played.setTextColor(getResources().getColor(R.color.purple_700));
        played.setPadding(20, 32,20,32);
        played.setTypeface(null, BOLD);
        played.setTextSize(16);
        tbrow0.addView(played);

        TextView won = new TextView(getContext());
        won.setText(" W ");
        won.setTextColor(getResources().getColor(R.color.purple_700));
        won.setPadding(20, 32,20,32);
        won.setTypeface(null, BOLD);
        won.setTextSize(16);
        tbrow0.addView(won);

        TextView lost = new TextView(getContext());
        lost.setText(" L ");
        lost.setTextColor(getResources().getColor(R.color.purple_700));
        lost.setPadding(20, 32,20,32);
        lost.setTypeface(null, BOLD);
        lost.setTextSize(16);
        tbrow0.addView(lost);

        TextView gf = new TextView(getContext());
        gf.setText(" GF ");
        gf.setTextColor(getResources().getColor(R.color.purple_700));
        gf.setPadding(20, 32,20,32);
        gf.setTypeface(null, BOLD);
        gf.setTextSize(16);
        tbrow0.addView(gf);

        TextView ga = new TextView(getContext());
        ga.setText(" GA ");
        ga.setTextColor(getResources().getColor(R.color.purple_700));
        ga.setPadding(20, 32,32,32);
        ga.setTextSize(16);
        ga.setTypeface(null, BOLD);
        tbrow0.addView(ga);

        //tbrow0.setLayoutParams();
        stk.addView(tbrow0);

        for( int i = 0; i < players.size(); i++){
            TableRow tbrow = new TableRow(getContext());

            //Set up table rows display style
            tbrow.setPadding(0, 16,0,16);
            tbrow.setBackgroundResource(R.drawable.card);


            TableLayout.LayoutParams tableRowParams=
                    new TableLayout.LayoutParams
                            (TableLayout.LayoutParams.FILL_PARENT,TableLayout.LayoutParams.WRAP_CONTENT);

            tableRowParams.setMargins(0, 8,0, 8);

            tbrow.setLayoutParams(tableRowParams);

            //convert all things to strings before setText
            String playerName = players.get(i).getPlayerFirstName()+" "+players.get(i).getPlayerLastName();

            // Table Rows
            TextView pname = new TextView(getContext());
            pname.setText(playerName);
            pname.setTextColor(Color.BLACK);
            pname.setGravity(Gravity.LEFT);
            pname.setTypeface(null, BOLD);
            pname.setTextSize(18);
            pname.setPadding(32, 32,0,32);
            tbrow.addView(pname);

            String gamesPlayed = Integer.toString(players.get(i).getPlayerGamesPlayed());
            TextView pplayed = new TextView(getContext());
            pplayed.setText(gamesPlayed);
            pplayed.setTextColor(Color.GRAY);
            pplayed.setGravity(Gravity.CENTER);
            pplayed.setPadding(0, 32,0,32);
            pplayed.setTextSize(16);

            tbrow.addView(pplayed);

            String gamesWon = Integer.toString(players.get(i).getGamesWon());
            TextView pwon = new TextView(getContext());
            pwon.setText(gamesWon);
            pwon.setTextColor(Color.GRAY);
            pwon.setGravity(Gravity.CENTER);
            pwon.setPadding(0, 32,0,32);
            pwon.setTextSize(16);

            tbrow.addView(pwon);

            String gameLost = Integer.toString(players.get(i).getGamesLost());
            TextView plost = new TextView(getContext());
            plost.setText(gameLost);
            plost.setTextColor(Color.GRAY);
            plost.setGravity(Gravity.CENTER);
            plost.setTextSize(16);
            plost.setPadding(0, 32,0,32);

            tbrow.addView(plost);

            String gamesFor = Integer.toString(players.get(i).getGoalsFor());
            TextView pgf = new TextView(getContext());
            pgf.setText(gamesFor);
            pgf.setTextColor(Color.GRAY);
            pgf.setGravity(Gravity.CENTER);
            pgf.setTypeface(null, BOLD);
            pgf.setTextSize(16);
            pgf.setPadding(0, 32,0,32);

            tbrow.addView(pgf);

            String gamesAgainst = Integer.toString(players.get(i).getGoalsAgainst());
            TextView pga = new TextView(getContext());
            pga.setText(gamesAgainst);
            pga.setTextColor(Color.GRAY);
            pga.setGravity(Gravity.CENTER);
            pga.setTypeface(null, BOLD);
            pga.setTextSize(16);
            pga.setPadding(0, 32,0,32);

            tbrow.addView(pga);

            stk.addView(tbrow);

        }

        return view;
    }

}