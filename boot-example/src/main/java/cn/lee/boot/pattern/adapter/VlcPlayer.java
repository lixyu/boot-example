package cn.lee.boot.pattern.adapter;

/**
 * @author ：lix492
 * 2021/6/9
 */

public class VlcPlayer implements AdvancedMediaPlayer {
    @Override
    public void playVlc(String fileName) {
        System.out.println("Play vlc fileName:"+fileName);
    }

    @Override
    public void playMp4(String fileName) {

    }
}
