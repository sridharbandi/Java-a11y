<div class="card bg-warning">
    <#list warnings as warning>
    <div class="alert alert-warning btncollapse" role="alert">
        <div><strong>Code(s) : </strong>${warning.issueCode}</div>
        <hr/>
        <div><strong>Technique(s) : </strong>
            <#list warning.issueTechniques as technique>
            <a href="${technique}" class="alert-link">${technique?split('/')[5]}</a>,
            </#list>
        </div>
        <hr/>
        <div><strong>Tag : </strong> ${warning.issueTag}</div>
        <hr/>
        <div><strong>Message : </strong> ${warning.issueMsg}</div>
        <hr/>
        <div><strong>Element : </strong> ${warning.issueElement?html}</div>
    </div>
</#list>
</div>