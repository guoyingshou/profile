<#import "spring.ftl" as spring />
<#import "userGadgets.ftl" as userGadgets />
<#import "siteGadgets.ftl" as site />

<#assign myscripts=["/ckeditor/ckeditor.js"] in site>
<#assign title= "about" in site>

<@site.layout>
    <@site.siteLogo />

    <div id="page-menu-wrapper">
        <div id="page-menu">
            <@site.aboutMenu />
        </div>
    </div>

    <div id="page-main-wrapper">
        <div id="page-main">
            <div id="main-content">
                <ul class="praise">
                    <#if abouts??>
                    <#list abouts as about>
                    <li>${about.content}</li>
                    </#list>
                    </#if>
                </ul>

                <#if viewerAccount?? && viewerAccount.hasRole('ROLE_VIP')>
                <a href="<@spring.url '/about/_create' />">add</a>
                </#if>
 
            </div>

            <div id="main-sidebar">
                <@spring.message 'siteFeature' />
            </div>

        </div>
    </div>
</@site.layout>

