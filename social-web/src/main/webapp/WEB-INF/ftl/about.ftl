<#import "spring.ftl" as spring />
<#import "tissue.ftl" as tissue />
<#import "commonGadgets.ftl" as commonGadgets />

<#assign myscripts=["/ckeditor/ckeditor.js"] in tissue>
<#assign mystyles=["/tissue/css/layout2.css"] in tissue>
<#assign title= "about" in tissue>

<@tissue.layout>
    <div id="page-logo-wrapper">
        <div id="page-logo" class="page-center">
            <@commonGadgets.aboutLogo />
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
                <@commonGadgets.showAbout />
            </div>
        </div>
    </div>
</@tissue.layout>

