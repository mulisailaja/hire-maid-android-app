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

	  
	 $sql = "SELECT * from cussignup where email = '$email' AND password = '$password'";  
	 


// Execute the query
$result = $conn->query($sql);
$message = 'Login Successfully';
// Check if a user with the given credentials exists
$row = mysqli_fetch_array($result);

    // User is authenticated, set session variable to indicate login
    if (mysqli_num_rows($result) == 1) {
        $_SESSION["id"] = $row["id"];
        $_SESSION["Name"] = $row["name"];
        $_SESSION["email"] = $row["email"];
    // Redirect to a protected page (e.g., home.html)
    
    echo "<script type='text/javascript'>alert('$message');window.location.href='index.php';</script>";

   
} else {





    echo "<script type='text/javascript'>alert('$message');window.location.href='index.php';</script>";
//	$_SESSION["firstname"] = $name;

    $_SESSION["Name"] = $row["name"];
    $_SESSION["email"] = $row["email"];
    
        } 
    }else {
    // Invalid credentials, show an error message
    echo "Invalid username or password.";
}

// Close the database connection
// $conn->close();

?>