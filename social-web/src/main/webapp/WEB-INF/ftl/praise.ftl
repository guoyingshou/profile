<#import "spring.ftl" as spring />
<#import "userGadgets.ftl" as userGadgets />
<#import "commonGadgets.ftl" as commonGadgets />

<#assign myscripts=["/ckeditor/ckeditor.js"] in commonGadgets>
<#assign title= "about" in commonGadgets>

<@commonGadgets.layout>
    <div id="page-logo-wrapper">
        <div id="page-logo">
            <@userGadgets.homeLogo />
        </div>
    </div>

    <div id="page-menu-wrapper">
        <div id="page-menu">
            <@userGadgets.aboutMenu />
        </div>
    </div>

    <div id="page-main-wrapper">
        <div id="page-main">
            <div id="main-content">
                <ul>
                    <#if abouts??>
                    <#list abouts as about>
                    <li>${about.content}</li>
                    </#list>
                    </#if>
                </ul>

                <#if viewerAccount??>
                <a href="<@spring.url '/about/_create' />">add</a>
                </#if>
            </div>

            <div id="main-sidebar">
                <h4>Main Features</h4>
                <ul>
                    <li>
                     create topics
                    </li>
                    <li>
                     host or join a group
                    </li>
                    <li>
                     make new friends
                    </li>
                </ul>
            </div>

        </div>
    </div>
</@commonGadgets.layout>

