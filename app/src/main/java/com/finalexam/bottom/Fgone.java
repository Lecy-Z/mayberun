package com.finalexam.bottom;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class Fgone extends Fragment implements View.OnClickListener {
    private Button mshare;
    private Context mContext;
    private TextView mstepcount;
    private WechatShareManager mShareManager;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fg1, container, false);
        mshare=(Button)view.findViewById(R.id.share) ;
        mshare.setOnClickListener(this);
        mstepcount=(TextView)view.findViewById(R.id.step_count);
        mContext = getContext();
        mShareManager = WechatShareManager.getInstance(mContext);
        return view;
}
    @Override
    public void onClick(View view) {
        if(isWeixinAvilible(mContext)) {

        }else {
            Toast.makeText(mContext, "您还没有安装微信，请先安装微信客户端", Toast.LENGTH_SHORT).show();
        }

        switch (view.getId()) {
            case R.id.share:
                WechatShareManager.ShareContentText mShareContentText = (WechatShareManager.ShareContentText) mShareManager.getShareContentText("今天走了"+mstepcount);
                mShareManager.shareByWebchat(mShareContentText);
                break;
            default:
                break;
        }
    }

    public static boolean isWeixinAvilible(Context context) {
        final PackageManager packageManager = context.getPackageManager();// 获取packagemanager
        List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);// 获取所有已安装程序的包信息
        if (pinfo != null) {
            for (int i = 0; i < pinfo.size(); i++) {
                String pn = pinfo.get(i).packageName;
                if (pn.equals("com.tencent.mm")) {
                    return true;
                }
            }
        }
        return false;
    }

}
