package com.githup.icezerocat.studyproxy;

/**
 * Description: 明星接口
 * CreateDate:  2020/9/14 8:55
 *
 * @author zero
 * @version 1.0
 */
public interface Star {
    /**
     * 唱歌
     *
     * @param name 歌名
     * @return 歌
     */
    String sing(String name);

    /**
     * 跳舞
     *
     * @param name 舞名
     * @return 舞
     */
    String dance(String name);
}
