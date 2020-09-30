package com.Lesson6.DAO.Implementation;

import com.Lesson6.DAO.PlaneDAO;
import com.Lesson6.Model.Plane;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import java.util.List;

@Repository
@Transactional
public class PlaneDAOImpl extends GeneralDAOImpl<Plane> implements PlaneDAO {

    public PlaneDAOImpl() {
        setClass(Plane.class);
    }

    private static final String SELECT_OLD_PLANES = "SELECT * FROM PLANE WHERE " +
            "((select to_number(to_char(sysdate, 'YYYY')) from dual)- year_produced) > 20";

    private static final String REGULAR_PLANES = "SELECT * FROM PLANE " +
            "JOIN FLIGHT ON PLANE.PLANE_ID = " +
            "(SELECT PLANE_ID, COUNT(id) FROM FLIGHT" +
            "GROUP BY PLANE_ID ORDER BY COUNT (id) DESC = WHERE COUNT(id) =>300 AND " +
            "YEAR(DATE_FLIGHT) = ?)";

    private static final String REGULAR_PLANES_MK2 = "SELECT * FROM PLANE " +
            "JOIN FLIGHT ON PLANE.PLANE_ID = " +
            "(SELECT PLANE_ID, COUNT(id) FROM FLIGHT" +
            "GROUP BY PLANE_ID ORDER BY COUNT (id) DESC = WHERE COUNT(id) =>300 AND " +
            "YEAR(DATE_FLIGHT) = ?)";

//    private static final String COUNT_PLANES = "SELECT PLANE_ID, COUNT(id) FROM FLIGHT" +    --------- Это не надо Всё поместилось в PLANE_FLIGHTS_BY_YEAR
//            "GROUP BY PLANE_ID ORDER BY COUNT (id) DESC = WHERE COUNT(id) =>300";

    private static final String PLANE_FLIGHTS_BY_YEAR = "SELECT PLANE_ID, COUNT(id) FROM FLIGHT" +
            "GROUP BY PLANE_ID ORDER BY COUNT (id) DESC = WHERE COUNT(id) =>300 AND " +
            "YEAR(DATE_FLIGHT) = ?";


    public List getOldPlanes() {
        Query query = entityManager.createNativeQuery(SELECT_OLD_PLANES, Plane.class);
        return query.getResultList();
    }

//    public List getRegularPlanes(int year){
//
//        Query query = entityManager.createQuery(string, Plane.class);
//        return query.getResultList();
//    }

}