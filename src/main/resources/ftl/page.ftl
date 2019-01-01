<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
          integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.3/css/all.css"
          integrity="sha384-UHRtZLI+pbxtHCWp1t77Bi1L4ZtiqrqD80Kn4Z8NTSRyMA2Fd33n5dQ8lWUE00s/" crossorigin="anonymous">
    <link href="https://fonts.googleapis.com/css?family=Roboto" rel="stylesheet">
    <link rel="stylesheet" href="css/main.css">
    <title>Report</title>
</head>
<body>
<#include 'heading.ftl'>
<div class="container">
    <br>
    <h3 class="text-white text-center">Page Statistics</h3>
    <#include 'pagemetrics.ftl'>
    <br>
    <h3 class="text-white text-center">Issues - Details</h3>
    <div class="row justify-content-start">
        <div class="col-xs-4 btncollapse">
            <button type="button" class="btn btn-danger" data-toggle="collapse" data-target="#collapseErrors" aria-expanded="true" aria-controls="collapseErrors" >
                Errors <span class="badge badge-light">${errorcount}</span>
            </button>
        </div>
        <div class="col-xs-4 btncollapse">
            <button type="button" class="btn btn-warning" data-toggle="collapse" data-target="#collapseWarnings" aria-expanded="false" aria-controls="collapseWarnings">
                Warnings <span class="badge badge-light">${warningcount}</span>
            </button>
        </div>
        <div class="col-xs-4 btncollapse">
            <button type="button" class="btn btn-info" data-toggle="collapse" data-target="#collapseNotices" aria-expanded="false" aria-controls="collapseNotices">
                Notices <span class="badge badge-light">${noticecount}</span>
            </button>
        </div>
    </div>
    <div class="row" id="issues">
        <div class="col collapse show btncollapse" data-parent="#issues" id="collapseErrors">
            <#include 'errors.ftl'>
        </div>
        <div class="col collapse btncollapse" data-parent="#issues" id="collapseWarnings">
            <#include 'warnings.ftl'>
        </div>
        <div class="col collapse btncollapse" data-parent="#issues" id="collapseNotices">
            <#include 'notices.ftl'>
        </div>
    </div>
    <#include 'footer.ftl'>
</div>

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
        integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js"
        integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"
        integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k"
        crossorigin="anonymous"></script>
</body>
</html>