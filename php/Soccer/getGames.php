<?php

require 'db_connection.php';

$allGames = mysqli_query($db_conn,"SELECT * FROM GAMES");
if(mysqli_num_rows($allGames) > 0){
    $all_games = mysqli_fetch_all($allGames,MYSQLI_ASSOC);
    echo json_encode(["success"=>1,"games"=>$all_games]);
}
else{
    echo json_encode(["success"=>0]);
}
