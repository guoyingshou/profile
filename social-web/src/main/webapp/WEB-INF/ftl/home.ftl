<#import "spring.ftl" as spring />
<#import "tissue.ftl" as tissue />
<#import "eventGadgets.ftl" as eventGadgets />

<#--
<#assign mystyles=["/tissue/css/home.css"] in tissue>
-->

<@tissue.layout "home">
    <div id="logo">
        <h1>Tissue Network</h1>
    </div>

    <div id="contentWrapper">
        <div id="sidebar">
           <@tissue.slogan />
        </div>

        <div id="content">
            <@eventGadgets.showLatestEvents />
        </div>
    </div>
</@tissue.layout>

