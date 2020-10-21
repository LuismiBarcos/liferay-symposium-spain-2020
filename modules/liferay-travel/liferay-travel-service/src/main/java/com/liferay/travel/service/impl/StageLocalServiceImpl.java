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
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.ResourceConstants;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.travel.model.Stage;
import com.liferay.travel.service.base.StageLocalServiceBaseImpl;

import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

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

	public Stage addStage(
			long groupId, long userId, long tripId, String name,
			String description, String place, String image)
		throws PortalException {

		Group group = _groupLocalService.getGroup(groupId);

		long stageId = counterLocalService.increment();

		Stage newStage = stagePersistence.create(stageId);

		newStage.setName(name);
		newStage.setDescription(description);
		newStage.setPlace(place);
		newStage.setImage(image);
		newStage.setTripId(tripId);

		resourceLocalService.addResources(
			group.getCompanyId(), groupId, userId, Stage.class.getName(),
			tripId, false, true, true);

		return super.addStage(newStage);
	}

	public Stage deleteStage(long stageId) throws PortalException {
		Stage stage = getStage(stageId);

		resourceLocalService.deleteResource(
			stage.getCompanyId(), Stage.class.getName(),
			ResourceConstants.SCOPE_INDIVIDUAL, stageId);

		return super.deleteStage(stageId);
	}

	public List<Stage> getStages(long tripId) {
		return stagePersistence.findByTripId(tripId);
	}

	public Stage updateStage(
			long stageId, String name, String description, String place,
			String image)
		throws PortalException {

		Stage stage = stagePersistence.findByPrimaryKey(stageId);

		stage.setName(name);
		stage.setDescription(description);
		stage.setPlace(place);
		stage.setImage(image);

		return super.updateStage(stage);
	}

	@Reference
	private GroupLocalService _groupLocalService;

}