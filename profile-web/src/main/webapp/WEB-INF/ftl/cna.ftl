<#import "tissue.ftl" as tissue />
<#import "gadgets.ftl" as gadgets />
<#import "spring.ftl" as spring />

<#assign myscripts=["/ckeditor/ckeditor.js"] in tissue>

<#assign mystyles=["/tissue/css/content-2cols.css", "/tissue/css/cna.css"] in tissue>

<@tissue.layout "cna">
    <div id="logo">
        <@tissue.cnaLogo />
    </div>

    <div id="contentWrapper">
        <div id="sidebar">
        </div>

       <div id="content">
           <#if posts??>
               <@gadgets.showPostList posts />
           </#if>
           <@gadgets.showPager />
       </div>
    </div>

</@tissue.layout>
