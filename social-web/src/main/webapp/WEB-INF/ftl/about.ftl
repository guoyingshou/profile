<#import "spring.ftl" as spring />
<#import "siteGadgets.ftl" as site />

<#assign myscripts=["/ckeditor/ckeditor.js"] in site>
<#assign title= "about" in site>

<@site.layout>

    <@site.siteLogo />

    <div id="page-menu-wrapper">
        <div id="page-menu">
            <@site.aboutMenu />
        </div>
    </div>

    <div id="page-main-wrapper">
        <div id="page-main">

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

        </div>
    </div>
</@site.layout>

