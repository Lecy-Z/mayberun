package com.finalexam.bottom;

import android.content.Context;

import com.tencent.mm.sdk.modelmsg.SendMessageToWX;
import com.tencent.mm.sdk.modelmsg.WXMediaMessage;
import com.tencent.mm.sdk.modelmsg.WXTextObject;

import com.tencent.mm.sdk.openapi.WXAPIFactory;

/**
 * 实现微信分享功能的核心类
 * @author chengcj1
 *
 */
public class WechatShareManager {

    private static final int THUMB_SIZE = 150;

    public static final int WECHAT_SHARE_WAY_TEXT = 1;   //文字

    private static WechatShareManager mInstance;
    private ShareContent mShareContentText;
    private Context mContext;

    private WechatShareManager(Context context){
        this.mContext = context;
        //初始化数据
        //初始化微信分享代码
        initWechatShare(context);
    }

    /**
     * 获取WeixinShareManager实例
     * 非线程安全，请在UI线程中操作
     * @return
     */


    public static WechatShareManager getInstance(Context context){
        if(mInstance == null){
            mInstance = new WechatShareManager(context);
        }
        return mInstance;
    }

    private void initWechatShare(Context context){
        if (Constants.wx_api == null) {
            Constants.wx_api = WXAPIFactory.createWXAPI(context, Constants.APP_ID, true);
        }
        Constants.wx_api.registerApp(Constants.APP_ID);
    }

    public void shareByWebchat(ShareContent shareContent){
        switch (shareContent.getShareWay()) {
            case WECHAT_SHARE_WAY_TEXT:
                shareText(shareContent);
                break;
        }
    }

    private abstract class ShareContent {
        protected abstract int getShareWay();
        protected abstract String getContent();
        protected abstract String getTitle();
    }

    /**
     * 设置分享文字的内容
     * @author chengcj1
     *
     */
    public class ShareContentText extends ShareContent {
        private String content;

        public ShareContentText(String content){
            this.content = content;
        }

        @Override
        protected int getShareWay() {
            return WECHAT_SHARE_WAY_TEXT;
        }

        @Override
        protected String getContent() {
            return content;
        }

        @Override
        protected String getTitle() {
            return null;
        }



    }

    /*
     * 获取文本分享对象
     */
    public ShareContent getShareContentText(String content) {
        if (mShareContentText == null) {
            mShareContentText = new ShareContentText(content);
        }
        return (ShareContentText) mShareContentText;
    }


    /*
     * 分享文字
     */
    private void shareText(ShareContent shareContent) {
        String text = shareContent.getContent();
        //初始化一个WXTextObject对象
        WXTextObject textObj = new WXTextObject();
        textObj.text = text;
        //用WXTextObject对象初始化一个WXMediaMessage对象
        WXMediaMessage msg = new WXMediaMessage();
        msg.mediaObject = textObj;
        msg.description = text;
        //构造一个Req
        SendMessageToWX.Req req = new SendMessageToWX.Req();
        //transaction字段用于唯一标识一个请求
        req.transaction = buildTransaction("textshare");
        req.message = msg;
        //发送的目标场景， 可以选择发送到会话 WXSceneSession 或者朋友圈 WXSceneTimeline。 默认发送到会话。
        Constants.wx_api.sendReq(req);
    }


    private String buildTransaction(final String type) {
        return (type == null) ? String.valueOf(System.currentTimeMillis()) : type + System.currentTimeMillis();
    }
}
