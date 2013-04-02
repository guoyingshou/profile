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
                            <div class="user-display-name">
                                <a href="<@spring.url '/users/${friend.id?replace("#", "")}/posts' />">
                                    ${friend.displayName}
                                </a>
                            </div>
                            <div class="user-headline">
                                ${friend.headline}
                            </div>
                            <div class="action">
                                <a class="delete" data-action="<@spring.url '/friends/${friend.id?replace("#","")}/_remove' />" href="#">
                                     remove
                                </a>
                            </div>
                        </div>
                    </li>
                    </#list>
                </ul>
                <@site.deleteConfirmForm />
            </div>

            <div id="main-sidebar">
                <@userGadgets.showPlansLearning/>
                <@userGadgets.showPlansLearned/>
            </div>

        </div>
    </div>
</@site.layout>

