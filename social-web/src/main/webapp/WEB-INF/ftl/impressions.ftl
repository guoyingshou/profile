<#import "spring.ftl" as spring />
<#import "siteGadgets.ftl" as site />
<#import "userGadgets.ftl" as userGadgets />

<#assign myscripts=["/ckeditor/ckeditor.js"] in site>

<#assign title = owner.displayName in site>

<@site.layout>

    <#include "userHeader.ftl" />

    <div id="page-main-wrapper">
        <div id="page-main">
           <div id="main-content">
               <ul class="impressions">
                   <#if impressions??>
                   <#list impressions as impression>
                   <li>${impression.content}</li>
                   </#list>
                   </#if>
               </ul>

               <#if viewerAccount?? && isFriend>
               <a href="<@spring.url '/users/${owner.id?replace("#","")}/impressions/_create' />">add impression</a>
               </#if>
           </div>

            <div id="main-sidebar">
                <@userGadgets.showPlansLearning/>
                <@userGadgets.showPlansLearned/>
            </div>
        </div>
    </div>

</@site.layout>
