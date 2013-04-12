<#import "spring.ftl" as spring />
<#import "siteGadgets.ftl" as site />

<#assign title="Verify email" in site>

<@site.layout>
    <@site.siteLogo/>

    <div id="page-main-wrapper">
        <div class="info">
            <@spring.message "Success.verifyEmail" />
        </div>
    </div>

</@site.layout>

