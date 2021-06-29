package cn.lee.boot.pattern.adapter;


import org.apache.commons.lang3.StringUtils;

/**
 * @author ：lix492
 * 2021/6/9
 */

public class MediaAdapter implements MediaPlayer {

    private AdvancedMediaPlayer advancedMediaPlayer;

    public MediaAdapter(String audioType){
        if (StringUtils.equalsIgnoreCase(audioType,"vlc")){
            advancedMediaPlayer=new VlcPlayer();
        }else if(StringUtils.equalsIgnoreCase(audioType,"mp4")){
            advancedMediaPlayer=new Mp4Player();
        }
    }

    @Override
    public void play(String audioType, String fileName) {
        if(audioType.equalsIgnoreCase("vlc")){
            advancedMediaPlayer.playVlc(fileName);
        }else if(audioType.equalsIgnoreCase("mp4")){
            advancedMediaPlayer.playMp4(fileName);
        }
    }
}
