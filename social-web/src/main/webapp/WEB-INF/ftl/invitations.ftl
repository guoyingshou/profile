<#import "spring.ftl" as spring />
<#import "tissue.ftl" as tissue />
<#import "userGadgets.ftl" as userGadgets />

<#assign myscripts=["/ckeditor/ckeditor.js"] in tissue>
<#assign mystyles=["/tissue/css/layout2.css"] in tissue>

<@tissue.layout "cna">
<div id="logo">
    <@userGadgets.homeLogo />
</div>

<div id="contentWrapper">
    <div id="sidebar">
        <@userGadgets.showOwnedPlans/>
        <@userGadgets.showArchivedPlans/>
    </div>

    <div id="content">
        <#if viewer??>
        <ul>
        <#list viewer.invitationsReceived as invitation>
            <li>
                <div>${invitation.invitor.displayName}</div>
                <div>${invitation.createTime?date}</div>
                <div>${invitation.content}</div>
                <div class="intention">
                    <a class="process-invite" data-action="<@spring.url '/invitations/${invitation.id}/accept' />" href="#">
                        Accept
                    </a>

                    <a class="process-invite" data-action="<@spring.url '/invitations/${invitation.id}/decline' />" href="#">
                        Decline
                    </a>
                </div>
            </li>
         </#list>
         </ul>
         </#if>
    </div>
</div>

</@tissue.layout>
