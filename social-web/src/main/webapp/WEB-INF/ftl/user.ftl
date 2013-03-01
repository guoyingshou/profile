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
    <div id="logo">
        <@userGadgets.userLogo />
    </div>

    <div id="contentWrapper">
        <div id="sidebar">
            <@userGadgets.showLearningPlans/>
            <@userGadgets.showLearnedPlans/>
            <#--
            <@topicGadgets.showNewTopics />
            -->
        </div>

       <div id="content">
           <#if posts??>
               <@postGadgets.showPosts posts />
               <@utilGadgets.showPager />
           <#elseif activities??>
               <@activityGadgets.showActivities />
           <#elseif impressions??>
               <@userGadgets.showImpressions />
           <#else>
               <@userGadgets.showResume />
           </#if>
       </div>
    </div>

</@tissue.layout>
