<#import "spring.ftl" as spring />
<#import "tissue.ftl" as tissue />
<#import "userGadgets.ftl" as userGadgets />
<#import "postGadgets.ftl" as postGadgets />
<#import "planGadgets.ftl" as planGadgets />
<#import "utilGadgets.ftl" as utilGadgets />

<#assign myscripts=["/ckeditor/ckeditor.js"] in tissue>
<#assign mystyles=["/tissue/css/layout2.css", "/tissue/css/user.css"] in tissue>

<@tissue.layout owner.displayName>
    <div id="logo">
        <@userGadgets.personLogo />
    </div>

    <div id="contentWrapper">
        <div id="sidebar">
            <@userGadgets.showOwnedPlans/>
            <@userGadgets.showArchivedPlans/>
            <@userGadgets.showNewTopics />
        </div>

       <div id="content">
           <#if posts??>
               <@postGadgets.showPosts posts />
           </#if>
           <@utilGadgets.showPager />
       </div>
    </div>

</@tissue.layout>
