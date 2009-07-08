package com.sitewidesystems.backlog.model.org;

import javax.persistence.*;

/**
 * Created by IntelliJ IDEA.
 * Creator: gerwood
 * Created: 07/06/2009 7:01:09 PM
 */
@Entity
@Table(name = "BL_GROUP_MEMBERS", uniqueConstraints = @UniqueConstraint(columnNames = {"GROUPID", "MEMBERID"}))
public class Membership {

    private String member;
    private Group memberOf;
    private String memberType;

    @Column(name="member")
    @Id
    public String getMember() {
        return member;
    }

    public void setMember(String member) {
        this.member = member;
    }

    @Id
    @JoinColumn(name="group")
    public Group getMemberOf() {
        return memberOf;
    }

    public void setMemberOf(Group memberOf) {
        this.memberOf = memberOf;
    }

    @Column(name="type")
    public String getMemberType() {
        return memberType;
    }

    public void setMemberType(String memberType) {
        this.memberType = memberType;
    }
}
