<?php

$response = array();

require_once __DIR__ . '/db_connect.php';

$db = new DB_CONNECT();

$result = mysql_query("SELECT *FROM staffdetails where status='Disabled'") or die(mysql_error());

if (mysql_num_rows($result) > 0) {

    $response["product"] = array();
 
    while ($row = mysql_fetch_array($result)) {

        $product = array();
        $product["username"] = $row["username"];
        array_push($response["product"], $product);
    }
    $response["success"] = 1;
    echo json_encode($response);
} else {
    $response["success"] = 0;
    $response["message"] = "No products found";
    echo json_encode($response);
}

?>