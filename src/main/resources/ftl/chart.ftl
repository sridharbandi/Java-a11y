var ctx = document.getElementById("myChart").getContext('2d');
var barChartData = {
        labels: [<#list urls as url > '${ url }',</#list >],
        datasets: [{
                label: 'Errors',
                backgroundColor: '#dc3545',
                data: [<#list errors as error > ${ error },</#list >]
        }, {
                label: 'Warnings',
                backgroundColor: '#ffc107',
                data: [<#list warnings as warning > ${ warning },</#list >]
        }, {
                label: 'Notices',
                backgroundColor: '#17a2b8',
                data: [<#list notices as notice > ${ notice },</#list >]
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
