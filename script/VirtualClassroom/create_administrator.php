<?php
$response = array();
 
// check for required fields
if (isset($_POST['username']) && isset($_POST['password']) && isset($_POST['confirmPassword']) && isset($_POST['securityQuestion']) && isset($_POST['securityQuestionAnswer']) && isset($_POST['securityKey'])) 
{
 
    $username = $_POST['username'];
    $password = $_POST['password'];
    $confirmPassword = $_POST['confirmPassword'];
	$securityQuestion = $_POST['securityQuestion'];
	$securityQuestionAnswer = $_POST['securityQuestionAnswer'];
	$securityKey = $_POST['securityKey'];

    // include db connect class
    require_once __DIR__ . '/db_connect.php';
 
    // connecting to db
    $db = new DB_CONNECT();
 
    // mysql inserting a new row
    $result = mysql_query("INSERT INTO administratordetails(username, password, confirmPassword,securityQuestion,securityQuestionAnswer,securityKey) 
	VALUES('$username', '$password', '$confirmPassword','$securityQuestion','$securityQuestionAnswer','$securityKey')");
 
    // check if row inserted or not
    if ($result) {
        // successfully inserted into database
        $response["success"] = 1;
        $response["message"] = "Administrator successfully created.";
 
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