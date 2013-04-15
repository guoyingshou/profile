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
                <@spring.message "signupForm" />
                <span class="constraint">
                    <@spring.message "Required.signupForm" />
               </span>
            </legend>
            <ul>
                <li>
                    <label for="username">
                        <@spring.message "username" />
                        <span style="display: none" class="error">
                            <@spring.message "Taken.username" />
                        </span>
                    </label>
                    <@spring.formInput "signupForm.username" />
                 </li>
                 <li>
                     <label for="password">
                         <@spring.message "password" />
                         <span class="constraint">
                             <@spring.message "Size.password" />
                         </span>
                     </label>
                     <@spring.formPasswordInput "signupForm.password" />
                  </li>
                  <li>
                      <label for="confirm">
                          <@spring.message "confirm" />
                      </label>
                      <@spring.formPasswordInput "signupForm.confirm" />
                  </li>
                  <li>
                    <label for="email">
                        <@spring.message "email" />
                        <span style="display: none" class="error">
                            <@spring.message "Taken.email" />
                        </span>
                    </label>
                    <@spring.formInput "signupForm.email" />
                </li>
                <li>
                     <label for="displayName">
                         <@spring.message "displayName" />
                         <span class="constraint">
                             <@spring.message "Size.displayName" />
                         </span>
                     </label>
                    <@spring.formInput "signupForm.displayName" />
                </li>
                <li>
                    <label for="headline">
                         <@spring.message "headline" />
                         <span class="constraint">
                             <@spring.message "Size.headline" />
                         </span>
                     </label>
                    <@spring.formTextarea "signupForm.headline" />
                </li>
                <li>
                    <input type="submit" value='<@spring.message "Signup.button" />' />
                </li>
            </ul>
        </form>

    <div>
</@site.layout>

