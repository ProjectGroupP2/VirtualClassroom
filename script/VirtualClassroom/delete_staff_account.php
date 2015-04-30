<?php
 $response = array();
 
if (isset($_POST['username']) ) {
    $username = $_POST['username'];
	
 
   
    require_once __DIR__ . '/db_connect.php';
 
    $db = new DB_CONNECT();
 
    $result = mysql_query("DELETE FROM staffdetails WHERE username = '$username' ");
 
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