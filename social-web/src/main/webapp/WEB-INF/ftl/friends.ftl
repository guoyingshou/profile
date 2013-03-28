<#import "spring.ftl" as spring />
<#import "siteGadgets.ftl" as site />
<#import "userGadgets.ftl" as userGadgets />

<#assign title = "dashboard" in site>

<@site.layout>
    <#include "siteLogo.ftl" />
    <#include "siteMenu.ftl" />

    <div id="page-main-wrapper">
        <div id="page-main">

            <div id="main-content">
                <ul class="friends">
                    <#list friends as friend>
                    <li>
                        <a href="/social/users/${friend.id?replace("#", "")}/posts">${friend.displayName}</a>
                    </li>
                    </#list>
                </ul>
            </div>

            <div id="main-sidebar">
                <@userGadgets.showPlansLearning/>
                <@userGadgets.showPlansLearned/>
            </div>

        </div>
    </div>
</@site.layout>

