<#import "spring.ftl" as spring />
<#import "commonGadgets.ftl" as commonGadgets />
<#import "userGadgets.ftl" as userGadgets />

<#assign title = "sign up" in commonGadgets />

<@commonGadgets.layout>
    <div id="page-logo-wrapper">
        <div id="page-logo">
            <@userGadgets.homeLogo />
        </div>
    </div>

    <div id="page-main-wrapper">
        <@userGadgets.signupForm />
    <div>
</@commonGadgets.layout>

