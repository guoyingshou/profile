<#import "spring.ftl" as spring />
<#import "commonGadgets.ftl" as commonGadgets />
<#import "userGadgets.ftl" as userGadgets />
<#import "postGadgets.ftl" as postGadgets />
<#import "topicGadgets.ftl" as topicGadgets />
<#import "activityGadgets.ftl" as activityGadgets />

<#assign myscripts=["/ckeditor/ckeditor.js"] in commonGadgets>
<#assign mystyles=["/tissue/css/layout2.css"] in commonGadgets>

<#assign title = owner.displayName in commonGadgets>
<#assign selected = selected in userGadgets>

<@commonGadgets.layout>
    <div id="page-logo-wrapper">
        <div id="page-logo">
            <@userGadgets.userLogo />
        </div>
    </div>

    <div id="page-menu-wrapper">
        <div id="page-menu">
            <@userGadgets.userMenu />
        </div>
    </div>

    <div id="page-main-wrapper">
        <div id="page-main">
            <div id="main-sidebar">
                <@userGadgets.showPlansLearning/>
                <@userGadgets.showPlansLearned/>
            </div>

           <div id="main-content">
               <ul class="impressions">
                   <#if impressions??>
                   <#list impressions as impression>
                   <li>${impression.content}</li>
                   </#list>
                   </#if>
               </ul>

               <#if viewerAccount?? && isFriend>

               <a class="add-impression" data-action="<@spring.url '/impressions/_create' />" data-target="ul.impressions" href="#">add impression</a>

               <form id="impressionForm" class="dialog pop-650" style="display:none" method="post">
                  <legend>
                      Impression
                      <a href="#" class="cancel"><span data-icon="&#xe008"></span></a>
                  </legend>
                  <ul>
                      <li>
                          <textarea id="content" name="content"></textarea>
                      </li>
                      <li>
                          <input type="hidden" id="to" name="to" value="${owner.id?replace('#','')}">
                      </li>
 
                      <li>
                          <input type="submit" value="submit"/>
                      </li>
                  </ul>
               </form>
               </#if>
           </div>
        </div>
    </div>

</@commonGadgets.layout>
