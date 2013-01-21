<#import "spring.ftl" as spring />
<#import "tissue.ftl" as tissue />

<#assign myscripts=["/ckeditor/ckeditor.js"] in tissue>
<#--
<#assign mystyles=["/tissue/css/dashboard.css"] in tissue>
-->

<@tissue.layout "about">
    <div id="logo">
        <@tissue.aboutLogo />
    </div>

    <div id="contentWrapper">
        <div id="sidebar">
            side bar
        </div>

        <div id="content">
            <@tissue.showAbout />
        </div>
    </div>
</@tissue.layout>

