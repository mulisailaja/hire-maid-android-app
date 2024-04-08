

<!DOCTYPE html>
<!-- Created By CodingLab - www.codinglabweb.com -->
<html lang="en" dir="ltr">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
     <title>Login Form |Hire Maid </title>
        
        <link rel="manifest" href="site.webmanifest">
		<link rel="shortcut icon" type="image/x-icon" href="images/image 9.png" style="margin-top: 20px;margin-left:20px;">
    <link rel="stylesheet" href="style.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.2/css/all.min.css"/>
  </head>
  <body style="background: url(./images/bg007.jpg);">
    <div class="container">
        
      <div class="wrapper" style="max-width: 640px;
      background: transparent;
      min-height:456px;
      width: 140%;
      box-shadow:0px 0 23px -1px #000;
      margin: 0px;
      padding: 58px;
      border-radius: 10px;
      overflow: hidden;
      margin-left: -110px;
      height: 300px;
    margin-top: 96px;">
     
      <!-- <img src="login_image.jpg" style="padding: 50px 0;
      background-position: center center;
      position: relative;
      width: 558px;
      height: 259px;
      left:-57px;
      margin-top: -123px;
  "><br> -->
        <div class="title"><span>MAID LOGIN..!</span></div>
        <form action="maidlogin.php" method="post">
          <div class="row">
            <!-- <i class="fas fa-user"></i> -->
            <input type="text" name = "email" placeholder="Enter Your Email" required>
          </div>
          <div class="row">
            <!-- <i class="fas fa-user"></i> -->
            <input type="text" name = "password" placeholder="Your Password" required id="id_password">
            <i class="far fa-eye" id="togglePassword" style="margin-left: 370px; margin-top:-45px; cursor: pointer;"></i>  
        </div>
         
  
            <!-- <i class="fas fa-lock"></i> -->
         <!-- <p class="input-group" style=" margin: 7px -13px 31px -13px;">
          <input id="passInput" class="form-control" placeholder="Your Password" 
          name="password" type="password" size="80" aria-required="false"> 
          <span class="input-group-addon" role="button" title="veiw password" id="passBtn">
            <i class="fa fa-eye fa-fw" aria-hidden="true"></i>
          </span>
          </p> -->
       
          
          <div class="row button">
            <input type="submit" value="Login">
          </div>
          <div class="signup-link" style="color: white;">Not a member? <a href="maidsign.html">Signup now</a></div>
        </form>
      </div>
    </div>

  </body>
 <style>
  @import url('https://fonts.googleapis.com/css2?family=Poppins:wght@200;300;400;500;600;700&display=swap');
*{
margin: 0;
padding: 0;
box-sizing: border-box;
font-family: 'Poppins',sans-serif;
}

body{
background: #eee;
overflow: hidden;
}
::selection{
background: rgba(26,188,156,0.3);
}
.container{
max-width: 440px;
padding: 0 20px;
margin: 6px auto;
}

.wrapper .title{
height: 29px;
background: transparent;
border-radius: 5px 5px 0 0;
color: black;
font-size: 30px;
font-weight: 600;
display: flex;
align-items: center;
justify-content: center;
}
.wrapper form{
padding: 30px 25px 25px 25px;
}
.wrapper form .row{
height: 45px;
margin-bottom: 15px;
position: relative;
}
.wrapper form .row input{
height: 100%;
width: 100%;
outline: none;
padding-left: 18px;
border-radius: 15px;
border: 1px solid lightgrey;
font-size: 16px;
transition: all 0.3s ease;
}
form .row input:focus{
border-color: #16a085;
box-shadow: inset 0px 0px 2px 2px rgba(26,188,156,0.25);
}
form .row input::placeholder{
color: #999;
}
.wrapper form .row i{
position: absolute;
width: 47px;
height: 100%;
color: black;
font-size: 18px;
display: flex;
align-items: center;
justify-content: center;
}
.wrapper form .pass{
margin: -8px 0 20px 0;
}
.wrapper form .pass a{
color: #16a085;
font-size: 17px;
text-decoration: none;
}
.wrapper form .pass a:hover{
text-decoration: underline;
}
.wrapper form .button input{
color: white;
font-size: 20px;
font-weight: 500;
padding-left: 0px;
background: black;
border: 1px solid lightgrey;
cursor: pointer;
width: 50%;
  margin-left: 106px;
}
form .button input:hover{
background: white;
color: black;
}
.wrapper form .signup-link{
text-align: center;
margin-top: 20px;
font-size: 17px;
}
.wrapper form .signup-link a{
color: black;
text-decoration: none;
}
form .signup-link a:hover{
text-decoration: underline;
}
.input-group {
  position: relative;
  display: table;
  border-collapse: separate;
      height: 100%;
  width: 100%;
  outline: none;
  padding-left: 60px;
  border-radius: 15px;
  border: 1px solid lightgrey;
  font-size: 16px;
  transition: all 0.3s ease;
}
  </style>
    <script>
   const togglePassword = document.querySelector('#togglePassword');
  const password = document.querySelector('#id_password');

  togglePassword.addEventListener('click', function (e) {
    // toggle the type attribute
    const type = password.getAttribute('type') === 'password' ? 'text' : 'password';
    password.setAttribute('type', type);
    // toggle the eye slash icon
    this.classList.toggle('fa-eye-slash');
});
</script>
</html>