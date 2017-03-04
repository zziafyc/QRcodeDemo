package com.zzia.qrcodedemo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.uuzuche.lib_zxing.activity.CodeUtils;
import com.uuzuche.lib_zxing.camera.CameraManager;

/**
 * Created by Administrator on 2017/2/28.
 * 邮箱：wgyscsf@163.com
 * 博客：http://blog.csdn.net/wgyscsf
 */

public class MakeQRCodeImgActivity extends AppCompatActivity {
    EditText mContentEdt;
    Button makeLogoQR;
    Button makeUnLogoQR;
    ImageView resultImv;
    Button flashNightControlBtn;
    boolean flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_makeimg);
        mContentEdt = (EditText) findViewById(R.id.am_edt_content);
        makeLogoQR = (Button) findViewById(R.id.am_btn_makeLogoQR);
        makeUnLogoQR = (Button) findViewById(R.id.am_btn_makeUnLogoQR);
        resultImv = (ImageView) findViewById(R.id.am_iv_result);
        flashNightControlBtn= (Button) findViewById(R.id.flashNightControl);


        // 生成二维码图片
        makeLogoQR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = mContentEdt.getText().toString();
                if (TextUtils.isEmpty(content)) {
                    Toast.makeText(MakeQRCodeImgActivity.this, "您的输入为空!", Toast.LENGTH_SHORT).show();
                    return;
                }
                mContentEdt.setText("");
                Bitmap mBitmap = CodeUtils.createImage(content, 400, 400, BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));
                resultImv.setImageBitmap(mBitmap);
            }
        });
        makeUnLogoQR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String content = mContentEdt.getText().toString();
                if (TextUtils.isEmpty(content)) {
                    Toast.makeText(MakeQRCodeImgActivity.this, "您的输入为空!", Toast.LENGTH_SHORT).show();
                    return;
                }
                mContentEdt.setText("");
                Bitmap mBitmap = CodeUtils.createImage(content, 400, 400, null);
                resultImv.setImageBitmap(mBitmap);
            }
        });
        flashNightControlBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Camera camera= Camera.open();
                if(!flag){
                    if (camera != null) {
                        Camera.Parameters parameter = camera.getParameters();
                        parameter.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
                        camera.setParameters(parameter);
                    }
                    // 打开闪光灯
                   // CodeUtils.isLightEnable(true);
                    flag=true;
                }else{
                    if (camera != null) {
                        Camera.Parameters parameter = camera.getParameters();
                        parameter.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
                        camera.setParameters(parameter);
                    }
                    //关闭闪光灯
                    //CodeUtils.isLightEnable(false);
                    flag=false;
                }
            }
        });
    }
}
