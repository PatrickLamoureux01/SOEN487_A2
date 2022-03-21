<?php
session_start();
/*
$url = 'http://localhost:8080/A2/albums/US1541/Gone/GoneDesc/1995/Nick/Jones/Nicky/blabla';

$ch = curl_init( $url );
curl_setopt( $ch, CURLOPT_POST, 1);
curl_setopt( $ch, CURLOPT_FOLLOWLOCATION, 1);
curl_setopt( $ch, CURLOPT_HEADER, 0);
curl_setopt( $ch, CURLOPT_RETURNTRANSFER, 1);

$response = curl_exec( $ch );
var_dump($response);
*/

$curlSession = curl_init();
curl_setopt($curlSession, CURLOPT_URL, 'http://localhost:8080/A2/albums');
curl_setopt($curlSession, CURLOPT_BINARYTRANSFER, true);
curl_setopt($curlSession, CURLOPT_RETURNTRANSFER, true);

$albums = json_decode(curl_exec($curlSession));
//var_dump($jsonData);
curl_close($curlSession);
?>

<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Search Album</title>

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
                        <h1 class="h3 mb-0 text-gray-800">Search Album</h1>
                    </div>

                    <div class="search">
                        Search Query <input type="text" name="searchBtn" id="searchBtn"></input>
                        <button class="btn btn-primary" type="button" onclick="search()">Search</button>
                    </div>

                    <div id="results" style="display:none;">
                        <p>The following albums have your search terms in them:</p>
                        <div id="res" name="res"></div>
                    </div>

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
    <script src="https://cdn.jsdelivr.net/npm/lodash@4.17.11/lodash.min.js"></script>

    <script>
        let albums = "";
        $(document).ready(function() {
            $.ajax({
                url: 'http://localhost:8080/A2/albums',
                success: function(data) {
                    albums = data;
                }
            });
        });

        function hasValueDeep(json, findValue) {
            const values = Object.values(json);
            let hasValue = values.includes(findValue);
            values.forEach(function(value) {
                if (typeof value === "object") {
                    hasValue = hasValue || hasValueDeep(value, findValue);
                }
            })
            return hasValue;
        }

        function search() {
            $('#res').empty();
            document.getElementById("results").style.display = "block";
            
            for (let i=0; i < albums.length; i++) {
                var hasVal = hasValueDeep(albums[i], $('#searchBtn').val());
                if (hasVal) {
                    document.getElementById("res").innerHTML += "ISRC: " + albums[i]['isrc'] +
                    "<br>" + 
                    "Album title: " + albums[i]['title'] +
                    "<br>" + 
                    "Album Artist: " + albums[i]['artist']['nickname'] +
                    "<br><br>";
                }
            }
        }
    </script>

</body>

</html>