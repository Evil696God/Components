

package com.kuke.common.utils.websocketutil;

/**
 * @author John.Gu
 * @data 2019/1/30
 * @email guqinghong@beyondsoft.com
 * @remark 默认的消息响应事件包装类，
 * 只包含文本，不包含数据实体
 */
public class ReceiveResponse {
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
