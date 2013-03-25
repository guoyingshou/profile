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
                        <@spring.message "Label.invitationForm.content" />
                    </label>
               </li>
               <li>
                   <@spring.formTextarea 'invitationForm.content' 'class="sum"' />
               </li>
               <li>
                   <input type="submit" name="submit" value='<@spring.message "Send.button"/>' />
               </li>
            </ul>
        </form>
    </div>

</@commonGadgets.layout>

