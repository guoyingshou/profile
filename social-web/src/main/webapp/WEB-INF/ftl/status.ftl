<#import "spring.ftl" as spring />
<#import "siteGadgets.ftl" as site />
<#import "userGadgets.ftl" as userGadgets />
<#import "activityGadgets.ftl" as activityGadgets />

<#assign myscripts=["/ckeditor/ckeditor.js"] in site>

<#assign title= owner.displayName in site>

<@site.layout>

    <#include "ownerHeader.ftl" />

    <div id="page-main-wrapper">
        <div id="page-main">

           <div id="main-content">
               <@activityGadgets.showActivities />
           </div>

           <div id="main-sidebar">
                <@userGadgets.showPlansLearning/>
                <@userGadgets.showPlansLearned/>
            </div>

       </div>
    </div>

</@site.layout>
