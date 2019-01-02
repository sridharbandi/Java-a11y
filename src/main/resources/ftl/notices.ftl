<div class="card bg-info">
    <#list notices as notice>
    <div class="alert alert-info btncollapse" role="alert">
        <div><strong>Code(s) : </strong>${notice.issueCode}</div>
        <hr/>
        <div><strong>Technique(s) : </strong>
            <#list notice.issueTechniques as technique>
            <a href="${technique}" class="alert-link">${technique?split('/')[5]}</a>,
            </#list>
        </div>
        <hr/>
        <div><strong>Tag : </strong> ${notice.issueTag}</div>
        <hr/>
        <div><strong>Message : </strong> ${notice.issueMsg}</div>
        <hr/>
        <div><strong>Element : </strong> ${notice.issueElement?html}</div>
    </div>
</#list>
</div>