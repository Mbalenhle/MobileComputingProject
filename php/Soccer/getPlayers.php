<?php

require 'db_connection.php';

$allPlayers = mysqli_query($db_conn,"SELECT player_id, player_first_name, player_last_name,
  player_games_played, player_games_won, player_games_lost,
  player_goals_for,player_goals_against FROM PLAYERS");
  
if(mysqli_num_rows($allPlayers) > 0){
    $all_players = mysqli_fetch_all($allPlayers,MYSQLI_ASSOC);
    echo json_encode(["success"=>1,"player_stats"=>$all_players]);
}
else{
    echo json_encode(["success"=>0]);
}
