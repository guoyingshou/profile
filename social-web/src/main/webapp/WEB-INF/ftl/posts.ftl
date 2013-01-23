<#import "spring.ftl" as spring />
<#import "tissue.ftl" as tissue />
<#import "personGadgets.ftl" as personGadgets />
<#import "postGadgets.ftl" as postGadgets />
<#import "planGadgets.ftl" as planGadgets />
<#import "utilGadgets.ftl" as utilGadgets />

<#assign myscripts=["/ckeditor/ckeditor.js"] in tissue>
<#assign mystyles=["/tissue/css/user.css"] in tissue>

<@tissue.layout owner.displayName>
    <div id="logo">
        <@personGadgets.personLogo />
    </div>

    <div id="contentWrapper">
        <div id="sidebar">
            <@personGadgets.showPlansOwned />
        </div>

       <div id="content">
           <#if posts??>
               <@postGadgets.showPosts posts />
           </#if>
           <@utilGadgets.showPager />
       </div>
    </div>

</@tissue.layout>
