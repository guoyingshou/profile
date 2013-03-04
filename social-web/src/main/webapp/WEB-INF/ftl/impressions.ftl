<#import "spring.ftl" as spring />
<#import "tissue.ftl" as tissue />
<#import "userGadgets.ftl" as userGadgets />
<#import "utilGadgets.ftl" as utilGadgets />
<#import "postGadgets.ftl" as postGadgets />
<#import "topicGadgets.ftl" as topicGadgets />
<#import "activityGadgets.ftl" as activityGadgets />

<#assign myscripts=["/ckeditor/ckeditor.js"] in tissue>
<#assign mystyles=["/tissue/css/layout2.css"] in tissue>

<@tissue.layout owner.displayName>
    <div id="page-logo">
        <@userGadgets.userLogo />
    </div>

    <div id="page-content-wrapper">
        <div id="sidebar">
            <@userGadgets.showLearningPlans/>
            <@userGadgets.showLearnedPlans/>
        </div>

       <div id="page-content">
           <@userGadgets.showImpressions />
       </div>
    </div>

</@tissue.layout>
