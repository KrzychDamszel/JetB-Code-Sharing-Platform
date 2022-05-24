package platform.business;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "codeObject")
public class CodeObject {

    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "code")
    private String code;

    @Column(name = "dateTime")
    private LocalDateTime dateTime;

    @Column(name = "uuid")
    private String uuid;

    @Column(name = "restriction")
    private Restriction restriction;

    @Column(name = "time")
    private long time;

    @Column(name = "views")
    private long views;


    public CodeObject() {
    }

    public CodeObject(long id, String code, LocalDateTime dateTime, String uuid, Restriction restriction, long time, long views) {
        this.id = id;
        this.code = code;
        this.dateTime = dateTime;
        this.uuid = uuid;
        this.restriction = restriction;
        this.time = time;
        this.views = views;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Restriction getRestriction() {
        return restriction;
    }

    public void setRestriction(Restriction restriction) {
        this.restriction = restriction;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public long getViews() {
        return views;
    }

    public void setViews(long views) {
        this.views = views;
    }
}
