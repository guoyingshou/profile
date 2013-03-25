<#import "spring.ftl" as spring />
<#import "commonGadgets.ftl" as commonGadgets />
<#import "userGadgets.ftl" as userGadgets />

<#assign title="signin" in commonGadgets>

<@commonGadgets.layout>
    <div id="page-logo-wrapper">
        <div id="page-logo">
            <@userGadgets.homeLogo />
        </div>
    </div>

    <div id="page-main-wrapper">
<@spring.bind 'passwordForm.*' />
<form id="resetPasswordForm" method="post" action="<@spring.url '/reset/${code}'/>">
    <div class="error">
        <@spring.showErrors "<br>" />
    </div>

    <legend>
        <@spring.message "Legend.resetPasswordForm" />
    </legend>
    <ul>
        <li>
            <label for="password">
                <@spring.message "Label.resetPasswordForm.password" />
            </label>
            <@spring.formPasswordInput "passwordForm.password" 'class="sum"' />
        </li>
        <li>
            <label for="confirm">
                <@spring.message "Label.resetPasswordForm.confirm" />
            </label>
            <@spring.formPasswordInput 'passwordForm.confirm' 'class="sum"' />
        </li>

        <li>
            <input type="submit" value='<@spring.message "Save.button" />'/>
        </li>
    </ul>
</form>

    </div>

</@commonGadgets.layout>

