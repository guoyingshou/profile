<#import "spring.ftl" as spring />
<#import "tissue.ftl" as tissue />
<#import "userGadgets.ftl" as userGadgets />

<#assign myscripts=["/ckeditor/ckeditor.js"] in tissue>

<@tissue.layout "cna">
    <div id="logo">
        <@userGadgets.personLogo />
    </div>

    <div id="contentWrapper">
        <div id="sidebar">
            <@userGadgets.showPlansOwned />
            <@userGadgets.showNewTopics />
        </div>

        <div id="content">
            <#if owner??>
                <@userGadgets.showFriends />
            </#if>
        </div>
    </div>

</@tissue.layout>
