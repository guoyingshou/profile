<#import "spring.ftl" as spring />
<#import "commonGadgets.ftl" as commonGadgets />
<#import "userGadgets.ftl" as userGadgets />
<#import "settingGadgets.ftl" as settingGadgets />
<#import "topicGadgets.ftl" as topicGadgets />
<#import "activityGadgets.ftl" as activityGadgets />

<#assign mystyles=["/tissue/css/layout2.css"] in commonGadgets>
<#assign title = "dashboard" in commonGadgets>

<#--
<#assign selected = selected in userGadgets>
-->

<@commonGadgets.layout>
    <div id="page-logo-wrapper">
        <div id="page-logo">
            <@userGadgets.homeLogo />
        </div>
    </div>

    <div id="page-menu-wrapper">
        <div id="page-menu">
            <@settingGadgets.settingMenu />
        </div>
    </div>

    <div id="page-main-wrapper">
        <div id="page-main">
            <div id="main-sidebar">
            <#--
                <@userGadgets.showPlansLearning/>
                <@userGadgets.showPlansLearned/>
                -->
            </div>

            <div id="main-content">
                <@settingGadgets.updatePasswordForm />
            </div>
        </div>
    </div>
</@commonGadgets.layout>

