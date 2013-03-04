<#import "spring.ftl" as spring />
<#import "tissue.ftl" as tissue />
<#import "userGadgets.ftl" as userGadgets />

<#--
<#assign myscripts = ['/tissue/js/user.js'] in tissue />
-->

<@tissue.layout "sign up">
    <div id="page-logo">
        <@userGadgets.homeLogo />
    </div>
    <@userGadgets.signupForm />
</@tissue.layout>

