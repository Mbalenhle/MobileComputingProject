package com.example.simplefragments;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.AdapterView;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import okhttp3.HttpUrl;

public class CreateGame extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    String localhost = "";
    ArrayList<Teams> teams;
    ArrayList<Players> players;

    Spinner spinTeamA, spinTeamB ;
    Spinner spinTeamAPlayer1, spinTeamAPlayer2, spinTeamAPlayer3, spinTeamAPlayer4, spinTeamAPlayer5;
    Spinner spinTeamBPlayer1, spinTeamBPlayer2, spinTeamBPlayer3, spinTeamBPlayer4, spinTeamBPlayer5;

    EditText editTeamAScore, editTeamBScore;

    Button createGameBtn;

    Games createGame = new Games();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_create_game);


        editTeamAScore = findViewById(R.id.editTeamAScore);
        editTeamBScore = findViewById(R.id.editTeamBScore);
        createGameBtn = findViewById(R.id.buttonCreateGame);

        createTeamsSpinners();

        createPlayersASpinners();
        createPlayersBSpinners();
        //createGameBtn.setVisibility(View.INVISIBLE);
        createGameBtn.setEnabled(false);
        createGameBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {// send create game to database and go back to the games
                createGame.setTeamAScore(Integer.parseInt(editTeamAScore.getText().toString()));
                createGame.setTeamBScore(Integer.parseInt(editTeamBScore.getText().toString()));
                String team_a_score =  createGame.getTeamAScore()+"";
                String team_b_score =  createGame.getTeamBScore()+"";
                String team_a_name = createGame.getTeamA();
                String team_b_name = createGame.getTeamB();
                String team_a_player1 = createGame.getTeamAPlayer1()+"";
                String team_a_player2 = createGame.getTeamAPlayer2()+"";
                String team_a_player3 = createGame.getTeamAPlayer3()+"";
                String team_a_player4 = createGame.getTeamAPlayer4()+"";
                String team_a_player5 = createGame.getTeamAPlayer5()+"";


                String team_b_player1 = createGame.getTeamBPlayer1()+"";
                String team_b_player2 = createGame.getTeamBPlayer2()+"";
                String team_b_player3 = createGame.getTeamBPlayer3()+"";
                String team_b_player4 = createGame.getTeamBPlayer4()+"";
                String team_b_player5 = createGame.getTeamBPlayer5()+"";



                AsyncNetwork getTeams = new AsyncNetwork();
                HttpUrl.Builder urlBuilder = HttpUrl.parse(localhost+"createGames.php").newBuilder();
                urlBuilder.addQueryParameter("team_a_score",team_a_score.trim());
                urlBuilder.addQueryParameter("team_b_score",team_b_score.trim() );
                urlBuilder.addQueryParameter("team_a_name", team_a_name.trim() );
                urlBuilder.addQueryParameter("team_b_name", team_b_name.trim());

                urlBuilder.addQueryParameter("team_a_player1", team_a_player1.trim());
                urlBuilder.addQueryParameter("team_a_player2", team_a_player2.trim() );
                urlBuilder.addQueryParameter("team_a_player3", team_a_player3.trim() );
                urlBuilder.addQueryParameter("team_a_player4", team_a_player4.trim() );
                urlBuilder.addQueryParameter("team_a_player5",team_a_player5.trim() );

                urlBuilder.addQueryParameter("team_b_player1", team_b_player1.trim() );
                urlBuilder.addQueryParameter("team_b_player2", team_b_player2.trim() );
                urlBuilder.addQueryParameter("team_b_player3", team_b_player3.trim());
                urlBuilder.addQueryParameter("team_b_player4", team_b_player4.trim() );
                urlBuilder.addQueryParameter("team_b_player5", team_b_player5.trim());

                String url = urlBuilder.build().toString();
                getTeams.execute(url);

                while( getTeams.Result.equals("Waiting")){
                }
                try{
                    JSONObject jsonObject =  new JSONObject(getTeams.Result);

                    if(jsonObject.getString("success").equals("1")){
                        Intent i = new Intent(getApplicationContext() , MainActivity.class );
                        startActivity(i);

                    }else{
                        Toast.makeText(getApplicationContext(), jsonObject.getString("message").toString(), Toast.LENGTH_LONG).show();
                    }

                }catch (Exception e ){
                    e.printStackTrace();
                }
            }
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position,long id) {
        //Toast.makeText(getApplicationContext()," "+players.get(position).getPlayerFirstName(), Toast.LENGTH_LONG).show();

        createGame.setTeamA(spinTeamA.getSelectedItem().toString());
        createGame.setTeamB(spinTeamB.getSelectedItem().toString());
        createGame.setTeamAPlayer1(players.get((int)spinTeamAPlayer1.getSelectedItemId()).getPlayerID());
        createGame.setTeamAPlayer2(players.get((int)spinTeamAPlayer2.getSelectedItemId()).getPlayerID());
        createGame.setTeamAPlayer3(players.get((int)spinTeamAPlayer3.getSelectedItemId()).getPlayerID());
        createGame.setTeamAPlayer4(players.get((int)spinTeamAPlayer4.getSelectedItemId()).getPlayerID());
        createGame.setTeamAPlayer5(players.get((int)spinTeamAPlayer5.getSelectedItemId()).getPlayerID());

        createGame.setTeamBPlayer1(players.get((int)spinTeamBPlayer1.getSelectedItemId()).getPlayerID());
        createGame.setTeamBPlayer2(players.get((int)spinTeamBPlayer2.getSelectedItemId()).getPlayerID());
        createGame.setTeamBPlayer3(players.get((int)spinTeamBPlayer3.getSelectedItemId()).getPlayerID());
        createGame.setTeamBPlayer4(players.get((int)spinTeamBPlayer4.getSelectedItemId()).getPlayerID());
        createGame.setTeamBPlayer5(players.get((int)spinTeamBPlayer5.getSelectedItemId()).getPlayerID());

        if(createGameValid()){
           // createGameBtn.setVisibility(View.VISIBLE);
            createGameBtn.setEnabled(true);
        }else{
            //createGameBtn.setVisibility(View.INVISIBLE);
            createGameBtn.setEnabled(false);
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }

    public void fatchDataFromDB(){

    }

    public void createTeamsSpinners(){

        //dummy array for teams to test the listview
        teams = new ArrayList<>();
        localhost = getString(R.string.ipaddress);
        //request data from database
        AsyncNetwork getTeams = new AsyncNetwork();
        HttpUrl.Builder urlBuilder = HttpUrl.parse(localhost+"getTeams.php").newBuilder();
        String url = urlBuilder.build().toString();
        getTeams.execute(url);

        while( getTeams.Result.equals("Waiting")){
        }
        try{
            JSONObject jsonObject =  new JSONObject(getTeams.Result);

            if(jsonObject.getString("success").equals("1")){
                JSONArray myteams = new JSONArray(jsonObject.getJSONArray("teams").toString());
                for(int i = 0; i < myteams.length(); i++){
                    JSONObject currentTeam = myteams.getJSONObject(i);
                    Teams firstTeam = new Teams();
                    String team_name = currentTeam.getString("team_name");
                    firstTeam.setTeamName(team_name);
                    int gamesPlayed = currentTeam.getInt("games_played");
                    firstTeam.setTotalGamesPlayed(gamesPlayed);
                    int gamesWon = currentTeam.getInt("games_won");
                    firstTeam.setGamesWonByTeam(gamesWon);
                    teams.add(firstTeam);

                }
            }else{
                Toast.makeText(getApplicationContext(), "error occured", Toast.LENGTH_SHORT).show();
            }

        }catch (Exception e ){
            e.printStackTrace();
        }
        String[] teamNames = new String[teams.size()];

        for(int i = 0; i < teams.size(); i++ ){
            String fullName = teams.get(i).getTeamName();
            teamNames[i]= fullName;
        }

        // lets declare the spinners and set listeners
        spinTeamA = (Spinner) findViewById(R.id.spinnerTeamA);
        spinTeamA.setOnItemSelectedListener(this);

        spinTeamB = (Spinner) findViewById(R.id.spinnerTeamB);
        spinTeamB.setOnItemSelectedListener(this);

        //lets put the teams in adapters
        ArrayAdapter teamA = new ArrayAdapter(this,android.R.layout.simple_spinner_item, teamNames);
        teamA.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter teamB = new ArrayAdapter(this,android.R.layout.simple_spinner_item, teamNames);
        teamB.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //let the adapter show the teams now
        spinTeamA.setAdapter(teamA);
        spinTeamB.setAdapter(teamB);

    }
    public void createPlayersASpinners(){

        String localhost = getString(R.string.ipaddress);

        players = new ArrayList<>();

        //request data from database
        AsyncNetwork getPlayers = new AsyncNetwork();
        HttpUrl.Builder urlBuilder = HttpUrl.parse(localhost+"getPlayers.php").newBuilder();
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
                Toast.makeText(getApplicationContext(), "error occured", Toast.LENGTH_SHORT).show();
            }

        }catch (Exception e ){
            e.printStackTrace();
        }
        String[] playerNames = new String[players.size()];

        for(int i = 0; i < players.size(); i++ ){
            String fullName = players.get(i).getPlayerFirstName() + " " + players.get(i).getPlayerLastName();
            playerNames[i]= fullName;
        }

        //lets get the spinner for team a players

        spinTeamAPlayer1 = (Spinner) findViewById(R.id.spinnerTeamAPlayer1);
        spinTeamAPlayer1.setOnItemSelectedListener(this);

        spinTeamAPlayer2 = (Spinner) findViewById(R.id.spinnerTeamAPlayer2);
        spinTeamAPlayer2.setOnItemSelectedListener(this);

        spinTeamAPlayer3 = (Spinner) findViewById(R.id.spinnerTeamAPlayer3);
        spinTeamAPlayer3.setOnItemSelectedListener(this);

        spinTeamAPlayer4 = (Spinner) findViewById(R.id.spinnerTeamAPlayer4);
        spinTeamAPlayer4.setOnItemSelectedListener(this);

        spinTeamAPlayer5 = (Spinner) findViewById(R.id.spinnerTeamAPlayer5);
        spinTeamAPlayer5.setOnItemSelectedListener(this);

        //ap1 = team A player 1
        ArrayAdapter ap1 = new ArrayAdapter(this,android.R.layout.simple_spinner_item, playerNames);
        ap1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter ap2 = new ArrayAdapter(this,android.R.layout.simple_spinner_item, playerNames);
        ap2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter ap3 = new ArrayAdapter(this,android.R.layout.simple_spinner_item, playerNames);
        ap3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter ap4 = new ArrayAdapter(this,android.R.layout.simple_spinner_item, playerNames);
        ap4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter ap5 = new ArrayAdapter(this,android.R.layout.simple_spinner_item, playerNames);
        ap5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinTeamAPlayer1.setAdapter(ap1);
        spinTeamAPlayer2.setAdapter(ap2);
        spinTeamAPlayer3.setAdapter(ap3);
        spinTeamAPlayer4.setAdapter(ap4);
        spinTeamAPlayer5.setAdapter(ap5);

    }
    public void createPlayersBSpinners(){

        String localhost = getString(R.string.ipaddress);

        players = new ArrayList<>();

        //request data from database
        AsyncNetwork getPlayers = new AsyncNetwork();
        HttpUrl.Builder urlBuilder = HttpUrl.parse(localhost+"getPlayers.php").newBuilder();
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
                Toast.makeText(getApplicationContext(), "error occured", Toast.LENGTH_SHORT).show();
            }

        }catch (Exception e ){
            e.printStackTrace();
        }

        String[] playerNames = new String[players.size()];

        for(int i = 0; i < players.size(); i++ ){
            String fullName = players.get(i).getPlayerFirstName() + " " + players.get(i).getPlayerLastName();
            playerNames[i]= fullName;
        }

        //lets get the spinner for team b players

        spinTeamBPlayer1 = (Spinner) findViewById(R.id.spinnerTeamBPlayer1);
        spinTeamBPlayer1.setOnItemSelectedListener(this);

        spinTeamBPlayer2 = (Spinner) findViewById(R.id.spinnerTeamBPlayer2);
        spinTeamBPlayer2.setOnItemSelectedListener(this);

        spinTeamBPlayer3 = (Spinner) findViewById(R.id.spinnerTeamBPlayer3);
        spinTeamBPlayer3.setOnItemSelectedListener(this);

        spinTeamBPlayer4 = (Spinner) findViewById(R.id.spinnerTeamBPlayer4);
        spinTeamBPlayer4.setOnItemSelectedListener(this);

        spinTeamBPlayer5 = (Spinner) findViewById(R.id.spinnerTeamBPlayer5);
        spinTeamBPlayer5.setOnItemSelectedListener(this);

        ArrayAdapter bp1 = new ArrayAdapter(this,android.R.layout.simple_spinner_item, playerNames);
        bp1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        ArrayAdapter bp2 = new ArrayAdapter(this,android.R.layout.simple_spinner_item, playerNames);
        bp2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter bp3 = new ArrayAdapter(this,android.R.layout.simple_spinner_item, playerNames);
        bp3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter bp4 = new ArrayAdapter(this,android.R.layout.simple_spinner_item, playerNames);
        bp4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter bp5 = new ArrayAdapter(this,android.R.layout.simple_spinner_item, playerNames);
        bp5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinTeamBPlayer1.setAdapter(bp1);
        spinTeamBPlayer2.setAdapter(bp2);
        spinTeamBPlayer3.setAdapter(bp3);
        spinTeamBPlayer4.setAdapter(bp4);
        spinTeamBPlayer5.setAdapter(bp5);

    }

    public boolean createGameValid(){
        if(checkValidEditTexts() && checkValidTeams() && checkValidPlayers()){
            return true;
        }
        return false;
    }
    public boolean checkValidEditTexts(){

        if(editTeamAScore.getText().equals("")||editTeamBScore.getText().equals(""))
            return false;
        if(editTeamAScore.getText().toString().equals(editTeamBScore.getText().toString()))
            return false;
        return true;
    }

    public boolean checkValidTeams(){
        if(spinTeamA.getSelectedItem().toString().equals(spinTeamB.getSelectedItem().toString()))
            return false;
        return true;
    }
    public boolean checkValidPlayers(){ // check if any players repeat

        int[] playersA = {createGame.getTeamAPlayer1(), createGame.getTeamAPlayer2(),
                createGame.getTeamAPlayer3(),createGame.getTeamAPlayer4(),createGame.getTeamAPlayer5()
        };
        int[] playersB = {createGame.getTeamBPlayer1(), createGame.getTeamBPlayer2(),
                createGame.getTeamBPlayer3(),createGame.getTeamBPlayer4(),createGame.getTeamBPlayer5()
        };

        for (int i = 0; i < playersA.length; i++) { // if same team has the same player
            for (int j = i + 1 ; j < playersA.length; j++) {
                if (playersA[i] == playersA[j] || playersB[i] == playersB[j]) {

                    return false;
                }
            }
        }
        for (int i = 0; i < playersA.length; i++) { // if team a has same player as team b
            for (int j = 0 ; j < playersA.length; j++) {
                if (playersA[i] == playersB[j] ) {
                    return false;
                }
            }
        }
        return true;
    }
}
