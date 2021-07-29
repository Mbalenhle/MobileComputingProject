<?php

require 'db_connection.php';

$allTeams = mysqli_query($db_conn,"SELECT * FROM TEAMS");
if(mysqli_num_rows($allTeams) > 0){
    $all_teams = mysqli_fetch_all($allTeams,MYSQLI_ASSOC);
    echo json_encode(["success"=>1,"teams"=>$all_teams]);
}
else{
    echo json_encode(["success"=>0]);
}
