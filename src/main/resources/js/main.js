var ctx = document.getElementById("myChart").getContext('2d');
var barChartData = {
    labels: ['January', 'February', 'March', 'April', 'May', 'June', 'July',],
    datasets: [{
        label: 'Errors',
        backgroundColor: '#dc3545',
        data: [18, 46, 49, 20, 56, 88, 66]
    }, {
        label: 'Warnings',
        backgroundColor: '#ffc107',
        data: [66, 16, 39, 70, 96, 28, 89]
    }, {
        label: 'Notices',
        backgroundColor: '#17a2b8',
        data: [18, 96, 19, 80, 36, 68, 16]
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
