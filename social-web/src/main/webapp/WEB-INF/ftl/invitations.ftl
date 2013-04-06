<#import "spring.ftl" as spring />
<#import "siteGadgets.ftl" as site />
<#import "userGadgets.ftl" as userGadgets />

<#assign title = "dashboard" in site>

<@site.layout>

    <#include "ownerHeader.ftl" />

    <div id="page-main-wrapper">
        <div id="page-main">
            <div id="main-content">
                <ul>
                    <#list invitationsReceived as invitation>
                    <#assign target = "li.invitation-${invitation.id?replace('#','')?replace(':', '-')}">
                    <li class="${target}">
                        <div>${invitation.account.user.displayName}</div>
                        <div>${invitation.createTime?date}</div>
                        <div>${invitation.content}</div>
                        <div class="action">
                            <a href="<@spring.url '/invitations/${invitation.id?replace("#", "")}/_accept' />">
                                <@spring.message "Accept.invitation" />
                            </a>
                            <a href="<@spring.url '/invitations/${invitation.id?replace("#","")}/_decline' />">
                                <@spring.message "Decline.invitation" />
                            </a>
                        </div>
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

