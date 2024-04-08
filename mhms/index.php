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
<link rel="stylesheet" href="Responsive Card Slider/css/style.css">
<style>
    .container {
    text-align: center;
   
    padding: 20px;
    border-radius: 8px;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

label {
    display: block;
    margin: 10px 0 5px;
}

input {
    width: 100%;
    padding: 8px;
    margin: 5px 0 15px;
    box-sizing: border-box;
}

button {
    background-color: #4caf50;
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

#result {
    margin-top: 20px;
}

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

    </style>
   </head>
   <body style="background:url(./uploads/bg008.jpg);background-repeat: no-repeat;background-size: cover;">
    <?php include_once('includes/header.php');?>
    <main>

    <div class="slideshow-container" style="max-width:1233px;
    position: relative;
    margin: auto;
    margin-top: 9px;
    margin-left: 145px;">

<div class="mySlides fade">
  <!-- <div class="numbertext">1 / 3</div> -->
  <img src="uploads\slide5.jpg" style="width: 124%;
  height:590px;
  margin-left: -144px;">
  <!-- <div class="text">HIREMAID</div> -->
</div>

<div class="mySlides fade">
  <!-- <div class="numbertext">2 / 3</div> -->
  
  <img src="uploads\slide3.jpg" style="width: 124%;
  height:590px;
  margin-left: -144px;">
  <!-- <div class="text">HIREMAID</div> -->
</div>

<div class="mySlides fade">
  <!-- <div class="numbertext">3 / 3</div> -->
  
  <img src="uploads\Slide-1.jpg" style="width: 124%;
  height:590px;
  margin-left: -144px;">
  <!-- <div class="text">HIREMAID</div> -->
</div>

</div>
<br>

<div style="text-align:center">
  <span class="dot"></span> 
  <span class="dot"></span> 
  <span class="dot"></span> 
</div>
<br>
<h1 style="margin-left: 653px;
    color: white;
    font-size: xxx-large;">Our Maids</h1>
    <div class="slide-container swiper">
            <div class="slide-content">
                <div class="card-wrapper swiper-wrapper">
                    <div class="card swiper-slide">
                        <div class="image-content">
                            <span class="overlay_card" style="background-color: #165069;"></span>

                            <div class="card-image">
                                <img src="laundry.jpeg" alt="" class="card-img">
                            </div>
                        </div>

                        <div class="card-content">
                            <h2 class="name">Laundry</h2>
                            <a href="servicefetch.php?CatID=laundry">
                            <p class="description">The lorem text the section that contains header with having open functionality. Lorem dolor sit amet consectetur adipisicing elit.</p>

                            <button class="button"><a href="maid-hiring.php" style="color:white">Book Now</a></button>
                        </div>
                    </div>
                    <div class="card swiper-slide">
                        <div class="image-content">
                        <span class="overlay_card" style="background-color: #165069;"></span>

                            <div class="card-image">
                                <img src="bathroomcleaning2.jpeg" alt="" class="card-img">
                            </div>
                        </div>

                        <div class="card-content">
                            <h2 class="name">Bathroom Cleaning</h2>
                            <a href="servicefetch.php?CatID=bathroom cleaning">
                            <p class="description">The lorem text the section that contains header with having open functionality. Lorem dolor sit amet consectetur adipisicing elit.</p>

                            <button class="button"><a href="maid-hiring.php" style="color:white">Book Now</a></button>
                        </div>
                    </div>
                   

                        <div class="card swiper-slide">
                        <div class="image-content">
                        <span class="overlay_card" style="background-color: #165069;"></span>

                            <div class="card-image">
                                <img src="sweeping1.jpeg" alt="" class="card-img">
                            </div>
                        </div>

                        <div class="card-content">
                            <h2 class="name">Sweeping</h2>
                            <a href="servicefetch.php?CatID=sweeping">
                            <p class="description">The lorem text the section that contains header with having open functionality. Lorem dolor sit amet consectetur adipisicing elit.</p>

                            <button class="button"><a href="maid-hiring.php" style="color:white">Book Now</a></button>
                        </div>
                    </div>

                   
                    <div class="card swiper-slide">
                        <div class="image-content">
                        <span class="overlay_card" style="background-color: #165069;"></span>

                            <div class="card-image">
                                <img src="kitchencleaning.jpeg" alt="" class="card-img">
                            </div>
                        </div>

                        <div class="card-content">
                            <h2 class="name">Kitchen Cleaning</h2>
                            <a href="servicefetch.php?CatID=kitchen cleaning">
                            <p class="description">The lorem text the section that contains header with having open functionality. Lorem dolor sit amet consectetur adipisicing elit.</p>

                            <button class="button"><a href="maid-hiring.php" style="color:white">Book Now</a></button>
                        </div>
                    </div>
                    <div class="card swiper-slide">
                        <div class="image-content">
                        <span class="overlay_card" style="background-color: #165069;"></span>

                            <div class="card-image">
                                <img src="utensilscleaning1.jpeg" alt="" class="card-img">
                            </div>
                        </div>

                        <div class="card-content">
                            <h2 class="name">Utensils Cleaning</h2>
                            <a href="servicefetch.php?CatID=utensils cleaning">
                            <p class="description">The lorem text the section that contains header with having open functionality. Lorem dolor sit amet consectetur adipisicing elit.</p>

                            <button class="button"><a href="maid-hiring.php" style="color:white">Book Now</a></button>
                        </div>
                    </div>
                    <div class="card swiper-slide">
                        <div class="image-content">
                        <span class="overlay_card" style="background-color: #165069;"></span>

                            <div class="card-image">
                                <img src="Mopping1.jpeg" alt="" class="card-img">
                            </div>
                        </div>

                        <div class="card-content">
                            <h2 class="name">Mopping</h2>
                            <a href="servicefetch.php?CatID=Mopping">
                            <p class="description">The lorem text the section that contains header with having open functionality. Lorem dolor sit amet consectetur adipisicing elit.</p>

                            <button class="button"><a href="maid-hiring.php" style="color:white">Book Now</a></button>
                        </div>
                    </div>
                    <div class="card swiper-slide">
                        <div class="image-content">
                        <span class="overlay_card" style="background-color: #165069;"></span>

                            <div class="card-image">
                                <img src="babytakecarer.jpeg" alt="" class="card-img">
                            </div>
                        </div>

                        <div class="card-content">
                        <h2 class="name">Baby Care</h2>
                    <a href="servicefetch.php?CatID=babycare">
                            <p class="description">The lorem text the section that contains header with having open functionality. Lorem dolor sit amet consectetur adipisicing elit.</p>

      
                            <button class="button"><a href="maid-hiring.php" style="color:white">Book Now</a></button>
                            
                        </div>
                        
                    </div>
                    <div class="card swiper-slide">
                        <div class="image-content">
                        <span class="overlay_card" style="background-color: #165069;"></span>

                            <div class="card-image">
                                <img src="fullhousecleaning1.jpeg" alt="" class="card-img">
                            </div>
                        </div>

                        <div class="card-content">
                            <h2 class="name">Deep Cleaning</h2>
                            <a href="servicefetch.php?CatID=deepcleaning">
                            <p class="description">The lorem text the section that contains header with having open functionality. Lorem dolor sit amet consectetur adipisicing elit.</p>

                            <button class="button"><a href="maid-hiring.php" style="color:white">Book Now</a></button>
                        </div>
                    </div>
                    <div class="card swiper-slide">
                        <div class="image-content">
                        <span class="overlay_card" style="background-color: #165069;"></span>

                            <div class="card-image">
                                <img src="ironing3.jpeg" alt="" class="card-img">
                            </div>
                        </div>

                        <div class="card-content">
                            <h2 class="name">Tidying and Folding</h2>
                            <a href="servicefetch.php?CatID=tidyingandfolding">
                            <p class="description">The lorem text the section that contains header with having open functionality. Lorem dolor sit amet consectetur adipisicing elit.</p>

                            <button class="button"><a href="maid-hiring.php" style="color:white">Book Now</a></button>
                        </div>
                    </div>
                    <div class="card swiper-slide">
                        <div class="image-content">
                        <span class="overlay_card" style="background-color: #165069;"></span>

                            <div class="card-image">
                                <img src="cooking2.jpeg" alt="" class="card-img">
                            </div>
                        </div>

                        <div class="card-content">
                            <h2 class="name">Cooking</h2>
                            <a href="servicefetch.php?CatID=cooking">
                            <p class="description">The lorem text the section that contains header with having open functionality. Lorem dolor sit amet consectetur adipisicing elit.</p>

                            <button class="button"><a href="maid-hiring.php" style="color:white">Book Now</a></button>
                        </div>
                    </div>
                </div>
            </div>

            

            <div class="swiper-button-next swiper-navBtn" style="color:white;"></div>
            <div class="swiper-button-prev swiper-navBtn" style="color:white;"></div>
            <div class="swiper-pagination" style="color:white;"></div>
        </div>
        <!-- Support Company End-->
        <script>
let slideIndex = 0;
showSlides();

function showSlides() {
  let i;
  let slides = document.getElementsByClassName("mySlides");
  let dots = document.getElementsByClassName("dot");
  for (i = 0; i < slides.length; i++) {
    slides[i].style.display = "none";  
  }
  slideIndex++;
  if (slideIndex > slides.length) {slideIndex = 1}    
  for (i = 0; i < dots.length; i++) {
    dots[i].className = dots[i].className.replace(" active", "");
  }
  slides[slideIndex-1].style.display = "block";  
  dots[slideIndex-1].className += " active";
  setTimeout(showSlides, 1000); // Change image every 2 seconds
}
</script>

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