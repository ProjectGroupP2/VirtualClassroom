<?php
 
/*
 * Following code will update a product information
 * A product is identified by product id (pid)
 */
 
// array for JSON response
$response = array();
 
// check for required fields
if (isset($_POST['username'])) {
 
    $username = $_POST['username'];
    
    // include db connect class
    require_once __DIR__ . '/db_connect.php';
 
    // connecting to db
    $db = new DB_CONNECT();
 
    // mysql update row with matched pid
    $result = mysql_query("UPDATE studentdetails SET status = 'Enabled' WHERE username = '$username'");
 
    // check if row inserted or not
    if ($result) {
        // successfully updated
        $response["success"] = 1;
        $response["message"] = "Status successfully updated.";
 
        // echoing JSON response
        echo json_encode($response);
    } else {
 
    }
} else {
    // required field is missing
	$response["success"] = 0;
    $response["message"] = "Required field(s) is missing";
 
    // echoing JSON response
    echo json_encode($response);
}
?>