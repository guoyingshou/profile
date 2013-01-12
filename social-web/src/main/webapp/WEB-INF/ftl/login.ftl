<#import "spring.ftl" as spring />

<form action="<@spring.url '/jsc'/>" method="post">
    name: <input type="text" id="j_username" name="j_username" size="20" maxlength="50" />
    <br/>
    pass: <input type="password" id="j_password" name="j_password" size="20" maxlength="50" />
    <br/>

    <!--
    <input type='checkbox' name='_spring_security_remember_me' id="_spring_security_remember_me" tabindex="3" value="true"/>
    <br/>
    -->

    <input type="submit" value="Login"/>
</form>
