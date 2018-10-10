package xdroid.mwee.com.zmstudy.model;


import com.mwee.android.tools.base.BusinessBean;


public abstract class SectionEntity<T, F> extends BusinessBean {

    public boolean isHeader;
    public T t;
    public F header;


    /**
     * 构建头部
     *
     * @param isHeader
     * @param header
     */
    public SectionEntity(boolean isHeader, F header) {
        this.isHeader = isHeader;
        this.header = header;
        this.t = null;
    }

    /**
     * 构建明细
     *
     * @param t
     */
    public SectionEntity(T t) {
        this.isHeader = false;
        this.header = null;
        this.t = t;
    }

}
