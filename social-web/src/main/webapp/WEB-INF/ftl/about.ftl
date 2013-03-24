<#import "spring.ftl" as spring />
<#import "userGadgets.ftl" as userGadgets />
<#import "commonGadgets.ftl" as commonGadgets />

<#assign myscripts=["/ckeditor/ckeditor.js"] in commonGadgets>
<#assign mystyles=["/tissue/css/layout2.css"] in commonGadgets>
<#assign title= "about" in commonGadgets>

<@commonGadgets.layout>
    <div id="page-logo-wrapper">
        <div id="page-logo" class="page-center">
            <@userGadgets.homeLogo />
        </div>
    </div>

    <div id="page-main-wrapper">
        <div id="page-main">
            <div id="main-sidebar">
                <h4>Main Features</h4>
                <ul>
                    <li>
                     create topics
                    </li>
                    <li>
                     host or join a group
                    </li>
                    <li>
                     make new friends
                    </li>
                </ul>
            </div>

            <div id="main-content">
                <ul>
                    <#if abouts??>
                    <#list abouts as about>
                    <li>${about.content}</li>
                    </#list>
                    </#if>
                </ul>

                <#if viewerAccount??>
                <a class="add-about" data-action="<@spring.url '/about/_create' />" href="#">add</a>

                <form id="aboutForm" class="dialog pop-650" style="display: none" method="post">
                    <legend>
                        About
                        <a href="#" class="cancel"><span data-icon="&#xe008"></span></a>
                    </legend>
                    <ul>
                        <li>
                            <textarea id="content" name="content"></textarea>
                        </li>
                        <li>
                            <input type="submit" value="submit"/>
                        </li>
                    </ul>
                </form>
                </#if>
            </div>
        </div>
    </div>
</@commonGadgets.layout>

