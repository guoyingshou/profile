<#import "spring.ftl" as spring />
<#import "commonGadgets.ftl" as commonGadgets />
<#import "commonGadgets.ftl" as commonGadgets />

<#assign myscripts=["/ckeditor/ckeditor.js"] in commonGadgets>
<#assign mystyles=["/tissue/css/layout2.css"] in commonGadgets>
<#assign title= "about" in commonGadgets>

<@commonGadgets.layout>
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
</@commonGadgets.layout>

