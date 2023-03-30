package cn.source.system.domain;

import cn.source.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;

/**
 * 房源图片对象 house_image
 *
 * @author 郭爱默
 * @date 2022-03-27
 */
public class HouseImage extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    private Long id;

    private Long houseId;

    private String imageName;

    private String imagePath;

    private String imgUrl;

    private BigDecimal imageSize;

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
    public void setImageName(String imageName)
    {
        this.imageName = imageName;
    }

    public String getImageName()
    {
        return imageName;
    }
    public void setImagePath(String imagePath)
    {
        this.imagePath = imagePath;
    }

    public String getImagePath()
    {
        return imagePath;
    }
    public void setImgUrl(String imgUrl)
    {
        this.imgUrl = imgUrl;
    }

    public String getImgUrl()
    {
        return imgUrl;
    }
    public void setImageSize(BigDecimal imageSize)
    {
        this.imageSize = imageSize;
    }

    public BigDecimal getImageSize()
    {
        return imageSize;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("houseId", getHouseId())
            .append("imageName", getImageName())
            .append("imagePath", getImagePath())
            .append("imgUrl", getImgUrl())
            .append("imageSize", getImageSize())
            .toString();
    }
}
