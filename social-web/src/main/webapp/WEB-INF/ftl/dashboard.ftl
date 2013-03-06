<#import "spring.ftl" as spring />
<#import "tissue.ftl" as tissue />
<#import "userGadgets.ftl" as userGadgets />
<#import "topicGadgets.ftl" as topicGadgets />
<#import "activityGadgets.ftl" as activityGadgets />

<#assign mystyles=["/tissue/css/layout2.css"] in tissue>
<#assign title = "dashboard" in tissue>

<@tissue.layout>
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
            <div id="main-sidebar">
                <@userGadgets.showLearningPlans/>
                <@userGadgets.showLearnedPlans/>
            </div>

            <div id="main-content">
            <#if friends??>
                <@userGadgets.showFriends />
            <#elseif activities??>
                <@activityGadgets.showActivities />
            <#else>
                <@userGadgets.showInvitations />
            </#if>
            </div>
        </div>
    </div>
</@tissue.layout>

