<#import "spring.ftl" as spring />
<#import "siteGadgets.ftl" as site />

<#assign myscripts = ["/ckeditor/ckeditor.js"] in site>
<#assign title="Invitation" in site>

<@site.layout>

    <#include "ownerHeader.ftl" />

    <div id="page-main-wrapper">
        <@spring.bind "invitationForm.*" />
        <form id="invitationForm" method="POST" action="<@spring.url '/users/${owner.id?replace("#","")}/invitations/_create' />">
            <div class="error">
                <@spring.showErrors "<br>" />
            </div>

            <legend>
                <@spring.message "Legend.invitationForm" />
            </legend>
            <ul>
                <li>
                    <label for="letter">
                        <@spring.message "Label.content" />
                    </label>
                    <@spring.formTextarea 'invitationForm.content' />
                </li>
                <li>
                    <input type="submit" name="submit" value='<@spring.message "SendText.submit"/>' />
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

