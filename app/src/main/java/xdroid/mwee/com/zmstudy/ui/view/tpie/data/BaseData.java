package xdroid.mwee.com.zmstudy.ui.view.tpie.data;


/**
 * Author：created by SugarT
 * Time：2019/7/23 15
 */
public class BaseData implements IBaseData {
    protected int color = -16777216;
    protected float paintWidth = 1.0F;
    protected float textSize = 30.0F;
    /**
     * 是否显示图表的Y值
     */
    protected boolean isTextSize = true;

    public BaseData() {
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getColor() {
        return this.color;
    }

    public void setTextSize(float textSize) {
        this.textSize = textSize;
    }

    public float getTextSize() {
        return this.textSize;
    }

    public void setIsTextSize(boolean isTextSize) {
        this.isTextSize = isTextSize;
    }

    public boolean getIsTextSize() {
        return this.isTextSize;
    }

    public void setPaintWidth(float paintWidth) {
        this.paintWidth = paintWidth;
    }

    public float getPaintWidth() {
        return this.paintWidth;
    }
}
