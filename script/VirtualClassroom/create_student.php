<?php
 $response = array();
 if (isset($_POST['firstName']) && isset($_POST['middleName']) && isset($_POST['lastName']) && isset($_POST['standard'])
 && isset($_POST['branch']) && isset($_POST['rollNumber']) && isset($_POST['password']) && isset($_POST['confirmPassword']) && isset($_POST['securityQuestion'])&& isset($_POST['securityQuestionAnswer'])  
  && isset($_POST['status']))
 {
     $firstName = $_POST['firstName'];
	 $middleName = $_POST['middleName'];
	 $lastName = $_POST['lastName']; 
	 $standard = $_POST['standard']; 
	 $branch = $_POST['branch']; 
	 $rollNumber = $_POST['rollNumber']; 
	 $password = $_POST['password']; 
	 $confirmPassword = $_POST['confirmPassword']; 
	 $securityQuestion = $_POST['securityQuestion'];
	 $securityQuestionAnswer = $_POST['securityQuestionAnswer'];
	 $username = $_POST['username'];
	 $status = $_POST['status'];
	 
	 
     
    require_once __DIR__ . '/db_connect.php';
     $db = new DB_CONNECT();
    
    $result = mysql_query("INSERT INTO studentdetails(firstName, middleName, lastName,standard,branch,rollNumber, password, confirmPassword,securityQuestion,securityQuestionAnswer,username,status)
	VALUES('$firstName', '$middleName', '$lastName','$standard','$branch', '$rollNumber', '$password','$confirmPassword','$securityQuestion','$securityQuestionAnswer','$username','$status')");
    
    if ($result) {
        $response["success"] = 1;
		$response["message"] = "Student successfully created.";
      
        echo json_encode($response);
    } else {
        $response["success"] = 0;
        $response["message"] = "Oops! An error occurred.";
        echo json_encode($response);
    }
}
 else 
 {   
    $response["success"] = 0;
    $response["message"] = "Required field(s) is missing";
    echo json_encode($response);
}
?>