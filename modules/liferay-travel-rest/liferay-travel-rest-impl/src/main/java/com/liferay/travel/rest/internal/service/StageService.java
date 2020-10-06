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

import com.liferay.travel.rest.internal.service.exception.StageNotFoundException;
import com.liferay.travel.rest.internal.service.exception.TripNotFoundException;
import com.liferay.travel.rest.dto.v1_0.Stage;

import java.util.*;

/**
 * @author Javier de Arcos
 */
public class StageService {

    public Collection<Stage> getStages(long tripId) {
        return _stages.get(tripId);
    }

    public Stage getStage(long stageId) {
        return _stages.values().stream()
                .flatMap(Collection::stream)
                .filter(stage -> stage.getId() == stageId)
                .findFirst()
                .orElseThrow(() -> new StageNotFoundException(stageId));
    }

    public Stage createStageInTrip(long tripId, Stage stage) {
        Optional.ofNullable(_stages.get(tripId))
            .map(trip -> trip.add(stage))
            .orElseThrow(() -> new TripNotFoundException(tripId));

        return stage;
    }

    public Stage replaceStageInTrip(long stageId, Stage stage) {

        return stage;
    }

    private Map<Long,Collection<Stage>> _stages = new HashMap<>();

    {
        Stage stage = new Stage();
        stage.setId(1L);
        stage.setName("Throw axes");
        stage.setDescription("What a better team building activity than throwing axes to your teammates");
        Collection<Stage> tripStages = new HashSet<>();
        tripStages.add(stage);
        _stages.put(1L, tripStages);
    }
}
