<#import "spring.ftl" as spring />
<#import "tissue.ftl" as tissue />
<#import "userGadgets.ftl" as userGadgets />
<#import "topicGadgets.ftl" as topicGadgets />
<#import "activityGadgets.ftl" as activityGadgets />

<#assign mystyles=["/tissue/css/layout2.css"] in tissue>

<@tissue.layout "home">
    <div id="logo">
        <@userGadgets.homeLogo />
    </div>

    <div id="contentWrapper">
        <div id="sidebar">
            <@userGadgets.showLearningPlans/>
            <@userGadgets.showLearnedPlans/>
            <#--
            <@topicGadgets.showNewTopics/>
            -->
        </div>

        <div id="content">
        <#if activities??>
            <@activityGadgets.showActivities />
        <#elseif invitationsReceived??>
            <@userGadgets.showInvitations />
        </#if>
        </div>
    </div>
</@tissue.layout>

