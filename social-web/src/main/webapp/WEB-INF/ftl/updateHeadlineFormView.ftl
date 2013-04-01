<#import "spring.ftl" as spring />
<#import "siteGadgets.ftl" as site />
<#import "userGadgets.ftl" as userGadgets />

<#assign title = "dashboard" in site>

<@site.layout>
    <#include "siteLogo.ftl" />

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
                    <@spring.message "updateHeadlineForm" />
                </legend>
                <ul>
                    <li>
                        <label for="displayName">
                            <@spring.message "displayName" />
                        </label>
                        <@spring.formInput "headlineForm.displayName" 'class="sum"' />
                    </li>
                    <li>
                        <label for="headline">
                            <@spring.message "headline" />
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
</@site.layout>

