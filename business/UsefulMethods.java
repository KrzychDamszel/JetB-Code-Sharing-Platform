package platform.business;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Map;

public class UsefulMethods {

    public static HttpHeaders setHeaders(String contentType, String value) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(contentType, value);

        return headers;
    }

    public static Restriction getRestriction(CodeObject obj) {
        return obj.getTime() > 0 && obj.getViews() > 0 ? Restriction.BOTH
                : obj.getTime() > 0 ? Restriction.TIME
                : obj.getViews() > 0 ? Restriction.VIEWS
                : Restriction.ABSENCE;
    }

    public static void checkNegativeItems(CodeObject codeMap) {
        if (codeMap.getTime() < 0) {
            codeMap.setTime(0);
        }
        if (codeMap.getViews() < 0) {
            codeMap.setViews(0);
        }
    }

    public static void checkRestrictions(CodeObject obj, LocalDateTime dateTime, CodeService codeService) {
        String choose = obj.getRestriction().toString();
        switch (choose) {
            case "ABSENCE":
                break;
            case "BOTH":
                checkTime(obj, dateTime, codeService);
                checkViews(obj, codeService);
                break;
            case "TIME":
                checkTime(obj, dateTime, codeService);
                break;
            case "VIEWS":
                checkViews(obj, codeService);
                break;
            default:
                break;
        }
    }

    static void checkTime(CodeObject obj, LocalDateTime dateTime, CodeService codeService) {
        if (Duration.between(obj.getDateTime().plusSeconds(obj.getTime()), dateTime).getSeconds() > 0) {
            codeService.delete(obj);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    static void checkViews(CodeObject obj, CodeService codeService) {
        if (obj.getViews() == 0) {
            codeService.delete(obj);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        } else {
            long newViews = obj.getViews() - 1;
            obj.setViews(newViews);
            codeService.save(obj);
        }
    }

    public static String getFileNameFtlh(CodeObject codeObject) {
        String chooseFtlhFile = codeObject.getRestriction().toString();
        String file = "";
        switch (chooseFtlhFile) {
            case "ABSENCE":
                file = "showOneAbsence";
                break;
            case "BOTH":
                file = "showOneBoth";
                break;
            case "TIME":
                file = "showOneTime";
                break;
            case "VIEWS":
                file = "showOneViews";
                break;
            default:
                break;
        }

        return file;
    }
}
