<#import "spring.ftl" as spring />
<#import "tissue.ftl" as tissue />

<#assign myscripts=["/ckeditor/ckeditor.js"] in tissue>

<#--
<#assign mystyles=["/tissue/css/cna.css"] in tissue>
-->

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
                       <p><a href="<@spring.url '/users/${friend.id}/' />">${friend.displayName}</a></p>
                    </li>
                </#list>
            </ul>
            </#if>
        </div>
    </div>

</@tissue.layout>
