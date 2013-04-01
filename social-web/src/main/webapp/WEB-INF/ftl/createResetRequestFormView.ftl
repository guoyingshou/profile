<#import "spring.ftl" as spring />
<#import "siteGadgets.ftl" as site />

<#assign title="signin" in site>

<@site.layout>
    <#include "siteLogo.ftl" />

    <div id="page-main-wrapper">
        <@spring.bind "resetRequestForm.*" />
        <form id="resetRequestForm" method="post" action="<@spring.url '/resetRequest' />">
            <div class="error">
                <@spring.showErrors "<br>" />
            </div>

    <legend>
        <@spring.message "resetRequestForm" />
    </legend>
    <ul>
        <li>
            <label for="email">
                <@spring.message "email" />
            </label>
            <@spring.formInput 'resetRequestForm.email' />
        </li>
        <li>
            <input type="submit" value='<@spring.message "Submit.button"/>'/>
        </li>
    </ul>
</form>

    </div>

</@site.layout>

