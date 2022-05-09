package com.longluo.top100;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * 146. LRU 缓存机制
 * <p>
 * 运用你所掌握的数据结构，设计和实现一个  LRU (最近最少使用) 缓存机制 。
 * <p>
 * 实现 LRUCache 类：
 * LRUCache(int capacity) 以正整数作为容量 capacity 初始化 LRU 缓存
 * int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。
 * void put(int key, int value) 如果关键字已经存在，则变更其数据值；如果关键字不存在，则插入该组「关键字-值」。
 * 当缓存容量达到上限时，它应该在写入新数据之前删除最久未使用的数据值，从而为新的数据值留出空间。
 * <p>
 * 进阶：你是否可以在 O(1) 时间复杂度内完成这两种操作？
 * <p>
 * 示例：
 * 输入
 * ["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
 * [[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
 * 输出
 * [null, null, null, 1, null, -1, null, -1, 3, 4]
 * <p>
 * 解释
 * LRUCache lRUCache = new LRUCache(2);
 * lRUCache.put(1, 1); // 缓存是 {1=1}
 * lRUCache.put(2, 2); // 缓存是 {1=1, 2=2}
 * lRUCache.get(1);    // 返回 1
 * lRUCache.put(3, 3); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
 * lRUCache.get(2);    // 返回 -1 (未找到)
 * lRUCache.put(4, 4); // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
 * lRUCache.get(1);    // 返回 -1 (未找到)
 * lRUCache.get(3);    // 返回 3
 * lRUCache.get(4);    // 返回 4
 * <p>
 * 提示：
 * 1 <= capacity <= 3000
 * 0 <= key <= 10000
 * 0 <= value <= 10^5
 * 最多调用 2 * 10^5 次 get 和 put
 * <p>
 * https://leetcode-cn.com/problems/lru-cache/
 */
public class Problem146_lruCache {

    // HashMap + LinkedList
    static class LRUCache_LinkedList<K, V> {
        Map<K, V> map;
        LinkedList<K> list;
        private int capacity = 0;

        public LRUCache_LinkedList(int capacity) {
            this.capacity = capacity;
            map = new HashMap<>();
            list = new LinkedList<>();
        }

        // time: O(n)
        public void put(K key, V value) {
            V V = map.get(key);
            if (V != null) {
                list.remove(key);
                list.addLast(key);
                map.put(key, value);
                return;
            }

            if (list.size() < capacity) {
                list.addLast(key);
                map.put(key, value);
            } else {
                K firstKey = list.removeFirst();
                map.remove(firstKey);
                list.addLast(key);
                map.put(key, value);
            }
        }

        // time: O(1)
        public V get(K key) {
            V v = map.get(key);
            if (v != null) {
                list.remove(key);
                list.addLast(key);
                return v;
            }

            return null;
        }
    }

    // LinkedHashMap
    static class LRUCache_LinkedHashMap extends LinkedHashMap {
        private int capacity = 0;

        public LRUCache_LinkedHashMap(int capacity) {
            super(capacity, 0.75f, true);
            this.capacity = capacity;
        }

        // time: O(1)
        public void put(int key, int value) {
            super.put(key, value);
        }

        // time: O(1)
        public int get(int key) {
            return (int) super.getOrDefault(key, -1);
        }

        @Override
        protected boolean removeEldestEntry(Map.Entry eldest) {
            return size() > capacity;
        }
    }

    /**
     * Your LRUCache object will be instantiated and called as such:
     * LRUCache obj = new LRUCache(capacity);
     * int param_1 = obj.get(key);
     * obj.put(key,value);
     */
    public static void main(String[] args) {
        LRUCache_LinkedList tst1 = new LRUCache_LinkedList(2);
        tst1.put(1, 2);
    }
}
