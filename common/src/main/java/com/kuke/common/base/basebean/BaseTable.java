

package com.kuke.common.base.basebean;

import java.io.Serializable;

/**
 *
 * @date: 2018-12-1
 * @version: 1.0
 * @author: wangchenxing
 */
public class BaseTable implements Serializable {
    protected String checkStringIsNull(String content) {
        if (content == null) {
            return "";
        } else {
            return content;
        }
    }
}
