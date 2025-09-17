package com.alcity.entity.appmember;

import com.alcity.entity.alenum.PLObjectiveTransactionType;
import com.alcity.entity.alenum.UserGender;
import com.alcity.entity.base.BaseTable;
import com.alcity.entity.learning.LearningSkill;
import com.alcity.entity.puzzle.PLGameInstance;
import com.alcity.entity.puzzle.PLObjective;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

@Table(
        name="PL_Objective_Transaction" , uniqueConstraints={
                @UniqueConstraint(columnNames = {"app_member_id", "game_instance_id","pl_objective_id"})
        }
)
@Entity
public class PLObjectiveTransaction extends BaseTable implements Serializable {

    @NotNull(message = "{transactionDate.notempty}")
    private String transactionDate;

    @NotNull(message = "{amount.notempty}")
    private Float amount;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "app_member_id", nullable = false)
    @JsonIgnore
    private AppMember appMember;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "gameInstance_id", nullable = false)
    @JsonIgnore
    private PLGameInstance gameInstance;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "pl_objective_id", nullable = false)
    @JsonIgnore
    private PLObjective plObjective;

    @Enumerated(EnumType.ORDINAL)
    private PLObjectiveTransactionType transactionType;

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }


    public PLObjective getPlObjective() {
        return plObjective;
    }

    public void setPlObjective(PLObjective plObjective) {
        this.plObjective = plObjective;
    }

    public PLGameInstance getGameInstance() {
        return gameInstance;
    }

    public void setGameInstance(PLGameInstance gameInstance) {
        this.gameInstance = gameInstance;
    }

    public PLObjectiveTransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(PLObjectiveTransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public AppMember getAppMember() {
        return appMember;
    }

    public void setAppMember(AppMember appMember) {
        this.appMember = appMember;
    }

    public PLObjectiveTransaction() {
    }

    public PLObjectiveTransaction( String transactionDate,AppMember appMember, Float amount,  PLGameInstance gameInstance, PLObjective plObjective, PLObjectiveTransactionType transactionType,
                                   Long version, String created, String updated, AppMember createdBy, AppMember updatedBy) {
        super(version, created, updated, createdBy, updatedBy);
        this.appMember = appMember;
        this.transactionDate = transactionDate;
        this.amount = amount;
        this.gameInstance = gameInstance;
        this.plObjective = plObjective;
        this.transactionType = transactionType;
    }
}
