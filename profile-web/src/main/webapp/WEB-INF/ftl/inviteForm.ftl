<#import "tissue.ftl" as tissue />
<#import "spring.ftl" as spring />

<#assign myscripts=["/ckeditor/ckeditor.js"] in tissue>

<#assign mystyles=["/tissue/css/content-2cols.css", "/tissue/css/cna.css"] in tissue>

<@tissue.layout "invitation">
    <div id="logo">
       <h1>${owner.displayName}</h1> 
    </div>

    <div id="menu">
        <#--
        <@tissue.menu />
        -->
    </div>

    <div id="contentWrapper">
        <div id="sidebar">
        </div>

        <div id="content">
            <form method="POST" action="<@spring.url '/users/${owner.id}/invites' />">
                
                <p><label for="letter">invitation letter</label></p>

                <p><textarea id="letter" name="content"></textarea></p>
                
                <input type="submit" name="submit" vaule="submit" />
            </form>
        </div>
    </div>

    <script>
        $(document).ready(function() {
        });
    </script>


</@tissue.layout>
