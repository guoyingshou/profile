<#import "spring.ftl" as spring />
<#import "siteGadgets.ftl" as site />

<#--
<#assign myscripts=["/ckeditor/ckeditor.js"] in site>
-->
<#assign title= "about" in site>

<@site.layout>

    <@site.siteLogo />

    <div id="page-menu-wrapper">
        <div id="page-menu">
            <@site.aboutMenu />
        </div>
    </div>

    <div id="page-main-wrapper">
        <div id="page-main">

            <div id="main-content">
            <#--
                <@spring.message 'aboutSite' />
                -->
            </div>

            <div id="main-sidebar">
                <@spring.message 'Objective.site' />
            </div>

        </div>
    </div>
</@site.layout>

