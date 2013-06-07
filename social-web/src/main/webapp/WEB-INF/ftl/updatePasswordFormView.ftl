<#import "spring.ftl" as spring />
<#import "siteGadgets.ftl" as site />
<#import "userGadgets.ftl" as userGadgets />

<#assign title = "dashboard" in site>

<@site.layout>
    <@site.siteLogo />

    <div id="page-menu-wrapper">
        <div id="page-menu">
            <@userGadgets.settingMenu />
        </div>
    </div>

    <div id="page-main-wrapper">
        <div id="page-main">
            <div id="main-content">
                <@spring.bind "passwordForm.*" />
                <form id="updatePasswordForm" method="post" action="/social/_updatePassword">
                    <div class="error">
                       <@spring.showErrors "<br>" />
                    </div>
                    <legend>
                        <@spring.message "Legend.updatePasswordForm" />
                    </legend>
                    <ul>
                        <li>
                           <label for="password">
                              <@spring.message "Label.password" />
                           </label>
                           <@spring.formPasswordInput "passwordForm.password" 'class="sum"' />
                        </li>
                        <li>
                            <label for="confirm">
                                <@spring.message "Label.confirm" />
                            </label>
                            <@spring.formPasswordInput "passwordForm.confirm" 'class="sum"' />
                        </li>
                        <li>
                            <input type="submit" value='<@spring.message "SaveText.submit" />'/>
                        </li>
                    </ul>
                </form>
            </div>

            <div id="main-sidebar">
            <#--
                <@userGadgets.showPlansLearning/>
                <@userGadgets.showPlansLearned/>
                -->
            </div>


        </div>
    </div>
</@site.layout>

