<#import "spring.ftl" as spring />
<#import "siteGadgets.ftl" as site />

<#assign title="signin" in site>

<@site.layout>

    <#include "siteLogo.ftl" />

    <div id="page-main-wrapper">
        <form id="signinForm" action="<@spring.url '/jsc'/>" method="post">
            <#if error??>
            <div>
                <span class="error">
                    <@spring.message "Invalid.signinForm" />
                </span>
            </div>
            </#if>

            <legend>
                <@spring.message "Legend.signinForm" />
            </legend>
            <ul>
                <li>
                    <label for="sign-username"><@spring.message "Label.signinForm.username" /></label>
                    <input type="text" class="sum" id="sign-username" name="j_username" size="20" maxlength="50" />
                </li>
                <li>
                    <label for="sign-password"><@spring.message "Label.signinForm.password" /></label>
                    <input type="password" class="sum" id="sign-password" name="j_password" size="20" maxlength="50" />
                </li>
                <li>
                    <input type="submit" value='<@spring.message "Signin.button" />'/>
                </li>
            </ul>
         </form>
    </div>

</@site.layout>

