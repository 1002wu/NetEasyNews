package cn.bproject.neteasynews.network

class Api {
    companion object{
        const val host = "http://c.m.163.com/"

        // 普通栏目前缀, 示例： http://c.m.163.com/nc/article/list/T1467284926140/0-20.html
        const val CommonUrl = host + "nc/article/list/"

        // 图片栏目的前缀， 示例： http://pic.news.163.com/photocenter/api/list/0031/6LRK0031,6LRI0031/0/20.json
        // 推荐图片：0031， 新闻图片： 0001 ， 明星图片：0003
        const val PictureBaseUrl = "http://pic.news.163.com/"
        const val PicRecommendColumn = "6LRK0031,6LRI0031"
        const val PicNewsColumn = "00AP0001,3R710001,4T8E0001"
        const val PicHotColumn = "00AN0001,00AO0001"
        const val PicStarColumn = "00AJ0003,0AJQ0003,3LF60003,00B70003,00B50003"

        // 特殊频道前缀：热点、网易号适用
        const val SpecialColumn1 = "recommend/getSubDocPic?"

        // 特殊频道前缀:	视频/段子、美女、萌宠适用
        const val SpecialColumn2 = "recommend/getChanListNews"
        // 文章详情前缀
        const val DetailUrl = host + "nc/article/"

        // 普通新闻栏目的结尾，注意要在前 面添加新闻从那一条开始获取
        // 如 ：http://c.m.163.com/nc/article/list/T1467284926140/0-20.html，获取最新的20条新闻
        // http://c.m.163.com/nc/article/list/T1467284926140/20-20.html，获取从第20条开始的后面20条新闻
        const val endUrl = "-20.html"

        // 所有特殊频道的结尾，如：热点、网易号、段子、美女、萌宠
        const val SpecialendUrl = "&size=10&offset="

        /**
         * 新闻文章详情页链接尾部
         * 示例：http://c.m.163.com/nc/article/C8T2G5QL0511A99L/full.html
         * 其中postId ：C8T2G5QL0511A99L
         */
        const val endDetailUrl = "/full.html"

        /**
         * 图片新闻详情页连接
         * 链接示例： http://c.m.163.com/photo/api/set/0031/13897.json
         * 图片详情页：0031为图片频道（列表连接后面的0031），13897为图片新闻setid
         */
        const val PictureDetailUrl = host + "photo/api/set/"
        // 图片新闻详情页结尾
        const val endPictureDetailUrl = ".json"


        /**
         * 视频详情链接
         * 示例： http://c.m.163.com/nc/video/detail/VC5BA022H.html
         * 其中VC5BA022H是视频的vid
         */
        const val videoDetailUrl = host + "nc/video/detail/"
        // 视频详情链接尾部
        const val EndUrlVideoDetailUrl = ".html"


        // 独家
        const val zhenhuaId = "T1370583240249"

        // NBA
        const val NBAId = "T1348649145984"

        // 头条
        const val toutiaoId = "T1348647909107"

        // 社会
        const val shehuiId = "T1348648037603"

        // 历史
        const val lishiId = "T1368497029546"

        // 军事
        const val junshiId = "T1348648141035"

        // 哒哒趣闻
        const val dadaId = "T1444289532601"

        // 航空
        const val aviationId = "T1474271789612"

        // 要闻
        const val yaowenspecialId = "T1467284926140"

        // 娱乐
        const val yuleId = "T1348648517839"

        // 影视歌
        const val dianyingId = "T1348648650048"

        // 财经
        const val caijingId = "T1348648756099"

        // 股票
        const val stockId = "T1473054348939"

        // 彩票
        const val caipiaoId = "T1356600029035"

        // 体育
        const val tiyuId = "T1348649079062"

        // 中国足球
        const val zhongchaoId = "T1348649503389"

        // 国际足球
        const val zuqiuId = "T1348649176279"

        // CBA
        const val CBAId = "T1348649475931"

        // 跑步
        const val paobuId = "T1411113472760"

        // 科技
        const val kejiId = "T1348649580692"

        // 手机
        const val shoujiId = "T1348649654285"

        // 数码
        const val shumaId = "T1348649776727"

        // 智能
        const val zhinengId = "T1351233117091"

        // 轻松一刻
        const val qingsongyikeId = "T1350383429665"

        // 云课堂
        const val gongkaikeId = "T1421997195219"

        // 态度公开课
        const val attitudeopenId = "T1456394562871"

        // 汽车
        const val qicheId = "T1348654060988"

        // 房产
        const val fangchanId = "T1348654085632"

        // 家居
        const val jiajuId = "T1348654105308"

        // 游戏
        const val youxiId = "T1348654151579"

        // 旅游
        const val lvyouId = "T1348654204705"

        // 健康
        const val jiankangId = "T1414389941036"

        // 读书
        const val dushuId = "T1401272877187"

        // 酒香
        const val jiuxiangId = "T1385429690972"

        // 教育
        const val jiaoyuId = "T1348654225495"

        // 亲子
        const val qinziId = "T1397116135282"

        // 暴雪游戏
        const val baoxueId = "T1397016069906"

        // 漫画
        const val manhuaId = "T1444270454635"

        // 二次元
        const val erciyuanlanmuId = "T1481105123675"

        // 态度营销
        const val taiduyingxiaoId = "T1464592736048"

        // 时尚
        const val nvrenId = "T1348650593803"

        // 情感
        const val qingganId = "T1348650839000"

        // 政务
        const val zhengwuId = "T1414142214384"

        // 艺术
        const val ArtId = "T1441074311424"

        // 阳光法院
        const val yangguangfayuanId = "T1482470888760"

        // 跟贴
        const val specialCommentId = "T1419315959525"

        // 海外
        const val zongheId = "T1462426218632"


        // 本地   暂不使用
        const val specialLocalId = "T1419316531256"

        // 博客
        const val specialBlogId = "T1419386532423"

        // 论坛
        const val specialBBSId = "T1419386592923"


        // 直播
        const val specialLiveId = "T1433137697241"


        // 图片新闻尾部，需要在签名添加参数，可获得从某条新闻之后的20条新闻
        // 示例 ： http://pic.news.163.com/photocenter/api/list/0001/00AN0001,00AO0001/0/20.json
        const val endPicture = "/20.json"
        // 图片
        const val specialPictureId = "T1419316384474"

        // 推荐图片：0031/6LRK0031,6LRI0031/    应使用瀑布流
        const val RecommendPictureId = "0031"
        const val RecommendPicture = "/6LRK0031,6LRI0031/"

        // 新闻图片：0001/00AP0001,3R710001,4T8E0001/    横向排版
        const val NewsPictureId = "0001"
        const val NewsPicture = "/00AP0001,3R710001,4T8E0001/"
        // 热点图片：0001/00AN0001,00AO0001/     横向排版
        const val HotPicture = "/00AN0001,00AO0001/"

        // 明星图片：0003/00AJ0003,0AJQ0003,3LF60003,00B70003,00B50003/      瀑布流排版
        const val StarPictureId = "0003"
        const val StarPicture = "/00AJ0003,0AJQ0003,3LF60003,00B70003,00B50003/"


        /**
         * 特殊格式新闻：
         * 下面几类连接基本一致：
         * 热点、网易号类的新闻连接形式差不多，但是网易号多了from参数
         * 使用getSubDocPic
         *
         * 段子、美女、萌宠连接基本一致
         * 使用	getChanListNews
         */
        // 热点
        const val specialRedianId = "T1427878984398"
        // 热点url
        const val RedianUrl = host + SpecialColumn1 + SpecialendUrl

        // 网易号
        const val specialSubId = "T1449126525962"
        // 网易号url
        const val WangYiHaoUrl = host + SpecialColumn1 + "from=netease_h" + SpecialendUrl

        // 下列为特殊频道URL， 统一连接形式为：
        // http://c.m.163.com/recommend/getChanListNews?channel=T1419316284722&size=10&offset=
        const val specialURL = host + SpecialColumn2

        // 视频
        // http://c.m.163.com/recommend/getChanListNews?channel=T1457068979049&offset=0&size=20&devId=44t6%2B5mG3ACAOlQOCLuIHg%3D%3D
        const val specialVideoId = "T1457068979049"
        // 手机开发id号，任意手机的开发Id号都可以
        const val devId = "&devId=44t6%2B5mG3ACAOlQOCLuIHg%3D%3D"

        // 段子
        const val specialJokeId = "T1419316284722"

        // 美女
        const val specialGirlId = "T1456112189138"

        // 萌宠
        const val specialAnimalId = "T1456112438822"

        // 热点视频 http://c.m.163.com/nc/video/list/V9LG4B3A0/n/10-10.html
        const val Video = host + "nc/video/list/"
        const val VideoCenter = "/n/"
        const val videoEndUrl = "-20.html"
        // 热点视频
        const val VideoReDianId = "V9LG4B3A0"
        // 推荐视频
        // http://c.m.163.com/recommend/getChanListNews?channel=T1457068979049&offset=0&size=20&devId=44t6%2B5mG3ACAOlQOCLuIHg%3D%3D
        const val VideorecommendId = "T1457068979049"
        // 搞笑视屏 http://c.m.163.com/nc/video/list/VAP4BFE3U/y/10-10.html
        // 美女视频 http://c.m.163.com/nc/video/list/VAP4BG6DL/y/0-10.html
        // 新闻现场 http://c.m.163.com/nc/video/list/VAV3H6JSN/y/0-10.html
        // 萌物 http://c.m.163.com/nc/video/list/VAP4BFR16/y/0-10.html
        // 黑科技  http://c.m.163.com/nc/video/list/VBF8F2PKF/y/0-10.html
        // 二次元 http://c.m.163.com/nc/video/list/VBF8F1PSA/y/0-10.html
        // 涨姿势  http://c.m.163.com/nc/video/list/VBF8F3SGL/y/0-10.html
        //直播视
        const val VideoLive = "http://data.live.126.net/livechannel/previewlist.json"

    }
}