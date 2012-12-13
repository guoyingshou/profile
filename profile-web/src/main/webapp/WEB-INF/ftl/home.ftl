<#import "tissue.ftl" as tissue />
<#import "gadgets.ftl" as gadgets />
<#import "spring.ftl" as spring />

<#assign mystyles=["http://www.tissue.com/resources/css/common.css", "http://www.tissue.com/resources/css/content-2cols.css", "http://www.tissue.com/resources/css/home.css"] in tissue>

<@tissue.layout "home">
    <div id="logo">
        <h1>Tissue Network</h1>
    </div>

    <div id="contentWrapper">
        <div id="sidebar">
            <ul>
                <li>
                    在线社会化学习工具
                </li>

                <li>
                    新一代的职业人士的社交网络平台：
                    <ul>
                        <li>分享学习方法</li>
                        <li>交流学习经验</li>
                        <li>展示个人能力</li>
                        <li>获取更多机会</li>
                    </ul>
                </li>
            </ul>
        </div>

        <div id="content">
            <@gadgets.showLatestEvents />
        </div>
    </div>
</@tissue.layout>

