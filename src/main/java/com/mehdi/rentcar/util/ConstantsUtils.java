package com.mehdi.rentcar.util;

import java.util.Arrays;

public final class ConstantsUtils {

    public enum SchemeType {
        CAR_COLOR(1, "Car Color"),
        CAR_MAKE(2, "Car Make"),
        CAR_STATUS(3, "Car Status"),
        CAR_MODEL(4, "Car Model"),
        RESERVATION_STATUS(5, "Reservation Status"),
        CAR_TYPE(6, "Car Type"),
        CAR_OPTION(7, "Car Option"),
        FUEL_TYPE(8, "Fuel Type"),
        CAR_TRANSMISSION(9, "Car Transmission");


        private final int id;
        private final String name;

        SchemeType(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public static SchemeType getById(int id) {
            return Arrays.stream(SchemeType.values())
                    .filter(schemeType -> id == schemeType.id)
                    .findFirst()
                    .orElse(null);
        }

        public static SchemeType getByName(String name) {
            return Arrays.stream(SchemeType.values())
                    .filter(schemeType -> name.equalsIgnoreCase(schemeType.name))
                    .findFirst()
                    .orElse(null);
        }
    }
}
