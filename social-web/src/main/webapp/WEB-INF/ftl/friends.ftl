<#import "spring.ftl" as spring />
<#import "tissue.ftl" as tissue />
<#import "userGadgets.ftl" as userGadgets />
<#import "topicGadgets.ftl" as topicGadgets />

<#assign myscripts=["/ckeditor/ckeditor.js"] in tissue>
<#assign mystyles=["/tissue/css/layout2.css"] in tissue>

<@tissue.layout "cna">
    <div id="logo">
        <@userGadgets.userLogo />
    </div>

    <div id="contentWrapper">
        <div id="sidebar">
            <@userGadgets.showOwnedPlans/>
            <@userGadgets.showArchivedPlans/>
            <@topicGadgets.showNewTopics />
        </div>

        <div id="content">
            <#if owner??>
                <@userGadgets.showFriends />
            </#if>
        </div>
    </div>

</@tissue.layout>
