<#import "spring.ftl" as spring />
<#import "tissue.ftl" as tissue />
<#import "personGadgets.ftl" as personGadgets />
<#import "activityGadgets.ftl" as activityGadgets />
<#import "planGadgets.ftl" as planGadgets />

<#--
<#assign mystyles=["/tissue/css/dashboard.css"] in tissue>
-->

<@tissue.layout "home">
    <div id="logo">
        <@personGadgets.homeLogo />
    </div>

    <div id="contentWrapper">
        <div id="sidebar">
            <@planGadgets.showPlans />
        </div>

        <div id="content">
            <@activityGadgets.showActivities />
        </div>
    </div>
</@tissue.layout>

