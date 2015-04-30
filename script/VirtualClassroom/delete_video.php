<?php
 $response = array();
 
if (isset($_POST['standard']) &&isset($_POST['subject']) && isset($_POST['videoName'])) {
    $standard = $_POST['standard'];
	$subject=$_POST['subject'];
	$videoName=$_POST['videoName'];
 
   
    require_once __DIR__ . '/db_connect.php';
 
    $db = new DB_CONNECT();
 
    $result = mysql_query("DELETE FROM videodetails WHERE standard = '$standard' and subject = '$subject' and videoName = '$videoName'");
 
       if (mysql_affected_rows() > 0) {
        $response["success"] = 1;
        $response["message"] = "Product successfully deleted";
  
        echo json_encode($response);
    } else {
        
        $response["success"] = 0;
        $response["message"] = "No product found";
  
        echo json_encode($response);
    }
} else {
    
    $response["success"] = 0;
    $response["message"] = "Required field(s) is missing";
  
    echo json_encode($response);
}
?>