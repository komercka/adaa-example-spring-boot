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
<nav class="navbar navbar-expand-lg navbar-dark bg-primary ">
    <a class="navbar-brand" href="#"><b>DEMO aplikace</b></a>

    <div class="collapse navbar-collapse">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
                <a class="nav-link text-light" href="transactions">Transakce</a>
            </li>
            <li class="nav-item">
                <a class="nav-link text-light" href="statements/list">Výpisy</a>
            </li>
        </ul>
        <div class="my-2 my-lg-0c text-light"><span>František Koudelka</span> <i class="fa fa-user-circle-o fa-lg" aria-hidden="true"></i></div>
    </div>
</nav>
<div class="container">
    <div class="row d-flex justify-content-center">
        <div class="col-lg-offset-2 col-lg-8">
            <header class="page-header text-center mt-2">
                <h3>Transakční historie</h3>
                <nav class="nav justify-content-between mt-4">
                    <span>Přehled plateb na účtu <span class="font-weight-bold">${iban}</span></br>
                    Aktuální zůstatek
                        <#if balance.creditDebitIndicator == "DEBIT">
                            <span class="text-danger">-${balance.amount.value} ${balance.amount.currency}</span>
                        <#else>
                            <span class="text-success">${balance.amount.value} ${balance.amount.currency}</span>
                        </#if>
                    </span>
                    <span><a href="statements/list">Download PDF statements</a></span>
                </nav>
            </header>
            <div class="accordion mt-3" id="accordionExample">
                <#list transactions as transaction>
                    <div class="card">
                        <div class="card-header" id="heading${transaction?index}" data-toggle="collapse"
                             data-target="#collapse${transaction?index}" aria-expanded="true"
                             aria-controls="collapse${transaction?index}">
                            <ul class="nav">
                                <li class="text-md-center" style="width: 20%;">
                                    <span>${transaction.valueDate}</span>
                                </li>
                                <li style="width: 5%;">
                                    <#if transaction.creditDebitIndicator == "CREDIT">
                                        <span><i class="fa fa-arrow-circle-o-left" aria-hidden="true"></i></span>
                                    <#elseif transaction.creditDebitIndicator == "DEBIT">
                                        <span><i class="fa fa-arrow-circle-o-right" aria-hidden="true"></i></span>
                                    </#if>
                                </li>
                                <li style="width: 50%;">
                                    <#if transaction.transactionType == "FEE">
                                        <span>Odměna</span>
                                    <#elseif transaction.counterParty?? && transaction.counterParty.name??>
                                        <span>${transaction.counterParty.name}</span>
                                    <#elseif transaction.counterParty?? && transaction.counterParty.accountNo?? && transaction.counterParty.bankCode??>
                                        <span>${transaction.counterParty.accountNo}/${transaction.counterParty.bankCode}</span>
                                    </#if>
                                </li>
                                <li class="text-md-right font-weight-bold" style="width: 25%;">
                                    <#if transaction.creditDebitIndicator == "DEBIT">
                                        <span class="text-danger">-${transaction.amount.value} ${transaction.amount.currency}</span>
                                    <#else>
                                        <span class="text-success">${transaction.amount.value} ${transaction.amount.currency}</span>
                                    </#if>
                                </li>
                            </ul>
                        </div>

                        <div id="collapse${transaction?index}" class="collapse"
                             aria-labelledby="heading${transaction?index}"
                             data-parent="#accordionExample">
                            <div class="card-body">
                                <#if transaction.transactionType == "FEE">
                                    <div class="ml-5">Odměna</div>
                                <#elseif transaction.creditDebitIndicator == "CREDIT" && transaction.counterParty??
                                    && transaction.counterParty.accountNo??  && transaction.counterParty.bankCode??>
                                    <div class="ml-5">Z účtu: ${transaction.counterParty.accountNo}
                                        /${transaction.counterParty.bankCode}</div>
                                <#elseif transaction.creditDebitIndicator == "DEBIT" && transaction.counterParty??
                                && transaction.counterParty.accountNo??  && transaction.counterParty.bankCode??>
                                    <div class="ml-5">Na účet: ${transaction.counterParty.accountNo}
                                        /${transaction.counterParty.bankCode}</div>
                                </#if>
                                <div class="mt-4">
                                    <ul class="nav nav-fill">
                                        <li class="nav-item">
                                            <span>
                                                Variabilní symbol<br/>
                                                <#if transaction.references?? && transaction.references.variable?has_content>
                                                    ${transaction.references.variable}
                                                <#else>
                                                    0
                                                </#if>
                                            </span>
                                        </li>
                                        <li class="nav-item">
                                            <span>Konstantní symbol<br/>
                                                <#if transaction.references?? && transaction.references.constant?has_content>
                                                    ${transaction.references.constant}
                                                <#else>
                                                    0
                                                </#if>
                                            </span>
                                        </li>
                                        <li class="nav-item">
                                            <span>Specifický symbol<br/>
                                                <#if transaction.references?? && transaction.references.specific?has_content>
                                                    ${transaction.references.specific}
                                                <#else>
                                                    0
                                                </#if>
                                            </span>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                </#list>
            </div>
        </div>
    </div>
</div>
</body>
</html>
