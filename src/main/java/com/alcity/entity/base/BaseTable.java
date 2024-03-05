package com.alcity.entity.base;



import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;


@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS )
public abstract class BaseTable implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;
    public Long getId() {
        return id;
    }

    @NotNull(message = "{bName.notempty}")
    private Long version;

    @NotNull(message = "{bLength.notempty}")
    private Long creationDate;

    @NotNull(message = "{bWidth.notempty}")
    private Long creatorUser;

    @NotNull(message = "{bHeight.notempty}")
    private Long lastModifiedDate;

    @NotNull(message = "{bWeight.notempty}")
    private Long lastModifiedUser;





    public BaseTable() {
    }

    public BaseTable(Long version, Long creationDate, Long creatorUser, Long lastModifiedDate, Long lastModifiedUser) {
        this.version = version;
        this.creationDate = creationDate;
        this.creatorUser = creatorUser;
        this.lastModifiedDate = lastModifiedDate;
        this.lastModifiedUser = lastModifiedUser;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public Long getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Long creationDate) {
        this.creationDate = creationDate;
    }

    public Long getCreatorUser() {
        return creatorUser;
    }

    public void setCreatorUser(Long creatorUser) {
        this.creatorUser = creatorUser;
    }

    public Long getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Long lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public Long getLastModifiedUser() {
        return lastModifiedUser;
    }

    public void setLastModifiedUser(Long lastModifiedUser) {
        this.lastModifiedUser = lastModifiedUser;
    }
}
