<#import "tissue.ftl" as tissue />
<#import "spring.ftl" as spring />

<#assign mystyles=["/tissue/css/content-2cols.css", "/tissue/css/signup.css"] in tissue>

<@tissue.layout "signup">
    <div id="logo">
        <h1>Tissue Network</h1>
    </div>

    <div id="contentWrapper">
        <div id="sidebar">
            <p>By clicking on "Create an account" below, you are agreeing to the Terms of Service and the Privacy Policy</p>
        </div>

        <div id="content">
            <div class="accountForm">
                <form action="<@spring.url '/signup' />" method="post">

                    <fieldset class="post">
                        <legend>Create your free personal account</legend>

                        <ul>
                            <li>
                                <label for="username">Username</label>
                                <@spring.formInput path="account.username" attributes="id='username' class='ac'" />
                                <p><@spring.showErrors "" /></p>
                            </li>

                            <li>
                                <label for="password">Password</label>
                                <@spring.formPasswordInput path="account.password" attributes="id='password' class='ac'" />
                                <p><@spring.showErrors "" /></p>
                            </li>

                            <li>
                                <label for="confirm">Confirm Password</label>
                                <@spring.formPasswordInput path="account.confirm" attributes="id='confirm' class='ac'" />
                                <p><@spring.showErrors "" /></p>
                            </li>

                            <li>
                                <label for="displayName">DisplayName</label>
                                <@spring.formInput path="account.displayName" attributes="id='displayName' class='ac'" />
                                <p><@spring.showErrors "" /></p>
                            </li>

                            <li>
                                <label for="email">Email</label>
                                <@spring.formInput path="account.email" attributes="id='email' class='ac'" />
                                <p><@spring.showErrors "" /></p>
                            </li>

                            <li>
                                <input type="submit" value="create an account" />
                            </li>
                        </ul>

                    </fieldset>
                </form>
            </div>
        </div>
    </div>
</@tissue.layout>

