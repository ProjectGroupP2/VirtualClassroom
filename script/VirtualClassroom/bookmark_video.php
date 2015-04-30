<?php
$response = array();
 
// check for required fields
if (isset($_POST['username']) && isset($_POST['videoname']) && isset($_POST['position']) ) 
{
 
    $username = $_POST['username'];
    $videoname = $_POST['videoname'];
    $position = $_POST['position'];
	
    // include db connect class
    require_once __DIR__ . '/db_connect.php';
 
    // connecting to db
    $db = new DB_CONNECT();
 
    // mysql inserting a new row
    $result = mysql_query("INSERT INTO bookmarkvideo(username,videoname,position) 
	VALUES('$username', '$videoname', '$position')");
 
    // check if row inserted or not
    if ($result) {
        // successfully inserted into database
        $response["success"] = 1;
        $response["message"] = "bookmark video successfully .";
 
        // echoing JSON response
        echo json_encode($response);
    } else {
        // failed to insert row
        $response["success"] = 0;
        $response["message"] = "Oops! An error occurred.";
 
        // echoing JSON response
        echo json_encode($response);
    }
} else {
    // required field is missing
    $response["success"] = 0;
    $response["message"] = "Required field(s) is missing";
 
    // echoing JSON response
    echo json_encode($response);
}
?>