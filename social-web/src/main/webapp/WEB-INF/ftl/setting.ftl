<#import "spring.ftl" as spring />
<#import "siteGadgets.ftl" as site />
<#import "userGadgets.ftl" as userGadgets />

<#assign title = "dashboard" in site>

<@site.layout>

    <#include "siteLogo.ftl" />

    <div id="page-menu-wrapper">
        <div id="page-menu">
            <@userGadgets.settingMenu />
        </div>
    </div>

    <div id="page-main-wrapper">
        <div id="page-main">
            <div id="main-content">
                <div>
                    <@spring.message 'username' />: 
                    ${viewerAccount.username}
                </div>

                <div>
                    <@spring.message 'email' />: 
                    ${viewerAccount.email}
                </div>

                <div>
                    <@spring.message 'displayName' />: 
                    ${viewerAccount.user.displayName}
                </div>

                <div>
                    <@spring.message 'headline' />: 
                    ${viewerAccount.user.headline}
                </div>
            </div>

            <#--
            <div id="main-sidebar">
                <@userGadgets.showPlansLearning/>
                <@userGadgets.showPlansLearned/>
            </div>
                -->

        </div>
    </div>
</@site.layout>

