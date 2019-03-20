package cn.itcast.core.vo;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class PieVo implements Serializable{

    private List legendData;

    private List<Map> seriesData;

    private Integer[] selected;

    public List getLegendData() {
        return legendData;
    }

    public void setLegendData(List legendData) {
        this.legendData = legendData;
    }

    public List<Map> getSeriesData() {
        return seriesData;
    }

    public void setSeriesData(List<Map> seriesData) {
        this.seriesData = seriesData;
    }

    public Integer[] getSelected() {
        return selected;
    }

    public void setSelected(Integer[] selected) {
        this.selected = selected;
    }
}
