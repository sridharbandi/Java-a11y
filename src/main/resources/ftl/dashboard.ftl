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
                    <h6>Errors</h6>
                    <h1>${errorscount}</h1>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-sm-6 pills badge badge-warning">
                <div class="section">
                    <h6>Warnings</h6>
                    <h1>${warningscount}</h1>
                </div>
            </div>
            <div class="col-sm-6 pills badge badge-info">
                <div class="section ">
                    <h6>Notices</h6>
                    <h1>${noticescount}</h1>
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