<#import "spring.ftl" as spring />
<#import "tissue.ftl" as tissue />

<#assign myscripts=["/ckeditor/ckeditor.js"] in tissue>
<#assign mystyles=["/tissue/css/layout2.css"] in tissue>

<@tissue.layout "about">
    <div id="page-logo">
        <@tissue.aboutLogo />
    </div>

    <div id="page-content-wrapper">
        <div id="page-sidebar">
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

        <div id="page-content">
            <@tissue.showAbout />
        </div>
    </div>
</@tissue.layout>

