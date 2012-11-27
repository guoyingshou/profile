<#import "tissue.ftl" as tissue />
<#import "spring.ftl" as spring />

<#assign myscripts=["/ckeditor/ckeditor.js", "/ckeditor/adapters/jquery.js"] in tissue>

<#assign mystyles=["http://www.tissue.com/resources/css/content-2cols.css", "http://www.tissue.com/resources/css/cna.css"] in tissue>

<@tissue.layout "cna">
    <div id="logo">
        <div>
            <h1>invitations</h1> 
        </div>
    </div>

    <div id="contentWrapper">
        <div id="sidebar">
        </div>

        <div id="content">
            <#if invitations??>
            <ul>
                <#list invitations as invitation>
                    <li>
                       <p>${invitation.invitor.displayName}</p>
                       <p>${invitation.createTime?date}</p>
                       <p>${invitation.content}</p>
                       <form method="post" action="<@spring.url '/profile/invitations/${invitation.id}' />">
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
