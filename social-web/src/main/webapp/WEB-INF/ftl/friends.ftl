<#import "spring.ftl" as spring />
<#import "tissue.ftl" as tissue />
<#import "userGadgets.ftl" as userGadgets />

<#assign myscripts=["/ckeditor/ckeditor.js"] in tissue>
<#assign mystyles=["/tissue/css/layout2.js"] in tissue>

<@tissue.layout "cna">
    <div id="logo">
        <@userGadgets.personLogo />
    </div>

    <div id="contentWrapper">
        <div id="sidebar">
            <@userGadgets.showOwnedPlans/>
            <@userGadgets.showArchivedPlans/>
            <@userGadgets.showNewTopics />
        </div>

        <div id="content">
            <#if owner??>
                <@userGadgets.showFriends />
            </#if>
        </div>
    </div>

</@tissue.layout>
