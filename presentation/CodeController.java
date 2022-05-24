package platform.presentation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import platform.business.*;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import static platform.business.UsefulMethods.*;

@Controller
public class CodeController {

    private HttpHeaders headers;

    @Autowired
    CodeService codeService;

    @GetMapping("/code/{uuid}")
    public String getOneRecordHtml(Model model, @PathVariable String uuid) {
        CodeObject codeObject = codeService.findCodeByUuid(uuid);
        LocalDateTime currentDateTime = LocalDateTime.now();
        LocalDateTime objectDateTime = codeObject.getDateTime();
        checkRestrictions(codeObject, currentDateTime, codeService);
        CodeObjectDto oneCode = new CodeObjectDto(codeObject);
        if (codeObject.getTime() > 0) {
            long visibilityTime = codeObject.getTime() - Duration.between(objectDateTime, currentDateTime).getSeconds();
            oneCode.setTime(visibilityTime);
        }
        model.addAttribute("oneCode", oneCode);
        String file = getFileNameFtlh(codeObject);

        return file;
    }

    @GetMapping("/code/latest")
    public String getLatestRecordsHTML(Model model) {
        List<CodeObjectDto> latestCodes = codeService.getLatestCodes().stream()
                .map(CodeObjectDto::new)
                .collect(Collectors.toList());
        model.addAttribute("codes", latestCodes);

        return "showLatest";
    }

    @GetMapping("/api/code/{uuid}")
    public ResponseEntity<CodeObjectDto> getOneRecordApi(@PathVariable String uuid) {
        CodeObject codeObject = codeService.findCodeByUuid(uuid);
        LocalDateTime currentDateTime = LocalDateTime.now();
        LocalDateTime objectDateTime = codeObject.getDateTime();
        checkRestrictions(codeObject, currentDateTime, codeService);
        CodeObjectDto oneCode = new CodeObjectDto(codeObject);
        if (codeObject.getTime() > 0) {
            long visibilityTime = codeObject.getTime() - Duration.between(objectDateTime, currentDateTime).getSeconds();
            oneCode.setTime(visibilityTime);
        }

        headers = setHeaders("Content-Type", "application/json");

        return ResponseEntity.ok()
                .headers(headers)
                .body(oneCode);
    }

    @GetMapping("/api/code/latest")
    public ResponseEntity<List<CodeObjectDto>> getLatestRecordsApi() {
        List<CodeObjectDto> latestCodes = codeService.getLatestCodes().stream()
                .map(CodeObjectDto::new)
                .collect(Collectors.toList());
        headers = setHeaders("Content-Type", "application/json");

        return ResponseEntity.ok()
                .headers(headers)
                .body(latestCodes);
    }

    @ResponseBody
    @PostMapping(value = "/api/code/new", headers = "Content-Type", produces = "application/json")
    public Map<String, String> postNewCode(@RequestBody CodeObject codeMap) {
        String uuid = UUID.randomUUID().toString();
        Restriction restriction = getRestriction(codeMap);
        checkNegativeItems(codeMap);
        CodeObject codeObject = codeService.save(new CodeObject(codeMap.getId(), codeMap.getCode(), LocalDateTime.now(),
                uuid, restriction, codeMap.getTime(), codeMap.getViews()));

        return Map.of("id", uuid.toString());
    }

    @GetMapping("/code/new")
    public String newCode(Model model) {

        return "newCode";
    }


}
