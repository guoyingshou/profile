<#import "spring.ftl" as spring />
<#import "tissue.ftl" as tissue />
<#import "userGadgets.ftl" as userGadgets />
<#import "postGadgets.ftl" as postGadgets />
<#import "topicGadgets.ftl" as topicGadgets />
<#import "utilGadgets.ftl" as utilGadgets />

<#assign myscripts=["/ckeditor/ckeditor.js", "/tissue/js/user.js"] in tissue>
<#assign mystyles=["/tissue/css/layout2.css"] in tissue>

<@tissue.layout owner.displayName>
    <div id="logo">
        <@userGadgets.personLogo />
    </div>

    <div id="contentWrapper">
        <div id="sidebar">
            <@userGadgets.showOwnedPlans/>
            <@userGadgets.showArchivedPlans/>
            <@topicGadgets.showNewTopics />
        </div>

       <div id="content">
           <#if posts??>
               <@postGadgets.showPosts posts />
           </#if>
           <@utilGadgets.showPager />
       </div>
    </div>

</@tissue.layout>
