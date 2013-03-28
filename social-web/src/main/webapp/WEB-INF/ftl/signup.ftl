<#import "spring.ftl" as spring />
<#import "siteGadgets.ftl" as site />

<#assign title = "sign up" in site />

<@site.layout>
    <#include "siteLogo.ftl" />

    <div id="page-main-wrapper">
        <@spring.bind 'signupForm.*' />
        <form id="signupForm" action="<@spring.url '/signup' />" method="post">
            <div class="error">
                <@spring.showErrors "<br/>" />
            </div>

            <legend>
                <@spring.message "Legend.signupForm" />
                <span class="requirement">
                    <@spring.message "Require.signupForm" />
               </span>
            </legend>
            <ul>
                <li>
                    <label for="username">
                        <@spring.message "Label.signupForm.username" />
                        <span style="display: none" class="error">
                            <@spring.message "Taken.signupForm.username" />
                        </span>
                    </label>
                    <@spring.formInput "signupForm.username" 'class="sum"' />
                 </li>
                 <li>
                     <label for="password">
                         <@spring.message "Label.signupForm.password" />
                         <span class="requirement">
                             <@spring.message "Size.signupForm.password" />
                         </span>
                     </label>
                     <@spring.formPasswordInput "signupForm.password" 'class="sum"' />
                  </li>
                  <li>
                      <label for="confirm">
                          <@spring.message "Label.signupForm.confirm" />
                      </label>
                      <@spring.formPasswordInput "signupForm.confirm" 'class="sum"' />
                  </li>
                  <li>
                    <label for="email">
                        <@spring.message "Label.signupForm.email" />
                        <span style="display: none" class="error">
                            <@spring.message "Taken.signupForm.email" />
                        </span>
                    </label>
                    <@spring.formInput "signupForm.email" 'class="sum"'/>
                </li>
                <li>
                     <label for="displayName">
                        <@spring.message "Label.signupForm.displayName" />
                    </label>
                    <@spring.formInput "signupForm.displayName" 'class="sum"'/>
                </li>
                <li>
                    <label for="headline">
                        <@spring.message "Label.signupForm.headline" />
                    </label>
                    <@spring.formTextarea "signupForm.headline" "class='sum'" />
                </li>
                <li>
                    <input type="submit" value='<@spring.message "Signup.button" />' />
                </li>
            </ul>
        </form>

    <div>
</@site.layout>

