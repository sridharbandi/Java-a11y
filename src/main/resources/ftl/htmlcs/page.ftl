<#ftl output_format="HTML">
<!DOCTYPE html>
<html lang="en">
<#include '../head.ftl'>
<body>
<#include '../heading.ftl'>
<div class="container">
    <br>
    <h3 class="text-white text-center">Page Statistics</h3>
    <#include 'pagemetrics.ftl'>
    <br>
    <h3 class="text-white text-center">Issues - Details</h3>

    <#assign colorMap = { "errors":"danger", "warnings":"warning", "notices":"info" }>
    <#assign issues = { "errors":"${errors}", "warnings":"${warnings}", "notices":"${notices}" }>
    <nav>
        <div class="nav nav-tabs" id="nav-tabissues" role="tablist">
            <#list issues?keys as issueType>
                <a class="nav-item nav-link text-${colorMap['${issueType}']} <#if issueType?is_first>show active</#if>"
                   id="${issueType}-tab" data-toggle="tab" href="#${issueType}"
                   role="tab" aria-controls="${issueType}" aria-selected="true">${issueType?cap_first}&nbsp;<span
                            class="badge badge-${colorMap['${issueType}']}"> ${issues[issueType]}</span></a>
            </#list>
        </div>
    </nav>
    <div class="tab-content" id="nav-tabissuesContent">
        <#list issues?keys as issueType>
            <div class="tab-pane fade <#if issueType?is_first>show active</#if>" id="${issueType}" role="tabpanel"
                 aria-labelledby="${issueType}-tab">
                <div class="card bg-${colorMap['${issueType}']}">
                    <#list results?filter(x -> x.type == issueType?index+1) as x>
                        <div class="alert alert-${colorMap['${issueType}']} btncollapse" role="alert">
                            <div><strong>Code(s) : </strong>${x.code}</div>
                            <hr/>
                            <#if standard == 'Section508'>
                                <div><strong>Section : </strong>${x.techniques[0]}</div>
                            <#else>
                                <div><strong>Technique(s) : </strong>
                                    <#list x.techniques as technique>
                                        <a href="${technique}"
                                           target="_blank" >${technique?split('/')[7]}</a><#sep>,</#sep>
                                    </#list>
                                </div>
                            </#if>
                            <hr/>
                            <div><strong>Tag : </strong> <span
                                        class="badge badge-pill badge-${colorMap['${issueType}']}">${x.tag}</span></div>
                            <hr/>
                            <div><strong>Message : </strong> ${x.msg}</div>
                            <#if x.element?has_content>
                                <hr/>
                                <div><strong>Element : </strong>${x.element}</div></#if>
                        </div>
                    </#list>
                </div>
            </div>
        </#list>
    </div>
    <#include '../footer.ftl'>
</div>

<#include '../scripts.ftl'>
</body>
</html>