<#import "spring.ftl" as spring />
<#import "tissue.ftl" as tissue />
<#import "userGadgets.ftl" as userGadgets />
<#import "activityGadgets.ftl" as activityGadgets />

<#assign mystyles=["/tissue/css/layout2.css"] in tissue>

<@tissue.layout "home">
    <iframe src="/group/logout" style="display:none"></iframe>
    <div id="page-logo">
        <@userGadgets.homeLogo />
    </div>
    <div id="page-content-wrapper">
        <div id="page-sidebar">
           <@tissue.slogan />
        </div>

        <div id="page-content">
            <@activityGadgets.showActivities />
        </div>
    </div>
</@tissue.layout>

