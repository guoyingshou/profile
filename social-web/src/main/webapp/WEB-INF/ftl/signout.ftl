<#import "spring.ftl" as spring />
<#import "tissue.ftl" as tissue />
<#import "userGadgets.ftl" as userGadgets />
<#import "activityGadgets.ftl" as activityGadgets />
<#import "commonGadgets.ftl" as commonGadgets />

<#assign mystyles=["/tissue/css/layout2.css"] in tissue>

<#assign title="signout" in tissue>

<@tissue.layout>

    <iframe src="/group/logout" style="display:none"></iframe>

    <div id="page-logo-wrapper">
        <div id="page-logo">
            <@userGadgets.homeLogo />
        </div>
    </div>
    <div id="page-main-wrapper">
        <div id="page-main">
            <div id="main-sidebar">
               <@commonGadgets.slogan />
            </div>

            <div id="main-content">
                <@activityGadgets.showActivities />
            </div>
        </div>
    </div>
</@tissue.layout>

