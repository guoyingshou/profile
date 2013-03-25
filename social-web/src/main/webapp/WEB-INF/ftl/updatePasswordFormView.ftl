<#import "spring.ftl" as spring />
<#import "commonGadgets.ftl" as commonGadgets />
<#import "userGadgets.ftl" as userGadgets />
<#import "topicGadgets.ftl" as topicGadgets />
<#import "activityGadgets.ftl" as activityGadgets />

<#assign mystyles=["/tissue/css/layout2.css"] in commonGadgets>
<#assign title = "dashboard" in commonGadgets>

<@commonGadgets.layout>
    <div id="page-logo-wrapper">
        <div id="page-logo">
            <@userGadgets.homeLogo />
        </div>
    </div>

    <div id="page-menu-wrapper">
        <div id="page-menu">
            <@userGadgets.settingMenu />
        </div>
    </div>

    <div id="page-main-wrapper">
        <div id="page-main">
            <div id="main-sidebar">
            <#--
                <@userGadgets.showPlansLearning/>
                <@userGadgets.showPlansLearned/>
                -->
            </div>

            <div id="main-content">
                <@spring.bind "passwordForm.*" />
                <form id="updatePasswordForm" method="post" action="/social/_updatePassword">
                    <div class="error">
                       <@spring.showErrors "<br>" />
                    </div>
                    <legend>
                        <@spring.message "Legend.passwordForm" />
                    </legend>
                    <ul>
                        <li>
                           <label for="password">
                              <@spring.message "Label.passwordForm.password" />
                           </label>
                           <@spring.formPasswordInput "passwordForm.password" 'class="sum"' />
                        </li>
                        <li>
                            <label for="confirm">
                                <@spring.message "Label.passwordForm.confirm" />
                            </label>
                            <@spring.formPasswordInput "passwordForm.confirm" 'class="sum"' />
                        </li>
                        <li>
                            <input type="submit" value='<@spring.message "Save.button" />'/>
                        </li>
                    </ul>
                </form>
            </div>
        </div>
    </div>
</@commonGadgets.layout>

