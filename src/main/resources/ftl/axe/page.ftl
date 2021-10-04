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
    <#assign results = { "violations":violations, "review":incomplete}>
    <nav>
        <div class="nav nav-tabs nav-fill" id="nav-tab" role="tablist">
            <#list results?keys as key>
                <a class="nav-item nav-link <#if key?is_first>active</#if>" id="${key}-tab" data-toggle="tab"
                   href="#${key}" role="tab"
                   aria-controls="${key}" aria-selected="<#if key?is_first>true<#else>false</#if>">${key?cap_first}
                    &nbsp;<span
                            class="badge badge-light">
                        <#assign nodes =results[key]?map(x -> x.nodes?size)>
                        <#assign total_count=0>
                        <#list nodes as node>
                            <#assign total_count += node>
                        </#list>
                        ${total_count}
                    </span></a>
            </#list>
        </div>
    </nav>
    <div class="tab-content" id="nav-tabContent">
        <#assign colorMap = { "critical":"danger", "serious":"warning", "moderate":"info", "minor":"light" }>
        <#list results?keys as key>
            <div class="tab-pane fade <#if key?is_first>show active</#if>" id="${key}" role="tabpanel"
                 aria-labelledby="${key}-tab">
                <nav>
                    <div class="nav nav-tabs" id="nav-tab${key}" role="tablist">
                        <#list colorMap?keys as status>
                            <a class="nav-item nav-link text-${colorMap['${status}']} <#if status?is_first>active</#if>"
                               id="${key}${status}-tab"
                               data-toggle="tab"
                               href="#${key}${status}" role="tab"
                               aria-controls="${key}${status}"
                               aria-selected="<#if status?is_first>true<#else>false</#if>">${status?cap_first}
                                &nbsp;<span
                                        class="badge badge-${colorMap['${status}']}">
                               <#assign type=results[key]?filter(x -> x.impact == '${status}') count=0>
                                    <#list type as item>
                                        <#assign count += item.nodes?size>
                                    </#list>
                                    ${count}
                            </span></a>
                        </#list>
                    </div>
                </nav>
                <div class="tab-content" id="nav-tab${key}Content">
                    <#list ['critical', 'serious', 'moderate', 'minor'] as issueType>
                        <div class="tab-pane fade <#if issueType?is_first>show active</#if>" id="${key}${issueType}"
                             role="tabpanel"
                             aria-labelledby="${key}${issueType}-tab">
                            <#list results[key]?filter(x -> x.impact == issueType) as issues>
                                <div class="card bg-${colorMap['${issueType}']}">
                                    <div class="alert alert-${colorMap['${issueType}']} btncollapse" role="alert">
                                        <div><strong>Description : </strong>${issues.description}</div>
                                        <div><strong>Help : </strong>${issues.help}</div>
                                        <div><strong>Help Url : </strong>
                                            <button type="button" class="btn btn-link"
                                                    onclick=" window.open('${issues.helpUrl}','_blank')">${issues.helpUrl}</button>
                                        </div>
                                        <div><strong>Rule Id : </strong>${issues.id}</div>
                                        <div><strong>Tags
                                                : </strong><#list issues.tags as tag> <span
                                            class="badge badge-pill badge-${colorMap['${issueType}']}">${tag}</span></#list>
                                        </div>
                                        <div><strong>Nodes : </strong></div>
                                        <#list issues.nodes as node>
                                            <div class="card bg-${colorMap['${issueType}']} issuenode">
                                                <div class="alert alert-${colorMap['${issueType}']} btncollapse"
                                                     role="alert">
                                                    <div><strong>Element : </strong>${node.html}</div>
                                                    <hr/>
                                                    <div><strong>Element Location
                                                            : </strong><#list node.target as target> <span
                                                        class="badge badge-pill badge-${colorMap['${issueType}']}">${target}</span></#list>
                                                    </div>
                                                    <hr/>
                                                    <#assign id ='a'+random["java.util.UUID"].randomUUID() >
                                                    <div><strong>How to solve? : </strong>
                                                        <button type="button" class="btn btn-link" data-toggle="modal"
                                                                data-target="#${id}">
                                                            Hint(s)
                                                        </button>
                                                    </div>
                                                    <div class="modal fade" id="${id}" tabindex="-1"
                                                         role="dialog" aria-labelledby="${id}Label"
                                                         aria-hidden="true" style="display: none">
                                                        <div class="modal-dialog modal-lg" role="document">
                                                            <div class="modal-content">
                                                                <div class="modal-body">
                                                                    <#assign types = {"Fix the following":node.all+node.none, "Fix at least
                                                                        one (1) of these issues:":node.any}>
                                                                    <#list types?keys as data>
                                                                        <#assign allData = types[data]>
                                                                        <#if allData?size != 0>
                                                                            <h6 class="text-center">${data}</h6>
                                                                            <table class="table table-sm">
                                                                                <thead>
                                                                                <tr>
                                                                                    <th scope="col">Id/Rule Id</th>
                                                                                    <th scope="col">Hint</th>
                                                                                    <th scope="col">Impact</th>
                                                                                </tr>
                                                                                </thead>
                                                                                <tbody>
                                                                                <#list allData as z>
                                                                                    <tr>
                                                                                        <td>${z.id}</td>
                                                                                        <td>${z.message}</td>
                                                                                        <td>${z.impact}</td>
                                                                                    </tr>
                                                                                </#list>
                                                                                </tbody>
                                                                            </table>
                                                                        </#if>
                                                                    </#list>
                                                                </div>
                                                                <div class="modal-footer">
                                                                    <button type="button" class="btn btn-dark"
                                                                            data-dismiss="modal">Close
                                                                    </button>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>

                                                </div>
                                            </div>
                                        </#list>
                                    </div>
                                </div>
                            <#else>
                                <p class="h3 text-center text-${colorMap['${issueType}']}">No ${issueType} issues
                                    found</p>
                            </#list>
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