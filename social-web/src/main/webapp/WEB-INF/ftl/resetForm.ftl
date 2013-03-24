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
        <@spring.bind "resetForm.*" />
        <form id="resetRequestForm" method="post" action="<@spring.url '/resetRequest' />">
            <div class="error">
                <@spring.showErrors "<br>" />
            </div>

    <legend>
        <@spring.message "Legend.resetRequestForm" />
    </legend>
    <ul>
        <li>
            <label for="email">
                <@spring.message "Label.resetRequestForm.email" />
            </label>
            <@spring.formInput 'resetForm.email' 'class="sum"' />
        </li>
        <li>
            <input type="submit" value='<@spring.message "Submit.button"/>'/>
        </li>
    </ul>
</form>

    </div>

</@commonGadgets.layout>

