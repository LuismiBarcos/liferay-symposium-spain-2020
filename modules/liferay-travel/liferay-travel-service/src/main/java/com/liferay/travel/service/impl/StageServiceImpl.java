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
import com.liferay.travel.model.Stage;
import com.liferay.travel.service.base.StageServiceBaseImpl;

import org.osgi.service.component.annotations.Component;

import java.util.List;

/**
 * The implementation of the stage remote service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the <code>com.liferay.travel.service.StageService</code> interface.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see StageServiceBaseImpl
 */
@Component(
	property = {
		"json.web.service.context.name=foo",
		"json.web.service.context.path=Stage"
	},
	service = AopService.class
)
public class StageServiceImpl extends StageServiceBaseImpl {

	public List<Stage> getStages(long tripId) {
		return stageLocalService.getStages(tripId);
	}

	public Stage getStage(long stageId) throws PortalException {
		return stageLocalService.getStage(stageId);
	}

	public Stage addStage(long tripId, String name, String description, String place) {
		return stageLocalService.addStage(tripId, name, description, place);
	}

	public Stage updateStage(long stageId, String name, String description, String place) throws PortalException {
		return stageLocalService.updateStage(stageId, name, description, place);
	}

	public Stage deleteStage(long stageId) throws PortalException {
		return stageLocalService.deleteStage(stageId);
	}
}