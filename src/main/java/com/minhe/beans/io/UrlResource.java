package com.minhe.beans.io;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * @program: toySpring
 * @description: 实现 Resource 接口的资源类，通过 URL 获取资源。
 * @author: MinheZ
 * @create: 2019-04-10 19:17
 **/

public class UrlResource implements Resource{

    private final URL url;

    public UrlResource(URL url) {
        this.url = url;
    }

    /*
     * 通过 url 获取资源
     * */
    public InputStream getInputStream() throws IOException {
        URLConnection urlConnection = url.openConnection();
        urlConnection.connect();
        return urlConnection.getInputStream();
    }

}
