<?php
 
/*
 * Following code will get single product details
 * A product is identified by product id (pid)
 */
 
// array for JSON response
$response = array();
 
// include db connect class
require_once __DIR__ . '/db_connect.php';
 
// connecting to db
$db = new DB_CONNECT();
 
// check for post data
if (isset($_GET['username'])&& isset($_GET['securityQuestion']) && isset($_GET['securityQuestionAnswer'])) {
 $username = $_GET['username'];
  $securityQuestion = $_GET['securityQuestion'];
   $securityQuestionAnswer = $_GET['securityQuestionAnswer'];
 
    // get a product from products table
    $result = mysql_query("SELECT *FROM studentdetails WHERE username = '$username' AND securityQuestion='$securityQuestion' AND securityQuestionAnswer='$securityQuestionAnswer'");
 
    if (!empty($result)) {
        // check for empty result
        if (mysql_num_rows($result) > 0) {
 
            $result = mysql_fetch_array($result);
 
            $product = array();
           
            $product["password"] = $result["password"];
           
            $response["success"] = 1;
 
            // user node
            $response["product"] = array();
 
            array_push($response["product"], $product);
 
            // echoing JSON response
            echo json_encode($response);
        } else {
            // no product found
            $response["success"] = 0;
            $response["message"] = "No product found";
 
            // echo no users JSON
            echo json_encode($response);
        }
    }
	else {
        // no product found
        $response["success"] = 0;
        $response["message"] = "No product found";
 
        // echo no users JSON
        echo json_encode($response);
    }
} 

else {
    // required field is missing
   $response["success"] = 0;
   $response["message"] = "Required field(s) is missing";
 
    // echoing JSON response
    echo json_encode($response);
}
?>