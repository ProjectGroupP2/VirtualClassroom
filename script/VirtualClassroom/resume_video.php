<?php
 $response = array();
 require_once __DIR__ . '/db_connect.php';
 $db = new DB_CONNECT();
 if (isset($_GET['username'] )&& isset($_GET['videoname'])) {
  $username = $_GET['username'];
  $videoname = $_GET['videoname'];
 	$result = mysql_query("SELECT position FROM bookmarkvideo WHERE username = '$username' AND videoname = '$videoname'")or die(mysql_error());
    if (mysql_num_rows($result) > 0) {

    $response["position"] = array();
	 
    
    while ($row = mysql_fetch_array($result)) {

        $position = array();
		
        $position["position"] = $row["position"];

        array_push($response["position"], $position);
		
    }
    $response["success"] = 1;
    echo json_encode($response);
} 
		else {
           
            $response["success"] = 0;
            $response["message"] = "No Video found";
 
            echo json_encode($response);
        }
    } 
	
	else {
        
      $response["success"] = 0;
      $response["message"] = "Required Field Missing";
 
       
     echo json_encode($response);
   }
 
?>