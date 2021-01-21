package com.zhl.consistenthashing.hashing;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * @author Zhanghualei
 * @Classname SortedMapWithoutVirtualNode
 * @Date 2021/1/20 14:27
 */
public class SortedMapWithoutVirtualNode implements LoadBalancer {

    private static Logger logger = LoggerFactory.getLogger(SortedMapWithoutVirtualNode.class);

    private TreeMap<Integer, String> treeMapHash;

    @Override
    public void addServerNode(String serverNodeName) {
        int hash = GetHashCode.getHashCode(serverNodeName);
        treeMapHash.put(hash, serverNodeName);
    }

    @Override
    public String delServerNode(String serverNodeName) {
        int hash = GetHashCode.getHashCode(serverNodeName);
        logger.info("服务器节点：{} 下线", serverNodeName);
        return treeMapHash.remove(hash);
    }

    @Override
    public String selectServerNode(String requestURL) {
        int hash = GetHashCode.getHashCode(requestURL);
        // 向右寻找第一个 key
        Map.Entry<Integer, String> subEntry= treeMapHash.ceilingEntry(hash);
        // 设置成一个环，如果超过尾部，则取第一个点
        subEntry = subEntry == null ? treeMapHash.firstEntry() : subEntry;
        return subEntry.getValue();
    }

    // 构建 Hash 环
    public SortedMap<Integer, String> buildHash(TreeMap<Integer, String> treeMap) {
        this.treeMapHash = treeMap;
        return treeMapHash;
    }
}