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

            <div id="main-content" class="layout2-content">
                <@spring.bind "emailForm.*" />
                <form id="updateEmailForm" method="post" action="/social/_updateEmail">
                    <div class="error">
                      <@spring.showErrors "<br>" />
                    </div>
                    <legend>
                        <@spring.message "Legend.emailForm" />
                    </legend>
                    <ul>
                       <li>
                          <label for="email">
                              <@spring.message "Label.emailForm.email" />
                          </label>
                          <@spring.formInput "emailForm.email" 'class="sum"' />
                       </li>
                       <li>
                           <input type="submit" value='<@spring.message "Save.button"/>'/>
                       </li>
                    </ul>
                </form>
            </div>
        </div>
    </div>
</@commonGadgets.layout>

