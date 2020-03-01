<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">

    <title>ADAA API client reference example</title>

    <script src="/js/jquery.min.js"></script>
    <script src="/js/bootstrap.min.js"></script>
    <link href="/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet"
          integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">

</head>
<body>
<nav class="navbar navbar-dark bg-primary justify-content-between">
    <span class="navbar-brand"><b>DEMO aplikace</b></span>
    <div class="text-light"><span>František Koudelka</span> <i class="fa fa-user-circle-o fa-lg" aria-hidden="true"></i>
    </div>
</nav>
<div class="container w-50">
    <div class="d-flex justify-content-center mt-4 p-3 text-center border">
        <form id="registerForm">
            <div>
                <h3>Registrace aplikace</h3>
            </div>
            <p class="text-left">
                Pro využívání produktu Přímý přístup k účtu potřebujeme abyste v první kroku autorizoval svoji aplikaci, že je to právě ona, následně tam budeme zasílat vaše data. Tato registrace je jednorázová. Děkujeme.
            </p>
            <div class="form-group">
                <input type="text" class="form-control" id="softwareNameInput" placeholder="Pojmenování aplikace">
            </div>
            <button type="submit" class="btn btn-primary">Pokračovat</button>
        </form>
    </div>
</div>
<script>
    window.onload = function () {
        document.getElementById("registerForm").onsubmit = function () {
            const softwareName = document.getElementById('softwareNameInput').value;
            // this is sandbox version of software registration, so there is no any validations of the software name
            if (isNotBlank(softwareName)) {
                window.location.replace(window.location.origin + "/register/software-statement?softwareName=" + softwareName)
            } else {
                window.location.replace("error");
            }

            return false;
        };

        function isNotBlank(str) {
            return str && !(/^\s*$/.test(str));
        }
    }
</script>
</body>
</html>
