package com.jkdev.wzryzhangyb.event;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by KJ on 2017/3/20.
 */

public class StartBrotherEvent {
    public SupportFragment targetFragment;

    public StartBrotherEvent(SupportFragment targetFragment) {
        this.targetFragment = targetFragment;
    }
}
