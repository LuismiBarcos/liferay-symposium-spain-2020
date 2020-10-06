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

package com.liferay.travel.service.impl;

import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.travel.exception.NoSuchStageException;
import com.liferay.travel.model.Stage;
import com.liferay.travel.model.Trip;
import com.liferay.travel.service.base.StageLocalServiceBaseImpl;

import org.osgi.service.component.annotations.Component;

import java.util.List;

/**
 * The implementation of the stage local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the <code>com.liferay.travel.service.StageLocalService</code> interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see StageLocalServiceBaseImpl
 */
@Component(
	property = "model.class.name=com.liferay.travel.model.Stage",
	service = AopService.class
)
public class StageLocalServiceImpl extends StageLocalServiceBaseImpl {

	public List<Stage> getStages(long tripId) {
		return stagePersistence.findByTripId(tripId);
	}

	public Stage getStage(long stageId) throws PortalException {
		return stagePersistence.findByPrimaryKey(stageId);
	}

	public Stage addStage(long tripId, String name, String description, String place) {
		long stageId = counterLocalService.increment();

		Stage newStage = stagePersistence.create(stageId);

		newStage.setName(name);
		newStage.setDescription(description);
		newStage.setPlace(place);
		newStage.setTripId(tripId);

		return stagePersistence.update(newStage);
	}

	public Stage updateStage(long stageId, String name, String description, String place) throws PortalException {
		Stage stage = stagePersistence.findByPrimaryKey(stageId);

		stage.setName(name);
		stage.setDescription(description);
		stage.setPlace(place);

		return stagePersistence.update(stage);
	}

	public Stage deleteStage(long stageId) throws PortalException {
		return stagePersistence.remove(stageId);
	}
}