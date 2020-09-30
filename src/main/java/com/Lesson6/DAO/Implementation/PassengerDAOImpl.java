package com.Lesson6.DAO.Implementation;

import com.Lesson6.DAO.PassengerDAO;
import com.Lesson6.Model.Passenger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import java.util.List;

@Repository
@Transactional
public class PassengerDAOImpl extends GeneralDAOImpl<Passenger> implements PassengerDAO {

    public PassengerDAOImpl() {
        setClass(Passenger.class);
    }

//    private static final String SQL_REGULAR_PASSENGERS = "SELECT *\n" +
//            "FROM PASSENGER\n" +
//            "WHERE EXISTS (\n" +
//            "    SELECT  *\n" +
//            "    FROM    FLIGHT_PASSENGER fp\n" +
//            "       JOIN FLIGHT f ON fp.FLIGHT_ID = f.FLIGHT_ID\n" +
//            "    WHERE fp.PASSENGER_ID = PASSENGER.PASSENGER_ID\n" +
//            "    GROUP BY fp.PASSENGER_ID, EXTRACT(YEAR FROM f.DATE_FLIGHT)\n" +
//            "    HAVING COUNT(DISTINCT f.FLIGHT_ID) >= 25" +
//            ")";

    private static final String REGULAR_PASSENGER = "SELECT * FROM PASSENGER" +
            "WHERE JOIN FLIGHT ON PASSENGER.ID = " +
            "(SELECT PASSENGER_ID, COUNT(id) FROM FLIGHT" +
            "GROUP BY PASSENGER_ID ORDER BY COUNT (id) DESC = WHERE COUNT(25) =>300 AND " +
            "YEAR(DATE_FLIGHT) = ?)";

    private static final String PLANE_FLIGHTS_BY_YEAR = "SELECT PASSENGER_ID, COUNT(id) FROM FLIGHT" +
            "GROUP BY PASSENGER_ID ORDER BY COUNT (id) DESC = WHERE COUNT(25) =>300 AND " +
            "YEAR(DATE_FLIGHT) = ?";


    public List getOldPlanes() {
        Query query = entityManager.createNativeQuery(REGULAR_PASSENGER, Passenger.class);
        return query.getResultList();
    }
}