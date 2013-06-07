<#import "spring.ftl" as spring />
<#import "siteGadgets.ftl" as site />
<#import "userGadgets.ftl" as userGadgets />

<#--
<#assign myscripts=["/ckeditor/ckeditor.js"] in site>
-->

<#assign title = owner.displayName in site>

<@site.layout>

    <#include "ownerHeader.ftl" />

    <div id="page-main-wrapper">
        <div id="page-main">
           <div id="main-content">
               <#if impressions??>
               <ul class="impressions">
                   <#list impressions as impression>
                   <li>
                       <div>
                           ${impression.content}
                       </div>
                       <div class="user">
                           <a class="username" href="<@spring.url '/users/${impression.account.user.id?replace("#","")}/posts' />">
                               ${impression.account.user.displayName} 
                           </a>
                       </div>
                   </li>
                   </#list>
               </ul>
               </#if>

               <#if viewerAccount?? && !viewerAccount.hasRole('ROLE_EVIL') && isFriend>
               <a href="<@spring.url '/users/${owner.id?replace("#","")}/impressions/_create' />">
                   <@spring.message 'AddImpressionText.viewer' />
               </a>
               </#if>
           </div>

            <div id="main-sidebar">
                <@userGadgets.showPlansLearning/>
                <@userGadgets.showPlansLearned/>
            </div>
        </div>
    </div>

</@site.layout>
