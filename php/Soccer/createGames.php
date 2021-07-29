<?php
require 'db_connection.php';


/*
update the teams,
update the players,
insert into the games.
*/  

$teamA = $_REQUEST["team_a_name"]; // this is a primary key and a foreign key to other table
$teamAScore= $_REQUEST["team_a_score"];
$teamB = $_REQUEST["team_b_name"];
$teamBScore = $_REQUEST["team_b_score"];

$teamAPlayer1= $_REQUEST["team_a_player1"];
$teamAPlayer2= $_REQUEST["team_a_player2"];
$teamAPlayer3= $_REQUEST["team_a_player3"];
$teamAPlayer4= $_REQUEST["team_a_player4"];
$teamAPlayer5= $_REQUEST["team_a_player5"];

$teamBPlayer1= $_REQUEST["team_b_player1"];
$teamBPlayer2= $_REQUEST["team_b_player2"];
$teamBPlayer3= $_REQUEST["team_b_player3"];
$teamBPlayer4= $_REQUEST["team_b_player4"];
$teamBPlayer5= $_REQUEST["team_b_player5"];

//update the teams first;
$success = "0";
$updateTeamA = "SELECT * FROM TEAMS WHERE team_name = '$teamA'";
if ($result = mysqli_query($db_conn, $updateTeamA)) {
  while ($row=$result->fetch_assoc()){
    if(  $teamAScore > $teamBScore ){
      $gamesWon = $row["games_won"] + 1;
    }
    else{
      $gamesWon = $row["games_won"];
    }
    $gamesPlayed = $row["games_played"]+1;

    $updateTeamAStats = mysqli_query($db_conn, "UPDATE TEAMS SET games_won = '$gamesWon', games_played = '$gamesPlayed' WHERE team_name = '$teamA' ");

      echo $db_conn->error;
      if(mysqli_affected_rows($db_conn) == 1 ){
        $success = "0";
      }else{
        //echo json_encode(["message"=>"update failed"]);
      }

  }
}

$updateTeamB = "SELECT * FROM TEAMS WHERE team_name = '$teamB'";
if ($result = mysqli_query($db_conn, $updateTeamB)) {
  while ($row=$result->fetch_assoc()){
    if(  $teamBScore > $teamAScore ){
      $gamesWon = $row["games_won"] + 1;
    }
    else{
      $gamesWon = $row["games_won"];
    }
    $gamesPlayed = $row["games_played"]+1;

    $updateTeamBStats = mysqli_query($db_conn, "UPDATE TEAMS SET games_won = '$gamesWon', games_played = '$gamesPlayed' WHERE team_name = '$teamB' ");

      if(mysqli_affected_rows($db_conn) == 1 ){
        $success = "0";
      }else{
        //echo json_encode(["message"=>"update failed"]);
      }

  }
}


// update team_a_players with Loop

$arrayTeamA = array($teamAPlayer1,$teamAPlayer2,$teamAPlayer3,$teamAPlayer4,$teamAPlayer5 );
$arrayTeamB = array($teamBPlayer1,$teamBPlayer2,$teamBPlayer3,$teamBPlayer4,$teamBPlayer5 );

for ($i = 0; $i < count($arrayTeamA); $i++){

  $myPlayer = $arrayTeamA[$i];

  $updateTeamAMember1 = "SELECT * FROM PLAYERS WHERE player_id = '$myPlayer'";
  if ($result = mysqli_query($db_conn, $updateTeamAMember1)) {
    while ($row=$result->fetch_assoc()){
      if(  $teamAScore > $teamBScore ){
        $gamesWon = $row["player_games_won"] + 1;
        $gamesLost = $row["player_games_lost"];

      }
      else{
        $gamesWon = $row["player_games_won"];
        $gamesLost = $row["player_games_lost"] + 1;

      }
      $gamesPlayed = $row["player_games_played"]+1;
      $gamesPlayedFor = $row["player_goals_for"]+$teamAScore;
      $gamesPlayedAgainst = $row["player_goals_against"]+$teamBScore;

      $updatePlayerAStats = mysqli_query($db_conn, "UPDATE PLAYERS
        SET player_games_played = '$gamesPlayed',
         player_games_won = '$gamesWon',
         player_games_lost ='$gamesLost',
         player_goals_for = '$gamesPlayedFor',
         player_goals_against = '$gamesPlayedAgainst'

          WHERE player_id = '$myPlayer' ");

        if(mysqli_affected_rows($db_conn) == 1 ){
          $success = "0";
        }else{
          //echo json_encode(["message"=>"update failed"]);
        }

    }
  }

}

//update states for team b players

for ($i = 0; $i < count($arrayTeamB); $i++){

  $myPlayer = $arrayTeamB[$i];

  $updateTeamBMember1 = "SELECT * FROM PLAYERS WHERE player_id = '$myPlayer'";
  if ($result = mysqli_query($db_conn, $updateTeamBMember1)) {
    while ($row=$result->fetch_assoc()){
      if(  $teamAScore < $teamBScore ){
        $gamesWon = $row["player_games_won"] + 1;
        $gamesLost = $row["player_games_lost"];

      }
      else{
        $gamesWon = $row["player_games_won"];
        $gamesLost = $row["player_games_lost"] + 1;

      }
      $gamesPlayed = $row["player_games_played"]+1;
      $gamesPlayedFor = $row["player_goals_for"]+$teamBScore;
      $gamesPlayedAgainst = $row["player_goals_against"]+$teamAScore;

      $updatePlayerAStats = mysqli_query($db_conn, "UPDATE PLAYERS
        SET player_games_played = '$gamesPlayed',
         player_games_won = '$gamesWon',
         player_games_lost ='$gamesLost',
         player_goals_for = '$gamesPlayedFor',
         player_goals_against = '$gamesPlayedAgainst'

          WHERE player_id = '$myPlayer' ");

        if(mysqli_affected_rows($db_conn) == 1 ){
          $success = "0";
        }else{
          //echo json_encode(["message"=>"update failed"]);
        }

    }
  }

}



$createGame = "INSERT INTO GAMES
 ( team_a_name, team_a_score, team_b_name, team_b_score,
   team_a_player1, team_a_player2, team_a_player3,  team_a_player4, team_a_player5,
   team_b_player1, team_b_player2, team_b_player3, team_b_player4, team_b_player5
 )
    VALUES ('$teamA', '$teamAScore', '$teamB', '$teamBScore', '$teamAPlayer1', '$teamAPlayer2','$teamAPlayer3', '$teamAPlayer4', '$teamAPlayer5',
      '$teamBPlayer1','$teamBPlayer2', '$teamBPlayer3', '$teamBPlayer4', '$teamBPlayer5'
    ) ";

    if ( mysqli_query($db_conn, $createGame) ) {
      echo json_encode(["success"=>1,"message"=>"game created"]);
    }
    else {
      echo json_encode(["success"=>0,"message"=>"failed to create game, try again"]);
    }

?>
