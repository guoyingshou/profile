<#import "spring.ftl" as spring />
<#import "tissue.ftl" as tissue />
<#import "personGadgets.ftl" as personGadgets />
<#import "activityGadgets.ftl" as activityGadgets />

<#--
<#assign mystyles=["/tissue/css/home.css"] in tissue>
-->

<@tissue.layout "home">
    <div id="logo">
        <@personGadgets.homeLogo />
    </div>
    <div id="contentWrapper">
        <div id="sidebar">
           <@tissue.slogan />
        </div>

        <div id="content">
            <@activityGadgets.showActivities />
        </div>
    </div>
</@tissue.layout>

