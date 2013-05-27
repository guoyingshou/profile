<#import "spring.ftl" as spring />
<#import "siteGadgets.ftl" as site />
<#import "activityGadgets.ftl" as activityGadgets />

<#assign title="home" in site>

<@site.layout>

    <@site.siteLogo />

    <div id="page-main-wrapper">
        <div id="page-main">
            <div id="main-content">
                <@activityGadgets.showActivities />
            </div>

            <div id="main-sidebar">
                <@spring.message 'Feature.site' />
            </div>
        </div>
    </div>
</@site.layout>

