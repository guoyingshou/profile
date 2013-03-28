<#import "spring.ftl" as spring />
<#import "siteGadgets.ftl" as site />
<#import "activityGadgets.ftl" as activityGadgets />

<#assign title="home" in site>

<@site.layout>

    <#if signout??>
        <iframe src="/group/signout" style="display:none"></iframe>
    </#if>

    <#include "siteLogo.ftl" />

    <div id="page-main-wrapper">
        <div id="page-main">
            <div id="main-content">
                <@activityGadgets.showActivities />
            </div>

            <div id="main-sidebar">
                <h3>在线社会化学习工具</h3>
                <div>分享学习方法</div>
                <div>交流学习经验</div>
                <div>展示个人能力</div>
                <div>获取更多机会</div>
            </div>
        </div>
    </div>
</@site.layout>

