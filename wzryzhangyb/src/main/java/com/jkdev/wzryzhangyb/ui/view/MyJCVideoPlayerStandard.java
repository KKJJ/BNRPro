package com.jkdev.wzryzhangyb.ui.view;

import android.content.Context;
import android.util.AttributeSet;

import com.jkdev.wzryzhangyb.event.SystemBarEvent;
import com.jkdev.wzryzhangyb.utils.LogUtil;

import org.greenrobot.eventbus.EventBus;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

/**
 * 主要为了控制状态栏，暂时没成功
 * <p>
 * Created by Kuang on 2017/3/27.
 */

public class MyJCVideoPlayerStandard extends JCVideoPlayerStandard {


    public MyJCVideoPlayerStandard(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyJCVideoPlayerStandard(Context context) {
        super(context);
    }

    @Override
    public void startWindowTiny() {
//        LogUtil.e(TAG, "startWindowTiny: ");
//        EventBus.getDefault().post(new SystemBarEvent(false));
        super.startWindowTiny();
    }

    @Override
    public void startWindowFullscreen() {
        LogUtil.e(TAG, "startWindowFullscreen: ");
        EventBus.getDefault().post(new SystemBarEvent(true));
        super.startWindowFullscreen();
    }


}
