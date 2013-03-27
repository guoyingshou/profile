<#import "spring.ftl" as spring />
<#import "commonGadgets.ftl" as commonGadgets />
<#import "userGadgets.ftl" as userGadgets />
<#import "topicGadgets.ftl" as topicGadgets />
<#import "activityGadgets.ftl" as activityGadgets />

<#assign title = "dashboard" in commonGadgets>

<@commonGadgets.layout>
    <div id="page-logo-wrapper">
        <div id="page-logo">
            <@userGadgets.homeLogo />
        </div>
    </div>

    <div id="page-menu-wrapper">
        <div id="page-menu">
            <@userGadgets.homeMenu />
        </div>
    </div>

    <div id="page-main-wrapper">
        <div id="page-main">

            <div id="main-content">
                <ul>
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
</@commonGadgets.layout>

