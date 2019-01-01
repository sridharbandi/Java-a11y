<div class="card bg-info">
    <#list issues as issue>
    <div class="alert alert-info btncollapse" role="alert">
        <div><strong>Code(s) : </strong>${issue.issueCode}</div>
        <hr/>
        <div><strong>Technique(s) : </strong>
            <#list issue.issueTechniques as technique>
            <a href="#" class="alert-link">${technique}</a>,
            </#list>
        </div>
        <hr/>
        <div><strong>Tag : </strong> ${issue.issueTag}</div>
        <hr/>
        <div><strong>Message : </strong> ${issue.issueMsg}</div>
        <hr/>
        <div><strong>Element : </strong> ${issue.issueElement}</div>
    </div>
</#list>
</div>