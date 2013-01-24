<#import "spring.ftl" as spring />
<#import "tissue.ftl" as tissue />
<#import "personGadgets.ftl" as personGadgets />
<#import "postGadgets.ftl" as postGadgets />
<#import "utilGadgets.ftl" as utilGadgets />

<#assign myscripts=["/ckeditor/ckeditor.js"] in tissue>
<#assign mystyles=["/tissue/css/user.css"] in tissue>

<@tissue.layout "Impression">
    <div id="logo">
        <@personGadgets.personLogo />
    </div>

    <div id="contentWrapper">
        <div id="sidebar">
            <@personGadgets.showPlansOwned />
            <@personGadgets.showNewTopics />
        </div>

       <div id="content">
           <@personGadgets.showImpressions /> 
       </div>
    </div>

</@tissue.layout>
