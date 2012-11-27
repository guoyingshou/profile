<#import "tissue.ftl" as tissue />
<#import "spring.ftl" as spring />

<#assign myscripts=["/ckeditor/ckeditor.js", "/ckeditor/adapters/jquery.js"] in tissue>

<#assign mystyles=["http://www.tissue.com/resources/css/content-2cols.css", "http://www.tissue.com/resources/css/cna.css"] in tissue>

<@tissue.layout "cna">
    <div id="logo">
        <@tissue.cnaLogo />
    </div>

    <div id="contentWrapper">
        <div id="sidebar">
        </div>

        <ul id="content">
        </ul>
    </div>

    <script>

        $(document).ready(function() {
            $('#content').load('/u2/plan/users/${owner.id}/posts');
        });

    </script>


</@tissue.layout>
