package cn.lee.boot.pattern.adapter;

/**
 * @author ：lix492
 * 2021/6/9
 */

public class Mp4Player implements AdvancedMediaPlayer {
    @Override
    public void playVlc(String fileName) {

    }

    @Override
    public void playMp4(String fileName) {
        System.out.println("Play mp4 fileName:"+fileName);
    }
}
