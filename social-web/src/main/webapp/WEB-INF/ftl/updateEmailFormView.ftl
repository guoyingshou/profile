<#import "spring.ftl" as spring />
<#import "siteGadgets.ftl" as site />
<#import "userGadgets.ftl" as userGadgets />
<#import "topicGadgets.ftl" as topicGadgets />

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
                <@spring.bind "emailForm.*" />
                <form id="updateEmailForm" method="post" action="/social/_updateEmail">
                    <div class="error">
                      <@spring.showErrors "<br>" />
                    </div>
                    <legend>
                        <@spring.message "Legend.updateEmailForm" />
                    </legend>
                    <ul>
                       <li>
                          <label for="email">
                              <@spring.message "Label.email" />
                          </label>
                          <@spring.formInput "emailForm.email" 'class="sum"' />
                       </li>
                       <li>
                           <input type="submit" value='<@spring.message "SaveText.submit"/>'/>
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

