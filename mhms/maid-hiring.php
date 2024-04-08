<?php
session_start();
error_reporting(0);
include('includes/dbconnection.php');

if (isset($_POST['submit'])) {
    $catid = $_POST['catid'];
    $name = $_POST['name'];
    $contno = $_POST['contno'];
    $email = $_POST['email'];
    $address = $_POST['address'];
    $gender = $_POST['gender'];
    $wsf = $_POST['wsf'];
    $wst = $_POST['wst'];
    $startdate = $_POST['startdate'];
    $amount = $_POST['amount'];
    $notes = $_POST['notes'];
    $bookingid = mt_rand(100000000, 999999999);

    // Check if the maid is available
    if (checkMaidAvailability($catid, $startdate, $wsf, $wst)) {
        $sql = "INSERT INTO tblmaidbooking (BookingID, CatID, Name, ContactNumber, Email, Address, Gender, WorkingShiftFrom, WorkingShiftTo, StartDate, Notes, Amount) VALUES (:bookingid, :catid, :name, :contno, :email, :address, :gender, :wsf, :wst, :startdate, :notes, :amount)";
        $query = $dbh->prepare($sql);
        $query->bindParam(':bookingid', $bookingid, PDO::PARAM_STR);
        $query->bindParam(':catid', $catid, PDO::PARAM_STR);
        $query->bindParam(':name', $name, PDO::PARAM_STR);
        $query->bindParam(':contno', $contno, PDO::PARAM_STR);
        $query->bindParam(':email', $email, PDO::PARAM_STR);
        $query->bindParam(':address', $address, PDO::PARAM_STR);
        $query->bindParam(':gender', $gender, PDO::PARAM_STR);
        $query->bindParam(':wsf', $wsf, PDO::PARAM_STR);
        $query->bindParam(':wst', $wst, PDO::PARAM_STR);
        $query->bindParam(':startdate', $startdate, PDO::PARAM_STR);
        $query->bindParam(':notes', $notes, PDO::PARAM_STR);
        $query->bindParam(':amount', $amount, PDO::PARAM_STR);

        $query->execute();
        $lastInsertId = $dbh->lastInsertId();

        if ($lastInsertId > 0) {
            echo '<script>alert("Your Booking Request Has Been Sent. We Will Contact You Soon")</script>';
            echo "<script>window.location.href ='maid-hiring.php'</script>";
        } else {
            echo '<script>alert("Something Went Wrong. Please try again")</script>';
        }
    } else {
        echo '<script>alert("Sorry, the selected maid is not available for the specified time. Please choose another time.")</script>';
    }
}

function checkMaidAvailability($catid, $startdate, $wsf, $wst)
{
    global $dbh;

    $sql = "SELECT * FROM tblmaidbooking WHERE CatID = :catid AND StartDate = :startdate AND ((WorkingShiftFrom BETWEEN :wsf AND :wst) OR (WorkingShiftTo BETWEEN :wsf AND :wst))";
    $query = $dbh->prepare($sql);
    $query->bindParam(':catid', $catid, PDO::PARAM_STR);
    $query->bindParam(':startdate', $startdate, PDO::PARAM_STR);
    $query->bindParam(':wsf', $wsf, PDO::PARAM_STR);
    $query->bindParam(':wst', $wst, PDO::PARAM_STR);
    $query->execute();
    $result = $query->fetch(PDO::FETCH_ASSOC);

    return !$result; // Return true if the maid is available, false otherwise
}
?>
<!doctype html>
<html class="no-js" lang="zxx">

<head>
    <title>Hire Maid </title>
        
        <link rel="manifest" href="site.webmanifest">
		<link rel="shortcut icon" type="image/x-icon" href="admin/images/image 9.png" style="margin-top: 20px;margin-left:20px;">
   
    

   <!-- CSS here -->
        <link rel="stylesheet" href="assets/css/bootstrap.min.css">
        <link rel="stylesheet" href="assets/css/owl.carousel.min.css">
        <link rel="stylesheet" href="assets/css/slicknav.css">
        <link rel="stylesheet" href="assets/css/price_rangs.css">
        <link rel="stylesheet" href="assets/css/animate.min.css">
        <link rel="stylesheet" href="assets/css/magnific-popup.css">
        <link rel="stylesheet" href="assets/css/fontawesome-all.min.css">
        <link rel="stylesheet" href="assets/css/themify-icons.css">
        <link rel="stylesheet" href="assets/css/themify-icons.css">
        <link rel="stylesheet" href="assets/css/slick.css">
        <link rel="stylesheet" href="assets/css/nice-select.css">
        <link rel="stylesheet" href="assets/css/style.css">
        <link rel="stylesheet" href="assets/css/responsive.css">
        <style>
            .availability-form {
                background-color: #fff;
    border-radius: 8px;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    padding: 20px;
    width: 524px;
    text-align: center;
    margin-left: 367px;
    margin-top: 52px;
    }

    label {
      display: flex;
      margin-bottom: 8px;
    }

    input {
      width: 100%;
      padding: 8px;
      margin-bottom: 16px;
      box-sizing: border-box;
    }

    button {
      background-color: #165069;
      color: #fff;
      border: none;
      padding: 10px;
      border-radius: 4px;
      cursor: pointer;
    }

    button:hover {
      background-color: #fb246a;
    }

    #availability-result {
      margin-top: 16px;
      font-weight: bold;
    }
    .form-contact{
     
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    padding: 20px;
    width: 893px;
    text-align: center;
    margin-left: -70px;
    margin-top: 7px;
    background-color:white;

    }
            </style>
</head>

<body style="background:url(./uploads/bg008.jpg);background-repeat: no-repeat;background-size: cover;">
    <?php include_once('includes/header.php');?>
    <!-- Hero Area Start-->
   
        <!-- Hero Area End -->
    <!-- ================ contact section start ================= -->




    <section class="contact-section">
            <div class="container">
                <div class="row" >
                    <div class="col-12">
                        <h2 class="contact-title" style="text-align: center;color: white;margin-top: -81px;">Looking To Hire A Maid?</h2>
                  
                    </div>
                    <div class="col-lg-8" style="margin-left: 215px;">

                        <form class="form-contact contact_form" action="" method="post">
                            <div class="row" style="width: 392px;">
                                <div class="col-sm-12">
                                    <div class="form-group">
                                        <label style="color: black;font-weight: bold;font-size: 20px;">Name:</label>
                                        <input class="form-control" name="name" id="name" type="text" placeholder="Enter your name">
                                    </div>
                                </div>
                                <div class="col-sm-12">
                                    <div class="form-group">
                                        <label style="color: black;font-weight: bold;font-size: 20px;">Contact Number:</label>
                                        <input type="text" name="contno" value="" class="form-control" required='true' maxlength="10" pattern="[0-9]+">
                                    </div>
                                </div>
                                <div class="col-sm-12">
                                    <div class="form-group">
                                        <label style="color: black;font-weight: bold;font-size: 20px;">Address(to be hired):</label>
                                        <textarea class="form-control" name="address" id="address" placeholder="Enter Your Addresss"></textarea>
                                    </div>
                                </div>
                                <div class="col-sm-12">
                                    <div class="form-group">
                                        <label style="color: black;font-weight: bold;font-size: 20px;">Email:</label>
                                        <input class="form-control" name="email" id="email" type="email" placeholder="Email">
                                    </div>
                                </div>

                                <div class="col-sm-12">
                                    <div class="form-group">
                                        <label style="color: black;font-weight: bold;font-size: 20px; padding-left: 20px;">Gender:</label>
                                       <select name="gender" class="form-control" required='true' >
                                        
                                 <option value="" style="width: 313px;">Select Gender</option>
                                 <option value="Female" style="width: 313px;">Female</option>
                                 <option value="Male" style="width: 313px;">Male</option>
                             </select>
                                    </div>
                                </div>

                                <div class="col-sm-12">
                                    <div class="form-group">
                                        <label style="color: black;font-weight: bold;font-size: 20px; padding-left: 20px;">wages</label>
                                       <select name="wages" class="form-control" required='true'  onchange="calWages()" >
                                        
                                 <option value="0" style="width: 313px;">Select</option>
                                 <option value="hourly" style="width: 313px;">Hourly</option>
                                 <option value="Monthly" style="width: 313px;">Monthly</option>
                             </select>
                                    </div>
                                </div>
                              

                                <div class="col-sm-12">
                                    <div class="form-group" style="margin-left: 176px;
    margin-top: -86px;">
                                        <label style="color:black;font-weight: bold;font-size: 20px;padding-left: 20px;">Select Service:</label>
                                        <select name="catid" class="form-control" required='true' onchange="updateFields()">
  <option value="">Select Service</option>
                                 <?php 
    $sql2 = "SELECT * from tblcategory ";
    $query2 = $dbh->prepare($sql2);
    $query2->execute();
    $result2 = $query2->fetchAll(PDO::FETCH_OBJ);

    foreach($result2 as $row2) {          
  ?>  
    <option value="<?php echo htmlentities($row2->CategoryName);?>" data-hrs="350" data-amount="<?php echo htmlentities($row2->Amount);?>">
      <?php echo htmlentities($row2->CategoryName);?></option>
  <?php } ?>
</select>

</div>
</div>
<input type="hidden" id="serviceDisplay"  name="service" readonly>
</div>
<div class="row" style="width: 392px;;margin-left: 456px;
    margin-top: -643px;">  
<div class="col-sm-12">
    <div class="form-group" style="margin-top: 84px;">
            <label style="color:black;font-weight: bold;font-size: 20px;">Amount:</label>
            <input class="form-control" name="amount" id="amountDisplay" type="yext" placeholder="Amount">
    </div>
</div>


<script>
  function updateFields() {
    var select = document.querySelector('select[name="catid"]');
    var amountDisplay = document.getElementById('amountDisplay');
    var serviceDisplay = document.getElementById('serviceDisplay');
    var selectedOption = select.options[select.selectedIndex];

    // Get the data-amount and text content from the selected option
    var amount = selectedOption.getAttribute('data-amount');
    var service = selectedOption.textContent;

    // Update the value of the input fields
    amountDisplay.value = amount;
    serviceDisplay.value = service;
  }


//   function calWages(){
//     var wages = document.querySelector('select[name="wages"]');
//     var selectedOption = select.options[select.selectedIndex];

  
</script>
                    
                                <!-- Add these input fields to your HTML form -->
<div class="col-sm-12">
    <div class="form-group">
        <label style="color: black;font-weight: bold;font-size: 20px;">Shift Start Time:</label>
        <input class="form-control" name="wsf" id="wsf" type="time">
    </div>
</div>

<div class="col-sm-12">
    <div class="form-group">
        <label style="color: black;font-weight: bold;font-size: 20px;">Shift End Time:</label>
        <input class="form-control" name="wst" id="wst" type="time">
    </div>
</div>

                                <div class="col-sm-12">
                                    <div class="form-group">
                                        <label style="color: black;font-weight: bold;font-size: 20px;">Start Date:</label>
                                        <input class="form-control" name="startdate" id="startdate" type="date">
                                    </div>
                                </div>
                                <div class="col-12">
                                    <div class="form-group">
                                        <label style="color: black;font-weight: bold;font-size: 20px;">Notes:</label>
                                        <textarea class="form-control" name="notes" id="notes" type="text" placeholder="Enter Some Notes"></textarea>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group mt-3">
                                <button type="submit" class="button button-contactForm boxed-btn" name="submit" style="margin-top: 61px;">Send</button>
                            </div>
                        </form>
                    </div>
                 
                </div>
            </div>
        </section>
    <!-- ================ contact section end ================= -->
  <?php include_once('includes/footer.php');?>
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
		
		<!-- Scrollup, nice-select, sticky -->
        <script src="./assets/js/jquery.scrollUp.min.js"></script>
        <script src="./assets/js/jquery.nice-select.min.js"></script>
		<script src="./assets/js/jquery.sticky.js"></script>
        <script src="./assets/js/jquery.magnific-popup.js"></script>

        <!-- contact js -->
        <script src="./assets/js/contact.js"></script>
        <script src="./assets/js/jquery.form.js"></script>
        <script src="./assets/js/jquery.validate.min.js"></script>
        <script src="./assets/js/mail-script.js"></script>
        <script src="./assets/js/jquery.ajaxchimp.min.js"></script>
        
		<!-- Jquery Plugins, main Jquery -->	
        <script src="./assets/js/plugins.js"></script>
        <script src="./assets/js/main.js"></script>


        <script>
  function updateFields() {
    var select = document.querySelector('select[name="catid"]');
    var amountDisplay = document.getElementById('amountDisplay');
    var serviceDisplay = document.getElementById('serviceDisplay');
    var wsfInput = document.getElementById('wsf');
    var wstInput = document.getElementById('wst');

    // Get the data-amount and text content from the selected option
    var amount = select.options[select.selectedIndex].getAttribute('data-amount');
    var service = select.options[select.selectedIndex].textContent;

    // Update the value of the input fields
    amountDisplay.value = amount;
    serviceDisplay.value = service;

    // Calculate the amount based on working hours
    var wsf = wsfInput.valueAsNumber;
    var wst = wstInput.valueAsNumber;

    if (!isNaN(wsf) && !isNaN(wst)) {
      var hoursWorked = (wst - wsf) / 3600000; // Convert milliseconds to hours

      // Adjust the amount based on the number of hours worked
      var calculatedAmount = amount * hoursWorked;

      // Update the amount display field with the calculated amount
      amountDisplay.value = calculatedAmount.toFixed(2); // Display the amount with two decimal places
    }
  }

  // Add event listeners to the working hours input fields and start date
  document.getElementById('wsf').addEventListener('change', updateFields);
  document.getElementById('wst').addEventListener('change', updateFields);
  document.getElementById('startdate').addEventListener('change', updateFields);
</script>

        
    </body>
    
    </html>