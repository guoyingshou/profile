<#import "spring.ftl" as spring />
<#import "tissue.ftl" as tissue />
<#import "personGadgets.ftl" as personGadgets />
<#import "eventGadgets.ftl" as eventGadgets />

<#assign mystyles=["/tissue/css/content-2cols.css", "/tissue/css/dashboard.css"] in tissue>

<@tissue.layout "home">
    <div id="logo">
        <@personGadgets.dashboardLogo />
    </div>

    <div id="contentWrapper">
        <div id="sidebar">
            side bar
        </div>

        <div id="content">
            <@eventGadgets.showTopicRelatedEvents />
        </div>
    </div>
</@tissue.layout>

