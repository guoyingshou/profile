<#import "spring.ftl" as spring />
<#import "tissue.ftl" as tissue />
<#import "personGadgets.ftl" as personGadgets />

<#assign myscripts=["/ckeditor/ckeditor.js"] in tissue>

<#--
<#assign mystyles=["/tissue/css/cna.css"] in tissue>
-->

<@tissue.layout "cna">
    <div id="logo">
        <@personGadgets.homeLogo />
    </div>

    <div id="contentWrapper">
        <div id="sidebar">
            <@personGadgets.showPlansOwned />
        </div>

        <div id="content">
            <#if viewer??>
            <ul>
                <#list viewer.invitationsReceived as invitation>
                    <li>
                       <p>${invitation.invitor.displayName}</p>
                       <p>${invitation.createTime?date}</p>
                       <p>${invitation.content}</p>
                       <form method="post" action="<@spring.url '/invitations/${invitation.id}' />">
                            <input type="submit" id="accept" value="accept"/>
                            <input type="submit" id="decline" value="decline"/>
                       </form>
                    </li>
                </#list>
            </ul>
            </#if>
        </div>
    </div>

    <script>
        $(document).ready(function() {
            $('form input[type="submit"]').on('click', function(e) {
                e.preventDefault();
                $form = $(this).parent();
                $action = $form.attr("action");
                $.post($action, {'action': e.target.id}, function(data) {
                    $form.replaceWith("<p>ok</p>");
                });
            }); 
        });
    </script>

</@tissue.layout>
