<#import "tissue.ftl" as tissue />
<#import "gadgets.ftl" as gadgets />
<#import "spring.ftl" as spring />

<#assign mystyles=["/tissue/css/content-2cols.css", "/tissue/css/dashboard.css"] in tissue>

<@tissue.layout "home">
    <div id="logo">
        <@tissue.dashboardLogo />
    </div>

    <div id="contentWrapper">
        <div id="sidebar">
            side bar
        </div>

        <div id="content">
            <@gadgets.showTopicRelatedEvents />
        </div>
    </div>
</@tissue.layout>

