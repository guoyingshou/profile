<#import "spring.ftl" as spring />
<#import "tissue.ftl" as tissue />
<#import "personGadgets.ftl" as personGadgets />
<#import "postGadgets.ftl" as postGadgets />
<#import "utilGadgets.ftl" as utilGadgets />

<#assign myscripts=["/ckeditor/ckeditor.js"] in tissue>

<#assign mystyles=["/tissue/css/content-2cols.css", "/tissue/css/cna.css"] in tissue>

<@tissue.layout "cna">
    <div id="logo">
        <@personGadgets.personLogo />
    </div>

    <div id="contentWrapper">
        <div id="sidebar">
        </div>

       <div id="content">
           <#if posts??>
               <@postGadgets.showPostList posts />
           </#if>
           <@utilGadgets.showPager />
       </div>
    </div>

</@tissue.layout>
