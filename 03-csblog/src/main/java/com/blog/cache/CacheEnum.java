package com.blog.cache;

public enum CacheEnum {
    /**
     * 分类实例
     */
    GET_CAT(CacheConstants.CAT, CacheConstants.EXPIRES_5_MIN),
    /**
     *分类列表实例
     */
    GET_CAT_LIST(CacheConstants.CAT_LIST, CacheConstants.EXPIRES_10_MIN),
    ;
    /**
     * 缓存名称
     */
    private final String name;
    /**
     * 过期时间
     */
    private final int expires;

    /**
     * 构造
     */
    CacheEnum(String name, int expires) {
        this.name = name;
        this.expires = expires;
    }

    public String getName() {
        return name;
    }

    public int getExpires() {
        return expires;
    }

}
