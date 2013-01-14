<#import "spring.ftl" as spring />
<#import "tissue.ftl" as tissue />
<#import "personGadgets.ftl" as personGadgets />

<#assign myscripts=["/ckeditor/ckeditor.js"] in tissue>

<@tissue.layout "cna">
    <div id="logo">
        <@personGadgets.personLogo />
    </div>

    <div id="contentWrapper">
        <div id="sidebar">
        </div>

        <div id="content">
            <#if viewer??>
            <ul>
                <#list owner.friends as friend>
                    <li>
                       <p><a href="<@spring.url '/users/${friend.id}/' />">${friend.displayName}</a></p>
                    </li>
                </#list>
            </ul>
            </#if>
        </div>
    </div>

</@tissue.layout>
