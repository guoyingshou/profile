<#import "spring.ftl" as spring />
<#import "commonGadgets.ftl" as commonGadgets />
<#import "userGadgets.ftl" as userGadgets />
<#import "commonGadgets.ftl" as commonGadgets />
<#import "topicGadgets.ftl" as topicGadgets />
<#import "activityGadgets.ftl" as activityGadgets />

<#assign myscripts=["/ckeditor/ckeditor.js"] in commonGadgets>
<#assign mystyles=["/tissue/css/layout2.css"] in commonGadgets>

<#assign title= owner.displayName in commonGadgets>

<@commonGadgets.layout>
    <div id="page-logo-wrapper">
        <div id="page-logo">
            <@userGadgets.userLogo />
        </div>
    </div>

    <div id="page-menu-wrapper">
        <div id="page-menu">
            <@userGadgets.userMenu />
        </div>
    </div>

    <div id="page-main-wrapper">
        <div id="page-main">
            <div id="main-sidebar">
                <@userGadgets.showPlansLearning/>
                <@userGadgets.showPlansLearned/>
            </div>

           <div id="main-content">
               <@topicGadgets.showPosts />
               <@commonGadgets.showPager />
           </div>
       </div>
    </div>
</@commonGadgets.layout>
