package com.example.learn_handling_exceptions.entities;

import org.springframework.data.jpa.domain.Specification;

public class RoomSpecification {

    public static Specification<Room> hasPriceGreaterThan(Float price) {
        return (root,query,criteriaBuilder) ->
            price > 0 ? criteriaBuilder.greaterThan(root.get("price"),price) : null;
    }

    public static Specification<Room> getAvailableRooms() {
        return (root,query,criteriaBuilder) ->
            criteriaBuilder.isTrue(root.get("status"));
    }

    public static Specification<Room> getRoomWithSpecificNumber(){
        return (root,query,criteriaBuilder) ->
            criteriaBuilder.between(root.get("number"),1000,15000);
    }


}
