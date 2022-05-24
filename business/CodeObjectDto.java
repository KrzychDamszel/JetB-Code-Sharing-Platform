package platform.business;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CodeObjectDto {

    private String code;
    private LocalDateTime date;
    private long time;
    private long views;

    private static final String DATE_FORMATTER = "yyyy/MM/dd HH:mm:ss";
    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern(DATE_FORMATTER);

    public CodeObjectDto() {
    }

    public CodeObjectDto(CodeObject codeObject) {
        this.code = codeObject.getCode();
        this.date = codeObject.getDateTime();
        this.time = codeObject.getTime();
        this.views = codeObject.getViews();
    }

    public String getCode() {
        return code;
    }

    public String getDate() {
        return this.date.format(FORMATTER);
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
}
