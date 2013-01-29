<#import "spring.ftl" as spring />
<#import "tissue.ftl" as tissue />
<#import "userGadgets.ftl" as userGadgets />
<#import "postGadgets.ftl" as postGadgets />
<#import "activityGadgets.ftl" as activityGadgets />
<#import "utilGadgets.ftl" as utilGadgets />

<#assign myscripts=["/ckeditor/ckeditor.js"] in tissue>
<#assign mystyles=["/tissue/css/layout2.css", "/tissue/css/user.css"] in tissue>

<@tissue.layout "Feed">
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
           <@activityGadgets.showActivities />
       </div>
    </div>

</@tissue.layout>
