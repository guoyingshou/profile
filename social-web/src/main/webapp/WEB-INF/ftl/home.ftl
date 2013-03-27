<#import "spring.ftl" as spring />
<#import "commonGadgets.ftl" as commonGadgets />
<#import "userGadgets.ftl" as userGadgets />
<#import "activityGadgets.ftl" as activityGadgets />

<#assign title="home" in commonGadgets>

<@commonGadgets.layout>

    <#if signout??>
        <iframe src="/group/signout" style="display:none"></iframe>
    </#if>

    <div id="page-logo-wrapper">
        <div id="page-logo">
            <@userGadgets.homeLogo />
        </div>
    </div>

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
</@commonGadgets.layout>

