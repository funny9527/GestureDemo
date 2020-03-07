package com.szy.multitouch;

import java.util.List;
import java.util.Map;

public interface IPath {
    void show(Map<Integer, List<GestureFrameLayout.Record>> map);
}
