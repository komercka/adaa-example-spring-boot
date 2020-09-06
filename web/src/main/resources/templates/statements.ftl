<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">

    <title>ADAA API client reference example</title>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
            integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
            crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet"
          integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-dark bg-primary ">
    <a class="navbar-brand" href="#"><b>DEMO aplikace</b></a>

    <div class="collapse navbar-collapse">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link text-light" href="transactions">Transakce</a>
            </li>
            <li class="nav-item active">
                <a class="nav-link text-light" href="statements/list">Výpisy</a>
            </li>
        </ul>
        <div class="my-2 my-lg-0c text-light"><span>František Koudelka</span> <i class="fa fa-user-circle-o fa-lg" aria-hidden="true"></i></div>
    </div>
</nav>
<div class="container">
    <div class="row d-flex justify-content-center">
        <div class="col-lg-offset-2 col-lg-8">
            <header class="page-header text-center mt-4">
                <h3>Stahování výpisů z účtu</h3>
            </header>
            <div class="mt-4">
                <#list statements as statement>
                    <table class="table text-center">
                        <thead>
                            <tr>
                                <th scope="col">ID</th>
                                <th scope="col">Issued date</th>
                                <th scope="col">Number of pages</th>
                                <th scope="col"></th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>${statement.statementId}</td>
                                <td>${statement.issued}</td>
                                <td>${statement.pagesCount}</td>
                                <td><a href="pdf?id=${statement.statementId?string["0"]}">Download</a></td>
                            </tr>
                        </tbody>
                    </table>
                </#list>
            </div>
        </div>
    </div>
</div>
</body>
</html>
