package platform.persistence;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import platform.business.CodeObject;

import java.util.List;
import java.util.Optional;

@Repository
public interface CodeRepository extends CrudRepository<CodeObject, Long> {

    Optional<CodeObject> findCodeObjectById(Long id);

    Optional<CodeObject> findCodeObjectByUuid(String uuid);

    List<CodeObject> findAllByOrderByDateTimeDesc();
}
