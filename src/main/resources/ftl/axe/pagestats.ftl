<#list list as issue>
    <div class="col-sm-6 pillspage badge badge-secondary">
        <h5>${issue.title?truncate(50, '...')}</h5>
        <h6 class="text-truncate">${issue.url}</h6>
        <div class="time">
            <span class="text-warning"><i class="fas fa-clock"></i> ${issue.date}</span>
        </div>
        <div class="row justify-content-center">
            <div class="col-4">
                <span class="specs">ENGINE : </span>
                <span class="badge badge-pill badge-success">${issue.testRunner.name}</span>
            </div>

            <div class="col-4">
                <span class="specs">BROWSER SIZE : </span>
                <span class="badge  badge-pill badge-success">${issue.dimension}</span>
            </div>
        </div>
        <div class="row justify-content-center pagespechold">
            <#assign allcriticalcount=0 allseriouscount=0 allmoderatecount=0 allminorcount=0>
            <#assign allarray = issue.violations+issue.incomplete>
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
            <div class="col-2 pagespec badge badge-danger">${allcriticalcount}</div>
            <div class="col-2 pagespec badge badge-warning">${allseriouscount}</div>
            <div class="col-2 pagespec badge badge-info">${allmoderatecount}</div>
            <div class="col-2 pagespec badge badge-light">${allminorcount}</div>
        </div>
        <div class="row">
            <div class="col viewreport">
                <a href="${issue.id}.html" target="_blank" class="btn btn-outline-light btn-sm" role="button">View Report</a>
            </div>
        </div>
    </div>
</#list>