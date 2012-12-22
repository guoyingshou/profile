<#import "tissue.ftl" as tissue />
<#import "spring.ftl" as spring />

<#assign myscripts=["/ckeditor/ckeditor.js"] in tissue>

<#assign mystyles=["/tissue/css/content-2cols.css", "/tissue/css/cna.css"] in tissue>

<@tissue.layout "cna">
    <div id="logo">
        <div>
            <h1>Friends</h1> 
        </div>
    </div>

    <div id="contentWrapper">
        <div id="sidebar">
        </div>

        <div id="content">
            <#if friends??>
            <ul>
                <#list friends as friend>
                    <li>
                       <p><a href="<@spring.url '/profile/users/${friend.id}/' />">${friend.displayName}</a></p>
                    </li>
                </#list>
            </ul>
            </#if>
        </div>
    </div>

<#--
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
-->

</@tissue.layout>
