<#import "spring.ftl" as spring />
<#import "userGadgets.ftl" as userGadgets />
<#import "commonGadgets.ftl" as commonGadgets />

<#assign myscripts=["/ckeditor/ckeditor.js"] in commonGadgets>
<#assign mystyles=["/tissue/css/layout2.css"] in commonGadgets>
<#assign title= "about" in commonGadgets>

<@commonGadgets.layout>
    <div id="page-logo-wrapper">
        <div id="page-logo">
            <@userGadgets.homeLogo />
        </div>
    </div>

    <div id="page-menu-wrapper">
        <div id="page-menu">
            <@userGadgets.aboutMenu />
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
                <div>
                   <h3>About education</h3>
                   <div>
                      fjadkfd
                   </div>
                </div>

                <div>
                   <h3>About learning</h3>
                   <div>
                      fjadkfd
                   </div>
                </div>

                <div>
                   <h3>About skills</h3>
                   <div>
                      fjadkfd
                   </div>
                </div>
            </div>
        </div>
    </div>
</@commonGadgets.layout>

