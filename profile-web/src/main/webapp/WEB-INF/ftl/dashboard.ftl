<#import "tissue.ftl" as tissue />
<#import "gadgets.ftl" as gadgets />
<#import "spring.ftl" as spring />

<#assign mystyles=["http://www.tissue.com/resources/css/content-2cols.css", "http://www.tissue.com/resources/css/dashboard.css"] in tissue>

<@tissue.layout "home">
    <div id="logo">
        <@tissue.dashboardLogo />
    </div>

    <div id="contentWrapper">
        <div id="sidebar">
            side bar
        </div>

        <div id="content">
            <@gadgets.showEvents />
        </div>
    </div>
</@tissue.layout>

