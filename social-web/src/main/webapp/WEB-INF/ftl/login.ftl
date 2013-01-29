<#import "spring.ftl" as spring />
<#import "tissue.ftl" as tissue />
<#import "formGadgets.ftl" as formGadgets />

<#assign mystyles=["/tissue/css/layout2.css"] in tissue>

<@tissue.layout "sign in">
    <div id="logo">
        <h1>Tissue Network</h1>
    </div>

    <div id="contentWrapper">
        <div id="sidebar">
            <@tissue.slogan />
        </div>

        <div id="content">
            <@formGadgets.signinForm />
        </div>
    </div>
</@tissue.layout>

