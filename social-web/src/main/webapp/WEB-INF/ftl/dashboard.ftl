<#import "spring.ftl" as spring />
<#import "tissue.ftl" as tissue />
<#import "userGadgets.ftl" as userGadgets />
<#import "activityGadgets.ftl" as activityGadgets />

<#assign mystyles=["/tissue/css/activity.css"] in tissue>

<@tissue.layout "home">
    <div id="logo">
        <@userGadgets.homeLogo />
    </div>

    <div id="contentWrapper">
        <div id="sidebar">
            <@userGadgets.showPlansOwned />
        </div>

        <div id="content">
            <@activityGadgets.showActivities />
        </div>
    </div>
</@tissue.layout>

