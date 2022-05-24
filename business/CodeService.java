package platform.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import platform.persistence.CodeRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CodeService {
    private final CodeRepository codeRepository;

    private final int LIMIT = 10;

    @Autowired
    public CodeService(CodeRepository codeRepository) {
        this.codeRepository = codeRepository;
    }

    public CodeObject findCodeById(long id) {
        Optional<CodeObject> foundCodeObject = codeRepository.findCodeObjectById(id);
        if (foundCodeObject.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        } else {
            return foundCodeObject.get();
        }
    }

    public CodeObject findCodeByUuid(String uuid) {
        Optional<CodeObject> foundCodeObject = codeRepository.findCodeObjectByUuid(uuid);
        if (foundCodeObject.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        } else {
            return foundCodeObject.get();
        }
    }

    public CodeObject save(CodeObject toSave) {

        return codeRepository.save(toSave);
    }

    public void delete(CodeObject toDelete) {
        codeRepository.delete(toDelete);
    }

    public List<CodeObject> getLatestCodes() {
        return codeRepository.findAllByOrderByDateTimeDesc().stream()
                .filter(elem -> elem.getRestriction().equals(Restriction.ABSENCE))
                .limit(LIMIT)
                .collect(Collectors.toList());
    }
}
