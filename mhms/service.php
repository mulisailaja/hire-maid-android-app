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
             <!-- Swiper CSS -->
        <link rel="stylesheet" href="Responsive Card Slider/css/swiper-bundle.min.css">

<!-- CSS -->

   </head>
   <body style="background:url(./uploads/bg008.jpg);background-repeat: no-repeat;background-size: cover;">
    <?php include_once('includes/header.php');?>
    <main>
    
        <!-- Support Company End-->

        <style>
    .mySlides {display: none;}
img {vertical-align: middle;}

/* Slideshow container */
.slideshow-container {
    max-width: 1186px;
    position: relative;
    margin: auto;
    margin-top: 9px;
    margin-left: 178px;

}

/* Caption text */
.text {
  color: black;
  font-size: 15px;
  padding: 8px 12px;
  position: absolute;
  bottom: 8px;
  width: 100%;
  text-align: center;
}

/* Number text (1/3 etc) */
.numbertext {
  color: #f2f2f2;
  font-size: 12px;
  padding: 8px 12px;
  position: absolute;
  top: 0;
}

/* The dots/bullets/indicators */
.dot {
  height: 7px;
  width: 6px;
  margin: 0 2px;
  background-color: #bbb;
  border-radius: 50%;
  display: inline-block;
  transition: background-color 0.6s ease;
}


.active {
  background-color: #717171;
}

/* Fading animation */
.fade {
  animation-name: fade;
  animation-duration: 1.5s;
}

@keyframes fade {
  from {opacity: .4} 
  to {opacity: 1}
}

/* On smaller screens, decrease text size */
@media only screen and (max-width: 300px) {
  .text {font-size: 11px}
} 



  .container {
  width: 1200px !important;
  padding: 0 !important;
  margin-right: auto;
  margin-left: auto;

  @media screen and (min-width: 992px) and (max-width: 1439px) {
    max-width: 1279px !important;
    padding: 0 !important;
    margin: 0 80px !important;
    width: auto !important;
  }

  @media screen and (max-width: 991px) {
    max-width: 959px !important;
    margin: 0 16px !important;
    padding: 0 !important;
    width: auto !important;
  }
}

.gradient-cards {
  display: grid;
  grid-template-columns: repeat(3, 1fr);
  gap: 32px;
  padding: 30px;
  @media screen and (max-width: 991px) {
    grid-template-columns: 1fr;
  }
}

.container-title {
  text-align: center;
  padding: 0 !important;
  margin-bottom: 40px;
  font-size: 40px;
  color: #fff;
  font-weight: 600;
  line-height: 60px;
}

.card {
  max-width: 550px;
  border: 0;
  width: 100%;
  margin-inline: auto;
  background:transparent;
}

.container-card {
  position: relative;
  border: 2px solid transparent;
  background: linear-gradient(71deg, #76699c, #5cb1af, #080509);
  background-clip: padding-box;
  border-radius: 45px;
  padding: 40px;
  img {
    margin-bottom: 32px;
  }
}

.bg-green-box,
.bg-white-box,
.bg-yellow-box,
.bg-blue-box {
  position: relative;
}

.bg-green-box::after,
.bg-white-box::after,
.bg-yellow-box::after,
.bg-blue-box::after {
  position: absolute;
  top: -1px;
  bottom: -1px;
  left: -1px;
  right: -1px;
  content: "";
  z-index: -1;
  border-radius: 45px;
}

.bg-green-box::after {
  background: linear-gradient(71deg, #0d1212, #3da077, #0d1212);
}

.bg-white-box::after {
  background: linear-gradient(71deg, #121013, #b0afb0, #121013);
}

.bg-yellow-box::after {
  background: linear-gradient(71deg, #110e0e, #afa220, #110e0e);
}

.bg-blue-box::after {
  background: linear-gradient(71deg, #0c0a0e, #5f6fad, #0c0a0e);
}

.card-title {
  font-weight: 600;
  color: white;
  letter-spacing: -0.02em;
  line-height: 40px;
  font-style: normal;
  font-size: 28px;
  padding-bottom: 8px;
  margin-left: 69px;
}

.card-description {
  font-weight: 600;
  line-height: 32px;
  color: hsla(0, 0%, 100%, 0.5);
  font-size: 16px;
  max-width: 470px;
}

  
@media only screen and (max-width: 700px) {
  .responsive {
    width: 49.99999%;
    margin: 6px 0;
  }
}

@media only screen and (max-width: 500px) {
  .responsive {
    width: 100%;
  }
}

.clearfix:after {
  content: "";
  display: table;
  clear: both;
}
    </style>
        <h1 style="ont-size: 2rem;
    color: white;
    margin-left: 660px;
    margin-top: 38px;">CATEGORIES</h1>
<!-- text -->.
<div class="container">


  <div class="gradient-cards">
    
    <div class="card">
      <div class="container-card bg-green-box">
        <a href="service.php">
       
      <img src="laundry.jpeg" alt="Cinque Terre"  style="margin: 20px;
    margin-left: 28px;width: 212px;
    height: 147px;
}">
    </a>
        <a class="card-title">Laundry</a>
       
      </div>
    </div>

    <div class="card">
      <div class="container-card bg-green-box">
        <a href="service.php">
      <img src="bathroomcleaning2.jpeg" alt="Cinque Terre"  style="margin: 20px;
    margin-left: 28px;width: 212px;
    height: 147px;
}">
    </a>
        <a class="card-title" style="margin-left:33px;">Bathroom Clean</a>
       
      </div>
    </div>

    <div class="card">
      <div class="container-card bg-green-box">
        <a href="service.php">
      <img src="sweeping1.jpeg" alt="Cinque Terre"  style="margin: 20px;
    margin-left: 28px;width: 212px;
    height: 147px;
}">
    </a>
        <a class="card-title">Sweeping</a>
       
      </div>
    </div>
    <div class="card">
      <div class="container-card bg-green-box">
        <a href="service.php">
      <img src="kitchencleaning.jpeg" alt="Cinque Terre"  style="margin: 20px;
    margin-left: 28px;width: 212px;
    height: 147px;
}">
    </a>
        
        <a class="card-title"  style="margin-left: 33px;">kitchen cleaning</a>
       
      </div>
    </div>

    <div class="card">
      <div class="container-card bg-green-box">
        <a href="service.php">
      <img src="fullhousecleaning1.jpeg" alt="Cinque Terre"  style="margin: 20px;
    margin-left: 28px;width: 212px;
    height: 147px;
}">
    </a>
        <a class="card-title"  style="margin-left: 33px;">Deep Cleaning</a>
       
      </div>
    </div>

    <div class="card">
      <div class="container-card bg-green-box">
        <a href="service.php">
      <img src="ironing3.jpeg" alt="Cinque Terre"  style="margin: 20px;
    margin-left: 28px;width: 212px;
    height: 147px;
}">
    </a>
        <a class="card-title">Folding</a>
       
      </div>
    </div>

    <div class="card">
      <div class="container-card bg-green-box">
        <a href="service.php">
      <img src="cooking2.jpeg" alt="Cinque Terre"  style="margin: 20px;
    margin-left: 28px;width: 212px;
    height: 147px;
}">
    </a>
  
        <a class="card-title">Cooking</a>
       
      </div>
    </div>

    <div class="card">
      <div class="container-card bg-green-box">
        <a href="service.php">
      <img src="Mopping1.jpeg" alt="Cinque Terre"  style="margin: 20px;
    margin-left: 28px;width: 212px;
    height: 147px;
}">
    </a>
         
        <a class="card-title">Mopping</a>
       
      </div>
    </div>

    <div class="card">
      <div class="container-card bg-green-box">
        <a href="service.php">
      <img src="utensilscleaning1.jpeg" alt="Cinque Terre"  style="margin: 20px;
    margin-left: 28px;width: 212px;
    height: 147px;
}">
    </a>
         
        <a class="card-title" style="margin-left: 33px;">Utensils Cleaning</a>
       
      </div>
    </div>
  </div>
</div>





    </main>
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
        
         <!-- Swiper JS -->
    <script src="Responsive Card Slider/js/swiper-bundle.min.js"></script>

<!-- JavaScript -->
<script src="Responsive Card Slider/js/script.js"></script>
    </body>
</html>