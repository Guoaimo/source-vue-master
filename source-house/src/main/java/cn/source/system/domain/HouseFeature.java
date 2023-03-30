package cn.source.system.domain;

import cn.source.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 房源特色对象 house_feature
 *
 * @author 郭爱默
 * @date 2022-03-27
 */
public class HouseFeature extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    private Long id;

    private Long houseId;

    private String feature;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setHouseId(Long houseId)
    {
        this.houseId = houseId;
    }

    public Long getHouseId()
    {
        return houseId;
    }
    public void setFeature(String feature)
    {
        this.feature = feature;
    }

    public String getFeature()
    {
        return feature;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("houseId", getHouseId())
            .append("feature", getFeature())
            .toString();
    }
}
