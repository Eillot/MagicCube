package com.simon.magiccube.dao.domain;

import java.io.Serializable;

public class PaginationQuery implements Serializable {
    private static final long serialVersionUID = -7230829363332251944L;
    /**
     * 当前页
     */
    private Integer pageNum = 1;
    /**
     * 页大小
     */
    private Integer pageSize = 10;
    /**
     * 偏移量
     */
    private Integer offSet;

    public PaginationQuery() {

    }

    public PaginationQuery(Integer pageNum, Integer pageSize) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getOffSet() {
//        if (null != offSet) {
//            return offSet;
//        }
        if (null == pageNum || 0 >= pageNum) {
            pageNum = 1;
        }
        offSet = (pageNum - 1) * pageSize;
        return offSet;
    }

    public void setOffSet(Integer offSet) {
        this.offSet = offSet;
    }
}
