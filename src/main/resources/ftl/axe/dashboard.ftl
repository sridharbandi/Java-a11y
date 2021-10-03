<#assign urlcount =list?size>
<#assign allcriticalcount=0 allseriouscount=0 allmoderatecount=0 allminorcount=0>
<#list list as item>
    <#assign allarray = item.violations+item.incomplete>
    <#assign allcriticals =allarray?filter(x -> x.impact == 'critical')>
    <#assign allseriouss =allarray?filter(x -> x.impact == 'serious')>
    <#assign allmoderates =allarray?filter(x -> x.impact == 'moderate')>
    <#assign allminors =allarray?filter(x -> x.impact == 'minor')>
    <#list allcriticals as criticalitem>
        <#assign allcriticalcount += criticalitem.nodes?size>
    </#list>
    <#list allseriouss as seriousitem>
        <#assign allseriouscount += seriousitem.nodes?size>
    </#list>
    <#list allmoderates as moderateitem>
        <#assign allmoderatecount += moderateitem.nodes?size>
    </#list>
    <#list allminors as minoritem>
        <#assign allminorcount += minoritem.nodes?size>
    </#list>
</#list>
<div class="row">
    <div class="col-sm-6">
        <div class="row">
            <div class="col-sm-6 pills badge badge-primary">
                <div class="section">
                    <h6>Number of URL's</h6>
                    <h1>${urlcount}</h1>
                </div>
            </div>
            <div class="col-sm-6 pills badge badge-danger">
                <div class="section">
                    <h6>Critical</h6>
                    <h1>${allcriticalcount}</h1>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-sm-4 pills badge badge-warning">
                <div class="section">
                    <h6>Serious</h6>
                    <h1>${allseriouscount}</h1>
                </div>
            </div>
            <div class="col-sm-4 pills badge badge-info">
                <div class="section ">
                    <h6>Moderate</h6>
                    <h1>${allmoderatecount}</h1>
                </div>
            </div>
            <div class="col-sm-4 pills badge badge-light">
                <div class="section ">
                    <h6>Minor</h6>
                    <h1>${allminorcount}</h1>
                </div>
            </div>
        </div>
    </div>
    <div class="col-sm-6">
        <div class="row justify-content-md-center">
            <div class="col-sm">
                <canvas id="myChart" width="400" height="250"></canvas>
            </div>
        </div>
    </div>
</div>