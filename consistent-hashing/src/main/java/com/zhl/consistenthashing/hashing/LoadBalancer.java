package com.zhl.consistenthashing.hashing;

import java.util.HashMap;

/**
 * @author Zhanghualei
 * @Classname LoadBancer
 * @Date 2021/1/20 14:23
 */
public interface LoadBalancer {

    // 添加服务器节点
    public void addServerNode(String serverNodeName);

    // 删除服务器节点
    public String delServerNode(String serverNodeName);

    // 选择服务器节点
    public String selectServerNode(String requestURL);
}
