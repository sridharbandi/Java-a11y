var ctx = document.getElementById("myChart").getContext('2d');
<#assign urls=list?map(x -> x.title)>
<#assign arraycriticals=[] arrayseriouss=[] arraymoderates=[] arrayminors=[]>
<#list list as item>
        <#assign chartcriticalcount=0 chartseriouscount=0 chartmoderatecount=0 chartminorcount=0>
        <#assign chartarray = item.violations+item.incomplete>
        <#assign chartcriticals = chartarray?filter(x -> x.impact == 'critical')>
        <#assign chartseriouss = chartarray?filter(x -> x.impact == 'serious')>
        <#assign chartmoderates = chartarray?filter(x -> x.impact == 'moderate')>
        <#assign chartminors = chartarray?filter(x -> x.impact == 'minor')>
        <#list chartcriticals as criticalitem>
                <#assign chartcriticalcount += criticalitem.nodes?size>
        </#list>
        <#list chartseriouss as seriousitem>
                <#assign chartseriouscount += seriousitem.nodes?size>
        </#list>
        <#list chartmoderates as moderateitem>
                <#assign chartmoderatecount += moderateitem.nodes?size>
        </#list>
        <#list chartminors as minoritem>
                <#assign chartminorcount += minoritem.nodes?size>
        </#list>
        <#assign arraycriticals += [chartcriticalcount] arrayseriouss += [chartseriouscount] arraymoderates += [chartmoderatecount] arrayminors += [chartminorcount]/>
</#list>
var barChartData = {
        labels: [<#list urls as url > '${ url }',</#list >],
        datasets: [{
                label: 'Critical',
                backgroundColor: '#dc3545',
                data: [<#list arraycriticals as issue > ${issue},</#list >]
        }, {
                label: 'Serious',
                backgroundColor: '#ffc107',
                data: [<#list arrayseriouss as issue > ${issue},</#list >]
        }, {
                label: 'Moderate',
                backgroundColor: '#17a2b8',
                data: [<#list arraymoderates as issue > ${issue},</#list >]
        }, {
                label: 'Minor',
                backgroundColor: '#f8f9fa',
                data: [<#list arrayminors as issue > ${issue},</#list >]
}]

};
var myChart = new Chart(ctx, {
        type: 'bar',
        data: barChartData,
        options: {
                tooltips: {
                        mode: 'index',
                        intersect: false,
                        callbacks: {
                                title: function (tooltipItems, data) {
                                        var idx = tooltipItems[0].index;
                                        return data.labels[idx];
                                }
                        }
                },
                scales: {
                        xAxes: [{
                                ticks: {
                                        callback: function (value) {
                                                return value.substr(0, 10);
                                        },
                                        beginAtZero: true
                                },
                                stacked: true
                        }],
                        yAxes: [{
                                stacked: true
                        }]
                }
        }
});
