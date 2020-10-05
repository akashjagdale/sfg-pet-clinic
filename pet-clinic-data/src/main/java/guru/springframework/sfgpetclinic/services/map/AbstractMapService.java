package guru.springframework.sfgpetclinic.services.map;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public abstract class AbstractMapService<T, ID> {
    protected Map<ID, T> map = new HashMap<>();

    T findById(ID id) {

        return map.get(id);
    }

    T save(ID id, T object) {
        return map.put(id, object);
    }

    Set<T> findAll() {
        return new HashSet<>(map.values());
    }

    Boolean delete(T object) {
        return map.entrySet().removeIf(entry -> entry.getValue().equals(object));
    }

    T deleteById(ID id) {
        return map.remove(id);
    }
}
