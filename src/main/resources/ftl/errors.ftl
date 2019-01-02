<div class="card bg-danger">
    <#list errors as error>
    <div class="alert alert-danger btncollapse" role="alert">
        <div><strong>Code(s) : </strong>${error.issueCode}</div>
        <hr/>
        <div><strong>Technique(s) : </strong>
            <#list error.issueTechniques as technique>
            <a href="#" class="alert-link">${technique}</a>,
            </#list>
        </div>
        <hr/>
        <div><strong>Tag : </strong> ${error.issueTag}</div>
        <hr/>
        <div><strong>Message : </strong> ${error.issueMsg}</div>
        <hr/>
        <div><strong>Element : </strong> ${error.issueElement}</div>
    </div>
</#list>
</div>