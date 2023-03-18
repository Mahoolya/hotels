package com.app.hotels.service.impl;

import com.app.hotels.domain.Hotel;
import com.app.hotels.domain.criteria.HotelCriteria;
import com.app.hotels.domain.exception.ResourceDoesNotExistException;
import com.app.hotels.repository.HotelRepository;
import com.app.hotels.service.HotelService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HotelServiceImpl implements HotelService {

    private static final int PAGE_SIZE = 20;

    private final HotelRepository hotelRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Hotel> findAll(HotelCriteria hotelCriteria, int currentPage) {
        List<Hotel> hotels;
        if(currentPage == 0){
            currentPage = 1;
        }
        if(hotelCriteria !=null){
            hotels = findAllByCriteria(hotelCriteria, currentPage);
        } else {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Hotel> criteriaQuery = criteriaBuilder.createQuery(Hotel.class);
            Root<Hotel> hotelRoot = criteriaQuery.from(Hotel.class);
            Pageable pageable = PageRequest.of(currentPage, PAGE_SIZE);
            hotels = entityManager.createQuery(criteriaQuery)
                    .setFirstResult((int) pageable.getOffset())
                    .setMaxResults(pageable.getPageSize())
                    .getResultList();
        }
        return hotels;
    }

    public List<Hotel> findAllByCriteria(HotelCriteria hotelCriteria, int currentPage){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Hotel> criteriaQuery = criteriaBuilder.createQuery(Hotel.class);
        Root<Hotel> hotelRoot = criteriaQuery.from(Hotel.class);
        List<Predicate> predicates = new ArrayList<>();

        List<String> cities = hotelCriteria.getCities();
        List<Predicate> cityPredicates = new ArrayList<>();
        if (cities != null && !cities.isEmpty()) {
            for (String city : cities) {
                cityPredicates.add(criteriaBuilder.equal(hotelRoot.get("city"), city));
            }
            Predicate cityFinalPredicate = criteriaBuilder.or(cityPredicates.toArray(new Predicate[0]));
            predicates.add(cityFinalPredicate);
        }

        List<String> countries = hotelCriteria.getCountries();
        List<Predicate> countryPredicates = new ArrayList<>();
        if (countries != null && !countries.isEmpty()) {
            for (String country : countries) {
                countryPredicates.add(criteriaBuilder.equal(hotelRoot.get("country"), country));
            }
            Predicate countryFinalPredicate = criteriaBuilder.or(countryPredicates.toArray(new Predicate[0]));
            predicates.add(countryFinalPredicate);
        }

        List<Integer> stars = hotelCriteria.getStars();
        List<Predicate> starsPredicates = new ArrayList<>();
        if(stars != null && !stars.isEmpty()){
            for(int star: stars){
                starsPredicates.add(criteriaBuilder.equal(hotelRoot.get("stars"), star));
            }
            Predicate starsFinalPredicate = criteriaBuilder.or(starsPredicates.toArray(new Predicate[0]));
            predicates.add(starsFinalPredicate);
        }

        BigDecimal minPrice = hotelCriteria.getMinPrice();
        if (minPrice != null) {
            Predicate minPricePredicate = criteriaBuilder.greaterThanOrEqualTo(hotelRoot.get("maxPrice"), minPrice);
            predicates.add(minPricePredicate);
        }

        BigDecimal maxPrice = hotelCriteria.getMaxPrice();
        if (maxPrice != null) {
            Predicate maxPricePredicate = criteriaBuilder.lessThanOrEqualTo(hotelRoot.get("minPrice"), maxPrice);
            predicates.add(maxPricePredicate);
        }

        Predicate finalPredicate = criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        criteriaQuery.where(finalPredicate);

        Pageable pageable = PageRequest.of(currentPage, PAGE_SIZE);
        List<Hotel> result = entityManager.createQuery(criteriaQuery)
                .setFirstResult((int) pageable.getOffset())
                .setMaxResults(pageable.getPageSize())
                .getResultList();

        return result;
    }

    @Override
    public Hotel findById(Long id) {
        return hotelRepository.findById(id)
                .orElseThrow(() -> new ResourceDoesNotExistException("Отель с id " + id + " не существует"));
    }

    @Override
    public Hotel create(Hotel hotel) {
        return hotelRepository.save(hotel);
    }

    @Override
    public Hotel update(Hotel hotel) {
        return hotelRepository.save(hotel);
    }

    @Override
    public void delete(Long id) {
        hotelRepository.deleteById(id);
    }

    @Override
    public List<String> findAllCountries() {
        return hotelRepository.findAllCountries();
    }

    @Override
    public List<String> findAllCities() {
        return hotelRepository.findAllCities();
    }

    @Override
    public List<Integer> findAllStars() {
        return hotelRepository.findAllStars();
    }

}
