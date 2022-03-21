<?php
session_start();
header("Access-Control-Allow-Origin: *");
?>

<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>View logs</title>

    <!-- Custom fonts for this template-->
    <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">

    <!-- Custom styles for this template-->
    <link href="css/sb-admin-2.min.css" rel="stylesheet">

</head>

<body id="page-top">

    <!-- Page Wrapper -->
    <div id="wrapper">

        <!-- Sidebar -->
        <ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">

            <!-- Sidebar - Brand -->
            <a class="sidebar-brand d-flex align-items-center justify-content-center" href="elements.php">
                <div class="sidebar-brand-icon">
                    <i class="fab fa-canadian-maple-leaf"></i>
                </div>
                <div class="sidebar-brand-text mx-3">Welcome!</div>
            </a>

            <!-- Divider -->
            <hr class="sidebar-divider my-0">

            <!-- Nav Item - Dashboard -->
            <li class="nav-item active">
                <a class="nav-link" href="index.php">
                    <i class="fas fa-columns"></i>
                    <span>Main Dashboard</span></a>
            </li>

            <!-- Divider -->
            <hr class="sidebar-divider">

            <!-- Heading -->
            <div class="sidebar-heading">
                Elements
            </div>

            <!-- Nav Item - Utilities Collapse Menu -->
            <li class="nav-item">
                <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseAlbums" aria-expanded="true" aria-controls="collapseAlbums">
                    <i class="fas fa-compact-disc"></i>
                    <span>Albums</span>
                </a>
                <div id="collapseAlbums" class="collapse" aria-labelledby="headingUtilities" data-parent="#accordionSidebar">
                    <div class="bg-white py-2 collapse-inner rounded">
                        <h6 class="collapse-header">Album Functions:</h6>
                        <a class="collapse-item" href="search-album.php">Search for Album</a>
                        <a class="collapse-item" href="view-album.php">View an Album</a>
                        <a class="collapse-item" href="create-album.php">Create an Album</a>
                        <a class="collapse-item" href="modify-album.php">Modify an Album</a>
                        <a class="collapse-item" href="delete-album.php">Delete an Album</a>
                        <a class="collapse-item" href="update-cover.php">Update Cover Image</a>
                        <a class="collapse-item" href="delete-cover.php">Delete Cover Image</a>
                    </div>
                </div>
            </li>
            <!-- Nav Item - Utilities Collapse Menu -->
            <li class="nav-item">
                <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseLogs" aria-expanded="true" aria-controls="collapseLogs">
                    <i class="fas fa-scroll"></i>
                    <span>Change Logs</span>
                </a>
                <div id="collapseLogs" class="collapse" aria-labelledby="headingUtilities" data-parent="#accordionSidebar">
                    <div class="bg-white py-2 collapse-inner rounded">
                        <h6 class="collapse-header">Logging Functions:</h6>
                        <a class="collapse-item" href="view-logs.php">View Change Logs</a>
                    </div>
                </div>
            </li>

        </ul>
        <!-- End of Sidebar -->

        <!-- Content Wrapper -->
        <div id="content-wrapper" class="d-flex flex-column">

            <!-- Main Content -->
            <div id="content">

                <!-- Topbar -->
                <nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">

                    <!-- Sidebar Toggle (Topbar) -->
                    <button id="sidebarToggleTop" class="btn btn-link d-md-none rounded-circle mr-3">
                        <i class="fa fa-bars"></i>
                    </button>

                </nav>
                <!-- End of Topbar -->

                <!-- Begin Page Content -->
                <div class="container-fluid">

                    <!-- Page Heading -->
                    <div class="d-sm-flex align-items-center justify-content-between mb-4">
                        <h1 class="h3 mb-0 text-gray-800">View Logs</h1>
                    </div>
                    <label for="from">From</label>
                    <input type="datetime-local" id="from" name="from" value="2022-03-21T19:30">
                    <br>
                    <label for="to">To</label>
                    <input type="datetime-local" id="to" name="to" value="2022-03-21T19:30">
                    <br>
                    <label for="type">Type</label>
                    <select name="type" id="type">
                        <option value="CREATE">CREATE</option>
                        <option value="UPDATE">UPDATE</option>
                        <option value="DELETE">DELETE</option>
                    </select>
                    <br>
                    <button class="btn btn-primary" type="button" id="submitBtn" onclick="soap()">Get Logs</button>

                </div>
                <!-- /.container-fluid -->

            </div>
            <!-- End of Main Content -->

            <!-- Footer -->
            <footer class="sticky-footer bg-white">
                <div class="container my-auto">
                    <div class="copyright text-center my-auto">
                        <span>Copyright &copy; SOEN487 A2 2022</span>
                    </div>
                </div>
            </footer>
            <!-- End of Footer -->

        </div>
        <!-- End of Content Wrapper -->

    </div>
    <!-- End of Page Wrapper -->

    <!-- Scroll to Top Button-->
    <a class="scroll-to-top rounded" href="#page-top">
        <i class="fas fa-angle-up"></i>
    </a>

    <!-- Bootstrap core JavaScript-->
    <script src="vendor/jquery/jquery.min.js"></script>
    <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

    <!-- Core plugin JavaScript-->
    <script src="vendor/jquery-easing/jquery.easing.min.js"></script>

    <!-- Custom scripts for all pages-->
    <script src="js/sb-admin-2.min.js"></script>

    <!-- Page level plugins -->
    <script src="vendor/chart.js/Chart.min.js"></script>

    <!-- Page level custom scripts -->
    <script src="js/demo/chart-area-demo.js"></script>
    <script src="js/demo/chart-pie-demo.js"></script>
    <script type="text/javascript">
        
        function soap() {
            
            var sr = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ser=\"http://Service.soap.example.com/\">" +
                "<soapenv:Header/>" +
                "<soapenv:Body>" +
                "<ser:getChangelog>" +
                "<!--Optional:-->" +
                "<arg0>?</arg0>" +
                "<!--Optional:-->" +
                "<arg1>?</arg1>" +
                "<!--Optional:-->" +
                "<arg2>?</arg2>" +
                "</ser:getChangelog>" +
                "</soapenv:Body>" +
                "</soapenv:Envelope>";
                $.ajax({
                    url: 'http://localhost:8086/A2/LogInterface/',
                    type: 'POST',
                    dataType: 'xml',
                    data: sr,
                    processData: false,
                    contentType: 'text/xml; charset=\"utf-8\"',
                    sucess: function(data) {
                        console.log(data);
                    }
                });
                /*
            var xmlhttp = new XMLHttpRequest();
            xmlhttp.open('POST', 'http://localhost:8086/A2/LogInterface', true);

            // build SOAP request
            


            xmlhttp.onreadystatechange = function() {
                if (xmlhttp.readyState == 4) {
                    if (xmlhttp.status == 200) {
                        alert(xmlhttp.responseText);
                        // alert('done. use firebug/console to see network response');
                    }
                }
            }
            // Send the POST request
            xmlhttp.setRequestHeader('Content-Type', 'text/xml');
            xmlhttp.send(sr);
            // send request
            // ...*/
        }
    </script>

</body>

</html>