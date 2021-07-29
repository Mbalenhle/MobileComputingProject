<?php
require 'db_connection.php';


    if(isset($_REQUEST['email']) && isset($_REQUEST['password']) && isset($_REQUEST['first_name']) && isset($_REQUEST['last_name'])){

      $email = $_REQUEST['email'];
      $password = md5($_REQUEST['password']);
      $firstname = $_REQUEST['first_name'];
      $lastname = $_REQUEST['last_name'];

      $checkUser = mysqli_query($db_conn,"SELECT player_id, email_address FROM PLAYERS WHERE email_address = '$email' and password = '$password'");
      if(mysqli_num_rows($checkUser) > 0){
          echo json_encode(["success"=>0,"message"=>"user already exists"]);
      }
      else{

        $sql = "INSERT INTO PLAYERS (player_first_name, player_last_name,
          player_games_played, player_games_won, player_games_lost,
          player_goals_for, player_goals_against,
          email_address, password
        )
          VALUES ('$firstname', '$lastname', 0, 0, 0, 0,0, '$email', '$password')";

        if ( mysqli_query($db_conn, $sql) ) {
          echo json_encode(["success"=>1,"message"=>"player successfully registered"]);
        }
        else {
          echo json_encode(["success"=>0,"message"=>"failed to register, try again"]);
        }
      }

    }
    else{
      echo json_encode(["success"=>0,"message"=>"fill all fiels"]);
    }

?>
