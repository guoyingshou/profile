<#import "spring.ftl" as spring />
<#import "siteGadgets.ftl" as site />

<#assign title="signin" in site>

<@site.layout>

    <#include "siteLogo.ftl" />

    <div id="page-main-wrapper">
        <@spring.bind 'resetPasswordForm.*' />
        <form id="resetPasswordForm" method="post" action="<@spring.url '/resetPassword'/>">
            <div class="error">
                <@spring.showErrors "<br>" />
            </div>

            <legend>
                <@spring.message "resetPasswordForm" />
            </legend>
            <ul>
                <li>
                    <label for="password">
                        <@spring.message "password" />
                    </label>
                    <@spring.formPasswordInput "resetPasswordForm.password" />
                </li>
                <li>
                    <label for="confirm">
                        <@spring.message "confirm" />
                    </label>
                   <@spring.formPasswordInput 'resetPasswordForm.confirm' />
                </li>
                <li>
                    <input type="hidden" name="reset" value="${resetPasswordForm.reset.code}" />
                <#--
                    <@spring.formHiddenInput 'resetPasswordForm.reset.code' />
                    -->
                </li>
                <li>
                    <input type="submit" value='<@spring.message "Save.button" />'/>
                </li>
            </ul>
         </form>
    </div>

</@site.layout>

