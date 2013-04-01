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
                    Username: 
                    ${viewerAccount.username}
                </div>

                <div>
                    Email: 
                    ${viewerAccount.email}
                </div>

                <div>
                    DisplayName:  
                    ${viewerAccount.user.displayName}
                </div>

                <div>
                    Headline:  
                    ${viewerAccount.user.headline}
                </div>
            </div>

            <div id="main-sidebar">
            <#--
                <@userGadgets.showPlansLearning/>
                <@userGadgets.showPlansLearned/>
                -->
            </div>

        </div>
    </div>
</@site.layout>

