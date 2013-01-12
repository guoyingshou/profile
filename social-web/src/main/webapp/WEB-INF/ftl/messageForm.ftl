<#import "spring.ftl" as spring />
<#import "tissue.ftl" as tissue />
<#import "personGadgets.ftl" as personGadgets />

<#assign myscripts=["/ckeditor/ckeditor.js", "/tissue/js/pos.js"] in tissue>

<#--
<#assign mystyles=["/resources/css/cna.css"] in tissue>
-->

<@tissue.layout "message">
    <div id="logo">
        <@personGadgets.personLogo />
    </div>

    <div id="contentWrapper">
        <div id="sidebar">
        </div>

        <div id="content">
            <form method="POST" action="<@spring.url '/users/${owner.id}/messages' />">
                
                <p><label for="message">message content</label></p>

                <p><textarea id="message" name="content"></textarea></p>
                
                <input type="submit" name="submit" vaule="submit" />
            </form>
        </div>
    </div>

    <script>
        $(document).ready(function() {
        });
    </script>


</@tissue.layout>
