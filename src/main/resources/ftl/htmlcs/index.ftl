<!DOCTYPE html>
<html lang="en">
<#include '../head.ftl'>
<body>
<#include '../heading.ftl'>
<div class="container">
    <br>
    <h3 class="text-white text-center"><i class="fas fa-chart-pie text-info"></i> Dashboard</h3>
    <#include 'dashboard.ftl'>
    <br>
    <h3 class="text-white text-center"><i class="fas fa-file text-info"></i> Page Statistics</h3>
    <div class="row">
        <#include 'pagestats.ftl'>
    </div>
    <#include '../footer.ftl'>
</div>
<#include '../scripts.ftl'>
<script>
    <#include 'chart.ftl'>
</script>
</body>
</html>