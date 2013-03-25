<#import "spring.ftl" as spring />
<#import "commonGadgets.ftl" as commonGadgets />
<#import "userGadgets.ftl" as userGadgets />

<#assign title="signin" in commonGadgets>

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

</@commonGadgets.layout>

