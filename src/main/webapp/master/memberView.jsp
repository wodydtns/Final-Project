<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>관리자 페이지</title>

<link href="../css/master/assets/vendor/bootstrap4/css/bootstrap.min.css" rel="stylesheet">
<link href="../css/master/assets/css/master.css" rel="stylesheet">
<link href="../css/master/assets/vendor/chartsjs/Chart.min.css" rel="stylesheet">
<link href="../css/master/assets/vendor/flagiconcss3/css/flag-icon.min.css" rel="stylesheet">
</head>

<body>
    <div class="wrapper">
        <nav id="sidebar" class="active">
            <div class="sidebar-header">
                <img src="assets/img/bootstraper-logo.png" alt="bootraper logo" class="app-logo">
            </div>
            <ul class="list-unstyled components">
                <li>
                    <a href="dashboard.html"><i class="fas fa-home"></i> Dashboard</a>
                </li>
                <li>
                    <a href="forms.html"><i class="fas fa-file-alt"></i> Forms</a>
                </li>
                <li>
                    <a href="tables.html"><i class="fas fa-table"></i> Tables</a>
                </li>
                <li>
                    <a href="charts.html"><i class="fas fa-chart-bar"></i> Charts</a>
                </li>
                <li>
                    <a href="icons.html"><i class="fas fa-icons"></i> Icons</a>
                </li>
                <li>
                    <a href="#uielementsmenu" data-toggle="collapse" aria-expanded="false" class="dropdown-toggle no-caret-down"><i class="fas fa-layer-group"></i> UI Elements</a>
                    <ul class="collapse list-unstyled" id="uielementsmenu">
                        <li>
                            <a href="ui-buttons.html"> Buttons</a>
                        </li>
                        <li>
                            <a href="ui-badges.html"> Badges</a>
                        </li>
                        <li>
                            <a href="ui-cards.html"> Cards</a>
                        </li>
                        <li>
                            <a href="ui-alerts.html"> Alerts</a>
                        </li>
                        <li>
                            <a href="ui-tabs.html"> Tabs</a>
                        </li>
                        <li>
                            <a href="ui-date-time-picker.html"> DateTime Picker</a>
                        </li>
                        <li>
                            <a href="ui-calendar.html"> Calendar</a>
                        </li>
                        <li>
                            <a href="ui-content-editor.html"> Content Editor</a>
                        </li>
                    </ul>
                </li>
                <li>
                    <a href="#authmenu" data-toggle="collapse" aria-expanded="false" class="dropdown-toggle no-caret-down"><i class="fas fa-user-shield"></i> Authentication</a>
                    <ul class="collapse list-unstyled" id="authmenu">
                        <li>
                            <a href="login.html"><i class="fas fa-lock"></i> Login</a>
                        </li>
                        <li>
                            <a href="signup.html"><i class="fas fa-user-plus"></i> Signup</a>
                        </li>
                        <li>
                            <a href="forgot-password.html"><i class="fas fa-user-lock"></i> Forgot password</a>
                        </li>
                    </ul>
                </li>
                <li>
                    <a href="#pagesmenu" data-toggle="collapse" aria-expanded="false" class="dropdown-toggle no-caret-down"><i class="fas fa-copy"></i> Pages</a>
                    <ul class="collapse list-unstyled" id="pagesmenu">
                        <li>
                            <a href="blank.html"><i class="fas fa-file"></i> Blank page</a>
                        </li>
                        <li>
                            <a href="404.html"><i class="fas fa-info-circle"></i> 404 Error page</a>
                        </li>
                        <li>
                            <a href="500.html"><i class="fas fa-info-circle"></i> 500 Error page</a>
                        </li>
                    </ul>
                </li>
                <li>
                    <a href="users.html"><i class="fas fa-user-friends"></i>Users</a>
                </li>
                <li>
                    <a href="settings.html"><i class="fas fa-cog"></i>Settings</a>
                </li>
            </ul>
        </nav>

        <div id="body" class="active">
            <nav class="navbar navbar-expand-lg navbar-primary bg-primary">
                <button type="button" id="sidebarCollapse" class="btn btn-outline-light default-light-menu"><i class="fas fa-bars"></i><span></span></button>
            </nav>
            
            <!-- 컨텐츠 부분 상단메뉴바 -->
            <nav class="navbar navbar-expand-lg navbar-light bg-light">
				  <div class="collapse navbar-collapse" id="navbarNav">
				    <ul class="navbar-nav">
				      <li class="nav-item">
				        <a class="nav-link" href="#">Home </a>
				      </li>
				      <li class="nav-item">
				        <a class="nav-link" href="#">Features</a>
				      </li>
				      <li class="nav-item">
				        <a class="nav-link" href="#">Pricing</a>
				      </li>
				      <li class="nav-item">
				        <a class="nav-link" href="#">Disabled</a>
				      </li>
				    </ul>
				  </div>
			</nav>

          

    <script src="../css/master/assets/vendor/jquery3/jquery-3.4.1.min.js"></script>
    <script src="../css/master/assets/vendor/bootstrap4/js/bootstrap.bundle.min.js"></script>
    <script src="../css/master/assets/vendor/fontawesome5/js/solid.min.js"></script>
    <script src="../css/master/assets/vendor/fontawesome5/js/fontawesome.min.js"></script>
    <script src="../css/master/assets/vendor/chartsjs/Chart.min.js"></script>
    <script src="../css/master/assets/js/dashboard-charts.js"></script>
    <script src="../css/master/assets/js/script.js"></script>

</body>
</html>