<#import "spring.ftl" as spring />
<#import "tissue.ftl" as tissue />
<#import "userGadgets.ftl" as userGadgets />
<#import "formGadgets.ftl" as formGadgets />

<#--
<#assign mystyles=["/tissue/css/layout2.css"] in tissue>
-->

<@tissue.layout "sign in">
    <div id="logo">
        <@userGadgets.homeLogo />
    </div>

    <@formGadgets.signinForm />

</@tissue.layout>

