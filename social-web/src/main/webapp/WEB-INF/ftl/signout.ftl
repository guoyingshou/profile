<#import "spring.ftl" as spring />
<#import "commonGadgets.ftl" as commonGadgets />
<#import "userGadgets.ftl" as userGadgets />
<#import "activityGadgets.ftl" as activityGadgets />
<#import "commonGadgets.ftl" as commonGadgets />

<#assign mystyles=["/tissue/css/layout2.css"] in commonGadgets>

<#assign title="signout" in commonGadgets>

<@commonGadgets.layout>

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
</@commonGadgets.layout>

