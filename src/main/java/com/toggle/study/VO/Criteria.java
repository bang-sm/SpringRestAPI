package com.toggle.study.VO;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class Criteria {
    private int page;
    private int perPageNum=10;
    
    public int getPageStart() {
        return (this.page-1)*perPageNum;
    }
    
    public int getPage() {
        return page;
    }
    public void setPage(int page) {
        if(page <= 0) {
            this.page = 1;
        } else {
            this.page = page;
        }
    }
    public int getPerPageNum() {
        return perPageNum;
    }
    public void setPerPageNum(int pageCount) {
        int cnt = this.perPageNum;
        if(pageCount != cnt) {
            this.perPageNum = cnt;
        } else {
            this.perPageNum = pageCount;
        }
    }
}
