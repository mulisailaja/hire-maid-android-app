<div class="topbar">
                  <nav class="navbar navbar-expand-lg navbar-light">
                     <div class="full" style="background-color: #10477c;">
                        <button type="button" id="sidebarCollapse" class="sidebar_toggle" style="background: mediumvioletred;"><i class="fa fa-bars"></i></button>
                        <div class="logo_section">
                           <a href="dashboard.php"><h3 style="color: white;padding-top: 20px;padding-left: 10px;">Maid</h3></a>
                        </div>
                        <div class="right_topbar">
                           <div class="icon_info">
                             
                              <ul class="user_profile_dd">
                                 <li style="background: mediumvioletred;"><?php
if (isset($_SESSION["name"])) {
   $name = $_SESSION["name"];
   ?>
   <a class="dropdown-toggle" data-toggle="dropdown">
       <img class="img-responsive rounded-circle" src="images/layout_img/user_img.jpg" alt="#" />
       <span class="name_user"><?php echo $name; ?></span>
   </a>
<?php
}
?>

                                    <div class="dropdown-menu">
                                       <a class="dropdown-item" href="profile.php">My Profile</a>
                                       <a class="dropdown-item" href="change-password.php">Settings</a>
                                       <a class="dropdown-item" href="logout.php"><span>Log Out</span> <i class="fa fa-sign-out"></i></a>
                                    </div>
                                 </li>
                              </ul>
                           </div>
                        </div>
                     </div>
                  </nav>
               </div>