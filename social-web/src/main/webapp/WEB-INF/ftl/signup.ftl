<#import "spring.ftl" as spring />
<#import "tissue.ftl" as tissue />
<#import "userGadgets.ftl" as userGadgets />
<#import "formGadgets.ftl" as formGadgets />

<#assign myscripts = ['/tissue/js/user.js'] in tissue />

<@tissue.layout "sign up">
    <div id="logo">
        <@userGadgets.homeLogo />
    </div>
    <@formGadgets.signupForm />
</@tissue.layout>

