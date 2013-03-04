<#import "spring.ftl" as spring />
<#import "tissue.ftl" as tissue />
<#import "userGadgets.ftl" as userGadgets />
<#import "topicGadgets.ftl" as topicGadgets />
<#import "activityGadgets.ftl" as activityGadgets />

<#assign mystyles=["/tissue/css/layout2.css"] in tissue>

<@tissue.layout "home">
    <div id="page-logo">
        <@userGadgets.homeLogo />
    </div>

    <div id="page-content-wrapper">
        <div id="page-sidebar">
            <@userGadgets.showLearningPlans/>
            <@userGadgets.showLearnedPlans/>
            <#--
            <@topicGadgets.showNewTopics/>
            -->
        </div>

        <div id="page-content">
        <#if friends??>
            <@userGadgets.showFriends />
        <#elseif activities??>
            <@activityGadgets.showActivities />
        <#else>
            <@userGadgets.showInvitations />
        </#if>
        </div>
    </div>
</@tissue.layout>

