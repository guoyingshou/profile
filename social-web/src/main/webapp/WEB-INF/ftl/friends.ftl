<#import "spring.ftl" as spring />
<#import "tissue.ftl" as tissue />
<#import "personGadgets.ftl" as personGadgets />

<#assign myscripts=["/ckeditor/ckeditor.js"] in tissue>

<@tissue.layout "cna">
    <div id="logo">
        <@personGadgets.personLogo />
    </div>

    <div id="contentWrapper">
        <div id="sidebar">
            <@personGadgets.showPlansOwned />
            <@personGadgets.showNewTopics />
        </div>

        <div id="content">
            <#if owner??>
                <@personGadgets.showFriends />
            </#if>
        </div>
    </div>

</@tissue.layout>
