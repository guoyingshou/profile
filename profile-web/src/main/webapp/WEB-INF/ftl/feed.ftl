<#import "spring.ftl" as spring />
<#import "tissue.ftl" as tissue />
<#import "personGadgets.ftl" as personGadgets />
<#import "postGadgets.ftl" as postGadgets />
<#import "activityGadgets.ftl" as activityGadgets />
<#import "utilGadgets.ftl" as utilGadgets />

<#assign myscripts=["/ckeditor/ckeditor.js"] in tissue>
<#assign mystyles=["/tissue/css/user.css"] in tissue>

<@tissue.layout "Feed">
    <div id="logo">
        <@personGadgets.personLogo />
    </div>

    <div id="contentWrapper">
        <div id="sidebar">
        </div>

       <div id="content">
           <@activityGadgets.showActivities />
       </div>
    </div>

</@tissue.layout>
