package com.second.hand.trading.server.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * sh_idle_item - 二手图书模型
 * @author
 */
public class IdleItemModel implements Serializable {
    /**
     * 自增主键
     */
    private Long id;

    /**
     * 图书名称
     */
    private String idleName;

    /**
     * 图书详情描述
     */
    private String idleDetails;

    /**
     * 图书图片集
     */
    private String pictureList;

    /**
     * 图书价格
     */
    private BigDecimal idlePrice;

    /**
     * 发货地区
     */
    private String idlePlace;

    /**
     * 图书分类标签（1-教材教辅 2-文学小说 3-专业书籍 4-考试考研 5-求购信息）
     */
    private Integer idleLabel;

    /**
     * 发布时间
     */
    private Date releaseTime;

    /**
     * 状态（发布1、下架2、删除0）
     */
    private Byte idleStatus;

    /**
     * 用户主键id
     */
    private Long userId;

    /**
     * 图书作者
     */
    private String bookAuthor;

    /**
     * 图书ISBN
     */
    private String bookIsbn;

    /**
     * 出版社
     */
    private String bookPublisher;

    /**
     * 图书成色（1-全新 2-九成新 3-八成新 4-七成新 5-其他）
     */
    private Byte bookCondition;

    private UserModel user;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdleName() {
        return idleName;
    }

    public void setIdleName(String idleName) {
        this.idleName = idleName;
    }

    public String getIdleDetails() {
        return idleDetails;
    }

    public void setIdleDetails(String idleDetails) {
        this.idleDetails = idleDetails;
    }

    public String getPictureList() {
        return pictureList;
    }

    public void setPictureList(String pictureList) {
        this.pictureList = pictureList;
    }

    public BigDecimal getIdlePrice() {
        return idlePrice;
    }

    public void setIdlePrice(BigDecimal idlePrice) {
        this.idlePrice = idlePrice;
    }

    public String getIdlePlace() {
        return idlePlace;
    }

    public void setIdlePlace(String idlePlace) {
        this.idlePlace = idlePlace;
    }

    public Integer getIdleLabel() {
        return idleLabel;
    }

    public void setIdleLabel(Integer idleLabel) {
        this.idleLabel = idleLabel;
    }

    public Date getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(Date releaseTime) {
        this.releaseTime = releaseTime;
    }

    public Byte getIdleStatus() {
        return idleStatus;
    }

    public void setIdleStatus(Byte idleStatus) {
        this.idleStatus = idleStatus;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public String getBookIsbn() {
        return bookIsbn;
    }

    public void setBookIsbn(String bookIsbn) {
        this.bookIsbn = bookIsbn;
    }

    public String getBookPublisher() {
        return bookPublisher;
    }

    public void setBookPublisher(String bookPublisher) {
        this.bookPublisher = bookPublisher;
    }

    public Byte getBookCondition() {
        return bookCondition;
    }

    public void setBookCondition(Byte bookCondition) {
        this.bookCondition = bookCondition;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        IdleItemModel other = (IdleItemModel) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getIdleName() == null ? other.getIdleName() == null : this.getIdleName().equals(other.getIdleName()))
            && (this.getIdleDetails() == null ? other.getIdleDetails() == null : this.getIdleDetails().equals(other.getIdleDetails()))
            && (this.getPictureList() == null ? other.getPictureList() == null : this.getPictureList().equals(other.getPictureList()))
            && (this.getIdlePrice() == null ? other.getIdlePrice() == null : this.getIdlePrice().equals(other.getIdlePrice()))
            && (this.getIdlePlace() == null ? other.getIdlePlace() == null : this.getIdlePlace().equals(other.getIdlePlace()))
            && (this.getIdleLabel() == null ? other.getIdleLabel() == null : this.getIdleLabel().equals(other.getIdleLabel()))
            && (this.getReleaseTime() == null ? other.getReleaseTime() == null : this.getReleaseTime().equals(other.getReleaseTime()))
            && (this.getIdleStatus() == null ? other.getIdleStatus() == null : this.getIdleStatus().equals(other.getIdleStatus()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getIdleName() == null) ? 0 : getIdleName().hashCode());
        result = prime * result + ((getIdleDetails() == null) ? 0 : getIdleDetails().hashCode());
        result = prime * result + ((getPictureList() == null) ? 0 : getPictureList().hashCode());
        result = prime * result + ((getIdlePrice() == null) ? 0 : getIdlePrice().hashCode());
        result = prime * result + ((getIdlePlace() == null) ? 0 : getIdlePlace().hashCode());
        result = prime * result + ((getIdleLabel() == null) ? 0 : getIdleLabel().hashCode());
        result = prime * result + ((getReleaseTime() == null) ? 0 : getReleaseTime().hashCode());
        result = prime * result + ((getIdleStatus() == null) ? 0 : getIdleStatus().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", idleName=").append(idleName);
        sb.append(", idleDetails=").append(idleDetails);
        sb.append(", pictureList=").append(pictureList);
        sb.append(", idlePrice=").append(idlePrice);
        sb.append(", idlePlace=").append(idlePlace);
        sb.append(", idleLabel=").append(idleLabel);
        sb.append(", releaseTime=").append(releaseTime);
        sb.append(", idleStatus=").append(idleStatus);
        sb.append(", userId=").append(userId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}