<#import "spring.ftl" as spring />
<#import "siteGadgets.ftl" as site />
<#import "userGadgets.ftl" as userGadgets />

<#assign title="signin" in site>

<@site.layout>

    <#include "userHeader.ftl" />

    <div id="page-main-wrapper">
       <@spring.bind "impressionForm.*" />

       <form id="impressionForm" method="post" action="<@spring.url '/users/${owner.id?replace("#", "")}/impressions/_create' />">
          <div class="error">
              <@spring.showErrors "<br>" />
          </div>

          <legend>
              Impression
          </legend>
          <ul>
              <li>
                  <@spring.formTextarea "impressionForm.content" 'class="sum"' />
              </li>
              <li>
                  <input type="submit" value="submit"/>
              </li>
          </ul>
      </form>
    </div>

</@site.layout>

