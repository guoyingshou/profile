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
                <@spring.bind "headlineForm.*" />
                <form id="updateHeadlineForm" method="post" action="/social/_updateHeadline">

                <div class="error">
                    <@spring.showErrors "<br>" />
                </div>
 
                <legend>
                    <@spring.message "Legend.updateHeadlineForm" />
                </legend>
                <ul>
                    <li>
                        <label for="displayName">
                            <@spring.message "Label.displayName" />
                            <span class="constraint">
                                <@spring.message "Size.displayName" />
                            </span>
                        </label>
                        <@spring.formInput "headlineForm.displayName" />
                    </li>
                    <li>
                        <label for="headline">
                            <@spring.message "Label.headline" />
                            <span class="constraint">
                                <@spring.message "Size.headline" />
                            </span>
                        </label>
                        <@spring.formTextarea "headlineForm.headline" />
                    </li>
                    <li>
                        <input type="submit" value='<@spring.message "SaveText.submit" />' />
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

