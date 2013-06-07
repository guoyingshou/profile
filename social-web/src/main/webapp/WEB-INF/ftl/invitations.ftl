<#import "spring.ftl" as spring />
<#import "siteGadgets.ftl" as site />
<#import "userGadgets.ftl" as userGadgets />

<#assign title = "dashboard" in site>

<@site.layout>

    <@site.siteLogo />
    <@userGadgets.viewerMenu />

    <div id="page-main-wrapper">
        <div id="page-main">
            <div id="main-content">
                <#if invitations??>
                <ul class="invitations">
                    <#list invitations as invitation>
                    <#assign target = "li.invitation-${invitation.id?replace('#','')?replace(':', '-')}">
                    <li id="${target}">
                        <div>
                            <a class="username" href="<@spring.url '/users/${invitation.account.user.id?replace("#", "")}/posts' />">
                                ${invitation.account.user.displayName}
                            </a>
                            [<span class="ts">${invitation.createTime?date}</span>]
                        </div>
                        <div>${invitation.content}</div>
                        <div class="action">
                            <a href="<@spring.url '/invitations/${invitation.id?replace("#", "")}/_accept' />">
                                <@spring.message "AcceptInvitationText.viewer" />
                            </a>
                            <a href="<@spring.url '/invitations/${invitation.id?replace("#","")}/_decline' />">
                                <@spring.message "DeclineInvitationText.viewer" />
                            </a>
                        </div>
                     </li>
                     </#list>
                 </ul>
                 </#if>
            </div>
            <div id="main-sidebar">
                <@userGadgets.showPlansLearning/>
                <@userGadgets.showPlansLearned/>
            </div>
        </div>
    </div>
</@site.layout>

