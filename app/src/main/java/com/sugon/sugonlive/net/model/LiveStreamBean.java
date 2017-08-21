package com.sugon.sugonlive.net.model;


import java.util.Date;

/**
 * Created by pjc on 2017/8/3.
 */

public class LiveStreamBean {

    private Integer id; // ID
    private String vSrcUrl; // 视频源地址
    private Integer userid;
    private String username;
    private Integer connCount; // 用户连接数
    private String description; //直播描述
    private Date startTime;
    private Integer alive;
    private String vCodec; // 视频编码
    private String vBps; // 视频码率
    private Integer width; // 画面宽度
    private Integer height; // 画面高度
    private Integer vFps; // 帧率
    private String aCodec; // 音频编码
    private String aBps; // 音频码率
    private Integer aChan; // 音频频道
    private Integer aFreq; // 音频频率
    private String inBytes; // 输入字节
    private String outBytes; // 输出字节

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getvSrcUrl() {
        return vSrcUrl;
    }

    public void setvSrcUrl(String vSrcUrl) {
        this.vSrcUrl = vSrcUrl;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getConnCount() {
        return connCount;
    }

    public void setConnCount(Integer connCount) {
        this.connCount = connCount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Integer getAlive() {
        return alive;
    }

    public void setAlive(Integer alive) {
        this.alive = alive;
    }

    public String getvCodec() {
        return vCodec;
    }

    public void setvCodec(String vCodec) {
        this.vCodec = vCodec;
    }

    public String getvBps() {
        return vBps;
    }

    public void setvBps(String vBps) {
        this.vBps = vBps;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getvFps() {
        return vFps;
    }

    public void setvFps(Integer vFps) {
        this.vFps = vFps;
    }

    public String getaCodec() {
        return aCodec;
    }

    public void setaCodec(String aCodec) {
        this.aCodec = aCodec;
    }

    public String getaBps() {
        return aBps;
    }

    public void setaBps(String aBps) {
        this.aBps = aBps;
    }

    public Integer getaChan() {
        return aChan;
    }

    public void setaChan(Integer aChan) {
        this.aChan = aChan;
    }

    public Integer getaFreq() {
        return aFreq;
    }

    public void setaFreq(Integer aFreq) {
        this.aFreq = aFreq;
    }

    public String getInBytes() {
        return inBytes;
    }

    public void setInBytes(String inBytes) {
        this.inBytes = inBytes;
    }

    public String getOutBytes() {
        return outBytes;
    }

    public void setOutBytes(String outBytes) {
        this.outBytes = outBytes;
    }

    public String getInBps() {
        return inBps;
    }

    public void setInBps(String inBps) {
        this.inBps = inBps;
    }

    public String getOutBps() {
        return outBps;
    }

    public void setOutBps(String outBps) {
        this.outBps = outBps;
    }

    public String getConnDuration() {
        return connDuration;
    }

    public void setConnDuration(String connDuration) {
        this.connDuration = connDuration;
    }

    private String inBps; // 输入码率
    private String outBps; // 输出码率
    private String connDuration; // 连接时长

}


