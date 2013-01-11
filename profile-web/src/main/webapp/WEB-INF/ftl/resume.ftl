<#import "spring.ftl" as spring />
<#import "tissue.ftl" as tissue />
<#import "personGadgets.ftl" as personGadgets />
<#import "postGadgets.ftl" as postGadgets />
<#import "utilGadgets.ftl" as utilGadgets />

<#assign myscripts=["/ckeditor/ckeditor.js", "/tissue/js/pop.js"] in tissue>
<#assign mystyles=["/tissue/css/user.css", "/tissue/css/pop.css"] in tissue>

<@tissue.layout "Resume">
    <div id="logo">
        <@personGadgets.personLogo />
    </div>

    <div id="contentWrapper">
        <div id="sidebar">
        </div>

       <div id="content">
           <@personGadgets.showResume />
       </div>
    </div>

</@tissue.layout>
