<#import "spring.ftl" as spring />
<#import "tissue.ftl" as tissue />
<#import "userGadgets.ftl" as userGadgets />
<#import "topicGadgets.ftl" as topicGadgets />
<#import "activityGadgets.ftl" as activityGadgets />

<#assign mystyles=["/tissue/css/layout2.css"] in tissue>

<#assign title="invitation" in tissue>

<@tissue.layout>
    <div id="page-logo-wrapper">
        <div id="page-logo">
            <@userGadgets.homeLogo />
        </div>
    </div>

    <div id="page-main-wrapper">
        <div id="page-main">
            <div id="main-sidebar">
                <@userGadgets.showLearningPlans/>
                <@userGadgets.showLearnedPlans/>
            </div>

            <div id="main-content">
                <@userGadgets.showInvitations />
            </div>
        </div>
    </div>
</@tissue.layout>

