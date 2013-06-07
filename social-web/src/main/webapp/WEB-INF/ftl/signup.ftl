<#import "spring.ftl" as spring />
<#import "siteGadgets.ftl" as site />

<#assign title = "sign up" in site />

<@site.layout>
    <@site.siteLogo />

    <div id="page-main-wrapper">
        <@spring.bind 'signupForm.*' />
        <form id="signupForm" action="<@spring.url '/signup' />" method="post">
            <div class="error">
                <@spring.showErrors "<br/>" />
            </div>

            <legend>
                <@spring.message "Legend.signupForm" />
                <span class="constraint">
                    <@spring.message "Required.signupForm" />
               </span>
            </legend>

            <div class="signup-main">
            <ul class="account">
                <li>
                    <label for="username">
                        <@spring.message "Label.username" />
                        <span style="display: none" class="error">
                            <@spring.message "Taken.username" />
                        </span>
                    </label>
                    <@spring.formInput "signupForm.username" />
                 </li>
                 <li>
                     <label for="password">
                         <@spring.message "Label.password" />
                         <span class="constraint">
                             <@spring.message "Size.password" />
                         </span>
                     </label>
                     <@spring.formPasswordInput "signupForm.password" />
                  </li>
                  <li>
                      <label for="confirm">
                          <@spring.message "Label.confirm" />
                      </label>
                      <@spring.formPasswordInput "signupForm.confirm" />
                  </li>
                  <li>
                    <label for="email">
                        <@spring.message "Label.email" />
                        <span style="display: none" class="error">
                            <@spring.message "Taken.email" />
                        </span>
                    </label>
                    <@spring.formInput "signupForm.email" />
                </li>
            </ul>

            <ul class="user">
                <li>
                     <label for="displayName">
                         <@spring.message "Label.displayName" />
                         <span class="constraint">
                             <@spring.message "Size.displayName" />
                         </span>
                     </label>
                    <@spring.formInput "signupForm.displayName" />
                </li>
                <li>
                    <label for="headline">
                         <@spring.message "Label.headline" />
                         <span class="constraint">
                             <@spring.message "Size.headline" />
                         </span>
                     </label>
                    <@spring.formTextarea "signupForm.headline" />
                </li>
                <li>
                    <input type="submit" value='<@spring.message "SignupText.submit" />' />
                </li>
            </ul>
            </div>
        </form>
    </div>
</@site.layout>

