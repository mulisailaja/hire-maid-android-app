<?php
session_start();
$servername = "localhost";
$username = "root";
$password = "";
$dbname = "mhmsdb";

$conn = new mysqli($servername, $username, $password, $dbname);

if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}

$userid = '1'; 
$data = [ 
    'user_id' =>  $userid,
    'payment_id' => $_POST['razorpay_payment_id'],
    'amount' => $_POST['totalAmount'],
    'product_id' => $_POST['product_id'],
];

// Define the SQL query to insert data into the orders table
$insert_sql = "INSERT INTO orders (user_id, payment_id, amount, product_id) VALUES (?, ?, ?, ?)";

// Prepare the statement for insertion
$insert_stmt = $conn->prepare($insert_sql);

// Bind the parameters and execute the insert query
$insert_stmt->bind_param("ssss", $data['user_id'], $data['payment_id'], $data['amount'], $data['product_id']);

if ($insert_stmt->execute()) {
    // Update tblmaidbooking status to 'Paid'
    $update_sql = "UPDATE tblmaidbooking SET Status = 'Paid' WHERE BookingID = ?";
    
    // Prepare the statement for update
    $update_stmt = $conn->prepare($update_sql);

    // Bind the parameter and execute the update query
    $update_stmt->bind_param("s", $data['product_id']);
    $update_stmt->execute();

    $arr = array('msg' => 'Payment successfully credited', 'status' => true);
    echo json_encode($arr);
} else {
    $arr = array('msg' => 'Error: Unable to insert data into the database', 'status' => false);
    echo json_encode($arr);
}

// Close the statements and database connection
$insert_stmt->close();
$update_stmt->close();
$conn->close();
?>
