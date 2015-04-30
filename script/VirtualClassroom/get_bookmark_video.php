<?php
 $response = array();
 require_once __DIR__ . '/db_connect.php';
 $db = new DB_CONNECT();
 if (isset($_GET["username"])) {
  $username = $_GET['username'];
 	$result = mysql_query("SELECT distinct videoname from bookmarkvideo WHERE username = '$username'")or die(mysql_error());
    if (mysql_num_rows($result) > 0) {
    $response["videoName"] = array();
    while ($row = mysql_fetch_array($result)) {

        $videoName = array();
        $videoName["videoname"] = $row["videoname"];
        array_push($response["videoname"], $videoName);
    }
    $response["success"] = 1;
   echo json_encode($response);
} 
		else {
           
            $response["success"] = 0;
            $response["message"] = "No video found";
 
            echo json_encode($response);
        }
    } 
	
	else {
        
      $response["success"] = 0;
      $response["message"] = "Required Field Missing";
 
       
     echo json_encode($response);
   }
 
?>