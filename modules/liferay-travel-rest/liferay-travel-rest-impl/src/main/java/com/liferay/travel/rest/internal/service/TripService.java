/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.travel.rest.internal.service;

import com.liferay.travel.rest.dto.v1_0.Stage;
import com.liferay.travel.rest.dto.v1_0.Trip;

import java.util.Collections;
import java.util.Date;
import java.util.Set;

/**
 * @author Javier de Arcos
 */
public class TripService {

    public Set<Trip> getTrips() {
        return Collections.singleton(_exampleTrip);
    }

    public Trip getTrip(long id) {
        return _exampleTrip;
    }

    private Trip _exampleTrip = new Trip() {{
        id = 1L;
        name = "Summer Team Building";
        startingDate = new Date();
        stages = Collections.singleton(new Stage() {{
            id = 1L;
            name = "Throw axes";
            description = "What a better team building activity than throwing axes to your teammates";
            place = "Madrid";
        }}).toArray(new Stage[0]);
    }};
}
