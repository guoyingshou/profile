<#import "spring.ftl" as spring />
<#import "commonGadgets.ftl" as commonGadgets />
<#import "userGadgets.ftl" as userGadgets />

<#assign title="signin" in commonGadgets>

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
        <@spring.bind "aboutForm.*" />
        <form id="aboutForm" method="post" action="<@spring.url '/about/_create'/>">
            <div class="error">
                <@spring.showErrors "<br>" />
            </div>
            <legend>
                About
            </legend>
            <ul>
                <li>
                    <@spring.formTextarea "aboutForm.content" 'class="sum"' />
                </li>
                <li>
                    <input type="submit" value="submit"/>
                </li>
            </ul>
        </form>
    </div>

</@commonGadgets.layout>

