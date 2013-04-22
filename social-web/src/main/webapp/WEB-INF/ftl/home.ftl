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

                <#--
                <ul class="site-objective">
                    <li>分享学习方法</li>
                    <li>交流学习经验</li>
                </ul>
                    -->
            </div>
        </div>
    </div>
</@site.layout>

