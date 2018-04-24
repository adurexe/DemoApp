package com.adurexe.wechatpay;

import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.tencent.mm.opensdk.constants.Build;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.json.JSONObject;

public class WechatPayActivity extends Activity {

    public static final java.lang.String PAY_WX_APPID = "wxb84552d78f4540f9";

    private IWXAPI api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wechat_pay);

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        api = WXAPIFactory.createWXAPI(this, PAY_WX_APPID);
        api.registerApp(PAY_WX_APPID);
        Button appayBtn = findViewById(R.id.appay_btn);
        appayBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String url = "http://wxpay.wxutil.com/pub_v2/app/app_pay.php";
                Button payBtn = findViewById(R.id.appay_btn);
                payBtn.setEnabled(false);
                Toast.makeText(WechatPayActivity.this, "获取订单中...", Toast.LENGTH_SHORT).show();
                try {
                    byte[] buf = Util.httpGet(url);
                    if (buf != null && buf.length > 0) {
                        String content = new String(buf);
                        Log.e("get server pay params:", content);
                        JSONObject json = new JSONObject(content);
                        if (null != json && !json.has("retcode")) {
                            PayReq req = new PayReq();
                            //req.appId = "wxf8b4f85f3a794e77";  // 测试用appId
                            //req.appId			= json.getString("appid");
                            //req.partnerId		= json.getString("partnerid");
                            //req.prepayId		= json.getString("prepayid");
                            //req.nonceStr		= json.getString("noncestr");
                            //req.timeStamp		= json.getString("timestamp");
                            //req.packageValue	= json.getString("package");
                            //req.sign			= json.getString("sign");
                            //req.extData			= "app data"; // optional

                            req.packageValue = "Sign=WXPay";
                            req.appId = "wxb84552d78f4540f9";  // 测试用appId
                            req.sign = "A2483DE35F4BC810EE54EFEDF158AA20";
                            req.partnerId = "203192412";
                            req.prepayId = "wx231035113089724b6b0f470e4016277568";
                            req.nonceStr = "EJzneAiU5nPcdMn5qxMD4Fx24p4IYNqE";
                            req.timeStamp = "1524451157";
                            req.extData = "app data"; // optional

                            Toast.makeText(WechatPayActivity.this, "正常调起支付", Toast.LENGTH_SHORT).show();
                            // 在支付之前，如果应用没有注册到微信，应该先调用IWXMsg.registerApp将应用注册到微信
                            api.sendReq(req);
                        } else {
                            Log.d("PAY_GET", "返回错误" + json.getString("retmsg"));
                            Toast.makeText(WechatPayActivity.this, "返回错误" + json.getString("retmsg"), Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Log.d("PAY_GET", "服务器请求错误");
                        Toast.makeText(WechatPayActivity.this, "服务器请求错误", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Log.e("PAY_GET", "异常：" + e.getMessage());
                    Toast.makeText(WechatPayActivity.this, "异常：" + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
                payBtn.setEnabled(true);
            }
        });
        Button checkPayBtn = findViewById(R.id.check_pay_btn);
        checkPayBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                boolean isPaySupported = api.getWXAppSupportAPI() >= Build.PAY_SUPPORTED_SDK_INT;
                Toast.makeText(WechatPayActivity.this, String.valueOf(isPaySupported), Toast.LENGTH_SHORT).show();
            }
        });
    }

}
