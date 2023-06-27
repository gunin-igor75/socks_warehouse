package sky.pro.socks_warehouse.repository.imp;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Tuple;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import sky.pro.socks_warehouse.repository.CustomRepository;

import java.util.List;

/**
 * Имплементация кастомного репозитория
 */
@Repository
@Transactional(readOnly = true)
public class CustomRepositoryImp implements CustomRepository {

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * пПолучение количества носков
     * @param color - цвет носков
     * @param operation - операция выбора носков
     * @param cottonPart - процент содержания хлопка
     * @return - количество носков
     */
    @Override
    public Long getCountPartSocks(String color, String operation, Integer cottonPart) {
        String query = """
                SELECT coalesce(sum(socks.quantity), 0) as total FROM socks
                WHERE socks.color= :color
                AND  socks.cotton_part""" + operation + ":cottonPart";
        List<Tuple> results = entityManager.createNativeQuery(query, Tuple.class)
                .setParameter("color", color)
                .setParameter("cottonPart", cottonPart)
                .getResultList();
        Tuple result = results.get(0);
        return result.get("total", Long.class);
    }
}
