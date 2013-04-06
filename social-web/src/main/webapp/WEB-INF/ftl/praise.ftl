<#import "spring.ftl" as spring />
<#import "userGadgets.ftl" as userGadgets />
<#import "siteGadgets.ftl" as site />

<#assign myscripts=["/ckeditor/ckeditor.js"] in site>
<#assign sec=JspTaglibs["http://www.springframework.org/security/tags"] />
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
                <ul>
                    <#if abouts??>
                    <#list abouts as about>
                    <li>${about.content}</li>
                    </#list>
                    </#if>
                </ul>

                <@sec.authorize access="hasRole('ROLE_VIP')">
                <a href="<@spring.url '/about/_create' />">add</a>
                </@sec.authorize>
 
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
</@site.layout>

