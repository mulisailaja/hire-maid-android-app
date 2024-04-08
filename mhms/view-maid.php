<?php
session_start();
error_reporting(0);
include('includes/dbconnection.php');

if(isset($_GET['assign_to'])){
   $assign = $_GET['assign_to'];
}

  ?>
<!DOCTYPE html>
<html lang="en">
   <head>
      
      <title>Maid Hiring Management System || Manage Maid</title>
   
      <link rel="stylesheet" href="css/bootstrap.min.css" />
      <!-- site css -->
      <link rel="stylesheet" href="style.css" />
      <!-- responsive css -->
      <link rel="stylesheet" href="css/responsive.css" />
      <!-- color css -->
      <link rel="stylesheet" href="css/colors.css" />
      <!-- select bootstrap -->
      <link rel="stylesheet" href="css/bootstrap-select.css" />
      <!-- scrollbar css -->
      <link rel="stylesheet" href="css/perfect-scrollbar.css" />
      <!-- custom css -->
      <link rel="stylesheet" href="css/custom.css" />
      <!-- calendar file css -->
      <link rel="stylesheet" href="js/semantic.min.css" />
      <!-- fancy box js -->
      <link rel="stylesheet" href="css/jquery.fancybox.css" />

      <style>
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
        </style>
      
   </head>
   <body class="inner_page tables_page">
      <div class="full_container">
         <div class="inner_container">
            <!-- Sidebar  -->
         
            <!-- right content -->
            <div id="content">
               <!-- topbar -->
              
               <!-- end topbar -->
               <!-- dashboard inner -->
               <table id="example" class="display" cellspacing="0" style="margin-top: 45px;
    font-size: 16px;text-align: center;" width="100%">
        <thead style="background-color: grey;">
                                          <tr>
                                             <th>S.No</th>
                                             <th>Proficient</th>
                                             <th>Name</th>
                                             <th>Email</th>
                                             <th>Contact Number</th>
                                             <th>Experience</th>
                                             <th>Location</th>
                                             <th>Willing to Work</th>
                                             <th>preferredLocations</th>
                                             <th>Date of Registration</th>
                                             
                                          </tr>
                                       </thead>
                                       <tbody>
                                          <?php
$sql="SELECT * from tblmaid where Name='$assign'";
$query = $dbh -> prepare($sql);
$query->execute();
$results=$query->fetchAll(PDO::FETCH_OBJ);

$cnt=1;
if($query->rowCount() > 0)
{
foreach($results as $row)
{               ?> 
                                          <tr>
                                              
                                             <td><?php echo htmlentities($cnt);?></td>
                                             
                                             <td><?php  echo htmlentities($row->CatID);?></td>
                                             <td><?php  echo htmlentities($row->Name);?></td>
                                             <td><?php  echo htmlentities($row->Email);?></td>
                                             <td><?php  echo htmlentities($row->ContactNumber);?></td>
                                             <td><?php  echo htmlentities($row->Experience);?></td>
                                             <td><?php  echo htmlentities($row->Address);?></td>
                                             <td><?php  echo htmlentities($row->willingToWork);?></td>
                                             <td><?php  echo htmlentities($row->preferredLocations);?></td>
                                             <td><?php  echo htmlentities($row->RegDate);?></td>
                                          </tr><?php $cnt=$cnt+1;}} ?>
                                       </tbody>
                                    </table>
                                 
              
                  <!-- footer -->
              
               </div>
               <!-- end dashboard inner -->
            </div>
         </div>
         <!-- model popup -->
       
      </div>
      <!-- jQuery -->
      <script src="js/jquery.min.js"></script>
      <script src="js/popper.min.js"></script>
      <script src="js/bootstrap.min.js"></script>
      <!-- wow animation -->
      <script src="js/animate.js"></script>
      <!-- select country -->
      <script src="js/bootstrap-select.js"></script>
      <!-- owl carousel -->
      <script src="js/owl.carousel.js"></script> 
      <!-- chart js -->
      <script src="js/Chart.min.js"></script>
      <script src="js/Chart.bundle.min.js"></script>
      <script src="js/utils.js"></script>
      <script src="js/analyser.js"></script>
      <!-- nice scrollbar -->
      <script src="js/perfect-scrollbar.min.js"></script>
      <script>
         var ps = new PerfectScrollbar('#sidebar');
      </script>
      <!-- fancy box js -->
      <script src="js/jquery-3.3.1.min.js"></script>
      <script src="js/jquery.fancybox.min.js"></script>
      <!-- custom js -->
      <script src="js/custom.js"></script>
      <!-- calendar file css -->    
      <script src="js/semantic.min.js"></script>
   </body>
</html>