<#import "spring.ftl" as spring />
<#import "siteGadgets.ftl" as site />
<#import "userGadgets.ftl" as userGadgets />

<#assign myscripts = ["/ckeditor/ckeditor.js"] in site>
<#assign title="Impression" in site>

<@site.layout>

    <#include "ownerHeader.ftl" />

    <div id="page-main-wrapper">
       <@spring.bind "impressionForm.*" />

       <form id="impressionForm" method="post" action="<@spring.url '/users/${owner.id?replace("#", "")}/impressions/_create' />">
          <div class="error">
              <@spring.showErrors "<br>" />
          </div>

          <legend>
              <@spring.message 'Legend.impressionForm' />
          </legend>
          <ul>
              <li>
                  <@spring.formTextarea "impressionForm.content" />
              </li>
              <li>
                  <input type="submit" value="<@spring.message 'Text.submit' />" />
              </li>
          </ul>
      </form>
      <script type="text/javascript">
          CKEDITOR.replace("content", {
             toolbar: [
                 { name: 'basicstyles', items: [ 'Bold', 'Italic' ] }
             ]
          });
      </script>
    </div>

</@site.layout>

