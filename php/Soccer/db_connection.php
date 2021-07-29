<?php
$localhost = "127.0.0.1";
$userName = "s1439706";
$userPass = "s1439706"; 
$dbName = "d1439706";

$db_conn = mysqli_connect($localhost, $userName , $userPass , $dbName) or die("failed to connect ".mysqli_connect_error());
