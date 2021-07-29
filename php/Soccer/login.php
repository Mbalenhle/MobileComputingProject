<?php
require 'db_connection.php';

if(isset($_REQUEST['email']) && isset($_REQUEST['password'])){

  $email = $_REQUEST['email'];
  $password = md5($_REQUEST['password']);

  $checkLogin = mysqli_query($db_conn,"SELECT player_id, email_address FROM PLAYERS WHERE email_address = '$email' and password = '$password'");

  if(mysqli_num_rows($checkLogin) > 0){
      $all_teams = mysqli_fetch_all($checkLogin,MYSQLI_ASSOC);
      echo json_encode(["success"=>1,"user"=>$all_teams]);
  }
  else{
    echo json_encode(["success"=>0]);
  }

}
else{
  echo json_encode(["success"=>0]);
}

?>
