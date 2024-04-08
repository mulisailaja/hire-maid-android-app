<?php
session_start();
error_reporting(0);

include('includes/dbconnection.php');
?>
<!doctype html>
<html class="no-js" lang="zxx">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="x-ua-compatible" content="ie=edge">
       <title>Hire Maid </title>
        
        <link rel="manifest" href="site.webmanifest">
		<link rel="shortcut icon" type="image/x-icon" href="admin/images/image 9.png" style="margin-top: 20px;margin-left:20px;">
        <meta name="description" content="">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="manifest" href="site.webmanifest">
		<link rel="shortcut icon" type="image/x-icon" href="assets/img/favicon.ico">
       

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
            <link rel='stylesheet' href='//maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css'>
<link rel='stylesheet' href='//cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.9.0/bootstrap-table.min.css'>
<link rel='stylesheet' href='https://cdn.datatables.net/1.10.9/css/jquery.dataTables.min.css'>

<link rel="stylesheet" href="assets/css/bootstrap.min.css">
<style>
   
/*    
   th {
     background-color: white;
   }
   tr:nth-child(odd) {
     background-color: grey;
   }
   th, td {
     padding: 0.5rem;
   }
   td:hover {
     background-color: lightsalmon;
   }
   
   .paginate_button {
     border-radius: 0 !important;
   } */
   
       /* CSS */
       #example {
  font-family: Arial, Helvetica, sans-serif;
  border-collapse: collapse;
  width: 100%;
}

#example td, #example th {
  border: 1px solid #ddd;
  padding: 8px;
}

#example tr:nth-child(even){background-color: #f2f2f2;}

#example tr:hover {background-color: #ddd;}

#example th {
  padding-top: 12px;
  padding-bottom: 12px;
  text-align: center;
  background-color: #10477c;
  color: white;
}
   .button-10 {
     display: flex;
     flex-direction: column;
     align-items: center;
     padding: 6px 14px;
     font-family: -apple-system, BlinkMacSystemFont, 'Roboto', sans-serif;
     border-radius: 6px;
     border: none;
   
     color: #fff;
     background: linear-gradient(180deg, #4B91F7 0%, #367AF6 100%);
      background-origin: border-box;
     box-shadow: 0px 0.5px 1.5px rgba(54, 122, 246, 0.25), inset 0px 0.8px 0px -0.25px rgba(255, 255, 255, 0.2);
     user-select: none;
     -webkit-user-select: none;
     touch-action: manipulation;
   }
   
   .button-10:focus {
     box-shadow: inset 0px 0.8px 0px -0.25px rgba(255, 255, 255, 0.2), 0px 0.5px 1.5px rgba(54, 122, 246, 0.25), 0px 0px 0px 3.5px rgba(58, 108, 217, 0.5);
     outline: 0;
   }
   </style>
   </head>

   <body>
    
    <?php include_once('includes/header.php');?>
    <main>

    <table id="example" class="display" cellspacing="0" style="margin-top: 45px;
    font-size: 16px;text-align: center;" width="100%">
        <thead style="background-color: grey;">
            <tr>
                <th>BookingID</th>
                <th>Name</th>
                <th>Amount</th>
                <th>AssignTo</th>
                <th>Booking Date</th>
                <th>Status</th>
            </tr>
        </thead>
 
        <!-- <tfoot>
            <tr>
                <th>BookingID</th>
                <th>Name</th>
                <th>Amount</th>
                <th>Service</th>
                <th>Booking Date</th>
                <th>Status</th>
            </tr>
        </tfoot> -->
 
        <tbody>
        <?php
        // Database connection
        $db_host = 'localhost';
        $db_user = 'root';
        $db_pass = '';
        $db_name = 'mhmsdb';

        $conn = new mysqli($db_host, $db_user, $db_pass, $db_name);

        if ($conn->connect_error) {
            die("Connection failed: " . $conn->connect_error);
        }
        $Name = $_SESSION["Name"];
        // Query to select all images from the table
        $sql = "SELECT * FROM tblmaidbooking where name='$Name'";
        $result = $conn->query($sql);
        if ($result->num_rows > 0) {
          while ($row = $result->fetch_assoc()) {
            // Retrieve the data
            $BookingID = $row['BookingID'];
            $Name = $row['Name'];
            $Amount = $row['Amount'];
            $AssignTo = $row['AssignTo'];
            $status = $row['Status'];
            $bookingdate = $row['BookingDate'];
        
            // Generate the HTML for each row with Bootstrap card styling
            echo '<tr>
                    <td>' . $BookingID . '</td>
                    <td>' . $Name . '</td>
                    <td>' . $Amount . '</td>
                    <td><a href="view-maid.php?assign_to=' . urlencode($AssignTo) . '">' . $AssignTo . '</a></td>
                    <td>' . $bookingdate . '</td>';
        
            // Check if the status is pending or rejected
            if ($status == 'pending' || $status == 'Cancelled'|| $status=='Paid') {
                // Display the status in one column
                echo '<td>' . $status . '</td>';
                
                // Display the "Make Payment" button in another column
            } else {
                // Display the "Make Payment" button in a single column
                echo '<td class="text-center"><a href="javascript:void(0)" class="btn btn-sm btn-primary buy_now" data-img="//www.tutsmake.com/wp-content/uploads/2019/03/jhgjhgjg.jpg" data-amount="'.$Amount.'" data-id="' . $BookingID . '"><button class="button-10" role="button" onclick="listview()">Make Payment</button></a></td>';
              }
        
            echo '</tr>';
        }
        
            }
        else {
            // Handle the case when there are no records
            echo '<tr><td colspan="7">No bookings found.</td></tr>';
        }
        ?>
          
          

          
           
        </tbody>
    </table>
    <!-- partial -->
  <script src='//cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
<script src='//maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js'></script>
<script src='https://cdn.datatables.net/1.10.9/js/jquery.dataTables.min.js'></script>

<script>
  $(document).ready(function() {
    var table = $('#example').DataTable({
        select: false,
        "columnDefs": [{
            "targets": [0],
            "visible": false,
            "searchable": false
        }]
    });

    $('#example tbody').on('click', 'tr', function() {
        alert(table.row(this).data()[0]);
    });
});

</script>                                                                                                                                                                                                                                                                       

    </main>
    

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
        

        <script src="https://checkout.razorpay.com/v1/checkout.js"></script>
        <script>

  $('body').on('click', '.buy_now', function(e){
    var prodimg = $(this).attr("data-img");
    var totalAmount = $(this).attr("data-amount");
    var product_id =  $(this).attr("data-id");
    var options = {
    "key": "rzp_test_v2uRVdVdpyEH96",
    "amount": (totalAmount*100), // 2000 paise = INR 20
    "name": "Tutsmake",
    "description": "Payment",
 
    "handler": function (response){
          $.ajax({
            url: 'payment-proccess.php',
            type: 'post',
            dataType: 'json',
            data: {
                razorpay_payment_id: response.razorpay_payment_id , totalAmount : totalAmount ,product_id : product_id,
            }, 
            success: function (msg) {

               window.location.href = 'https://www.tutsmake.com/Demos/php/razorpay/success.php';
            }
        });
     
    },

    "theme": {
        "color": "#528FF0"
    }
  };
  var rzp1 = new Razorpay(options);
  rzp1.open();
  e.`preventDefault`();
  });

</script>

<script src=""></script>

<script>
 
  $('body').on('click', '.buy_now', function(e){
    var prodimg = $(this).attr("data-img");
    var totalAmount = $(this).attr("data-amount");
    var product_id =  $(this).attr("data-id");
    var options = {
    "key": "rzp_test_v2uRVdVdpyEH96", // secret key id
    "amount": (totalAmount*100), // 2000 paise = INR 20
    "name": "Tutsmake",
    "description": "Payment",
 
    "handler": function (response){
          $.ajax({
            url: 'payment-proccess.php',
            type: 'post',
            dataType: 'json',
            data: {
                razorpay_payment_id: response.razorpay_payment_id , totalAmount : totalAmount ,product_id : product_id,
            }, 
            success: function (msg) {
 
               window.location.href = 'payment-success.php';

            }
        });
      
    },
 
    "theme": {
        "color": "#528FF0"
    }
  };
  var rzp1 = new Razorpay(options);
  rzp1.open();
  e.preventDefault();
  });
 
</script>
    </body>
   
</html>