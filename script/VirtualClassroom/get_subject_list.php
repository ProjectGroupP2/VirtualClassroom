<?php
 $response = array();
 require_once __DIR__ . '/db_connect.php';
 $db = new DB_CONNECT();
 if (isset($_GET["standard"])) {
  $standard = $_GET['standard'];
 	$result = mysql_query("SELECT *FROM subjectdetails WHERE standard = '$standard'")or die(mysql_error());
    if (mysql_num_rows($result) > 0) {

    $response["subject"] = array();
 
    while ($row = mysql_fetch_array($result)) {

        $subject = array();
        $subject["subject"] = $row["subject"];
        array_push($response["subject"], $subject);
    }
    $response["success"] = 1;
    echo json_encode($response);
} 
		else {
           
            $response["success"] = 0;
            $response["message"] = "No Subject found";
 
            echo json_encode($response);
        }
    } 
	
	else {
        
      $response["success"] = 0;
      $response["message"] = "Required Field Missing";
 
       
     echo json_encode($response);
   }
 
?>