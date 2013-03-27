<#import "spring.ftl" as spring />
<#import "commonGadgets.ftl" as commonGadgets />
<#import "userGadgets.ftl" as userGadgets />
<#import "topicGadgets.ftl" as topicGadgets />
<#import "activityGadgets.ftl" as activityGadgets />

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
            <div id="main-content">
                <@spring.bind "headlineForm.*" />
                <form id="updateHeadlineForm" method="post" action="/social/_updateHeadline">

                <div class="error">
                    <@spring.showErrors "<br>" />
                </div>
 
                <legend>
                    <@spring.message "Legend.headlineForm" />
                </legend>
                <ul>
                    <li>
                        <label for="displayName">
                            <@spring.message "Label.headlineForm.displayName" />
                        </label>
                        <@spring.formInput "headlineForm.displayName" 'class="sum"' />
                    </li>
                    <li>
                        <label for="headline">
                            <@spring.message "Label.headlineForm.headline" />
                        </label>
                        <@spring.formTextarea "headlineForm.headline" 'class="sum"' />
                    </li>
                    <li>
                        <input type="submit" value='<@spring.message "Save.button" />' />
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
</@commonGadgets.layout>

