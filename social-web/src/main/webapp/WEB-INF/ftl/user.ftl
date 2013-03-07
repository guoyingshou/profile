<#import "spring.ftl" as spring />
<#import "tissue.ftl" as tissue />
<#import "userGadgets.ftl" as userGadgets />
<#import "commonGadgets.ftl" as commonGadgets />
<#import "postGadgets.ftl" as postGadgets />
<#import "topicGadgets.ftl" as topicGadgets />
<#import "activityGadgets.ftl" as activityGadgets />

<#assign myscripts=["/ckeditor/ckeditor.js"] in tissue>
<#assign mystyles=["/tissue/css/layout2.css"] in tissue>

<#assign title= owner.displayName in tissue>

<@tissue.layout>
    <div id="page-logo-wrapper">
        <div id="page-logo">
            <@userGadgets.userLogo />
        </div>
    </div>

    <div id="page-menu-wrapper">
        <div id="page-menu">
            <@userGadgets.userMenu />
        </div>
    </div>

    <div id="page-main-wrapper">
        <div id="page-main">
            <div id="main-sidebar">
                <@userGadgets.showLearningPlans/>
                <@userGadgets.showLearnedPlans/>
            </div>

           <div id="main-content">
           <#if posts??>
               <@postGadgets.showPosts posts />
               <@commonGadgets.showPager />
           <#elseif activities??>
               <@activityGadgets.showActivities />
           <#elseif impressions??>
               <@userGadgets.showImpressions />
           <#else>
               <@userGadgets.showResume />
           </#if>
           </div>
       </div>
    </div>

</@tissue.layout>
