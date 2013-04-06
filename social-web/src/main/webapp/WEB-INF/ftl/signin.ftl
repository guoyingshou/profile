<#import "spring.ftl" as spring />
<#import "siteGadgets.ftl" as site />

<#assign title="signin" in site>

<@site.layout>

    <@site.siteLogo/>

    <div id="page-main-wrapper">
        <form id="signinForm" action="<@spring.url '/jsc'/>" method="post">
            <#if error??>
            <div>
                <span class="error">
                    <@spring.message "Fail.signinForm" />
                </span>
            </div>
            </#if>

            <#if reset??>
            <div>
                <span class="error">
                    <@spring.message "Success.resetPassword" />
                </span>
            </div>
            </#if>

            <legend>
                <@spring.message "signinForm" />
            </legend>
            <ul>
                <li>
                    <label for="sign-username">
                        <@spring.message "Signin.usernameOrPassword" />
                    </label>
                    <input type="text" id="sign-username" name="j_username" size="20" maxlength="50" />
                </li>
                <li>
                    <label for="sign-password">
                        <@spring.message "Signin.password" />
                    </label>
                    <input type="password" id="sign-password" name="j_password" size="20" maxlength="50" />
                </li>
                <li>
                    <input type="submit" value='<@spring.message "Signin.button" />'/>
                </li>
            </ul>
         </form>
    </div>

</@site.layout>

