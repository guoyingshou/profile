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
                        <div class="user">
                            <div class="displayName">
                                <a href="<@spring.url '/users/${friend.id?replace("#", "")}/posts' />">
                                    ${friend.displayName}
                                </a>

                                <a class="pop" data-form-selector="#confirmForm" data-dialog-width="320" data-action="<@spring.url '/friends/${friend.id?replace("#","")}/_remove' />" href="#">
                                     <@spring.message "Disconnect.user" />
                                </a>
                            </div>
                            <div class="headline">
                                ${friend.headline}
                            </div>
                        </div>
                    </li>
                    </#list>
                </ul>
                <@site.confirmForm />
            </div>

            <div id="main-sidebar">
                <@userGadgets.showPlansLearning/>
                <@userGadgets.showPlansLearned/>
            </div>

        </div>
    </div>
</@site.layout>

