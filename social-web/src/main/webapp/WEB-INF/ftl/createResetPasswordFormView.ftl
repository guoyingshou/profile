<#import "spring.ftl" as spring />
<#import "siteGadgets.ftl" as site />

<#assign title="signin" in site>

<@site.layout>

    <@site.siteLogo />

    <div id="page-main-wrapper">
        <@spring.bind 'resetPasswordForm.*' />
        <form id="resetPasswordForm" method="post" action="<@spring.url '/resetPassword'/>">
            <div class="error">
                <@spring.showErrors "<br>" />
            </div>

            <legend>
                <@spring.message "Legend.resetPasswordForm" />
            </legend>
            <ul>
                <li>
                    <label for="password">
                        <@spring.message "Label.password" />
                    </label>
                    <@spring.formPasswordInput "resetPasswordForm.password" />
                </li>
                <li>
                    <label for="confirm">
                        <@spring.message "Label.confirm" />
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
                    <input type="submit" value='<@spring.message "SaveText.submit" />'/>
                </li>
            </ul>
         </form>
    </div>

</@site.layout>

