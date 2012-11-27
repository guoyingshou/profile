<#import "tissue.ftl" as tissue />
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
            content

        </div>
    </div>
</@tissue.layout>

