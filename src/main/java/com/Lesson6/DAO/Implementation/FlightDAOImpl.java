package com.Lesson6.DAO.Implementation;

import com.Lesson6.DAO.FlightDAO;
import com.Lesson6.Model.Filter;
import com.Lesson6.Model.Flight;
import com.Lesson6.Model.Plane;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import javax.persistence.criteria.*;
import java.util.List;

@Repository
@Transactional
public class FlightDAOImpl extends GeneralDAOImpl<Flight> implements FlightDAO {

    public FlightDAOImpl() {
        setClass(Flight.class);
    }

//    flightsByDate(Filter filter) - список рейсов по дате (в один день),
//    по промежутку даты (с даты-по дату) городу отправки, городу назначения, модели самолета

//    SELECT CITY_FROM, cnt
//    FROM (SELECT CITY_FROM, COUNT(ID) AS cnt
//    FROM FLIGHT
//    GROUP BY CITY_FROM
//    ORDER BY COUNT(ID) DESC)
//    WHERE ROWNUM <=10

    private static final String SELECT_MOST_POPULAR_FROM = "SELECT cityFrom ,cnt FROM (SELECT cityFrom, COUNT(id) AS cnt FROM Flight GROUP BY cityFrom ORDER BY COUNT (id) DESC) WHERE ROWNUM <=10";
    private static final String SELECT_MOST_POPULAR_TO = "SELECT cityTo ,cnt FROM (SELECT cityTo, COUNT(id) AS cnt FROM Flight GROUP BY cityTo ORDER BY COUNT (id) DESC) WHERE ROWNUM <=10";


    public List findFlightByFilter(Filter filter) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        CriteriaQuery<Flight> query = cb.createQuery(Flight.class);
        Root<Flight> flightRoot = query.from(Flight.class);
        Join<Flight, Plane> planeJoin = flightRoot.join("PLANE");

//        query.select(flightRoot);

        Predicate predicate = cb.conjunction();

        if (filter.getCityFrom() != null) {
            predicate = cb.and(predicate, cb.equal(planeJoin.get("cityFrom"), filter.getCityFrom()));
        }
        if (filter.getCityTo() != null) {
            predicate = cb.and(predicate, cb.equal(planeJoin.get("cityTo"), filter.getCityTo()));
        }
        if (filter.getDateFrom() != null & filter.getDateTo() != null) {
            predicate = cb.and(predicate, cb.between(planeJoin.get("dateFlight"), filter.getDateFrom(), filter.getDateTo()));
        }
        if (filter.getModel() != null) {
            predicate = cb.and(predicate, cb.equal(planeJoin.get("plane"), filter.getModel()));
        }

        query.where(predicate);
        return entityManager.createQuery(query).getResultList();
    }

    public List mostPopularTo() {
        Query query = entityManager.createNativeQuery(SELECT_MOST_POPULAR_TO, Flight.class);
        return query.getResultList();
    }

    public List mostPopularFrom() {
        Query query = entityManager.createNativeQuery(SELECT_MOST_POPULAR_FROM, Flight.class);
        return query.getResultList();
    }
}