package com.tech.send_sms.sendsms.models;

import com.google.common.base.MoreObjects;
import org.springframework.core.style.ToStringCreator;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "smssend")
final public class SmsModel {

    @Id
    @GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "SMSSEND_SEQ_GEN")
    @SequenceGenerator(name = "SMSSEND_SEQ_GEN", sequenceName = "SMSSEND_SEQ", allocationSize = 1)
    @Column (name = "Id")
    private Long id;

    @Transient
    @Column(name = "ANumber")
    private String ANumber;

    @Size(min = 1, max = 11, message = "min 1, max 11")
    @Column(name = "BNumber")
   // @Pattern(regexp="^[()0-9]+$", message="only number and () symbols")
    private String BNumber;

    @Size(min = 1, max = 255, message = "min 1, max 255")
   // @Pattern(regexp="^[а-яА-Я0-9]+$", message="only russian text")
    @Column(name = "Text")
    private String Text;

    public SmsModel(String ANumber, String text, String BNumber) {
        this.ANumber = ANumber;
        Text = text;
        this.BNumber = BNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getANumber() {
        return ANumber;
    }

    public void setANumber(String ANumber) {
        this.ANumber = ANumber;
    }

    public String getText() {
        return Text;
    }

    public void setText(String text) {
        Text = text;
    }

    public String getBNumber() {
        return BNumber;
    }

    public void setBNumber(String BNumber) {
        this.BNumber = BNumber;
    }

/*
    @Override
    public String toString() {
        return new ToStringCreator(this).toString();
    }
*/

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("ANumber", ANumber)
                .add("BNumber", BNumber)
                .add("Text", Text)
                .toString();
    }
}
