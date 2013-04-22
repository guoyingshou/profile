<#import "spring.ftl" as spring />
<#import "userGadgets.ftl" as userGadgets />
<#import "siteGadgets.ftl" as site />

<#--
<#assign myscripts=["/ckeditor/ckeditor.js"] in site>
-->
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
                <#if abouts??>
                <ul class="praise">
                    <#list abouts as about>
                    <li>
                        <div class="praise">
                            ${about.content} ...... 
                            <a class="username" href="<@spring.url '/users/${about.account.user.id?replace("#", "")}/posts' />">
                                ${about.account.user.displayName} 
                            </a>
                        </div>
                    </li>
                    </#list>
                </ul>
                </#if>

                <#if viewerAccount?? && viewerAccount.hasRole('ROLE_VIP')>
                <div class="action">
                    <a href="<@spring.url '/about/_create' />">Add</a>
                </div>
                </#if>
 
            </div>

            <div id="main-sidebar">
                <@spring.message 'Objective.site' />
            </div>

        </div>
    </div>
</@site.layout>

