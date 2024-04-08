<?php

session_start();

// Check if form is submitted
if ($_SERVER["REQUEST_METHOD"] == "POST") {

    $email = $_POST["email"];
    $password = $_POST["password"];

	  $servername = "localhost";
    $db_username = "root";
    $db_password = "";
    //change DB name 
    $dbname = "mhmsdb"; 
	
	
// Create connection
$conn = new mysqli($servername, $db_username, $db_password, $dbname);

// Check connection
if ($conn->connect_error) {
  die("Connection failed: " . $conn->connect_error);
}

	  
	 $sql = "SELECT * from tbladmin where email = '$email' AND password = '$password'";  
	 


// Execute the query
$result = $conn->query($sql);
$message = 'Login Successfully';
// Check if a user with the given credentials exists
if ($result->num_rows == 1) {

    // User is authenticated, set session variable to indicate login
    $_SESSION["logged_in"] = true;
    $userInfo = $result->fetch_assoc();
    $_SESSION["id"] = $userInfo["id"];
    $_SESSION["Name"] = $userInfo["name"];
    $_SESSION["email"] = $userInfo["email"];
    // Redirect to a protected page (e.g., home.html)
    echo "<script type='text/javascript'>alert('$message');window.location.href='dashboard.php';</script>";

    exit();
} else {
    // Invalid credentials, show an error message
    echo "Invalid username or password.";
}

// Close the database connection
$conn->close();
}
?>