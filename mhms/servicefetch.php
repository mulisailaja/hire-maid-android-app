<!DOCTYPE html>
    <html lang="en">
    <head>
         
    <title>Hire Maid </title>
        
        <link rel="manifest" href="site.webmanifest">
		<link rel="shortcut icon" type="image/x-icon" href="admin/images/image 9.png" style="margin-top: 20px;margin-left:20px;">

		<!-- CSS here -->
            <link rel="stylesheet" href="assets/css/bootstrap.min.css">
            <link rel="stylesheet" href="assets/css/owl.carousel.min.css">
            <link rel="stylesheet" href="assets/css/flaticon.css">
            <link rel="stylesheet" href="assets/css/price_rangs.css">
            <link rel="stylesheet" href="assets/css/slicknav.css">
            <link rel="stylesheet" href="assets/css/animate.min.css">
            <link rel="stylesheet" href="assets/css/magnific-popup.css">
            <link rel="stylesheet" href="assets/css/fontawesome-all.min.css">
            <link rel="stylesheet" href="assets/css/themify-icons.css">
            <link rel="stylesheet" href="assets/css/slick.css">
            <link rel="stylesheet" href="assets/css/nice-select.css">
            <link rel="stylesheet" href="assets/css/style.css">
      <style>
       @import url('https://fonts.googleapis.com/css2?family=Montserrat:wght@100;200;300;400&display=swap');

:root {
   --main-color: #E0E8ED;
   --sub-color:#c1c1c1;
   --white: #0d1014;
 
}


#availability-result {
            margin-top: 20px;
            font-weight: bold;
        }

        #datetime-picker {
            margin-top:-45px;
        }
.box-container{
   display: grid;
   grid-template-columns: repeat(auto-fit, 35rem);
   gap: 1.5rem;
   justify-content: center;
   align-items: flex-start;
}
.box{
  border-radius: 0.5rem;
    padding: 2rem;
    padding-top: 1rem;
    border: var(--border);
}
.box p{
   line-height: 1.5;
   padding-top: .5rem;
   font-size: 1.8rem;
   color: var(--sub-color);
}
.box p {
   color: var(--white);
}
/* media queries  */

@media (max-width:991px){

   html{
      font-size: 55%;
   }

   .header .flex .fa-bars{
      display: inline-block;
   }

   .header .flex .btn{
      display: none;
   }

   .header .navbar{
      flex-flow: column;
      padding: 2rem;
      display: none;
   }

   .header .navbar.active{
      display: flex;
   }

}

@media (max-width:768px){

   .home .box img{
      height: 40vh;
   }

   .swiper-button-next,
   .swiper-button-prev{
      top: 35%;
   }

}

@media (max-width:450px){

   html{
      font-size: 50%;
   }

   .header .flex .logo{
      font-size: 2rem;
   }

   .home .box img{
      height: 25rem;
   }

   .about .row .image img{
      width: 100%;
   }

   .gallery img{
      height: 25rem;
      width: 30rem;
   }

   .bookings .box-container{
      grid-template-columns: 1fr;
   }

}
button {
    background-color: #165069;
    color: #fff;
    border: none;
    padding: 10px 20px;
    text-align: center;
    text-decoration: none;
    display: inline-block;
    font-size: 16px;
    cursor: pointer;
    border-radius: 5px;
}

    </style>
    </head>
    <body style="background:url(./uploads/bg008.jpg);background-repeat: no-repeat;background-size: cover;">
      
    <?php include_once('includes/header.php');?>
    <h1 style="font-size: 2rem;
    margin-top: 56px;
    margin-left: 647px;color:white;">MAID DETAILS</h1>
<?php
// Replace these variables with your actual database credentials
$servername = "localhost";
$username = "root";
$password = "";
$database = "mhmsdb";

// Create connection
$conn = new mysqli($servername, $username, $password, $database);

// Check connection
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}


// Define the category for cooking maids
$Category = $_GET["CatID"];

// Perform a query using prepared statement
$stmt = $conn->prepare("SELECT * FROM tblmaid WHERE CatID = ?");
$stmt->bind_param("s", $_GET["CatID"]);
$stmt->execute();

// Get the result set
$res = $stmt->get_result();

// Fetch data
while ($row = $res->fetch_assoc()) {
  // Your code to display data goes here
  
  echo ' <div class="column" style="margin-top: -135px;">
  <div class="card" style="margin: 192px 309px;">
   <div class="box"> 
   <p> <img src="admin/images/' . (isset($row["ProfilePic"]) ? $row["ProfilePic"] : '') . '" height="50px;" style="margin-left: 358px;
   padding: 0px;
   width: 28%;
   height: 47%; ></p>
   <p>ID Proof:<img src="admin/idproofimages/' . (isset($row["IdProof"]) ? $row["IdProof"] : '') . '" height="50px;" ></p>
                <p style="margin-top: -136px;    font-size: 16px;"> Name:' . (isset($row['Name'])? $row['Name'] : '') . '</p> 
                <p style="font-size: 16px;">Email:' . (isset($row['Email']) ? $row['Email'] : '') . '</p> 
      <p style="font-size: 16px;">Contact:' . (isset($row['ContactNumber']) ? $row['ContactNumber'] : '') . '</p> 
                <p style="font-size: 16px;">DOB:' . (isset($row['Dateofbirth']) ? $row['Dateofbirth'] : '') . '</p>
      <p style="font-size: 16px;">Address:' . (isset($row['Address']) ? $row['Address'] : '') . '</p> 
            <p style="font-size: 16px;">Category:' . (isset($row['CatID']) ? $row['CatID'] : '') . '</p> 
      <p style="font-size: 16px;">Experience:' . (isset($row['Experience']) ? $row['Experience'] : '') . '</p> 
                <p style="font-size: 16px;">Gender:' . (isset($row['Gender']) ? $row['Gender'] : '') . '</p> 
                <button class="button"><a href="maid-hiring.php" style="color:white">check availability</a></button>
              
               
                
            </div>
    </div>
    </div>
    ';
}

// Handle the case when no maid details are found
if ($res->num_rows === 0) {
  echo '<center style="font-size: large;margin-top: 130px;color:white;">No maid details found for the specified criteria!!</center>';
}

// Close the statement
$stmt->close();

// Close the connection
$conn->close();
?>
 
   
  <!-- JS here -->
	
		<!-- All JS Custom Plugins Link Here here -->
      <script src="./assets/js/vendor/modernizr-3.5.0.min.js"></script>
		<!-- Jquery, Popper, Bootstrap -->
		<script src="./assets/js/vendor/jquery-1.12.4.min.js"></script>
        <script src="./assets/js/popper.min.js"></script>
        <script src="./assets/js/bootstrap.min.js"></script>
	    <!-- Jquery Mobile Menu -->
        <script src="./assets/js/jquery.slicknav.min.js"></script>

		<!-- Jquery Slick , Owl-Carousel Plugins -->
        <script src="./assets/js/owl.carousel.min.js"></script>
        <script src="./assets/js/slick.min.js"></script>
        <script src="./assets/js/price_rangs.js"></script>
        
		<!-- One Page, Animated-HeadLin -->
        <script src="./assets/js/wow.min.js"></script>
		<script src="./assets/js/animated.headline.js"></script>
        <script src="./assets/js/jquery.magnific-popup.js"></script>

		<!-- Scrollup, nice-select, sticky -->
        <script src="./assets/js/jquery.scrollUp.min.js"></script>
        <script src="./assets/js/jquery.nice-select.min.js"></script>
		<script src="./assets/js/jquery.sticky.js"></script>
        
        <!-- contact js -->
        <script src="./assets/js/contact.js"></script>
        <script src="./assets/js/jquery.form.js"></script>
        <script src="./assets/js/jquery.validate.min.js"></script>
        <script src="./assets/js/mail-script.js"></script>
        <script src="./assets/js/jquery.ajaxchimp.min.js"></script>
        
		<!-- Jquery Plugins, main Jquery -->	
        <script src="./assets/js/plugins.js"></script>
        <script src="./assets/js/main.js"></script>
       
    </body>
   

    </html>